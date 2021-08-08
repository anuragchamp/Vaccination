package Controllers;

import Entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBoard implements Initializable {


    @FXML
    Pane parent;

    @FXML
    Pane child;

    @FXML
    public Label firstname;
    @FXML
    public Label lastname;

    @FXML
    public Label email;


    @FXML
    public Label phonenumber;


    @FXML
    public ComboBox<String>  vaccineName;


    @FXML
    public ComboBox<String>  noOfDoses;

    public void setUserDetails(User user){

        phonenumber.setText(String.valueOf(user.getPhonenumber()));
        lastname.setText(user.getLastname());
        firstname.setText(user.getFirstname());
        email.setText(user.getEmail());

    }


    @FXML
    public void logout() throws  Exception{

        parent.getChildren().remove(child);
       Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("../Views/sample.fxml"));
        parent.getChildren().add(newLoadedPane);


    }

    @FXML
    public void cancelAppoinment(){

    }


    @FXML
    public void scheduling(){

    }

    @FXML
    public void reScheduling(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vaccineName.setValue("select Vaccine");
        vaccineName.getItems().add("Moderna");
        vaccineName.getItems().add("Pfizer");


        noOfDoses.setValue("select dose number");
        noOfDoses.getItems().add("Dose - 1");
        noOfDoses.getItems().add("Dose - 2");

    }
}
