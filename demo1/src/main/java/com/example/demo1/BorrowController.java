package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BorrowController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Borrow> borrow_table;

    @FXML
    private TableColumn<Borrow, String> bt_book;

    @FXML
    private TableColumn<Borrow, Integer> bt_borrowID;

    @FXML
    private TableColumn<Borrow, LocalDate> bt_broughtDate;

    @FXML
    private TableColumn<Borrow, String> bt_student;

    @FXML
    private TableColumn<Borrow, LocalDate> bt_tokenDate;

    @FXML
    private Button btn_All;


    @FXML
    private Button btn_Create;

    @FXML
    private Button btn_Delete;

    @FXML
    private Button btn_byID;

    @FXML
    private Button btn_BroughtDate;

    @FXML
    private Button btn_main;

    @FXML
    private ChoiceBox<String> cb_book;

    @FXML
    private ChoiceBox<String> cb_student;

    @FXML
    private DatePicker dp_BroughtDate;

    @FXML
    private DatePicker dp_tokenDate;

    @FXML
    private Text t_message;

    @FXML
    private TextField tf_borrowID;

    //move to home page
    @FXML
    void GoToHome(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //search for borrow by id.
    @FXML
    void ByID(MouseEvent event)
    {
        borrow_table.getItems().clear();
        borrow_table.setItems(BorrowDB.getBorrow(Integer.parseInt(tf_borrowID.getText())));
    }

    //creates new borrow and if created will print "Created Successfully" with clearing text fields
    @FXML
    void CreateBorrow(MouseEvent event)
    {
        if(tf_borrowID.getText().equals("") || cb_student.getValue() == null || cb_book.getValue() == null || dp_tokenDate.getValue() == null || dp_BroughtDate.getValue() == null)
        {
            t_message.setVisible(true);
            t_message.setText("fill all the fields");
        }
        else {
            Borrow borrow = new Borrow();
            borrow.setBorrow_id(Integer.parseInt(tf_borrowID.getText()));
            borrow.setTokenDate(Date.valueOf(dp_tokenDate.getValue()));
            borrow.setBroughtDate(Date.valueOf(dp_BroughtDate.getValue()));
            String bookname = cb_book.getSelectionModel().getSelectedItem();
            int ISBN = BookDB.bookByName(bookname);
            borrow.setISBN(ISBN);
            String STUDENTname = cb_student.getSelectionModel().getSelectedItem();
            int studentid = StudentDB.studentByName(STUDENTname);
            borrow.setStudent_id(studentid);
            int val = BorrowDB.createBorrow(borrow);
            if (val != 0) {
                t_message.setVisible(true);
                t_message.setText("Created Successfully");
            }
        }
    }

    //search for borrow by brought Date
    @FXML
    void ByBroughtDate(MouseEvent event) {
        borrow_table.getItems().clear();
        borrow_table.setItems(BorrowDB.getBorrow(Date.valueOf(dp_BroughtDate.getValue())));
    }


    @FXML
    void GetAll(MouseEvent event)
    {
        borrow_table.getItems().clear();
        borrow_table.setItems(BorrowDB.getAllBorrow());
    }

    //Deleting a borrow with clearing the text field and printing "Deleted Successfully"
    @FXML
    void deleteBorrow(MouseEvent event) {
        int val = BorrowDB.DeleteBorrow(Integer.parseInt(tf_borrowID.getText()));
        if (val!=0)
        {
            tf_borrowID.clear();
            t_message.setVisible(true);
            t_message.setText("Deleted Successfully");
        }
    }

    //initializing the table and making the text non-visible and calling the choice box functions.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        t_message.setVisible(false);
        studentchoices();
        bookchoices();
        bt_borrowID.setCellValueFactory(new PropertyValueFactory<Borrow,Integer>("borrow_id"));
        bt_student.setCellValueFactory(new PropertyValueFactory<Borrow,String>("studentName"));
        bt_book.setCellValueFactory(new PropertyValueFactory<Borrow,String>("bookName"));
        bt_tokenDate.setCellValueFactory(new PropertyValueFactory<Borrow, LocalDate>("tokenDate"));
        bt_broughtDate.setCellValueFactory(new PropertyValueFactory<Borrow,LocalDate>("broughtDate"));
    }


    //showing the data in a choice box as names instead of id's
    void studentchoices(){
        ResultSet res = StudentDB.studentNames();
        try {
            while (res.next()) {
                cb_student.getItems().add(res.getString("name"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    //showing the data in a choice box as names instead of id's
    void bookchoices(){
        ResultSet res = BookDB.bookNames();
        try {
            while (res.next()) {
                cb_book.getItems().add(res.getString("name"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
