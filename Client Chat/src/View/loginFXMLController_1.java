/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author mohamed
 */
public class loginFXMLController_1 implements Initializable {
    
    @FXML
    private Label label;
    
    
    //////////// on Login button click listener
    @FXML
    private void handleLoginAction(ActionEvent event) {
        
        //System.out.println("Logging in ... please wait!");
        try {
        
             System.out.println("You are going to homePage");
          
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
            Parent parent =  FXMLLoader.load(getClass().getResource("/View/homeFXML.fxml"));
           
            Stage stage = new Stage();
            stage.setTitle("SitChat");
            stage.setScene(new Scene(parent));  
            stage.show();
            
            System.out.println("Showing Main \"Home\" Page");
            
        } catch (IOException ex) {
            System.out.println("ERROR:: " + ex.toString()); 
        }
        
    
    }
    
   
     //////////// on GoTo Signup link click listener
    @FXML
    private void handleGoToSignupAction(ActionEvent event) {
          
       // System.out.println("Going to Signup page ... ");
          try {
        
            System.out.println("Going to Signup page ... ");
            
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
            Parent parent =  FXMLLoader.load(getClass().getResource("/View/signupFXML.fxml"));
           
            Stage stage = new Stage();
            stage.setTitle("SignUp");
            stage.setScene(new Scene(parent));  
            stage.show();
            
            System.out.println("Showing SignUp Page");
            
        } catch (IOException ex) {
            System.out.println("ERROR:: " + ex.toString()); 
        }
        
      
    }
  
    
     //////////// on Forgot Password link click listener
    @FXML
    private void handleForgotPasswordAction(ActionEvent event) {
          
        System.out.println("Forgot Password ... ");
         try {
        
            System.out.println("Going to ServerStart... ");
            
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
            Parent parent =  FXMLLoader.load(getClass().getResource("/View/serverStartFXML.fxml"));
           
            Stage stage = new Stage();
            stage.setTitle("Start Server!");
            stage.setScene(new Scene(parent));  
            stage.show();
            
            System.out.println("Showing StartingServer Page");
            
        } catch (IOException ex) {
            System.out.println("ERROR:: " + ex.toString()); 
        }
        
    }
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
