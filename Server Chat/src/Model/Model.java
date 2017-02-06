/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import DataTransferObject.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Samir
 */
public class Model extends UnicastRemoteObject implements ServerServices{

    DatabaseHandler1 databaseHandler;
    
    HashMap<String, ClientServices> onlineUsers;
    
    public Model() throws RemoteException
    {
        onlineUsers = new HashMap<String, ClientServices>();
    }
    
    
    /////////////// SignUp Method()
    @Override
    public boolean signUp(User user,ClientServices clientServices)throws RemoteException {
    
        ////// 1. chek if a user exists with the same email
        
        boolean exists = databaseHandler.checkUserExistance(user.getUserEmail());
        
        if (exists)
        {
            //////// User Already Exists with this email  
            return false; 
        }
       
        ///// 2. if no user exists >>> A. then add new user
        else
        {
            boolean inserted = databaseHandler.insertNewUser(user); 
            if (inserted)
            {
                //////////// add  to Online Users Vector
                
                onlineUsers.put(user.getUserEmail(), clientServices);
                
                return true;    /////// user inserted successfully
            }
            else
            {
                return false;   ///////// db internal error
            }
            
        }
        
    }

    
    /////////////// Login Method()
    @Override
    public User signIn(User user, ClientServices clientServices) throws RemoteException
    {
    
         ///////// 1. check user existence
         
         boolean exists = databaseHandler.checkUserExistance(user.getUserEmail());
         if( ! exists )
         {
             return null;
         }
         
         //////// 2. verify password and email
    
         else
         {
            User u = databaseHandler.verifyPassword(user.getUserEmail(), user.getUserPassword());
             if (u != null)
             {
                 //////////// add  to Online Users Vector
                onlineUsers.put(user.getUserEmail(), clientServices);
                 return u;
             }
             else
                 return null;
         }
         
         
    }
    
    
    ///////////// add new friend  Method()
    @Override
    public int addFriend ( ContactList contact )throws RemoteException
    {
        /////////// 1. check friend existence in s7s
        
        boolean exists = databaseHandler.checkUserExistance(contact.getFriend().getUserEmail());
        if (! exists)
        {
            //return "This user is NOT a member is s7s";
            return -1;
        }
        ////////// 2. check friendShip between them
        else
        {
            boolean isFriend = databaseHandler.checkingFriendship(contact.getUser().getUserEmail(), contact.getFriend().getUserEmail());
            if ( isFriend )
            {
                //return "You're Already Friends!";
                return 0;
            }
            ////////// 3. add new friend in db
            else
            {
                User user = new User(contact.getUser().getUserEmail(), "", "", "", "", "");
                User friend = new User(contact.getFriend().getUserEmail(), "", "", "", "", "");
                
                boolean inserted = databaseHandler.insertFriend(new ContactList(user, friend, "", "Friends", "No"));
                if (inserted)
                    //return "You are Now Friends";
                    return 1;
                else
                    //return "DB_ERROR";
                    return -2;
            }
        
        }
        
    }
    
    

    @Override
    public void changeUserStatus(User user, String status, ArrayList<ContactList> contacts) throws RemoteException
     {
        databaseHandler.updateStatus(user);
    }

    
    @Override
    public String getUserStatus(String userEmail) throws RemoteException
    {
        String userStatus = databaseHandler.getUserStatus(userEmail);
        return userStatus;
    }

    @Override
    public int getUsersNumber() throws RemoteException
    {
        int numberOfUsers = databaseHandler.getAllUsers();
        return numberOfUsers;
    }
    
    @Override
      public  void tellClient(UserMsg msg) throws RemoteException
       {
           ///////
       }

    @Override
    public ArrayList<ContactList> getContactList(User user) throws RemoteException {
     
         ArrayList<ContactList> friends = databaseHandler.getFriends(user.getUserEmail());
        
         return friends;
    
    }

    @Override
    public ArrayList<UserMsg> getOfflineRequests(User user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registerFriendInContactList(ContactList list) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void signOut(String userEmail) throws RemoteException {
        
        //////////// remove Online Users Vector
           
        onlineUsers.remove(userEmail);
        
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
      
      
}
