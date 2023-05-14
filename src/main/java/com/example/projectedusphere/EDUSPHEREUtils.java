package com.example.projectedusphere;

import Controllers.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;

public class EDUSPHEREUtils {
    private static String RegistrationNumber;
    private static String Email;
    private static String Roll;

    private static String Name;
    private static String Password;

    private static String Bio;
    private static String Note;

    private static String time;
    private static String time2;

    private static String[] Subject = new String[5];

    private static String[] classtaken = new String[5];
    private static String[] classattended = new String[5];
    private static String[] Paths = new String[5];

    public static String[] getPaths(){
        return Paths;
    }
    public static void setPaths(String[] PAths){
        Paths = PAths;
    }

    public static String[] getClasstaken(){
        return classtaken;
    }
    public static void setClasstaken(String[] CLASSTAKEN){
        classtaken = CLASSTAKEN;
    }

    public static String[] getClassattended(){
        return classattended;
    }
    public static void setClassattended(String[] CLASSATTENDED){
        classattended = CLASSATTENDED;
    }

    public static String[] getSubject(){
        return Subject;
    }
    public static void setSubject(String[] SUB){
        Subject = SUB;
    }

    public static String getTime(){return time;}
    public static void setTime(String Time){time = Time;}
    public static String getTime2(){return time2;}
    public static void setTime2(String Time){time2 = Time;}
    public static String getKeepnote(){return Note;}
    public static void setNote(String text){Note = text;}

    public static String getBio(){return Bio;}
    public static void setBio(String text){Bio = text;}

    public static String getName(){return Name;}
    public static void setName(String name){Name = name;}

    public static String getPassword(){return Password;}
    public static void setPassword(String password){Password = password;}

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
                    userInsert.setString(4, Roll);
                    userInsert.setString(5, Registration);

                    userInsert.executeUpdate();

                    setName(Name);
                    setPassword(Password);
                    setEmail(Email);
                    setRoll(Roll);
                    setRegistrationNumber(Registration);

                    changeScene(event, "LogIn.fxml", "Log In", null, null);

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
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE userName = ?");
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
                    String findbio = resultSet.getString("about");
                    String findnote = resultSet.getString("note");
                    String findtime = resultSet.getString("time");
                    String findtime2 = resultSet.getString("time2");

                    String[] findSub = new String[5];
                    String[] findClassTaken = new String[5];
                    String[] findClassAttended = new String[5];
                    String[] findPaths= new String[5];


                    findSub[0] = resultSet.getString("Sub1");
                    findSub[1] = resultSet.getString("Sub2");
                    findSub[2] = resultSet.getString("Sub3");
                    findSub[3] = resultSet.getString("Sub4");
                    findSub[4] = resultSet.getString("Sub5");

                    findClassTaken[0] = resultSet.getString("sub1Class");
                    findClassTaken[1] = resultSet.getString("sub2Class");
                    findClassTaken[2] = resultSet.getString("sub3Class");
                    findClassTaken[3] = resultSet.getString("sub4Class");
                    findClassTaken[4] = resultSet.getString("sub5Class");

                    findClassAttended[0] = resultSet.getString("sub1At");
                    findClassAttended[1] = resultSet.getString("sub2At");
                    findClassAttended[2] = resultSet.getString("sub3At");
                    findClassAttended[3] = resultSet.getString("sub4At");
                    findClassAttended[4] = resultSet.getString("sub5At");

                    findPaths[0] = resultSet.getString("pdf1");
                    findPaths[1] = resultSet.getString("pdf2");
                    findPaths[2] = resultSet.getString("pdf3");
                    findPaths[3] = resultSet.getString("pdf4");
                    findPaths[4] = resultSet.getString("pdf5");


