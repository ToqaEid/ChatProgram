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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author mohamed
 */
public class loginFXMLController implements Initializable {
    
    
    @FXML
   private TextField uEmailField;
   
   @FXML
   private TextField uPasswordField;
   
   @FXML
   private Label errorEmailLabel;
   @FXML
   private Label errorPasswordLabel;
   
    
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
    
   
    
    //////////// on Login button click listener
    @FXML
    private void handleLoginAction(ActionEvent event) {
        
        ArrayList<TextField> inputFields = new ArrayList<>();
        inputFields.add(uEmailField);
        inputFields.add(uPasswordField);
        
        ArrayList<Label> errorLabels = new ArrayList<>();
        errorLabels.add(errorEmailLabel);
        errorLabels.add(errorPasswordLabel);
        
        for(int i=0; i<inputFields.size(); i++ )
        {
            boolean validateResult =  validateFields( inputFields.get(i), errorLabels.get(i),"*");
            if( ! validateResult ) 
                return;
        }
        
   
        
        //System.out.println("Logging in ... please wait!");
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
