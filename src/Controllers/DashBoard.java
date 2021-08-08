package Controllers;

import Entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DashBoard {


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

    public void setUserDetails(User user){

        phonenumber.setText(String.valueOf(user.getPhonenumber()));
        lastname.setText(user.getLastname());
        firstname.setText(user.getFirstname());
        email.setText(user.getEmail());

    }


    @FXML
    public void logout(){



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



}
