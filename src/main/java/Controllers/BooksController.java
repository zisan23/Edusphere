package Controllers;

import com.example.projectedusphere.Books;
import com.example.projectedusphere.EDUSPHEREUtils;
import com.example.projectedusphere.FeatureSelector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.awt.Desktop;

public class BooksController extends com.example.projectedusphere.Books implements Initializable {
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
    private Label s1;

    @FXML
    private Button selectFileButton;

    @FXML
    private Button viewPdfButton;

    @FXML
    private Label s2;

    @FXML
    private Button selectFileButton2;

    @FXML
    private Button viewPdfButton2;

    @FXML
    private Label s3;

    @FXML
    private Button selectFileButton1;

    @FXML
    private Button viewPdfButton1;

    @FXML
    private Button selectFileButton3;

    @FXML
    private Button viewPdfButton3;

    @FXML
    private Label s4;

    @FXML
    private Button selectFileButton4;

    @FXML
    private Button viewPdfButton4;
    @FXML
    private Label s5;

    @FXML
    private Button update;

    private String[] pdfPath = new String[5];
    private static String[] sub = new String[5];
    private String reg;
    public void setreg(String Reg){this.reg = Reg;}
    public String getRegistration(){return this.reg;}

    public void setPaths(String[] PDFPATH){
        this.pdfPath = PDFPATH;
    }
    public String[] getPaths(){return pdfPath;}

    public BooksController(){
        super();
        setreg(super.getRegistration());
        setPaths(super.getPaths());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FeatureSelector.SelectScene(profile, books, time, attendence, result, exit, note);

        sub = EDUSPHEREUtils.getSubject();

        s1.setText(sub[0]);
        s2.setText(sub[1]);
        s3.setText(sub[2]);
        s4.setText(sub[3]);
        s5.setText(sub[4]);

        setPaths(EDUSPHEREUtils.getPaths());

        setreg(EDUSPHEREUtils.getRegistrationNumber());


        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EDUSPHEREUtils.BOOK(event, pdfPath, reg);
            }
        });
    }

    @FXML
    void selectFile(ActionEvent event) {
        // Create a file chooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        // Show the dialog and get the selected file
        File selectedFile = fileChooser.showOpenDialog(selectFileButton.getScene().getWindow());

        // Save the path of the selected file to a string
        if (selectedFile != null) {
            pdfPath[0] = selectedFile.getAbsolutePath();
            //s1.setText(pdfPath);
        }
    }

    @FXML
    void viewPdf(ActionEvent event) {
        if (pdfPath[0] != null) {
            try {
                // Open the PDF file in the default PDF viewer on the user's computer
                Desktop.getDesktop().open(new File(pdfPath[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void selectFile1(ActionEvent event) {
        // Create a file chooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        // Show the dialog and get the selected file
        File selectedFile = fileChooser.showOpenDialog(selectFileButton1.getScene().getWindow());

        // Save the path of the selected file to a string
        if (selectedFile != null) {
            pdfPath[1] = selectedFile.getAbsolutePath();
            //s1.setText(pdfPath);
        }
    }

    @FXML
    void viewPdf1(ActionEvent event) {
        if (pdfPath[1] != null) {
            try {
                // Open the PDF file in the default PDF viewer on the user's computer
                Desktop.getDesktop().open(new File(pdfPath[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void selectFile2(ActionEvent event) {
        // Create a file chooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        // Show the dialog and get the selected file
        File selectedFile = fileChooser.showOpenDialog(selectFileButton2.getScene().getWindow());

        // Save the path of the selected file to a string
        if (selectedFile != null) {
            pdfPath[2] = selectedFile.getAbsolutePath();
            //s1.setText(pdfPath);
        }
    }

    @FXML
    void viewPdf2(ActionEvent event) {
        if (pdfPath[2] != null) {
            try {
                // Open the PDF file in the default PDF viewer on the user's computer
                Desktop.getDesktop().open(new File(pdfPath[2]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void selectFile3(ActionEvent event) {
        // Create a file chooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        // Show the dialog and get the selected file
        File selectedFile = fileChooser.showOpenDialog(selectFileButton3.getScene().getWindow());

        // Save the path of the selected file to a string
        if (selectedFile != null) {
            pdfPath[3] = selectedFile.getAbsolutePath();
            //s1.setText(pdfPath);
        }
    }

    @FXML
    void viewPdf3(ActionEvent event) {
        if (pdfPath[3] != null) {
            try {
                // Open the PDF file in the default PDF viewer on the user's computer
                Desktop.getDesktop().open(new File(pdfPath[3]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void selectFile4(ActionEvent event) {
        // Create a file chooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        // Show the dialog and get the selected file
        File selectedFile = fileChooser.showOpenDialog(selectFileButton4.getScene().getWindow());

        // Save the path of the selected file to a string
        if (selectedFile != null) {
            pdfPath[4] = selectedFile.getAbsolutePath();
            //s1.setText(pdfPath);
        }
    }

    @FXML
    void viewPdf4(ActionEvent event) {
        if (pdfPath[4] != null) {
            try {
                // Open the PDF file in the default PDF viewer on the user's computer
                Desktop.getDesktop().open(new File(pdfPath[4]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
