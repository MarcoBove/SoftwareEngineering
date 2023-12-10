/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Action;
import model.AlarmAction;
import model.DisplayMessageAction;
import model.RulesManager;
import model.ScenesManager;

/**
 * FXML Controller class
 *
 * @author 39327
 */
public class NewActionPageController implements Initializable {
    
    private ScenesManager sceneManager;
    private RulesManager ruleManager;
    private File selectedFile;
    private ObservableList<Action> createdAction;
    
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
    private Button doneActionsButton;
    @FXML
    private AnchorPane actionPage2;
    @FXML
    private Pane alarmChoicePane;
    @FXML
    private Label choosenAlarm;
    @FXML
    private Pane displayMessagePane;
    @FXML
    private TextField messageToDisplay;
    @FXML
    private Button addADisplayButton;
    @FXML
    private Button addAlarmButton;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneManager = ScenesManager.getInstance();
        ruleManager = RulesManager.getInstance();
        
        createdAction = FXCollections.observableArrayList();
        createActionTable1.setItems(createdAction);
        createActionTable1Name.setCellValueFactory((new PropertyValueFactory<>("description")));
        
        addActionsButton.disableProperty().bind(Bindings.isNotEmpty(createdAction));
        addADisplayButton.disableProperty().bind(messageToDisplay.textProperty().isEmpty());
        doneActionsButton.disableProperty().bind(Bindings.isEmpty(createdAction));
        deleteActionsButton.disableProperty().bind(createActionTable1.getSelectionModel().selectedItemProperty().isNull());
    }    

    @FXML
    private void deleteActionsButtonAction(ActionEvent event) {
        createdAction.remove(createActionTable1.getSelectionModel().getSelectedItem());
    }

    /*
    It navigates you back to the trigger creation page.
    */
    @FXML
    private void cancelActionsButtonAction(ActionEvent event) {
        ruleManager.getLast().setAction(null);
        ruleManager.getLast().setTrigger(null);
        
        displayMessagePane.setVisible(false);
        alarmChoicePane.setVisible(false);
        sceneManager.changeScene("/view/new_trigger_page.fxml","New Trigger page"); 
    }
    
    /*
    It navigates you back to the main page upon completion of the rule creation process.
    */
    @FXML
    private void doneActionsButtonAction(ActionEvent event) { 
        ruleManager.getLast().setAction(createdAction.get(0));
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
        createdAction.add(new DisplayMessageAction(messageToDisplay.getText()));
        
        actionPage1.setVisible(true);
        actionPage2.setVisible(false);
        messageToDisplay.setText("");
        displayMessagePane.setVisible(false);
    }
    
    /* 
    Function that creates the new action of type AlarmAction, adds it to the
    list of actions for the display, and sets the action field of the rule to 
    the newly created rule
    */
    @FXML
    private void addAlarmAction(ActionEvent event) {
        createdAction.add(new AlarmAction(selectedFile));
        
        alarmChoicePane.setVisible(false);
        actionPage1.setVisible(true);
        actionPage2.setVisible(false);
        addAlarmButton.setDisable(false);
        choosenAlarm.setText("");
    }
}
