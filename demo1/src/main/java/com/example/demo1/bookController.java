package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class bookController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<book, Integer> b_isbn;

    @FXML
    private TableColumn<book, String> b_name;

    @FXML
    private TableColumn<book, Integer> b_pagecount;

    @FXML
    private TableColumn<book, String> b_author;

    @FXML
    private TableColumn<book, String> b_type;

    @FXML
    private TableView<book> book_table;

    @FXML
    private ChoiceBox<String> bType;

    @FXML
    private Button btn_All;

    @FXML
    private Button btn_Create;

    @FXML
    private Button btn_byName;

    @FXML
    private Button btn_main;

    @FXML
    private Button btn_byisbn;

    @FXML
    private Button btn_delete;

    @FXML
    private Text t_delete;

    @FXML
    private Text t_result;
    @FXML
    private TextField tf_isbn;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_pageCount;

    @FXML
    private TextField tf_authorID;

    //search for book by ISBN
    @FXML
    void byISBN(MouseEvent event)
    {
        book_table.getItems().clear();
        book_table.setItems(BookDB.getBook(Integer.parseInt(tf_isbn.getText())));
    }
    //search for book by name
    @FXML
    void byName(MouseEvent event)
    {
        book_table.getItems().clear();
        book_table.setItems(BookDB.getBook(tf_name.getText()));
    }

    //creates new book and if created will print "Created Successfully" with clearing text fields
    @FXML
    void createBook(MouseEvent event) {
        book book2 = new book();
        book2.setISBN(Integer.parseInt(tf_isbn.getText()));
        book2.setName(tf_name.getText());
        book2.setPageCount(Integer.parseInt(tf_pageCount.getText()));
        String booktype =bType.getSelectionModel().getSelectedItem();
        int typid = StudentDB.typeByName(booktype);
        book2.setType_id(typid);
        book2.setAuthor_id(Integer.parseInt(tf_authorID.getText()));
        int val = BookDB.createBook(book2);
        if (val!=0)
        {
            tf_isbn.clear();
            tf_authorID.clear();
            tf_name.clear();
            tf_pageCount.clear();
            t_result.setVisible(true);
            t_result.setText("Created Successfully");
        }
    }
    //move to home page
    @FXML
    void GoToHome(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Deleting a book with clearing the text field and printing "Deleted Successfully"
    @FXML
    void deleteBook(MouseEvent event) {
        int val = BookDB.DeleteBook(Integer.parseInt(tf_isbn.getText()));
        if (val!=0)
        {
            tf_isbn.clear();
            t_result.setVisible(true);
            t_result.setText("Deleted Successfully");
        }
    }

    @FXML
    void getAll(MouseEvent event)
    {
        book_table.getItems().clear();
        book_table.setItems(BookDB.getAllBooks());
    }

    //initializing the table and making the text non-visible and calling the choice box function.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        t_result.setVisible(false);
        choices();
        b_isbn.setCellValueFactory(new PropertyValueFactory<book,Integer>("ISBN"));
        b_name.setCellValueFactory(new PropertyValueFactory<book,String>("name"));
        b_pagecount.setCellValueFactory(new PropertyValueFactory<book,Integer>("pageCount"));
        b_author.setCellValueFactory(new PropertyValueFactory<book,String>("authorName"));
        b_type.setCellValueFactory(new PropertyValueFactory<book,String>("typeName"));
    }

    //showing the data in a choice box as names instead of id's
    void choices(){
        ResultSet res = StudentDB.typeNames();
        try {
            while (res.next()) {
                bType.getItems().add(res.getString("Name"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
