/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.*;
import DataTransferObject.*;
import Model.ClientServices;
import Model.Model;
import Model.ServerServices;
import View.ClientChat;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.control.TextArea;

/**
 *
 * @author toqae
 */
public class Controller {

    private User user;
    //Views & model references 
        private ClientHomeController_2 homeView;
        private signupFXMLController signupView;
        private Model model;
    //Services references    
        private ClientServices service;
        private static Registry registry;
        private static ServerServices servicesRef;
    
    /***
     *Default Constructor will be used once to lookup for server services in registry
     ***/
    public Controller() {
        try {
            
            //lookup on registry for server services 
            //System.setProperty("java.rmi.server.hostname", "192.168.1.2");
            registry = LocateRegistry.getRegistry(1099);
            servicesRef = (ServerServices) registry.lookup("chatServices");
            if (servicesRef != null) {
                System.out.println("register");
                //System.out.println(servicesRef.tellClient(new UserMsg(new User("s"), new User("r"), "d")));
            } else {
                System.out.println("unregister");
            }


        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Controller(ClientHomeController_2 view) {
        this.homeView = view;
    }
    public Controller(signupFXMLController view) {
        this.signupView = view;
    }
    //Testing Block
        public void signUpUser(){
        try {
            servicesRef.signUp(model, user);
        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    //End of Testing Block
    public void sendMsg(String msg, String uemail) {
        //testing block
            user = new User(); 
            user.setUserEmail("g@gmail.com");
        //end of testing block
        
        //1.form the object to send to server
            UserMsg userMsg = new UserMsg(new User(uemail), user, msg);
        
        //2.call tellClient in server that will send msg to other client
            try {
                servicesRef.tellClient(userMsg);
            } catch (RemoteException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void displayMsg(UserMsg userMsg){
        homeView.display(userMsg.getSender().getUserEmail(), userMsg.getMsg());
    }

    public static void main(String args[]) {
        Controller controller = new Controller();
        
        Application.launch(ClientChat.class, args);
    }

}
