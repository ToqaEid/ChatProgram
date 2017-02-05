/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTransferObject;

import DataTransferObject.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author samir
 */
public class DatabaseHandler1  implements DbInterface{

    private String url;
    private String username;
    private String password;
    
    private Connection connection;
    private PreparedStatement preparedStatement;
    
    ////////////////////////// Db Constructors
    
    public DatabaseHandler1() {
        this.url = "jdbc:mysql://localhost:3306/mysql";
        this.username = "root";
        this.password = "";
    }

    public DatabaseHandler1(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    ////////////////// Private start and end DbConnection methods
    
    private void establishConnection(String url, String username, String password) {

        try {
            
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established");
        
        } catch (SQLException ex) {
        
            System.err.println("ERROR:: testdb.DatabaseHandler.establishConnection() " + ex.toString() );
            
        }
    }

    private void closeConnection() {

        try {
                preparedStatement.close();
                connection.close();
                System.out.println("Connection Closed");
        } catch (SQLException ex) {
            
            System.out.println("Connection is not Closed");

        }
    }

  
    /////////////// creating db tables
    
    public void createTables() {
        try {
            establishConnection(url, username, password);
 
            ///// 1. creating USER table
            String createUserTable = "CREATE TABLE IF NOT EXISTS mydb.`USER` (\n"
                    + "  `EMAIL` VARCHAR(60) NOT NULL,\n"
                    + "  `userName` VARCHAR(45) NOT NULL,\n"
                    + "  `password` VARCHAR(16) NOT NULL,\n"
                    + "  `gender` VARCHAR(5) NULL,\n"
                    + "  `country` VARCHAR(8) NULL,\n"
                    + "  `status` VARCHAR(20) NULL,\n"
                    + "  PRIMARY KEY (`EMAIL`))";
 
            preparedStatement = connection.prepareStatement(createUserTable);
            preparedStatement.executeUpdate();
            System.out.println("Table USER created successfully");
            
            ///// 2. creating CONTACT table
            String createContactListTable = "CREATE TABLE IF NOT EXISTS mydb.`CONTACTS` ("
                    + "  `USER_EMAIL` VARCHAR(60) NOT NULL,"
                    + "  `friendEmail` VARCHAR(60) NOT NULL,"
                    + "  `userCategory` VARCHAR(45) NULL,"
                    + "  `friendCategory` VARCHAR(45) NULL,"
                    + "  `blocked` VARCHAR(25) NULL,"
                    + "  PRIMARY KEY (`USER_EMAIL`, `friendEmail`),"
                    + "  INDEX `fk_USER_has_USER_USER1_idx` (`friendEmail` ASC),"
                    + "  INDEX `fk_USER_has_USER_USER_idx` (`USER_EMAIL` ASC),"
                    + "  CONSTRAINT `fk_USER_has_USER_USER` "
                    + "    FOREIGN KEY (`USER_EMAIL`) "
                    + "    REFERENCES `mydb`.`USER` (`EMAIL`) "
                    + "    ON DELETE NO ACTION "
                    + "    ON UPDATE NO ACTION,"
                    + "  CONSTRAINT `fk_USER_has_USER_USER1` "
                    + "    FOREIGN KEY (`friendEmail`) "
                    + "    REFERENCES `mydb`.`USER` (`EMAIL`) "
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION)";
            
            preparedStatement = connection.prepareStatement(createContactListTable);
            preparedStatement.executeUpdate();
            
            System.out.println("Tables CONTACT created successfully");
            
        } catch (SQLException ex) {
            
            System.err.println("ERROR:: testdb.DatabaseHandler.createTables()" + ex.toString());
            
        } finally {            
            //close connection
            closeConnection();
        }
    }

    /////////////////////// db processing functions   //////////////////////////
    
