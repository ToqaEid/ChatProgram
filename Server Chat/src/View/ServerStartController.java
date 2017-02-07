/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
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

/**
 *
 * @author mohamed
 */
public class ServerStartController implements Initializable {

    @FXML
    private void handleStartServerAction(ActionEvent event) {
        try {
              new Controller().startserver();
            System.out.println("Openning Server Manager");
            
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
            Parent parent =  FXMLLoader.load(getClass().getResource("/View/ServerHome.fxml"));
           
            Stage stage = new Stage();
            
            stage.setTitle("Server Administrator");
            stage.setScene(new Scene(parent));  
            stage.show();
            
            System.out.println("Server Page is OPENED");
            
        } catch (IOException ex) {
            System.out.println("ERROR:: " + ex.toString()); 
        ex.printStackTrace();
        }
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
