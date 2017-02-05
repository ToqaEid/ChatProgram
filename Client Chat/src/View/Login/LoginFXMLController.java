/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Login;

import View.Information;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samir
 */
public class LoginFXMLController extends View.Information implements Initializable, View.UserRegInt {

    @FXML
    private TextField uEmailField;

    @FXML
    private TextField uPasswordField;
    @FXML
    private Label errorEmailLabel;
    @FXML
    private Label errorPasswordLabel;
    Information info = new Information();

    @FXML
    private void loginButtonAction(ActionEvent event) {
        // JOptionPane.showMessageDialog(null, event);
        ArrayList<TextField> inputFields = getInputFieldsData();
        ArrayList<Label> errorMsgLabelRef = getErrorMsgLabelRef();
        for (int i = 0; i < inputFields.size(); i++) {
            boolean validateResult = info.validateField(inputFields.get(i), errorMsgLabelRef.get(i), "*");
            if (!validateResult) {
                return;
            }
        }
         try {
        
             System.out.println("You are going to homePage");
          
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
            Parent parent =  FXMLLoader.load(getClass().getResource("/View/ClientHomeView_2.fxml"));
           
            Stage stage = new Stage();
            stage.setTitle("SitChat");
            stage.setScene(new Scene(parent));  
            stage.show();
            
            System.out.println("Showing Main \"Home\" Page");
            
        } catch (IOException ex) {
            System.out.println("ERROR:: " + ex.toString()); 
        }
    }

    @FXML
    private void goToSignUpPage(ActionEvent event) {

        //System.out.println("Going to login page ... ");
        try {

            System.out.println("Going to SignUp page ... ");

            ((Node) (event.getSource())).getScene().getWindow().hide();

            Parent parent = FXMLLoader.load(getClass().getResource("/View/signupFXML.fxml"));

            Stage stage = new Stage();
            stage.setTitle("SignUp");
            stage.setScene(new Scene(parent));
            stage.show();

            System.out.println("Showing Signup Page");

        } catch (IOException ex) {
            System.out.println("ERROR:: " + ex.toString());
        }

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public ArrayList<TextField> getInputFieldsData() {
        ArrayList<TextField> inputFields = new ArrayList<>();
        inputFields.add(uEmailField);
        inputFields.add(uPasswordField);
        return inputFields;

    }

    @Override
    public ArrayList<Label> getErrorMsgLabelRef() {
        ArrayList<Label> errorLabels = new ArrayList<>();
        errorLabels.add(errorEmailLabel);
        errorLabels.add(errorPasswordLabel);
        return errorLabels;

    }

}
