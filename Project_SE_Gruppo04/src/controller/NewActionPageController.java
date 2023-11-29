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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Action;
import model.AlarmAction;
import model.DisplayMessageAction;
import model.FileAppendAction;
import model.FileCopyAction;
import model.FileMoveAction;
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
    private File selectedDirectory;
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
    private AnchorPane fileChoicePane;
    @FXML
    private Label choosenFile;
    @FXML
    private AnchorPane displayMessagePane;
    @FXML
    private TextField messageToDisplay;
    @FXML
    private Button addADisplayButton;
    @FXML
    private Button addFileActionButton;
    @FXML
    private Button fileButton;
    @FXML
    private VBox vBoxAppendFile;
    @FXML
    private TextArea appendArea;
    @FXML
    private MenuButton menuActions;
    @FXML
    private VBox vBoxMoveFile;
    @FXML
    private Button directoryChooserMove;
    @FXML
    private Label chosenDirectoryCopy;
    @FXML
    private Label chosenDirectoryMove;
    @FXML
    private VBox vBoxCopyFile;
    @FXML
    private Button directoryChooserCopy;
   
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
        fileChoicePane.setVisible(false);
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
    
    /*shows the pane related to new action process */
    @FXML
    private void addActionsButton(ActionEvent event) {
        actionPage1.setVisible(false);
        actionPage2.setVisible(true);
        menuActions.setDisable(false);
    }
    
    /*
    Enables returning from the action creation page to the list of created 
    Actions without actually creating a new action.
    */
    @FXML
    private void cancelCreateActions2ButtonAction(ActionEvent event) {
        addFileActionButton.setDisable(true);
        actionPage1.setVisible(true);
        actionPage2.setVisible(false);
        fileChoicePane.setVisible(false);
        displayMessagePane.setVisible(false);
        vBoxAppendFile.setVisible(false);
    }
    
    /* Shows the pane related to the creation of an action of type DisplayMessageAction. */
    @FXML
    private void displayMessageCreationProcess(ActionEvent event) {  
        menuActions.setDisable(true);
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
    
    //FileAction method
    private void selectFile(String title, FileChooser.ExtensionFilter filter){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().add(filter);
        Window ownerWindow = null;
        selectedFile = fileChooser.showOpenDialog(ownerWindow);
        if(selectedFile != null)
            choosenFile.setText(selectedFile.getName());
        addFileActionButton.setDisable(false);
    }
    
    /* Shows the pane related to the creation of an action of type AlarmAction. 
    It allows to choose the audio file that will be played when the rule becomes active." */
    @FXML
    private void alarmActionCreationProcess(ActionEvent event) {
        menuActions.setDisable(true);
        fileChoicePane.setVisible(true);
        vBoxAppendFile.setVisible(false);
        /*It allows to choose the audio file that will be played when the rule becomes active."*/
        fileButton.setOnAction(e -> {
            selectFile("Select an audio file",new FileChooser.ExtensionFilter("File audio (*.mp3, *.wav, *.ogg)", "*.mp3", "*.wav", "*.ogg"));
        });
      
         /* Function that creates the new action of type AlarmAction, adds it to the
            list of actions for the display, and sets the action field of the rule to 
            the newly created rule
            */
        addFileActionButton.setOnAction(e -> {
            createdAction.add(new AlarmAction(selectedFile));
        
            fileChoicePane.setVisible(false);
            actionPage1.setVisible(true);
            actionPage2.setVisible(false);
            addFileActionButton.setDisable(true);
            choosenFile.setText("");
        
        });
    }

    @FXML
    private void fileAppendActionCreationProcess(ActionEvent event){
        menuActions.setDisable(true);
        fileChoicePane.setVisible(true);
        vBoxAppendFile.setVisible(true);
        /*It allows to choose the text file."*/
        fileButton.setOnAction(e -> {
            selectFile("Select a text file", new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt"));
        });
        
        /* Function that creates the new action of type FileAppendAction, adds it to the
            list of actions for the display, and sets the action field of the rule to 
            the newly created rule
            */
        addFileActionButton.setOnAction(e -> {
            createdAction.add(new FileAppendAction(appendArea.getText(),selectedFile));
        
            fileChoicePane.setVisible(false);
            vBoxAppendFile.setVisible(false);
            actionPage1.setVisible(true);
            actionPage2.setVisible(false);
            addFileActionButton.setDisable(true);
            choosenFile.setText("");
        });
    }

    @FXML
    private void fileMoveActionCreationProcess(ActionEvent event) {
        menuActions.setDisable(true);
        fileChoicePane.setVisible(true);
        vBoxMoveFile.setVisible(true);
        /*It allows to choose the text file."*/
        fileButton.setOnAction(e -> {
            FileChooser fileChooser= new FileChooser();
        fileChooser.setTitle("Select Source File to move");
        Window ownerWindow = null;
        selectedFile = fileChooser.showOpenDialog(ownerWindow);
        if(selectedFile != null)
         choosenFile.setText(selectedFile.getName());
    });
        
        directoryChooserMove.setOnAction(e -> {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the destination directory ");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Window ownerWindow = null;
         selectedDirectory = directoryChooser.showDialog(ownerWindow);
        if(selectedDirectory != null)
         chosenDirectoryMove.setText(selectedDirectory.getName());

        });
        
        addFileActionButton.setDisable(false);
       
        /* Function that creates the new action of type FileAppendAction, adds it to the
            list of actions for the display, and sets the action field of the rule to 
            the newly created rule
            */
        addFileActionButton.setOnAction(e -> {
            createdAction.add(new FileMoveAction(selectedFile,selectedDirectory));
        
            fileChoicePane.setVisible(false);
            vBoxMoveFile.setVisible(false);
            actionPage1.setVisible(true);
            actionPage2.setVisible(false);
            addFileActionButton.setDisable(true);
            choosenFile.setText("");
            chosenDirectoryMove.setText("");
        });
    }

    @FXML
    private void fileCopyActionCreationProcess(ActionEvent event) {
        
        menuActions.setDisable(true);
        fileChoicePane.setVisible(true);
        vBoxCopyFile.setVisible(true);
        /*It allows to choose the text file."*/
        fileButton.setOnAction(e -> {
            FileChooser fileChooser= new FileChooser();
        fileChooser.setTitle("Select Source File to copy");
        Window ownerWindow = null;
        selectedFile = fileChooser.showOpenDialog(ownerWindow);
        if(selectedFile != null)
         choosenFile.setText(selectedFile.getName());
    });
        
        directoryChooserCopy.setOnAction(e -> {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the destination directory");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Window ownerWindow = null;
         selectedDirectory = directoryChooser.showDialog(ownerWindow);
        if(selectedDirectory != null)
         chosenDirectoryCopy.setText(selectedDirectory.getName());

        });
        
      
         addFileActionButton.setDisable(false);
        /* Function that creates the new action of type FileAppendAction, adds it to the
            list of actions for the display, and sets the action field of the rule to 
            the newly created rule
            */
        addFileActionButton.setOnAction(e -> {
            createdAction.add(new FileCopyAction(selectedFile,selectedDirectory));
        
            fileChoicePane.setVisible(false);
            vBoxCopyFile.setVisible(false);
            actionPage1.setVisible(true);
            actionPage2.setVisible(false);
            addFileActionButton.setDisable(true);
            choosenFile.setText("");
            chosenDirectoryCopy.setText("");
        });
    }

}
