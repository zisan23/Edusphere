package Controllers;

import com.example.projectedusphere.EDUSPHEREUtils;
import com.example.projectedusphere.FeatureSelector;
import com.example.projectedusphere.Result;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;


public class ResultController extends com.example.projectedusphere.Result implements Initializable {

    @FXML
    private ImageView exit;

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
    private Button note;
    @FXML
    private TextField grade1;

    @FXML
    private TextField grade2;

    @FXML
    private TextField grade3;

    @FXML
    private TextField grade4;

    @FXML
    private TextField grade5;

    @FXML
    private Button calculateButton;

    @FXML
    private Label show;
    @FXML
    private TextField credit1;
    @FXML
    private TextField credit2;
    @FXML
    private TextField credit3;
    @FXML
    private TextField credit4;
    @FXML
    private TextField credit5;
    @FXML
    private Label c1;
    @FXML
    private Label c2;
    @FXML
    private Label c3;
    @FXML
    private Label c4;
    @FXML
    private Label c5;

    public ResultController(){
        super();
    }


    private static String[] sub = new String[5];
    private static String[] credit = new String[5];
    private static String[] grade = new String[5];
    private static Double[] cred = new Double[5];
    private static Double[] grd = new Double[5];
    private static Double Result = 0.0;
    private static int totalCred = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FeatureSelector.SelectScene(profile, books, time, attendence, result, exit, note);

        sub = EDUSPHEREUtils.getSubject();

        c1.setText(sub[0]);
        c2.setText(sub[1]);
        c3.setText(sub[2]);
        c4.setText(sub[3]);
        c5.setText(sub[4]);


        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Result = 0.0;
                totalCred = 0;

                credit[0] = credit1.getText();
                credit[1] = credit2.getText();
                credit[2] = credit3.getText();
                credit[3] = credit4.getText();

                if(credit2.getText().isEmpty()){
                    credit[1] = "x";
                }
                else{
                    credit[1] = credit2.getText();
                }

                if(credit3.getText().isEmpty()){
                    credit[2] = "x";
                }
                else{
                    credit[3] = credit3.getText();
                }

                if(credit4.getText().isEmpty()){
                    credit[3] = "x";
                }
                else{
                    credit[3] = credit4.getText();
                }

                if(credit5.getText().isEmpty()){
                    credit[4] = "x";
                }
                else{
                    credit[4] = credit5.getText();
                }




                grade[0] = grade1.getText();
                grade[1] = grade2.getText();
                grade[2] = grade3.getText();
                grade[3] = grade4.getText();

                if(grade2.getText().isEmpty()){
                    grade[1] = "x";
                }
                else{
                    grade[1] = grade2.getText();
                }

                if(grade3.getText().isEmpty()){
                    grade[2] = "x";
                }
                else{
                    grade[2] = grade3.getText();
                }

                if(grade4.getText().isEmpty()){
                    grade[3] = "x";
                }
                else{
                    grade[3] = grade4.getText();
                }

                if(grade5.getText().isEmpty()){
                    grade[4] = "x";
                }
                else{
                    grade[4] = grade5.getText();
                }



                for(int i=0; i<5; i++){
                    if(credit[i] == "x" || grade[i] == "x"){
                        cred[i] = 0.0;
                        grd[i] = 0.0;
                    }
                    else{
                        cred[i] = Double.parseDouble(credit[i]);
                        grd[i] = Double.parseDouble(grade[i]);
                    }
                }



                for(int i=0; i<5; i++){
                    if(cred[i] == 0.0 || grd[i] == 0.0){

                    }
                    else {
                        Result = Result + (cred[i] * grd[i]);
                        totalCred += cred[i];
                    }
                }

                Result = Result / totalCred;

                show.setText(String.format("%.2f", Result));
            }
        });
    }
}
