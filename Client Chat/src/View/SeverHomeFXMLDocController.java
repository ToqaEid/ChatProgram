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
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Israa
 */
public class SeverHomeFXMLDocController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private BorderPane rootPane;
   // @FXML
   // private SplitPane splitPane;
    
   
    
      @FXML
    private void stopServerButton(ActionEvent event) {
       System.out.println("Checking out server services ... ");
        
        try {
        
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
            Parent parent =  FXMLLoader.load(getClass().getResource("/View/serverStartFXML.fxml"));
           
            Stage stage = new Stage();
            stage.setTitle("Server Admin.");
            stage.setScene(new Scene(parent));  
            stage.show();
            
        } catch (IOException ex) {
            System.out.println("ERROR:: " + ex.toString()); 
        }
        
        
    }
  
    
    
    @FXML
    private void handleAnnounceButtonAction(ActionEvent event) throws IOException {
           
            BorderPane announcePane=new BorderPane();
            TextArea txtArea=new TextArea();
            Button sendBtn =new Button("Send");
            announcePane.setCenter(txtArea);
            Button emojiBtn=new Button("emotions");
            Button imgBtn=new Button("Image");
            //SplitPane splitPane=new SplitPane();
            //splitPane.setOrientation(Orientation.VERTICAL);
            VBox vbox = new VBox(2);
            SplitPane editPane =new SplitPane(emojiBtn,imgBtn);
            vbox.getChildren().addAll(editPane,sendBtn);
            vbox.setAlignment(Pos.BOTTOM_RIGHT);
            //splitPane.getItems().addAll(editPane,sendBtn);
            announcePane.setBottom(vbox);
            rootPane.setCenter(announcePane);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
