/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DataTransferObject.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author toqae
 */
public interface ClientServices extends Remote{
    public void showAddFriendRequest() throws RemoteException;
    public void changeStatusIcon(User user) throws RemoteException;
    public void receiveMsg(UserMsg userMsg)throws RemoteException;
    
    
}
