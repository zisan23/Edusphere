package Controllers;

import com.example.projectedusphere.EDUSPHEREUtils;
import com.example.projectedusphere.FeatureSelector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

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
    @FXML
    private ImageView exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FeatureSelector.ExitSelector(exit);
        signupbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EDUSPHEREUtils.changeScene(event, "SignUp.fxml", "Sign Up", null, null);

            }
        });

        loginbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!loginusername.getText().equals("")){
                    EDUSPHEREUtils.login(event, loginusername.getText(), loginpassword.getText());

                }
                else{
                    System.out.println("Please provide User Name");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please provide User Name");
                    alert.show();
                }
            }
        });
    }
}