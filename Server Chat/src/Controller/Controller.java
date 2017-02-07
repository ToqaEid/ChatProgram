/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Model;
import View.View;
import java.rmi.NotBoundException;
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
    //Registry registry ;
    /**constructor*/
    public Controller(){}
    public Controller(View theView)
    {
        this.theView = theView;
     }
    
    public void startserver()
    {
        try
        {
            System.out.println("server ready");
            theModel = new Model(this);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("chatServices",theModel);	
		
        }
        catch(RemoteException exception)
        {
            System.out.println("error registring server object");
            exception.printStackTrace();
        }
    }
    public void stopServer(){
        try {
            Registry registry = LocateRegistry.getRegistry();
            registry.unbind("chatServices");
            System.out.println("server stoped");
        } catch (RemoteException ex) {
           System.out.println("unable to unbin server services");
           ex.printStackTrace();
        } catch (NotBoundException ex) {
           System.out.println("unable to unbin server services");
           ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        View.launch(View.class,args);
        
    }
    
}

