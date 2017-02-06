/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import DataTransferObject.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
/**
 *
 * @author Samir
 */
public interface ServerServices extends Remote{
    
    boolean signUp(User newUser, ClientServices clientServices) throws RemoteException;
    
    int addFriend(ContactList list) throws RemoteException;
    
    void changeUserStatus(User user, String status, ArrayList<ContactList> contacts) throws RemoteException;
    
    String getUserStatus(String userEmail)throws RemoteException;;
    
    int getUsersNumber()throws RemoteException;; 
    
    void tellClient(UserMsg msg) throws RemoteException;
    
    User signIn(User user, ClientServices clientServices) throws RemoteException;
   
    
    //////////////////////
    ArrayList<ContactList> getContactList(User user) throws RemoteException;
    
    ArrayList<UserMsg> getOfflineRequests(User user) throws RemoteException;
    
    
    void registerFriendInContactList(ContactList list) throws RemoteException;
    
    void signOut(String userEmail) throws RemoteException;
    
    
    
   
}
