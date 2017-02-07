/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author toqae
 */
public class ClientChat extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login/LoginFXML.fxml"));
        Scene scene = new Scene(root);
        File f = new File("src/View/loginStyle.css");
        if(f.exists()){
            System.out.println("exsits");
        }
        scene.getStylesheets().clear();
       // scene.getStylesheets().add(getClass().getResource("src/View/loginStyle.css").toExternalForm());

       // scene.getStylesheets().add("file:///"+"C:/Users/Israa/Documents/GitHub/ChatProgram/Client Chat/src/View/Login/loginStyle.css");
        stage.setTitle("S7S el3alamy");
        stage.getIcons().add(new Image("/images/user.png"));
        
        
        stage.setScene(scene);
        stage.show();
        System.out.println("sd");
    }

    /**
     * @param args the command line arguments
     */
   /* public static void main(String[] args) {
        launch(args);
    }
    */
}
