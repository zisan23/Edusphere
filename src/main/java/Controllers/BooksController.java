package Controllers;

import com.example.projectedusphere.FeatureSelector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksController implements Initializable {
    @FXML
    private Button profile;
    @FXML
    private Button books;
    @FXML
    private Button time;

    @FXML
    private Button attendence;
    @FXML
    private Button result;
    @FXML
    private ImageView exit;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FeatureSelector.SelectScene(profile, books, time, attendence, result, exit);
    }
}
