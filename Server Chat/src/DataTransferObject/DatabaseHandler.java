/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransferObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author toqae
 */
public class DatabaseHandler {
    private String url;
    private String username; 
    private String password;
    private Connection con;
    
   /***************************Connection Private Methods***************************************/
    private void establishConnection(String url, String username , String password){
        try {
            //1.load and register the db driver
                DriverManager.registerDriver(new OracleDriver());
            //2. establish connection 
                con = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println("Connection is not established");
            ex.printStackTrace();
        }      
    }
     
    private void closeConnection (PreparedStatement pst){
        try {
            pst.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Connection is not Closed");
            ex.printStackTrace();
        }
     }
    /******End of Connection Private Methods*************/
    
    /***************************Constructors***************************************/
    public DatabaseHandler(){
        this.url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
        this.username = "system";
        this.password = "root";
    }
    public DatabaseHandler(String url, String username , String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }
    /******End of Constructors*************/
    
    /***************************Public Methods***************************************/
    public void insertUser(User newUser){
        PreparedStatement  pst = null;
        try {
                establishConnection(url, username , password);
            //prepare the query
                pst = con.prepareStatement("INSERT INTO user(uemail, uname, upass, gender, country) VALUES (?, ?, ?, ?,?)");
                pst.setString(1, newUser.getUserEmail());
                pst.setString(2, newUser.getUserNickName());
                pst.setString(3, newUser.getUserPassword());
                pst.setString(4, newUser.getUserGender());
                pst.setString(5, newUser.getUserCountry());

            //execute the query 
                int queryResult = pst.executeUpdate() ;
                System.out.println(queryResult);
                
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in query");
        }finally{
            //close connection
                closeConnection(pst);
        }
    
    }
    
    private boolean checkExistFriend(User FriendUser){
        PreparedStatement  pst = null;
        try {
                establishConnection(url, username , password);
            //prepare the query
                pst = con.prepareStatement("select * from contactlist where uemail = ? OR femail = ?");  
                pst.setString(2, FriendUser.getUserNickName());
                pst.setString(3, FriendUser.getUserGender());
                
            //execute the query 
                ResultSet queryResult = pst.executeQuery() ; 
            //check queryResult length
                if(queryResult.getRow() != 0 ){ //there is existring user
                    closeConnection(pst);
                    return true; //exists
                }
                
                
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            //close connection
                closeConnection(pst);
                return false;
        }
        
    }
    
    public void insertFriend(User user, User friend){
        PreparedStatement  pst = null;
        try {
                establishConnection(url, username , password);
            //prepare the query
                pst = con.prepareStatement("INSERT INTO contactList(uemail, femail) VALUES (?, ?)");
                pst.setString(1, user.getUserEmail());
                pst.setString(2, friend.getUserEmail());

            //execute the query 
                int queryResult = pst.executeUpdate() ;
                System.out.println(queryResult);
                
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in query");
        }finally{
            //close connection
                closeConnection(pst);
        }
    
    }
    
    public void setContactListCategory(ContactList contactList){
        PreparedStatement  pst = null;
        try {
                establishConnection(url, username , password);
            //prepare the query
                pst = con.prepareStatement("UPDATE contactlist SET ucategory=?, fcategory=? WHERE uemail=?, femail=?");
                pst.setString(1, contactList.getUserCategory());
                pst.setString(2, contactList.getFriendCategory());
                pst.setString(3, contactList.getUser().getUserEmail());
                pst.setString(4, contactList.getFriend().getUserEmail());
                
            //execute the query 
                int queryResult = pst.executeUpdate() ;
                System.out.println(queryResult);
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            //close connection
                closeConnection(pst);
        }
    }
    
    public void blockUser(ContactList contactList){
        PreparedStatement  pst = null;
        try {
                establishConnection(url, username , password);
            //prepare the query
                pst = con.prepareStatement("UPDATE contactlist SET blocked=? WHERE uemail=?, femail=?");
                pst.setString(1, contactList.getIsBlocked());
                pst.setString(2, contactList.getUser().getUserEmail());
                pst.setString(3, contactList.getFriend().getUserEmail());
                
            //execute the query 
                int queryResult = pst.executeUpdate() ;
                System.out.println(queryResult);
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            //close connection
                closeConnection(pst);
        }
    }
    
    //public void getUserStatus
    
    public void updateStatus(User user){
        PreparedStatement  pst = null;
        try {
                establishConnection(url, username , password);
            //prepare the query
                pst = con.prepareStatement("UPDATE user SET status=? WHERE uemail=?");
                pst.setString(1, user.getUserStatus());
                pst.setString(2, user.getUserEmail());              

            //execute the query 
                int queryResult = pst.executeUpdate() ;
                System.out.println(queryResult);
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            //close connection
                closeConnection(pst);
        }
    }
    /******End of Public Methods*************/
}
