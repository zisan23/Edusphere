package Controllers;

import com.example.projectedusphere.EDUSPHEREUtils;
import com.example.projectedusphere.FOT;
import com.example.projectedusphere.FeatureSelector;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class FOTController extends FOT implements Initializable {
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
    private Label totalHours;

    @FXML
    private TextField studytime;

    @FXML
    private Button savebt;

    @FXML
    private DatePicker eddate;

    @FXML
    private Label reamainingtime;

    @FXML
    private Label progress;

    private LocalDateTime time1;
    private LocalDate time2;
    private LocalTime time22;
    private LocalDateTime time3;
    private String time4;

    private Duration duration;

    private long diffInHours;
    private String Storetime;
    private String reg;
    private long temp;
    private String temp2;
    private int count = 0;
    private int storage;

    public FOTController(){
        super();
        setTime(super.getTime());
        setTime2(super.getTime2());
        setreg(super.getRegistration());
    }

    public void setTime(String Time){
        Storetime = Time;
    }
    public String getTime(){
        return this.Storetime;
    }
    public void setTime2(String Time){
        temp2 = Time;
    }
    public String getTime2(){
        return this.temp2;
    }
    public void setreg(String Reg){this.reg = Reg;}
    public String getRegistration(){return this.reg;}



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FeatureSelector.SelectScene(profile, books, time, attendence, result, exit, note);

        savebt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                time1 = LocalDateTime.now();

                time2 = eddate.getValue();

                time22 = LocalTime.MIDNIGHT;

                time3 = LocalDateTime.of(time2, time22);

                duration = Duration.between(time1, time3);

                diffInHours = duration.toHours();

                Storetime = Long.toString(diffInHours);


                time4 = studytime.getText(); // input today's study time

                long calc = Long.parseLong(time4); //

                temp = diffInHours - calc; // remaining time

                temp2 = Long.toString(temp); //remaining time value


                EDUSPHEREUtils.FOT(event, Storetime, temp2, reg);
                if (count == 0) {
                    storage = Integer.parseInt(Storetime);
                    count++;
                }

                float percent = ((Float.parseFloat(time4) / storage) * 100);

                if (percent > 100) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Time Limit Exceeded!!!");
                    alert.setContentText("You Have Not Enough Time Left!!!");
                    alert.show();
                }
                else if(percent < 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Date Error");
                    alert.setContentText("Provided Date Has Passed Already");
                    alert.show();
                }

                else {
                    Platform.runLater(() -> totalHours.setText(Storetime));
                    Platform.runLater(() -> reamainingtime.setText(temp2));
                    Platform.runLater(() -> progress.setText(Float.toString(percent)));
                }
            }
        });
    }
}