                    if(findpass.equals(Password)){
                        setName(findname);
                        setPassword(Password);
                        setEmail(findemail);
                        setRoll(findroll);
                        setRegistrationNumber(findreg);
                        setBio(findbio);
                        setNote(findnote);
                        setTime(findtime);
                        setTime2(findtime2);
                        setSubject(findSub);
                        setClassattended(findClassAttended);
                        setClasstaken(findClassTaken);
                        setPaths(findPaths);

                        changeScene(event, "Dashboard.fxml", "Dashboard", null, null);
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





    public static void bio(ActionEvent event, String text, String Registration){
        Connection connection = null;
        PreparedStatement userInsert = null;
        PreparedStatement finduser = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/edusphere", "root", "ZisanMahmud@12002060");
            finduser = connection.prepareStatement("SELECT * FROM users WHERE registration = ?");
            finduser.setString(1, Registration);

            resultSet = finduser.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("Invalid Registration");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Registration");
                alert.show();
            }
            else{
                while(resultSet.next()){
                    String findreg = resultSet.getString("registration");

                    if(findreg.equals(Registration)){
                        userInsert = connection.prepareStatement("UPDATE users SET about = ? WHERE registration = ?");
                        userInsert.setString(1, text);
                        userInsert.setString(2, Registration);
                        userInsert.executeUpdate();
                        Bio = text;
                        setBio(Bio);
                        setNote(Note);
                        setTime(time);
                    }
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (userInsert != null) {
                try {
                    userInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(finduser != null){
                try{
                    finduser.close();
                } catch (SQLException e){
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




    public static void Note(ActionEvent event, String text, String Registration){
        Connection connection = null;
        PreparedStatement userInsert = null;
        PreparedStatement finduser = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/edusphere", "root", "ZisanMahmud@12002060");
            finduser = connection.prepareStatement("SELECT * FROM users WHERE registration = ?");
            finduser.setString(1, Registration);

            resultSet = finduser.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("Invalid Registration");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Registration");
                alert.show();
            }
            else{
                while(resultSet.next()){
                    String findreg = resultSet.getString("registration");

                    if(findreg.equals(Registration)){

                        userInsert = connection.prepareStatement("UPDATE users SET note = ? WHERE registration = ?");
                        userInsert.setString(1, text);
                        userInsert.setString(2, Registration);
                        userInsert.executeUpdate();
                        Note = text;
                        setNote(Note);
                        setBio(Bio);
                        setTime(time);
                    }
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (userInsert != null) {
                try {
                    userInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(finduser != null){
                try{
                    finduser.close();
                } catch (SQLException e){
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




    public static void Subjects(ActionEvent event, String[] subject, String Registration){
        Connection connection = null;
        PreparedStatement userInsert = null;
        PreparedStatement finduser = null;
        ResultSet resultset = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/edusphere", "root", "ZisanMahmud@12002060");
            finduser = connection.prepareStatement("SELECT * FROM users WHERE registration = ?");
            finduser.setString(1, Registration);

            resultset = finduser.executeQuery();

            if(!resultset.isBeforeFirst()){
                System.out.println("Invalid Registration");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Registration");
                alert.show();
            }
            else{
                while(resultset.next()){
                    String findreg = resultset.getString("registration");

                    if(findreg.equals(Registration)){
                        userInsert = connection.prepareStatement("UPDATE users SET Sub1=?, Sub2 = ?, Sub3=?, Sub4 = ?, Sub5 = ? WHERE registration =?");
                        for(int i=0; i<5; i++){
                            userInsert.setString(i+1, subject[i]);
                        }
                        userInsert.setString(6, Registration);
                        userInsert.executeUpdate();
                        Subject = subject;
                        setSubject(Subject);
                    }
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (userInsert != null) {
                try {
                    userInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(finduser != null){
                try{
                    finduser.close();
                } catch (SQLException e){
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




    public static void FOT(ActionEvent event, String Time, String Time2, String Registration) {
        Connection connection = null;
        PreparedStatement userInsert = null;
        PreparedStatement finduser = null;
        ResultSet resultset = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/edusphere", "root", "ZisanMahmud@12002060");
            finduser = connection.prepareStatement("SELECT * FROM users WHERE registration = ?");
            finduser.setString(1, Registration);

            resultset = finduser.executeQuery();

            if(!resultset.isBeforeFirst()){
                System.out.println("Invalid Registration");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Registration");
                alert.show();
            }
            else{
                while(resultset.next()){
                    String findreg = resultset.getString("registration");

                    if(findreg.equals(Registration)){
                        userInsert = connection.prepareStatement("UPDATE users SET time=?, time2=? WHERE registration = ?");
                        userInsert.setString(1, Time);
                        userInsert.setString(2, Time2);
                        userInsert.setString(3, Registration);
                        userInsert.executeUpdate();
                        time = Time;
                        time2 = Time2;
                        setBio(Bio);
                        setNote(Note);
                        setTime(time);
                        setTime2(time2);
                    }
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (userInsert != null) {
                try {
                    userInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(finduser != null){
                try{
                    finduser.close();
                } catch (SQLException e){
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




    public static void Attendance(ActionEvent event, String[] classTaken, String[] classAttended, String Registration){
        Connection connection = null;
        PreparedStatement userInsert = null;
        PreparedStatement finduser = null;
        ResultSet resultset = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/edusphere", "root", "ZisanMahmud@12002060");
            finduser = connection.prepareStatement("SELECT * FROM users WHERE registration = ?");
            finduser.setString(1, Registration);

            resultset = finduser.executeQuery();

            if(!resultset.isBeforeFirst()){
                System.out.println("Invalid Registration");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Registration");
                alert.show();
            }
            else {
                while (resultset.next()) {
                    String findreg = resultset.getString("registration");

                    if (findreg.equals(Registration)) {
                        userInsert = connection.prepareStatement("UPDATE users SET sub1Class=?, sub2Class=?, sub3Class=?, sub4Class=?, sub5Class=?, sub1At=?, sub2At=?, sub3At=?, sub4At=?, sub5At=? WHERE registration=?");
                        for(int i=0; i<5; i++){
                            String temp = classTaken[i];
                            userInsert.setString(i+1,temp);
                        }
                        for(int i=5; i<10; i++){
                            String temp1 = classAttended[i-5];
                            userInsert.setString(i+1, temp1);
                        }
                        userInsert.setString(11, Registration);

                        userInsert.executeUpdate();

                        classtaken = classTaken;
                        setClasstaken(classtaken);
                        classattended = classAttended;
                        setClassattended(classattended);

                    }
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (userInsert != null) {
                try {
                    userInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(finduser != null){
                try{
                    finduser.close();
                } catch (SQLException e){
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

    public static void BOOK(ActionEvent event, String[] paths, String Registration){
        Connection connection = null;
        PreparedStatement userInsert = null;
        PreparedStatement finduser = null;
        ResultSet resultset = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/edusphere", "root", "ZisanMahmud@12002060");
            finduser = connection.prepareStatement("SELECT * FROM users WHERE registration = ?");
            finduser.setString(1, Registration);

            resultset = finduser.executeQuery();

            if(!resultset.isBeforeFirst()){
                System.out.println("Invalid Registration");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Registration");
                alert.show();
            }
            else{
                while(resultset.next()){
                    String findreg = resultset.getString("registration");

                    if(findreg.equals(Registration)){
                        userInsert = connection.prepareStatement("UPDATE users SET pdf1=?, pdf2=?, pdf3=?, pdf4=?, pdf5=? WHERE registration=?");

                        for(int i=0; i<5; i++){
                            userInsert.setString(i+1, paths[i]);
                        }

                        userInsert.setString(6, Registration);
                        userInsert.executeUpdate();

                        Paths = paths;
                        setPaths(Paths);
                    }
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (userInsert != null) {
                try {
                    userInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(finduser != null){
                try{
                    finduser.close();
                } catch (SQLException e){
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
