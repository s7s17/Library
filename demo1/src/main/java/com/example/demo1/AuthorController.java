package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    static int val ;

    @FXML
    private TableColumn<author, Integer> auth_id;

    @FXML
    private TableColumn<author, String> auth_name;

    @FXML
    private TableColumn<author, String> auth_surname;

    @FXML
    private TableView<author> auth_table;

    @FXML
    private Button btn_byid;

    @FXML
    private Button btn_byname;

    @FXML
    private Button btn_create;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_main;

    @FXML
    private Button btn_getall;
    @FXML
    private Text t_Delete;
    @FXML
    private Text t_message;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_surname;

    @FXML
    void allAuthors(MouseEvent event)
    {
        auth_table.getItems().clear();
        auth_table.setItems(AuthorDB.getAllAuthor());
    }

    @FXML
    void authorByID(MouseEvent event)
    {
        auth_table.getItems().clear();
        auth_table.setItems(AuthorDB.getAuthor(Integer.parseInt(tf_id.getText())));
    }

    @FXML
    void authorByName(MouseEvent event)
    {
        auth_table.getItems().clear();
        auth_table.setItems(AuthorDB.getAuthor(tf_name.getText()));
    }
    //creates new author and if created will print "Created Successfully" with clearing text fields
    @FXML
    void createAuthor(MouseEvent event) {
        author author2 = new author();
        author2.setId(Integer.parseInt(tf_id.getText()));
        author2.setName(tf_name.getText());
        author2.setSurName(tf_surname.getText());
        val =AuthorDB.createAuthor(author2);
        if (val !=0)
        {
            tf_id.clear();
            tf_name.clear();
            tf_surname.clear();
            t_message.setVisible(true);
            t_message.setText("Created Successfully");
        }
    }

    //move us to home page
    @FXML
    void GoToHome(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Deleting an author with clearing the text field and printing "Deleted Successfully"
    @FXML
    void deleteAuthor(MouseEvent event) {
        val =AuthorDB.DeleteAuthor(Integer.parseInt(tf_id.getText()));
        if (val != 0)
        {
            tf_id.clear();
            t_message.setVisible(true);
            t_message.setText("Created Successfully");
        }
    }


    //initializing the table and making the text non-visible.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        t_message.setVisible(false);
        auth_id.setCellValueFactory(new PropertyValueFactory<author,Integer>("id"));
        auth_name.setCellValueFactory(new PropertyValueFactory<author,String>("name"));
        auth_surname.setCellValueFactory(new PropertyValueFactory<author,String>("surName"));


    }
}
