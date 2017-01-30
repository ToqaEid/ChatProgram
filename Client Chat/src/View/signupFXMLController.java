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
public class signupFXMLController implements Initializable {
    
    
     //////////// on Signup button click listener
    @FXML
    private void handleSignUpAction(ActionEvent event) {
          
        System.out.println("You are signing up");
        
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
