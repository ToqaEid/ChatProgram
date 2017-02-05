/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Login;

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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samir
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private void loginButtonAction(ActionEvent event)
    {
        JOptionPane.showMessageDialog(null, event);
    }
   
     @FXML
    private void goToSignUpPage(ActionEvent event)
    {
        
        
        //System.out.println("Going to login page ... ");
         try {
        
            System.out.println("Going to SignUp page ... ");
          
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
            Parent parent =  FXMLLoader.load(getClass().getResource("/View/signupFXML.fxml"));
           
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
