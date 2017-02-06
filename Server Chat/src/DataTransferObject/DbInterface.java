/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransferObject;

import DataTransferObject.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mohamed
 */
public interface DbInterface {
    
    boolean insertNewUser(User user);
    boolean insertFriend(ContactList contactList);
    boolean checkUserExistance(String userEmail);
    User verifyPassword(String userEmail, String userPassword);
    boolean checkingFriendship(String userEmail,String friendEmail);
    boolean setFriendCategory(ContactList contactList) ;
    boolean blockUser(ContactList contactList);
    String getUserStatus(String userEmail);
    boolean updateStatus(User user);
    int getOnlineUsers();
    int getOfflineUsers();
    ArrayList getFriends (String userEmail); 
    
}
