/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Model;
import Model.ServerServices;
import View.ServerChat;
import View.View;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Samir
 */
public class Controller {

    Model theModel;
    View theView;

    /**
     * constructor
     */
    public Controller(View theView) {
        this.theView = theView;
        try {
            System.out.println("server ready");
            ServerServices serverObject = new Model();
            //Registry registry = LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry(1099);
            //System.setProperty("java.rmi.server.hostname","192.168.1.2");
            registry.rebind("chatServices", serverObject);

        } catch (RemoteException exception) {
            System.out.println("error registring server object");
            exception.printStackTrace();
        }

    }

    public static void main(String[] args) {

        View.launch(View.class, args);

    }

}