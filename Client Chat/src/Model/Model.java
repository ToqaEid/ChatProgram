/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Controller;
import DataTransferObject.User;
import DataTransferObject.UserMsg;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author toqae
 */
public class Model extends UnicastRemoteObject implements ClientServices{
    private Controller controller;
    
    public Model(Controller controller) throws RemoteException
    {
       this.controller =controller;
    }
    
    @Override
    public void showAddFriendRequest() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeStatusIcon(User user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receiveMsg(UserMsg userMsg) throws RemoteException {
        controller.displayMsg(userMsg);
    }
    
}
