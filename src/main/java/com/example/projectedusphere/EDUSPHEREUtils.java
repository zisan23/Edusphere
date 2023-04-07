package com.example.projectedusphere;

import Controllers.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class EDUSPHEREUtils {
    private static String RegistrationNumber;
    private static String Email;
    private static String Roll;

    public static String getEmail(){
        return Email;
    }
    public static void setEmail(String email){
        Email = email;
    }

    public static String getRegistrationNumber(){
        return RegistrationNumber;
    }
    public static void setRegistrationNumber(String registrationNumber){
        RegistrationNumber = registrationNumber;
    }

    public static String getRoll(){
        return Roll;
    }
    public static void setRoll(String roll){
        Roll = roll;
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String Title, String StudentName, String Password){
        Parent root = null;
        if(StudentName != null || Password != null){
            try {
                FXMLLoader loader = new FXMLLoader(EDUSPHEREUtils.class.getResource(fxmlFile));
                root = loader.load();
                DashboardController dashboardController = loader.getController();
                dashboardController.getClass();
            }
            catch (IOException e){
                System.out.println(e.getStackTrace());
            }
        }
        else{
            try{
                root = FXMLLoader.load(EDUSPHEREUtils.class.getResource(fxmlFile));
            }
            catch (IOException e){
                System.out.println("FXML File Load Error");
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(Title);
        stage.setScene(new Scene(root, 1200, 700));
        stage.show();
    }

    public static void signUp(ActionEvent event, String Name, String Email, String Password, String Roll, String Registration){
        Connection connection = null;
        PreparedStatement userInsert = null;
        PreparedStatement checkUserExists = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/edusphere", "root", "ZisanMahmud@12002060");
            checkUserExists = connection.prepareStatement("SELECT * FROM users WHERE registration = ?");
            checkUserExists.setString(1, Registration);

            resultSet = checkUserExists.executeQuery();

            if(Name.isEmpty() || Email.isEmpty() || Password.isEmpty() || Roll.isEmpty() || Registration.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provide all necessary information");
                alert.show();
            }
            else{
                if(resultSet.isBeforeFirst()){
                    System.out.println("User already exists");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("User already exists");
                    alert.show();
                }
                else{
                    userInsert = connection.prepareStatement("INSERT INTO users (userName, email, password, roll, registration) VALUES(?,?,?,?,?)");
                    userInsert.setString(1, Name);
                    userInsert.setString(2, Email);
                    userInsert.setString(3, Password);
                    userInsert.setString(4, String.valueOf(Roll));
                    userInsert.setString(5, Registration);

                    userInsert.executeUpdate();

                    changeScene(event, "Dashboard.fxml", "Dasboard", null, null);

                }
            }
        }

        catch (SQLException e){
            e.printStackTrace();
        }

        finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(userInsert != null){
                try{
                    userInsert.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(checkUserExists != null){
                try{
                    checkUserExists.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void login(ActionEvent event, String Username, String Password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/edusphere", "root", "ZisanMahmud@12002060");
            preparedStatement = connection.prepareStatement("SELECT userName, email, password, roll, registration FROM users WHERE userName = ?");
            preparedStatement.setString(1, Username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst() || Username == null){
                System.out.println("Invalid Username");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Username");
                alert.show();
            }
            else{
                while(resultSet.next()){
                    String findname = resultSet.getString("userName");
                    String findemail = resultSet.getString("email");
                    String findpass = resultSet.getString("password");
                    String findroll = resultSet.getString("roll");
                    String findreg = resultSet.getString("registration");

                    if(findpass.equals(Password)){
                        setEmail(findemail);
                        setRoll(findroll);
                        setRegistrationNumber(findreg);
                        changeScene(event, "Dashboard.fxml", "Dasboard", null, null);
                    }
                    else{
                        System.out.println("Wrong Password!!!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Wrong Password!!!");
                        alert.show();
                    }

                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
