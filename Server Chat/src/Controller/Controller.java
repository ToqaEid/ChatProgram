/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClientServices;
import Model.Model;
import Model.ServerServices;
import View.View;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samir
 */
public class Controller {

    Model theModel;
    View theView;

    //Testing Block
        Vector<ClientServices> clientsVector= new Vector<ClientServices>();
    /**
     * constructor
     */
    public Controller(View theView) {
        this.theView = theView;
        try {
            theModel = new Model(this);
            System.out.println("server ready");
            System.out.println(theModel);
            Registry registry = LocateRegistry.getRegistry();
            //Registry registry = LocateRegistry.createRegistry();
            registry.rebind("chatServices", theModel);
        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Testing Block
        public void addToVector(ClientServices clientRef){
                clientsVector.add(clientRef);
        }
    //End of Testing Block
    
    public static void main(String[] args) {
   
        View.launch(View.class, args);

    }

}