    ///////// checking user existence "Is he a S7S member?"
    public boolean checkUserExistance(String userEmail) {
        
        try {

            establishConnection(url, username, password);
        
            preparedStatement = connection.prepareStatement("select email from mydb.user where email = ?");
            preparedStatement.setString(1, userEmail);
            
            ResultSet result = preparedStatement.executeQuery();
            
            if (!result.next()) {
                
                System.out.println("User doeas not exist!");
                return false;       
            }
            
            closeConnection();
        } catch (SQLException ex) {
            
            System.err.println("ERROR is :: testdb.DatabaseHandler.checkUserExistance()" + ex.toString());
        }   
        
        System.out.println("User exists.");
        return true;       

    }

    
    /////// Verifying email and password. "Is password is correct?"
    public User verifyPassword(String userEmail, String userPassword) {
        
        User user = null;
        
        try {

            establishConnection(url, username, password);
        
            preparedStatement = connection.prepareStatement("select * from mydb.user where email = ? and password = ?");
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, userPassword);
            
            ResultSet result = preparedStatement.executeQuery();
            
             while(result.next())
             {
                  user = new User(result.getString(1), result.getString(2), 
                                    result.getString(3),result.getString(4),result.getString(5), result.getString(6));
             }
            closeConnection();
        } catch (SQLException ex) {
            
            System.err.println("ERROR:: testdb.DatabaseHandler.verifyPassword()" + ex.toString());
        }
        
