package Controllers;

import com.example.projectedusphere.Attendance;
import com.example.projectedusphere.EDUSPHEREUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class GraphController implements Initializable {

    @FXML
    private BarChart<String, Number> graph;
    @FXML
    private Button Return;
    @FXML
    private Button show;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;

    private static String[] temp = new String[5];
    private static String[] temp2 = new String[5];
    private static String[] temp3 = new String[5];
    private static String[] temp4 = new String[5];

    private static String[] Atemp1 = new String[2];
    private static String[] Atemp2 = new String[2];
    private static String[] Atemp3 = new String[2];
    private static String[] Atemp4 = new String[2];
    private static String[] Atemp5 = new String[2];



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Return.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EDUSPHEREUtils.changeScene(event,"Attendence.fxml", "Attendance", null, null);
            }
        });

        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                temp = EDUSPHEREUtils.getSubject();

                temp2 = EDUSPHEREUtils.getClasstaken();

                temp3 = EDUSPHEREUtils.getClassattended();

                for(int i=0; i<5; i++){

                    if(Double.parseDouble(temp3[i]) == 0.0 || Double.parseDouble(temp2[i]) == 0.0){
                        temp4[i] = "0.0";
                    }
                    else {
                        temp4[i] = Double.toString((Double.parseDouble(temp3[i]) / Double.parseDouble(temp2[i])) * 100);
                    }
                }


                xAxis.setLabel("Classes");
                yAxis.setLabel("Attendance Percentage");
                graph.setTitle("Attendance Tracker");

                ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();

                XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                XYChart.Series<String, Number> series3 = new XYChart.Series<>();
                XYChart.Series<String, Number> series4 = new XYChart.Series<>();
                XYChart.Series<String, Number> series5 = new XYChart.Series<>();


                if(temp4[0] != "0.0"){
                    series1.setName(temp[0]);
                }
                if(temp4[0] != "0.0"){
                    series2.setName(temp[1]);
                }
                if(temp4[0] != "0.0"){
                    series3.setName(temp[2]);
                }
                if(temp4[0] != "0.0"){
                    series4.setName(temp[3]);
                }
                if(temp4[0] != "0.0"){
                    series5.setName(temp[4]);
                }



                Atemp1 = temp4[0].split("\\.");
                Atemp2 = temp4[1].split("\\.");
                Atemp3 = temp4[2].split("\\.");
                Atemp4 = temp4[3].split("\\.");
                Atemp5 = temp4[4].split("\\.");


                series1.getData().add(new XYChart.Data<>("", Integer.parseInt(Atemp1[0])));
                series2.getData().add(new XYChart.Data<>("", Integer.parseInt(Atemp2[0])));
                series3.getData().add(new XYChart.Data<>("", Integer.parseInt(Atemp3[0])));
                series4.getData().add(new XYChart.Data<>("", Integer.parseInt(Atemp4[0])));
                series5.getData().add(new XYChart.Data<>("", Integer.parseInt(Atemp5[0])));

                data.addAll(series1, series2, series3, series4, series5);
                graph.setData(data);
            }
        });

    }
}
