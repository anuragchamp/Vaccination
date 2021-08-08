package Controllers;

import Entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.ConnectionToDataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    public Pane parent;


    @FXML
    public Pane LoginPage;


    @FXML
    public ImageView image;


    @FXML
    public Label errorText;


    @FXML
    public TextField userEmail;


    @FXML
    public PasswordField userPassword;


    @FXML
    public Hyperlink linkToRegisterPage;


    Pane newLoadedPane;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try{
            //need to change the name
            InputStream stream = new FileInputStream("C:\\Users\\Anurag chhaperwal\\OneDrive\\Desktop\\Project03\\src\\images\\GettyImages-1263990592_1350.jpg");
            Image imageImage = new Image(stream);
            //Creating the image view
            //Setting image to the image view
            image.setImage(imageImage);
            System.out.println("image");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }



@FXML
public void doLogin(ActionEvent event) throws Exception{

        String email = userEmail.getText();
        String password = userPassword.getText();

        User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        ConnectionToDataBase cDb = new ConnectionToDataBase();
        cDb.setConnection();
         User us = cDb.getUser(user);
         if(us != null) {

             Node node = (Node) event.getSource();
             Stage stage = (Stage) node.getScene().getWindow();
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DashBoard.fxml"));
             Parent root = loader.load();
            DashBoard  obj =   loader.getController();
            us.setPassword(null);
             obj.setUserDetails(us);
             Scene scene = new Scene(root);
             stage.setScene(scene);
             stage.show();

             System.out.println(obj);

         }

         else{
             errorText.setText("Invalid username and password");
         }


    }

    @FXML
    public void loadRegisterPage() throws IOException {
       // Parent root = FXMLLoader.load(getClass().getResource("../View/RegisterPage.fxml"));

        parent.getChildren().remove(LoginPage);
        newLoadedPane =  FXMLLoader.load(getClass().getResource("../Views/RegisterPage.fxml"));
        parent.getChildren().add(newLoadedPane);
    }



}
