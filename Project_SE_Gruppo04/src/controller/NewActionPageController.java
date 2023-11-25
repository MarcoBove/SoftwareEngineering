/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Action;
import model.AlarmAction;
import model.DisplayMessageAction;
import model.Rule;
import model.RuleManager;
import model.SceneManager;

/**
 * FXML Controller class
 *
 * @author 39327
 */
public class NewActionPageController implements Initializable {
    
    private static SceneManager sceneManager;
    private static RuleManager ruleManager;
    
    @FXML
    private AnchorPane actionPage;
    @FXML
    private AnchorPane actionPage1;
    @FXML
    private TableView<Action> createActionTable1;
    @FXML
    private TableColumn<Action,String> createActionTable1Name;
    @FXML
    private Button deleteActionsButton;
    @FXML
    private Button addActionsButton;
    @FXML
    private Button cancelActionsButton;
    @FXML
    private Button doneActionsButton;
    @FXML
    private AnchorPane actionPage2;
    @FXML
    private VBox vBoxCreateActions2;
    @FXML
    private Pane alarmChoicePane;
    @FXML
    private Label choosenAlarm;
    @FXML
    private Pane displayMessagePane;
    @FXML
    private TextField messageToDisplay;
    @FXML
    private Button cancelCreateActions2Button;
    @FXML
    private Button cancelCreateActions2Button1;
    @FXML
    private Button addADisplayButton;
    @FXML
    private Button addAlarmButton;
    
    private File selectedFile;
    private Action a;
    private ObservableList<Action> createdAction;
    private int min;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneManager = SceneManager.getInstance();
        ruleManager = RuleManager.getInstance();
        
        min=1;
        
        
        createdAction = FXCollections.observableArrayList();
        
        
        createActionTable1.setItems(createdAction);
        createActionTable1Name.setCellValueFactory((new PropertyValueFactory<>("description")));
        
        deleteActionsButton.setDisable(true);
        addADisplayButton.disableProperty().bind(messageToDisplay.textProperty().length().lessThan(min));
        
        
    }    

    
    @FXML
    private void deleteActionsButtonAction(ActionEvent event) {
        
    }

    
    /*
    It navigates you back to the trigger creation page.
    */
    @FXML
    private void cancelActionsButtonAction(ActionEvent event) {
        sceneManager.changeScene("/view/new_trigger_page.fxml","New Trigger page");
        displayMessagePane.setVisible(false);
        alarmChoicePane.setVisible(false);
    }
    
    
    /*
    It navigates you back to the main page upon completion of the rule creation process.
    */
    @FXML
    private void doneActionsButtonAction(ActionEvent event) {
        
        sceneManager.changeScene("/view/homePage.fxml","Home Page");
        
    }

    
    /*
    It allows to choose the audio file that will be played when the rule becomes active."
    */
    @FXML
    private void alarmChoice(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleziona un file audio");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("File audio (*.mp3, *.wav, *.ogg)", "*.mp3", "*.wav", "*.ogg");
        fileChooser.getExtensionFilters().add(extFilter);
        Window ownerWindow = null;
        selectedFile = fileChooser.showOpenDialog(ownerWindow);
        choosenAlarm.setText(selectedFile.getName());
        addAlarmButton.setDisable(false);
        
        ruleManager.getLast().setAction(a);
        
    }
    
    
    /*
    Enables returning from the action creation page to the list of created 
    Actions without actually creating a new action.
    */
    @FXML
    private void cancelCreateActions2ButtonAction(ActionEvent event) {

        actionPage1.setVisible(true);
        actionPage2.setVisible(false);
        alarmChoicePane.setVisible(false);
        displayMessagePane.setVisible(false);

    }

    /*shows the pane related to new action process */
    @FXML
    private void addActionsButton(ActionEvent event) {
        
        actionPage1.setVisible(false);
        actionPage2.setVisible(true);
          
    }

    
    /* Shows the pane related to the creation of an action of type AlarmAction. */
    @FXML
    private void alarmActionCreationProcess(ActionEvent event) {
        
        alarmChoicePane.setVisible(true);
        
    }
    

    /* Shows the pane related to the creation of an action of type DisplayMessageAction. */
    @FXML
    private void displayMessageCreationProcess(ActionEvent event) {
        
        displayMessagePane.setVisible(true);
        
    }

    
    /*
    Function that creates the new action of type DisplayMEssageAction, adds it to the
    list of actions for the display, and sets the action field of the rule to 
    the newly created rule
    */
    @FXML
    private void addDisplayMessageAction(ActionEvent event) {
        a = new DisplayMessageAction(messageToDisplay.getText());
        actionPage1.setVisible(true);
        actionPage2.setVisible(false);
        messageToDisplay.setText("");
        createdAction.add(a);
        displayMessagePane.setVisible(false);
        addActionsButton.setDisable(true);
        
        
    }

    
    /* 
    Function that creates the new action of type AlarmAction, adds it to the
    list of actions for the display, and sets the action field of the rule to 
    the newly created rule
    */
    @FXML
    private void addAlarmAction(ActionEvent event) {
        a = new AlarmAction(selectedFile);

      
        createdAction.add(a);
        
        ruleManager.getLast().setAction(a);
        alarmChoicePane.setVisible(false);
        addActionsButton.setDisable(true);
        actionPage1.setVisible(true);
        actionPage2.setVisible(false);
        addAlarmButton.setDisable(false);
        choosenAlarm.setText("");
        
       
    }

}
