package Controllers;

import Entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import utils.ConnectionToDataBase;

import java.net.URL;
import java.util.ResourceBundle;


public class RegisterPage implements Initializable {


    @FXML
    public Pane parent;

    @FXML
    public Pane child;

    @FXML
    public TextField firstname;

    @FXML
    public TextField  lastname;

    @FXML
    public TextField email;

    @FXML
    public PasswordField password;

    @FXML
    public TextField phonenumber;

    @FXML
    public PasswordField confirmPassword;


    @FXML
    public Label errorLabel;

    @FXML
    public Hyperlink loginLink;


    @FXML
    public void registerUser() throws Exception{
        String userFirstname =  firstname.getText();
        String userLastname = lastname.getText();
        long phoneNumber =  Long.parseLong(phonenumber.getText());
        String userEmail = email.getText();
        String userPassword = password.getText();
        String userConfirmPassword = confirmPassword.getText();

        if(userConfirmPassword.equals(userPassword)){
            //register

            User user = new User();
            user.setEmail(userEmail);
            user.setFirstname(userFirstname);
            user.setLastname(userLastname);
            user.setPassword(userPassword);
            user.setPhonenumber(phoneNumber);
            System.out.println(user);

            ConnectionToDataBase cDb = new ConnectionToDataBase();
            cDb.setConnection();
           int i =  cDb.insertUser(user);

           if(i > 0){

              changeToLoginAfterRegisterSuccesfully();
           }
           else{
               errorLabel.setText("Error !! Please try again");
           }

        }
        else{
            errorLabel.setText("Password and confirmed password not matched !! Please try again");
        }

    }

    public void changeToLoginAfterRegisterSuccesfully() {

        try{
           parent.getChildren().remove(child);

            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("../Views/sample.fxml"));

            parent.getChildren().add(newLoadedPane);
            System.out.println(newLoadedPane);
        }
        catch (Exception e){
            System.out.println(e);
        }

      //  parent.getChildren().add(newLoadedPane);

    }

    @FXML
    public void goToLoginPage(){
        changeToLoginAfterRegisterSuccesfully();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // changeToLoginAfterRegisterSuccesfully();
    }
}
