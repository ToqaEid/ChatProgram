/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 *
 * @author mohamed
 */
public class signupFXMLController implements Initializable {
    
   @FXML
   private TextField fullNameField;
   
   @FXML
   private TextField emailField;
   
   @FXML
   private TextField passwordField;
   
   @FXML
   private TextField confirmPasswordField;
   
   @FXML
   private TextField countryField;
   
   @FXML
   private Label errorMsgLabel;
   @FXML
   private Label errorName;
   @FXML
   private Label errorEmail;
   @FXML
   private Label errorPassword;
   @FXML
   private Label errorCoPassword;
   @FXML
   private Label errorCountry;
   
    public Boolean validateFields (TextField field , Label error, String msg)
    {
        
            String str = field.getText().trim();
            if( str.length() == 0 )
            {
                error.setText(msg);
                return false;
            }
        
            error.setText("");
                
        return true;
    }
    
    
     //////////// on Signup button click listener
    @FXML
    private void handleSignUpAction(ActionEvent event) {
        
        
        ArrayList<TextField> inputFields = new ArrayList<>();
        inputFields.add(fullNameField);
        inputFields.add(emailField);
        inputFields.add(passwordField);
        inputFields.add(confirmPasswordField);
        inputFields.add(countryField);
        
        ArrayList<Label> errorLabels = new ArrayList<>();
        errorLabels.add(errorName);
        errorLabels.add(errorEmail);
        errorLabels.add(errorPassword);
        errorLabels.add(errorCoPassword);
        errorLabels.add(errorCountry);
        
        for(int i=0; i<inputFields.size(); i++ )
        {
            boolean validateResult =  validateFields( inputFields.get(i), errorLabels.get(i),"*");
            if( ! validateResult ) 
                return;
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
            Node n = parent.lookup("vds");
           
        } catch (IOException ex) {
            System.out.println("ERROR:: " + ex.toString()); 
        }
        
    }
    
    
     //////////// on GoTo Login button click listener
    @FXML
    private void handleGoToLoginAction(ActionEvent event) {
          
        //System.out.println("Going to login page ... ");
         try {
        
            System.out.println("Going to login page ... ");
          
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
            Parent parent =  FXMLLoader.load(getClass().getResource("/View/loginFXML.fxml"));
           
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(parent));  
            stage.show();
            
            System.out.println("Showing Login Page");
            
        } catch (IOException ex) {
            System.out.println("ERROR:: " + ex.toString()); 
        }
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    
}
