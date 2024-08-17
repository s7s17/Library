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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class getStudentController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Student> st_table;
    @FXML
    private TableColumn<Student, LocalDate> S_BIRTHDATE;

    @FXML
    private TableColumn<Student, Integer> S_CLASS;

    @FXML
    private TableColumn<Student, String> S_GENDER;

    @FXML
    private TableColumn<Student, Integer> S_ID;

    @FXML
    private TableColumn<Student, String> S_NAME;

    @FXML
    private TableColumn<Student, String> S_SURNAME;

    @FXML
    private Button btn_all;

    @FXML
    private Button btn_byid;
    @FXML
    private Button btn_switch;
    @FXML
    private Button btn_byname;
    @FXML
    private TextField tf_search;

    @FXML
    void getAll(MouseEvent event)
    {
        st_table.getItems().clear();
        st_table.setItems(StudentDB.getAllStudents());
    }

    @FXML
    void getStudentByID(MouseEvent event) {
        st_table.getItems().clear();
        st_table.setItems(StudentDB.getStudent(Integer.parseInt(tf_search.getText())));
    }

    @FXML
    void getStudentByName(MouseEvent event)
    {
        st_table.getItems().clear();
        st_table.setItems(StudentDB.getStudent(tf_search.getText()));
    }
    //move back to student page with clearing the table of the student
    @FXML
    void switchtomain(MouseEvent event) throws IOException {
        st_table.getItems().clear();
        root = FXMLLoader.load(getClass().getResource("Student.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //initializing the table.

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        S_NAME.setCellValueFactory(new PropertyValueFactory<Student , String>("name"));
        S_ID.setCellValueFactory(new PropertyValueFactory<Student , Integer>("id"));
        S_GENDER.setCellValueFactory(new PropertyValueFactory<Student , String>("gender"));
        S_BIRTHDATE.setCellValueFactory(new PropertyValueFactory<Student , LocalDate>("dateOfBirth"));
        S_SURNAME.setCellValueFactory(new PropertyValueFactory<Student , String>("surName"));
        S_CLASS.setCellValueFactory(new PropertyValueFactory<Student , Integer>("classroom"));

    }
}

