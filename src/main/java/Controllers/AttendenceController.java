package Controllers;

import com.example.projectedusphere.Attendance;
import com.example.projectedusphere.EDUSPHEREUtils;
import com.example.projectedusphere.FeatureSelector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AttendenceController extends Attendance implements Initializable {

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
    private Label subject1, subject2, subject3, subject4, subject5;

    @FXML
    private Button s1add, s2add, s3add, s4add, s5add;

    @FXML
    private Button s11add, s22add, s33add, s44add, s55add;

    @FXML
    private Button s1min, s2min, s3min, s4min, s5min;

    @FXML
    private Label s1Taken, s2Taken, s3Taken, s4Taken, s5Taken;

    @FXML
    private Button s11min, s22min, s33min, s44min, s55min;

    @FXML
    private Label s1Atd, s2Atd, s3Atd, s4Atd, s5Atd;

    @FXML
    private Label s1Per, s2Per, s3Per, s4Per, s5Per;

    @FXML
    private Button graph, update;

    private static String[] sub = new String[5];

    private static String[] classTaken = new String[5];
    private static String[] classAttended = new String[5];
    private static Double[] percentage = new Double[5];
    private static String[] temp1 = new String[5];
    private static String[] temp2 = new String[5];

    private String reg;
    public void setreg(String Reg){this.reg = Reg;}
    public String getRegistration(){return this.reg;}
    public String[] getClassTaken(){return classTaken;}
    public void setClassTaken(String[] CLASSTAKEN){ classTaken = CLASSTAKEN;}

    public String[] getClassAttended(){return classAttended;}
    public void setClassAttended(String[] CLASSATTENDED){classAttended = CLASSATTENDED;}


    public AttendenceController(){
        super();
        setreg(super.getRegistration());
        setClassTaken(super.getClassTaken());
        setClassAttended(super.getClassAttended());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FeatureSelector.SelectScene(profile, books, time, attendence, result, exit, note);

        for(int i=0; i<5; i++){
            if(classTaken[i] == null){
                classTaken[i] = "0";
            }
        }
        for(int i=0; i<5; i++){
            if(classAttended[i] == null){
                classAttended[i] = "0";
            }
        }

        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                classTaken = getClassTaken();
                classAttended = getClassAttended();
                for(int i=0; i<5; i++){
                    percentage[i] = ((Double.parseDouble(classAttended[i]) / Double.parseDouble(classTaken[i])) * 100);
                }
                EDUSPHEREUtils.Attendance(event, classTaken, classAttended, reg);
            }
        });
        graph.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EDUSPHEREUtils.changeScene(event, "Graph.fxml", "Attendance Graph Model", null, null);
            }
        });

        sub = EDUSPHEREUtils.getSubject();

        subject1.setText(sub[0]);
        subject2.setText(sub[1]);
        subject3.setText(sub[2]);
        subject4.setText(sub[3]);
        subject5.setText(sub[4]);

        s1Taken.setText(classTaken[0]);
        s2Taken.setText(classTaken[1]);
        s3Taken.setText(classTaken[2]);
        s4Taken.setText(classTaken[3]);
        s5Taken.setText(classTaken[4]);

        s1Atd.setText(classAttended[0]);
        s2Atd.setText(classAttended[1]);
        s3Atd.setText(classAttended[2]);
        s4Atd.setText(classAttended[3]);
        s5Atd.setText(classAttended[4]);


        s1add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                classTaken[0] = Double.toString(Double.parseDouble(classTaken[0]) + 1);
                s1Taken.setText(classTaken[0]);
                percentage[0] =  (Double.parseDouble(classAttended[0]) / Double.parseDouble(classTaken[0])) * 100;
                s1Per.setText(String.format("%.2f", percentage[0]));
            }
        });

        s1min.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Double.parseDouble(classTaken[0]) <= 0 || Double.parseDouble(classTaken[0]) <= Double.parseDouble(classAttended[0])){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else {
                    classTaken[0] = Double.toString(Double.parseDouble(classTaken[0]) - 1);
                    s1Taken.setText(classTaken[0]);
                    percentage[0] =  (Double.parseDouble(classAttended[0]) / Double.parseDouble(classTaken[0])) * 100;
                    s1Per.setText(String.format("%.2f", percentage[0]));
                }
            }
        });


        s11add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(Double.parseDouble(classAttended[0]) >= Double.parseDouble(classTaken[0])){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else{
                    classAttended[0] = Double.toString(Double.parseDouble(classAttended[0]) + 1);
                    s1Atd.setText(classAttended[0]);
                    percentage[0] =  (Double.parseDouble(classAttended[0]) / Double.parseDouble(classTaken[0])) * 100;
                    s1Per.setText(String.format("%.2f", percentage[0]));
                }
            }
        });

        s11min.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Double.parseDouble(classAttended[0]) <= 0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else {
                    classAttended[0] = Double.toString(Double.parseDouble(classAttended[0]) - 1);
                    s1Atd.setText(classAttended[0]);
                    percentage[0] =   (Double.parseDouble(classAttended[0]) / Double.parseDouble(classTaken[0])) * 100;
                    s1Per.setText(String.format("%.2f", percentage[0]));
                }
            }
        });

        s2add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                classTaken[1] = Double.toString(Double.parseDouble(classTaken[1]) + 1);
                s2Taken.setText(classTaken[1]);
                percentage[1] =  (Double.parseDouble(classAttended[1]) / Double.parseDouble(classTaken[1])) * 100;
                s2Per.setText(String.format("%.2f", percentage[1]));
            }
        });

        s2min.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Double.parseDouble(classTaken[1]) <= 0 || Double.parseDouble(classTaken[1]) <= Double.parseDouble(classAttended[1])){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else {
                    classTaken[1] = Double.toString(Double.parseDouble(classTaken[1]) - 1);
                    s2Taken.setText(classTaken[1]);
                    percentage[1] =  (Double.parseDouble(classAttended[1]) / Double.parseDouble(classTaken[1])) * 100;
                    s2Per.setText(String.format("%.2f", percentage[1]));
                }
            }
        });


        s22add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(Double.parseDouble(classAttended[1]) >= Double.parseDouble(classTaken[1])){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else{
                    classAttended[1] = Double.toString(Double.parseDouble(classAttended[1]) + 1);
                    s2Atd.setText(classAttended[1]);
                    percentage[1] =  (Double.parseDouble(classAttended[1]) / Double.parseDouble(classTaken[1])) * 100;
                    s2Per.setText(String.format("%.2f", percentage[1]));
                }
            }
        });

        s22min.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Double.parseDouble(classAttended[1]) <= 0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else {
                    classAttended[1] = Double.toString(Double.parseDouble(classAttended[1]) - 1);
                    s2Atd.setText(classAttended[1]);
                    percentage[1] =  (Double.parseDouble(classAttended[1]) / Double.parseDouble(classTaken[1])) * 100;
                    s2Per.setText(String.format("%.2f", percentage[1]));
                }
            }
        });



        s3add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                classTaken[2] = Double.toString(Double.parseDouble(classTaken[2]) + 1);
                s3Taken.setText(classTaken[2]);
                percentage[2] =  (Double.parseDouble(classAttended[2]) / Double.parseDouble(classTaken[2])) * 100;
                s3Per.setText(String.format("%.2f", percentage[2]));
            }
        });

        s3min.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Double.parseDouble(classTaken[2]) <= 0 || Double.parseDouble(classTaken[2]) <= Double.parseDouble(classAttended[2])){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else {
                    classTaken[2] = Double.toString(Double.parseDouble(classTaken[2]) - 1);
                    s3Taken.setText(classTaken[2]);
                    percentage[2] =  (Double.parseDouble(classAttended[2]) / Double.parseDouble(classTaken[2])) * 100;
                    s3Per.setText(String.format("%.2f", percentage[2]));
                }
            }
        });


        s33add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(Double.parseDouble(classAttended[2]) >= Double.parseDouble(classTaken[2])){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else{
                    classAttended[2] = Double.toString(Double.parseDouble(classAttended[2]) + 1);
                    s3Atd.setText(classAttended[2]);
                    percentage[2] = (Double.parseDouble(classAttended[2]) / Double.parseDouble(classTaken[2])) * 100;
                    s3Per.setText(String.format("%.2f", percentage[2]));
                }
            }
        });

        s33min.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Double.parseDouble(classAttended[2]) <= 0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else {
                    classAttended[2] = Double.toString(Double.parseDouble(classAttended[2]) - 1);
                    s3Atd.setText(classAttended[2]);
                    percentage[2] = (Double.parseDouble(classAttended[2]) / Double.parseDouble(classTaken[2])) * 100;
                    s3Per.setText(String.format("%.2f", percentage[2]));
                }
            }
        });



        s4add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                classTaken[3] = Double.toString(Double.parseDouble(classTaken[3]) + 1);
                s4Taken.setText(classTaken[3]);
                percentage[3] = (Double.parseDouble(classAttended[3]) / Double.parseDouble(classTaken[3])) * 100;
                s4Per.setText(String.format("%.2f", percentage[3]));
            }
        });

        s4min.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Double.parseDouble(classTaken[3]) <= 0 || Double.parseDouble(classTaken[3]) <= Double.parseDouble(classAttended[3])){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else {
                    classTaken[3] = Double.toString(Double.parseDouble(classTaken[3]) - 1);
                    s4Taken.setText(classTaken[3]);
                    percentage[3] = (Double.parseDouble(classAttended[3]) / Double.parseDouble(classTaken[3])) * 100;
                    s4Per.setText(String.format("%.2f", percentage[3]));
                }
            }
        });


        s44add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(Double.parseDouble(classAttended[3]) >= Double.parseDouble(classTaken[3])){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else{
                    classAttended[3] = Double.toString(Double.parseDouble(classAttended[3]) + 1);
                    s4Atd.setText(classAttended[3]);
                    percentage[3] = (Double.parseDouble(classAttended[3]) / Double.parseDouble(classTaken[3])) * 100;
                    s4Per.setText(String.format("%.2f", percentage[3]));
                }
            }
        });

        s44min.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Double.parseDouble(classAttended[3]) <= 0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else {
                    classAttended[3] = Double.toString(Double.parseDouble(classAttended[3]) - 1);
                    s4Atd.setText(classAttended[3]);
                    percentage[3] = (Double.parseDouble(classAttended[3]) / Double.parseDouble(classTaken[3])) * 100;
                    s4Per.setText(String.format("%.2f", percentage[3]));
                }
            }
        });


        s5add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                classTaken[4] = Double.toString(Double.parseDouble(classTaken[4]) + 1);
                s5Taken.setText(classTaken[4]);
                percentage[4] = (Double.parseDouble(classAttended[4]) / Double.parseDouble(classTaken[4])) * 100;
                s5Per.setText(String.format("%.2f", percentage[4]));

            }
        });

        s5min.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Double.parseDouble(classTaken[4]) <= 0 || Double.parseDouble(classTaken[4]) <= Double.parseDouble(classAttended[4])){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else {
                    classTaken[4] = Double.toString(Double.parseDouble(classTaken[4]) - 1);
                    s5Taken.setText(classTaken[4]);
                    percentage[4] = (Double.parseDouble(classAttended[4]) / Double.parseDouble(classTaken[4])) * 100;
                    s5Per.setText(String.format("%.2f", percentage[4]));
                }
            }
        });


        s55add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(Double.parseDouble(classAttended[4]) >= Double.parseDouble(classTaken[4])){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else{
                    classAttended[4] = Double.toString(Double.parseDouble(classAttended[4]) + 1);
                    s5Atd.setText(classAttended[4]);
                    percentage[4] = (Double.parseDouble(classAttended[4]) / Double.parseDouble(classTaken[4])) * 100;
                    s5Per.setText(String.format("%.2f", percentage[4]));
                }
            }
        });

        s55min.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Double.parseDouble(classAttended[4]) <= 0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Invalid Action");
                    alert.show();
                }
                else {
                    classAttended[4] = Double.toString(Double.parseDouble(classAttended[4]) - 1);
                    s5Atd.setText(classAttended[4]);
                    percentage[4] = (Double.parseDouble(classAttended[4]) / Double.parseDouble(classTaken[4])) * 100;
                    s5Per.setText(String.format("%.2f", percentage[4]));
                }
            }
        });

    }
}
