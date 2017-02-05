/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author toqae
 */
public class ClientHomeController_2 implements Initializable {
    
    @FXML
    private BorderPane rootPane;
    @FXML
    private Label label;
    @FXML
    private TextArea chatInputField;
    @FXML
    private TextArea chatOutputField;
    @FXML
    private Label userName;
    @FXML
    private ListView contactsList;
    @FXML
    private ListView groupsList;
    
    private TabPane tabPane;
    private boolean firstEntery = true; 
    private HashMap<String, Tab> openedTabs = new HashMap<>();
   
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    @FXML
    private void emojButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    @FXML
    private void imgButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    @FXML
    private void fileButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    @FXML
    private void fontButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    @FXML
    private void sendButtonAction(ActionEvent event) {
        sendButton();
    }
    @FXML
    private void signOutButtonAction(ActionEvent event) {
       System.out.println("You are signing out ... ");
        
        try {
        
             System.out.println("You are going to loginPage");
          
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
            Parent parent =  FXMLLoader.load(getClass().getResource("/View/Login/LoginFXML.fxml"));
           
            Stage stage = new Stage();
            stage.setTitle("SitChat");
            stage.setScene(new Scene(parent));  
            stage.show();
            
            System.out.println("Showing login page");
            
        } catch (IOException ex) {
            System.out.println("ERROR:: " + ex.toString()); 
        }
        
        
    }
    @FXML
    private void saveButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    @FXML
    private void closeButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        prepareContactList();
        prepareGroupList();
         
        
       
    }  
    public void sendButton(){
        String msg = chatInputField.getText();
        
        
    }
    public void prepareContactList(){
        List<String> values = Arrays.asList("Contact one", "contact two", "contact three");

        contactsList.setItems(FXCollections.observableList(values));
        
        contactsList.getSelectionModel().selectedItemProperty().addListener(
      new ChangeListener() {
             @Override
             public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(firstEntery){ 
                  tabPane = new TabPane();
                  firstEntery = false;
                }
                if(openedTabs.get(contactsList.getSelectionModel().selectedItemProperty().toString()) == null){
                    Tab tab = new Tab();
                    tab.setText("new tab");
                    BorderPane chatSide=new BorderPane();
                    Button saveBtn=new Button("Save");
                    Button closeBtn=new Button("Close");
                    HBox hbox = new HBox(2);
                    hbox.getChildren().addAll(saveBtn);//,closeBtn);
                    hbox.setAlignment(Pos.BOTTOM_RIGHT);
                    chatSide.setTop(hbox);
                    TextArea txtArea=new TextArea();
                    chatSide.setCenter(txtArea);
                    Button emojiBtn=new Button("emotions");
                    Button imgBtn=new Button("Image");
                    Button fileBtn=new Button("File");
                    Button fontBtn=new Button("Font");

                    VBox vbox = new VBox(2);
                    SplitPane editPane =new SplitPane(emojiBtn,imgBtn,fileBtn,fontBtn);

                   TextArea chatArea=new TextArea();
                   Button sendBtn=new Button("Send");
                   FlowPane chatPane=new FlowPane(chatArea,sendBtn);
                   vbox.getChildren().addAll(editPane,chatPane);
                   chatSide.setBottom(vbox);
               // vbox.setAlignment(Pos.BOTTOM_RIGHT);
                    tab.setContent(chatSide);
                    tab.setOnCloseRequest(new EventHandler<Event>(){
                        @Override
                        public void handle(Event e){
                            openedTabs.remove(contactsList.getSelectionModel().selectedItemProperty().toString());
                        }
                    });
                    tabPane.getTabs().add(tab);
                    tabPane.setSide(Side.BOTTOM); 
                    rootPane.setCenter(tabPane);
                    openedTabs.put(contactsList.getSelectionModel().selectedItemProperty().toString(), tab);
                }
             }
         });
         
    }
    public void prepareGroupList(){
                 List<String> values = Arrays.asList("Group one", "Group two", "Group three");

        groupsList.setItems(FXCollections.observableList(values));
        
        groupsList.getSelectionModel().selectedItemProperty().addListener(
      new ChangeListener() {
             @Override
             public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(firstEntery){ 
                  tabPane = new TabPane();
                  firstEntery = false;
                }
                if(openedTabs.get(groupsList.getSelectionModel().selectedItemProperty().toString()) == null){
                    Tab tab = new Tab();
                    tab.setText("new tab");
                    BorderPane chatSide=new BorderPane();
                    Button saveBtn=new Button("Save");
                    Button partBtn=new Button("Add Participants");
                    HBox hbox = new HBox(2);
                    hbox.getChildren().addAll(partBtn, saveBtn);//,closeBtn);
                    hbox.setAlignment(Pos.BOTTOM_RIGHT);
                    chatSide.setTop(hbox);
                    TextArea txtArea=new TextArea();
                    chatSide.setCenter(txtArea);
                    Button emojiBtn=new Button("emotions");
                    Button imgBtn=new Button("Image");
                    Button fileBtn=new Button("File");
                    Button fontBtn=new Button("Font");

                    VBox vbox = new VBox(2);
                    SplitPane editPane =new SplitPane(emojiBtn,imgBtn,fileBtn,fontBtn);

                   TextArea chatArea=new TextArea();
                   Button sendBtn=new Button("Send");
                   FlowPane chatPane=new FlowPane(chatArea,sendBtn);
                   vbox.getChildren().addAll(editPane,chatPane);
                   chatSide.setBottom(vbox);
               // vbox.setAlignment(Pos.BOTTOM_RIGHT);
                    tab.setContent(chatSide);
                    tab.setOnClosed(new EventHandler<Event>(){
                        @Override
                        public void handle(Event e){
                            openedTabs.remove(groupsList.getSelectionModel().selectedItemProperty().toString());
                        }
                    });
                    tabPane.getTabs().add(tab);
                    tabPane.setSide(Side.BOTTOM); 
                    rootPane.setCenter(tabPane);
                    openedTabs.put(groupsList.getSelectionModel().selectedItemProperty().toString(), tab);
                }
             }
         });

    }
}
