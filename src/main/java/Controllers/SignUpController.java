package Controllers;

import com.example.projectedusphere.EDUSPHEREUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button signuploginbutton;
    @FXML
    private Button signupmainbutton;
    @FXML
    private TextField signupusername;
    @FXML
    private TextField signupemail;
    @FXML
    private TextField signuppassword;
    @FXML
    private TextField signuproll;
    @FXML
    private TextField signupregistration;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signuploginbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EDUSPHEREUtils.changeScene(event, "LogIn.fxml", "Log In", null, null);

            }
        });

        signupmainbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EDUSPHEREUtils.signUp(event, signupusername.getText(), signupemail.getText(), signuppassword.getText(), signuproll.getText(), signupregistration.getText());

            }
        });
    }
}
