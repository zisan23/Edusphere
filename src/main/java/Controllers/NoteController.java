package Controllers;

import com.example.projectedusphere.EDUSPHEREUtils;
import com.example.projectedusphere.FeatureSelector;
import com.example.projectedusphere.Notes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class NoteController extends Notes implements Initializable {
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
    @FXML
    private Button note;

    @FXML
    private TextField tf_note;
    @FXML
    private Button updatebutton;
    @FXML
    private TextArea tf_area;

    private String Keepnote;
    private String reg;

    public NoteController(){
        super();
        setNote(super.getKeepnote());
        setreg(super.getRegistration());
    }

    public void setNote(String note){this.Keepnote = note;}
    public String getKeepnote(){return this.Keepnote;}

    public void setreg(String Reg){this.reg = Reg;}
    public String getRegistration(){return this.reg;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FeatureSelector.SelectScene(profile, books,time, attendence, result, exit, note);
        tf_area.setWrapText(true);
        tf_area.setText(Keepnote);

        updatebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                Keepnote = tf_note.getText();
                EDUSPHEREUtils.Note(event, Keepnote, reg);
                tf_area.setWrapText(true);
                tf_area.setText(Keepnote);

            }

        });
    }
}
