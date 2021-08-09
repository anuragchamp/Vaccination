package Controllers;

import Entity.User;
import Entity.VaccineInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import utils.ConnectionToDataBase;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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

    @FXML
    public DatePicker datePicker;

    @FXML
    public Label errortext;

    @FXML
    public Button btn;

    User loggedUser;

    public void setUserDetails(User user){

        loggedUser = new User();
        loggedUser.setId(user.getId());
        loggedUser.setFirstname(user.getFirstname());
        loggedUser.setLastname(user.getLastname());
        loggedUser.setEmail(user.getEmail());
        loggedUser.setPhonenumber(user.getPhonenumber());

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


    public void setVaccineInfo(List<VaccineInfo> listOfInfo){
        if(listOfInfo.size() == 0){
            Label lb = new Label();
            lb.setText("No dose applied for this user yet!!!");
            child.getChildren().add(lb);
            lb.setLayoutX(66);
            lb.setLayoutY(333);
        }
        else{
           for(int i=0;i<listOfInfo.size();i++){
               VaccineInfo info = listOfInfo.get(i);
               Label lb = new Label();
               lb.setText(info.getTimestamp()+"  "+info.getDoseName() +" "+info.getDoseNo());
               child.getChildren().add(lb);
               if(i == 0){
                   lb.setLayoutX(66);
                   lb.setLayoutY(333);
               }
               else if(i == 1){
                   lb.setLayoutX(66);
                   lb.setLayoutY(360);
               }

           }

        }



    }



    @FXML
    public void cancelAppoinment(){

    }


    @FXML
    public void scheduling(){




        //validation for noofdoses
        String doseNo = noOfDoses.getSelectionModel().getSelectedItem();
        if(doseNo.trim().equals("select dose number")){
            errortext.setText("Please choose valid dose number");
            return;
        }

        ConnectionToDataBase cDb = new ConnectionToDataBase();
        cDb.setConnection();
        List<VaccineInfo> vaccineInfo = cDb.getVaccineInfo(loggedUser.getId());

        System.out.println(vaccineInfo);

        if(vaccineInfo.size() == 0){
            if(doseNo.trim().equals("Dose - 2")){
                errortext.setText("1st Dose is not applied so, please select Dose -1");
                return;
            }
        }
        else if(vaccineInfo.size() == 1){
            if(doseNo.trim().equals("Dose - 1")){
                errortext.setText("1st Dose is already appilied so, please select Dose - 2");
                return;
            }
        }
        else if(vaccineInfo.size() == 2){
          btn.setDisable(true);
        }

        String vaccineNameStr = vaccineName.getSelectionModel().getSelectedItem();
        if(vaccineNameStr.equals("select Vaccine")){
            errortext.setText("please select vaccine");
            return;
        }


        if(datePicker.getEditor().getText().trim().equals("")){
            errortext.setText("pleaes select date !!!!!");
            return;
        }

        String dateSelectedByUser =  datePicker.getEditor().getText();
        System.out.println(dateSelectedByUser);

        String[] splitDate = dateSelectedByUser.split("/");

        Set<Integer> divisibleNumber = new HashSet<>();

        int date =Integer.parseInt(splitDate[1]);

        for(int i=2; i < date / 2; i++){
            if(date % i == 0){
                divisibleNumber.add(i);
            }
        }





        System.out.println(divisibleNumber);








    }

    @FXML
    public void reScheduling(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        errortext.setText("");
        vaccineName.setValue("select Vaccine");
        vaccineName.getItems().add("Moderna");
        vaccineName.getItems().add("Pfizer");


        noOfDoses.setValue("select dose number");
        noOfDoses.getItems().add("Dose - 1");
        noOfDoses.getItems().add("Dose - 2");

    }
}
