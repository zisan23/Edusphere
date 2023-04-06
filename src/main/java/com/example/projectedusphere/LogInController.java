package com.example.projectedusphere;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    private Button signupbutton;

    @FXML
    private Button loginbutton;

    @FXML
    private TextField loginusername;

    @FXML
    private PasswordField loginpassword;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signupbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }
}