        System.out.println("Email and password MATCHED together");
        return user;

    }

    /////////////// checking FRIENDSHIP. "is X a friend to Y?"
    public boolean checkingFriendship(String userEmail,String friendEmail) {
        
        try {
        
            establishConnection(url, username, password);
            
            preparedStatement = connection.prepareStatement("select * from mydb.contacts where "+
                                                            "(USER_EMAIL = ? and friendEmail = ? ) " +
                                                            "OR (friendEmail = ? and USER_EMAIL = ? );");
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, friendEmail);
            preparedStatement.setString(3, userEmail);
            preparedStatement.setString(4, friendEmail);
            
            ResultSet result = preparedStatement.executeQuery();
            
            if (result.next()) {
            
                System.out.println("They're FRIENDs");
                return true;
                
            } 
            closeConnection();
        } catch (SQLException ex) {
            
            System.err.println("ERROR:: testdb.DatabaseHandler.checkingFriendship() " + ex.toString());
        }

        System.out.println("They're NOT friends");
        return false;
    }

    ///////////// inserting a new s7s user
    public boolean insertNewUser(User user) {
       
            try {
                establishConnection(url, username, password);
       
                preparedStatement = connection.prepareStatement("INSERT INTO mydb.user VALUES (?, ?, ?, ?,?,?)");
                preparedStatement.setString(1, user.getUserEmail());
                preparedStatement.setString(2, user.getUserNickName());
                preparedStatement.setString(3, user.getUserPassword());
                preparedStatement.setString(4, user.getUserGender());
                preparedStatement.setString(5, user.getUserCountry());
                preparedStatement.setString(6, user.getUserStatus());

                int queryResult = preparedStatement.executeUpdate();
                
                if (queryResult != 0)
                {
                    System.out.println("New User Added Successfully");
                    return true;
                }
              
            } catch (SQLException ex) {
                
                System.err.println("ERROR:: testdb.DatabaseHandler.insertNewUser()" + ex.toString());
                
            } finally {
                closeConnection();
            }
            
            System.out.println("New User is NOT added!");
            return false;
              
    }

    
    /////////// Adding new friend to an existing user
    public boolean insertFriend(ContactList contactList) {

        try {
            establishConnection(url, username, password);
            
            preparedStatement = connection.prepareStatement("INSERT INTO mydb.contacts VALUES (?,?,?,?,'NotBlocked')");
            preparedStatement.setString(1, contactList.getUser().getUserEmail());
            preparedStatement.setString(2, contactList.getFriend().getUserEmail());
            preparedStatement.setString(3, contactList.getUserCategory());
            preparedStatement.setString(4, contactList.getFriendCategory());
           
            int queryResult = preparedStatement.executeUpdate();
            
            if (queryResult != 0)
            {
                System.out.println("New Friend added successfully");
                return true;
            }
            
        } catch (SQLException ex) {
            
            System.out.println("ERROR:: testdb.DatabaseHandler.insertFriend()"+ ex.toString());
            
        } finally {
            //close connection
            closeConnection();
        }

        System.out.println("New friend is NOT added!");
        return false;
    }

    ////////// update a friend's category "Friends, Family, Work ..."
    public boolean setFriendCategory(ContactList contactList) {
        
        try {
            establishConnection(url, username, password);
        
            preparedStatement = connection.prepareStatement("update mydb.contacts set friendCategory = ?"+
                                                                " where (USER_EMAIL= ? and friendEmail= ?) "+
                                                                "OR (friendEmail= ? and USER_EMAIL= ? );");
            
            preparedStatement.setString(1, contactList.getFriendCategory());
            preparedStatement.setString(2, contactList.getFriend().getUserEmail());
            preparedStatement.setString(3, contactList.getUser().getUserEmail());
            preparedStatement.setString(4, contactList.getFriend().getUserEmail());
            preparedStatement.setString(5, contactList.getUser().getUserEmail());
            
            System.out.println("");
            
            int queryResult = preparedStatement.executeUpdate();
            
            if (queryResult != 0)
            {
                System.out.println("Friend's category UPDATED successfully");
                return true;
            }
            
        } catch (SQLException ex) {
            
            System.err.println("ERROR:: testdb.DatabaseHandler.setFriendCategory() " + ex.toString());
            
        } finally {
            
            closeConnection();
        }
        
        System.out.println("Did NOT update friend's category.");
        return false;
    }

    ///////////// Block OR unBlock a friend 
    public boolean blockUser(ContactList contactList) {
        try {
            establishConnection(url, username, password);
           
            preparedStatement = connection.prepareStatement("UPDATE mydb.contacts SET blocked= ? WHERE user_Email = ? and friendemail = ?");
            preparedStatement.setString(1, contactList.getIsBlocked());
            preparedStatement.setString(2, contactList.getUser().getUserEmail());
            preparedStatement.setString(3, contactList.getFriend().getUserEmail());

            int queryResult = preparedStatement.executeUpdate();

            if (queryResult != 0)
            {
                System.out.println("Blocking status is Updated successfully");
                return true;
            }
            
        } catch (SQLException ex) {
            
            System.err.println("ERROR:: testdb.DatabaseHandler.blockUser()"+ex.toString());
            
        } finally {
            
            closeConnection();
        }
        
        System.out.println("Blocking status did NOT updated!");
        return false;
        
    }

    /////////// Grrting user's Status
    public String getUserStatus(String userEmail) {
        try {
            establishConnection(url, username, password);
            
            preparedStatement = connection.prepareStatement("select status from mydb.user where email = ?");
            preparedStatement.setString(1, userEmail);
            ResultSet queryResult = preparedStatement.executeQuery();
            
            queryResult.next();
            
            String userStatus = queryResult.getString(1);
            
            return userStatus;

        } catch (SQLException ex) {
            
            System.out.println("testdb.DatabaseHandler.getUserStatus()" + ex.toString());
            
        } finally {
            //close connection
            closeConnection();
        }
        return "ERROR";    //////////////////// failed to get user's status
    }

    
    ///////////// updating user's status
    public boolean updateStatus(User user) {
        try {
            
            establishConnection(url, username, password);
            
            preparedStatement = connection.prepareStatement("UPDATE mydb.user SET status=? WHERE email=?");
            preparedStatement.setString(1, user.getUserStatus());
            preparedStatement.setString(2, user.getUserEmail());

            int queryResult = preparedStatement.executeUpdate();
            
            if (queryResult != 0)
            {
                System.out.println("Staus updated Successfully");
                return true;
            }
            
        } catch (SQLException ex) {
            
            System.err.println("testdb.DatabaseHandler.updateStatus()"+ex.toString());
            
        } finally {
            closeConnection();
        }
        
        System.out.println("Did NOT update status!");
        return false;
    }

    /////////////////////////////// for statistics
    
    //////////// get number of ONLINE users at the current time
    public int getOnlineUsers() {
        
        try {
            establishConnection(url, username, password);
        
            preparedStatement = connection.prepareStatement("select count(*) from mydb.user where status=?");
            preparedStatement.setString(1,"online");
            
            ResultSet queryResult = preparedStatement.executeQuery();
            queryResult.next();
            
            int onlineUsers = Integer.parseInt(queryResult.getString(1));
            
            return onlineUsers;

        } catch (SQLException ex) {
           
            System.out.println("ERROR:: testdb.DatabaseHandler.getOnlineUsers()"+ ex.toString());
            
        } finally {
            //close connection
            closeConnection();
        }
        return -1;   ///////////// error while fetching their count
    }

    
    
    //////////// get number of OFFLINE users at the current time
    public int getOfflineUsers() {
        
        try {
            establishConnection(url, username, password);
        
            preparedStatement = connection.prepareStatement("select count(*) from mydb.user where status=?");
            preparedStatement.setString(1,"offline");
            
            ResultSet queryResult = preparedStatement.executeQuery();
            queryResult.next();
            
            int offlineUsers = Integer.parseInt(queryResult.getString(1));
            
            return offlineUsers;

        } catch (SQLException ex) {
           
            System.out.println("ERROR:: testdb.DatabaseHandler.getOfflineUsers()"+ ex.toString());
            
        } finally {
            //close connection
            closeConnection();
        }
        return -1;   ///////////// error while fetching their count
    }

        //////////// get number of ALL users at the current time
    public int getAllUsers() {
        
        try {
            establishConnection(url, username, password);
        
            preparedStatement = connection.prepareStatement("select count(*) from mydb.user");
            
            ResultSet queryResult = preparedStatement.executeQuery();
            queryResult.next();
            
            int allUsers = Integer.parseInt(queryResult.getString(1));
            
            return allUsers;

        } catch (SQLException ex) {
           
            System.out.println("ERROR:: testdb.DatabaseHandler.getOnlineUsers()"+ ex.toString());
            
        } finally {
            //close connection
            closeConnection();
        }
        return -1;   ///////////// error while fetching their count
    }

    
    ////////// Get friends for Specific user
    public ArrayList getFriends (String userEmail)
    {
        ArrayList<ContactList> friends = new ArrayList();
        
        try {
            establishConnection(url, username, password);
            
            preparedStatement = connection.prepareStatement("select * from mydb.contacts WHERE user_Email = ? OR friendEmail = ?");
            preparedStatement.setString(1,userEmail);
            preparedStatement.setString(2,userEmail);
            
            ResultSet queryResult = preparedStatement.executeQuery();
            
             while(queryResult.next())
             {
                 //////////////// check which column contains my Email to get friends data
                 if (  userEmail.equalsIgnoreCase(queryResult.getString(1)) )
                 {
                     User user = new User();user.setUserEmail("");
                     User friend = new User(); friend.setUserEmail(queryResult.getString(2));
                     
                     ContactList contact = new ContactList(user, friend,"" ,queryResult.getString(4),queryResult.getString(5));
                     
                     friends.add(contact);
                 }
                 else if (  userEmail.equalsIgnoreCase(queryResult.getString(2)) )
                 {
                     User user = new User();user.setUserEmail("");
                     User friend = new User(); friend.setUserEmail(queryResult.getString(1));
                     
                     ContactList contact = new ContactList(user, friend,"" ,queryResult.getString(3),queryResult.getString(5));
                 
                    friends.add(contact);
                 }
             }
             
        } catch (SQLException ex) {
            
            System.out.println("ERROR :: Controller.DatabaseHandler1.getFriends()" + ex.toString());
            
        }
          
        return friends;
    }
    


    
}
