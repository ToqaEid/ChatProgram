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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toqae
 */
public class DatabaseHandler {
    private String url;
    private String username; 
    private String password;
    private Connection con;
   
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
        
        createTables();
    }
    /******End of Constructors*************/
   
   /***************************Private Methods***************************************/ 
    
   /*=========Connection Private Methods==========*/
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
    /*====End of Connection Private Methods====*/
    
    /*=======helpful private methods===========*/
    private void createUserTable(PreparedStatement  pst) throws SQLException{
        String createUserTable = "CREATE TABLE user("
                          + "uemail VARCHAR(25) NOT NULL, "
                          + "uname VARCHAR(25) NOT NULL, "
                          + "upass VARCHAR(25) NOT NULL, "
                          + "gender VARCHAR(20) NOT NULL, " 
                          + "country VARCHAR(25) , " 
                          + "PRIMARY KEY (uemail) "
                          + ")";
      //prepare the query
          pst = con.prepareStatement(createUserTable);

    }
    private void createContactListTable(PreparedStatement  pst) throws SQLException{
        //create create query    
            String createContactListTable = "CREATE TABLE contactlist("
				+ "uemail VARCHAR(25) NOT NULL, "
				+ "femail VARCHAR(25) NOT NULL, "
				+ "ucategory VARCHAR(25) NOT NULL, "
				+ "fcategory VARCHAR(20) NOT NULL, " 
                                + "blocked VARCHAR(20) , " 
                                + "PRIMARY KEY (uemail + femail) "
				+ ")";
        //prepare the query
            pst = con.prepareStatement(createContactListTable);

    }
    private boolean checkUser(User newUser){
        PreparedStatement  pst = null;
        try {
                establishConnection(url, username , password);
            //prepare the query
                pst = con.prepareStatement("select * from user where uemail = ?");  
                pst.setString(1, newUser.getUserEmail());
                
            //execute the query 
                ResultSet queryResult = pst.executeQuery() ; 
            //check queryResult length
                if(queryResult.getRow() != 0 ){ //there is existring user
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
    
    /*====End of helpful private methods===*/   
    
    /**********End of Private Methods*****************/
    
    
    /***************************Public Methods***************************************/
    public void createTables(){
       PreparedStatement  pst = null;
        try {
            //open connections
                establishConnection(url, username , password);
            //create Tables
                createUserTable(pst);
                createContactListTable(pst);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in query");
        }finally{
            //close connection
                closeConnection(pst);
        }
    }
    
    
    public void insertUser(User newUser){
        if(!checkUser(newUser)){    //user does not exist before
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
