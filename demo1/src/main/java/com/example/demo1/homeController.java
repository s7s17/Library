package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btn_Students;

    @FXML
    private Button btn_authors;

    @FXML
    private Button btn_books;

    @FXML
    private Button btn_borrow;

    @FXML
    void authorPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("author.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Author Page");
        stage.show();
    }

    @FXML
    void bookPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("book.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Page");
        stage.show();
    }

    @FXML
    void borrowPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("borrow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Borrow Page");
        stage.show();
    }

    @FXML
    void studentPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Student.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Students Page");
        stage.show();
    }

}
