/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.ClientHomeController_2;
import DataTransferObject.*;
import Model.ClientServices;
import Model.ServerServices;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toqae
 */

public class Controller {
    private User user;
    private ClientHomeController_2 homeView;
    private ClientServices service;
    private static Registry registry;
    private static ServerServices servicesRef;
    
    public Controller(){
        try {
            //lookup on registry for server services 
            registry = LocateRegistry.getRegistry();
            servicesRef =(ServerServices) registry.lookup("chatServices");
            if(servicesRef != null)
                System.out.println("register");
            else
                   System.out.println("unregister");
            
        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Controller(ClientHomeController_2 view) {
        this.homeView = view;
        
    }
    public void sendMsg(UserMsg msg){
        try {
            if(servicesRef.tellClient(msg)){
                System.out.println("sdfg");
            }else{
                System.out.println("asd");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static void main(String args[]){
        ///Controller controller = new Controller();
        try {
            //lookup on registry for server services 
            System.setProperty("java.rmi.server.hostname","192.168.1.2");
            registry = LocateRegistry.getRegistry(1099);
            servicesRef =(ServerServices) registry.lookup("chatServices");
            if(servicesRef != null)
                System.out.println("register");
            else
                   System.out.println("unregister");
            
        } catch (RemoteException ex) {
            System.out.println("remote");
            ex.printStackTrace();
        } catch (NotBoundException ex) {
            System.out.println("not");
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Application.launch(ClientChat.class,args);
    }
    
}
