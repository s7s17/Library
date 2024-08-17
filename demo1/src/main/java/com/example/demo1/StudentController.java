package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Spinner<Integer> sp_class;
    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,9,1);
    @FXML
    private Button btn_Create;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_Student;
    @FXML
    private Button btn_main;

    @FXML
    private DatePicker dp_DOB;

    @FXML
    private Text t_message;

    @FXML
    private Text t_result;

    @FXML
    private TextField tf_Name;


    @FXML
    private RadioButton Female;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private RadioButton Male;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_surName;

    //creates new student and if created will print "Created Successfully" with clearing text fields
    @FXML
    void newStudent(MouseEvent event) throws IOException {
        Student student = new Student();
        student.setId(Integer.parseInt(tf_id.getText()));
        student.setName(tf_Name.getText());
        student.setSurName(tf_surName.getText());
        student.setDateOfBirth(Date.valueOf(dp_DOB.getValue()));
        if(Female.isSelected()) {
            student.setGender(Female.getText());
        }
        else {
        student.setGender(Male.getText());
        }
        student.setClassroom(sp_class.getValue());

        int val = StudentDB.createStudent(student);
        if(val != 0)
        {
            tf_id.clear();
            tf_Name.clear();
            tf_surName.clear();
            t_result.setVisible(true);
            t_result.setText("Created Successfully");
        }
    }

    //Deleting a student with clearing the text field and printing "Deleted Successfully"
    @FXML
    void deleteStudent(MouseEvent event) throws IOException {
        int val = StudentDB.DeleteStudent(Integer.parseInt(tf_id.getText()));
        if(val != 0)
        {
            tf_id.clear();
            t_result.setVisible(true);
            t_result.setText("Deleted Successfully");
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

    //move to student searching page
    @FXML
    public void switchToStudent(MouseEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("getStudents.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //initializing a spinner and set text to non-visible.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        t_result.setVisible(false);
        sp_class.setValueFactory(svf);

    }
}
