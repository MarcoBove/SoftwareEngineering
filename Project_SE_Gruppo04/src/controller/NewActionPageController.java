/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Action;
import model.AlarmAction;
import model.CompositeAction;
import model.DisplayMessageAction;
import model.ExternalProgramExecutionAction;
import model.FileAppendAction;
import model.FileCopyAction;
import model.FileDeleteAction;
import model.FileMoveAction;
import model.RulesManager;

/**
 * FXML Controller class
 *
 * @author gruppo_04
 */
public class NewActionPageController implements Initializable {

    private ScenesController scenesController;
    private RulesManager rulesManager;
    
    private File selectedFile;
    private File selectedDirectory;
    
    private ObservableList<Action> createdAction;

    @FXML
    private AnchorPane actionPage1;
    @FXML
    private TableView<Action> createActionTable1;
    @FXML
    private TableColumn<Action, String> createActionTable1Name;
    @FXML
    private Button deleteActionsButton;
    @FXML
    private Button addActionsButton;
    @FXML
    private Button doneActionsButton;
    @FXML
    private AnchorPane actionPage2;
    @FXML
    private AnchorPane inputChoicePane;
    @FXML
    private TextArea messageToDisplay;
    @FXML
    private Button addActionButton;
    @FXML
    private Button fileButton;
    @FXML
    private VBox vBoxAppendFile;
    @FXML
    private TextArea appendArea;
    @FXML
    private MenuButton menuActions;
    @FXML
    private Label chosenFile;
    @FXML
    private Label desc;
    @FXML
    private Button directoryChooser;
    @FXML
    private Label chosenDirectory;
    @FXML
    private VBox vBoxMCFile;
    @FXML
    private VBox vBoxDisplayMessage;
    @FXML
    private VBox hBoxFileChooser;
    @FXML
    private VBox vBoxProgram;
    @FXML
    private TextArea argumentsText;
    @FXML
    private Button programButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scenesController = ScenesController.getInstance();
        rulesManager = RulesManager.getInstance();
        //actions table settings
        createdAction = FXCollections.observableArrayList();
        createActionTable1.setItems(createdAction);
        createActionTable1Name.setCellValueFactory((new PropertyValueFactory<>("description")));
        createActionTable1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Tooltip.install(createActionTable1, new Tooltip("To select multiple actions from the table, hold down the CTRL key while clicking on the items.\n"
                + "Alternatively, hold down the SHIFT key and click the start and end of the range to select."));
        // buttons settings
        addActionsButton.disableProperty().set(false);
        doneActionsButton.disableProperty().bind(Bindings.isEmpty(createdAction));
        deleteActionsButton.disableProperty().bind(createActionTable1.getSelectionModel().selectedItemProperty().isNull());
        clear();
    }

    //BUTTONS
    @FXML
    private void deleteActionsButtonAction(ActionEvent event) {
        //The button press delets the selected actions from the list
        createdAction.removeAll(createActionTable1.getSelectionModel().getSelectedItems());
    }

    /*
        It navigates you back to the trigger creation page.
     */
    @FXML
    private void cancelActionsButtonAction(ActionEvent event) {
        rulesManager.getLast().setAction(null);
        rulesManager.getLast().setTrigger(null);
        inputChoicePane.setVisible(false);
        clear();
        
        scenesController.changeScene("/view/new_trigger_page.fxml", "New Trigger page");
    }

    /*
    It navigates you back to the main page upon completion of the rule creation process.
     */
    @FXML
    private void doneActionsButtonAction(ActionEvent event) {
        rulesManager.getLast().setAction(createCompositeAction());
        clear();
        
        scenesController.changeScene("/view/homePage.fxml", "Home Page");
    }

    /*shows the pane related to new action process */
    @FXML
    private void addActionsButton(ActionEvent event) {
        goPage2();
    }
    
    /*
    Enables returning from the action creation page to the list of created 
    Actions without actually creating a new action.
     */
    @FXML
    private void cancelCreateActions2ButtonAction(ActionEvent event) {
        goPage1();
        clear();
    }

    //ACTION CREATION FUNCTIONS
    /* Shows the pane related to the creation of an action of type DisplayMessageAction
        and at the end of the process create the action. */
    @FXML
    private void displayMessageCreationProcess(ActionEvent event) {
        showInput();
        vBoxDisplayMessage.setVisible(true);
        
        addActionButton.disableProperty().bind(messageToDisplay.textProperty().isEmpty());
        /* 
            Function that creates the new action of type DisplayMessageAction, adds it to the
            list of actions for the display, and sets the action field of the rule to 
            the newly created rule
         */
        addActionButton.setOnAction(e -> {
            DisplayMessageAction dm = new DisplayMessageAction(messageToDisplay.getText());
            dm.addObserver(new DisplayMessageController());
            createdAction.add(dm);
            
            goPage1();
            clear();
        });

    }

    /* Shows the pane related to the creation of an action of type AlarmAction. 
        It allows to choose the audio file that will be played when the rule becomes active.
     */
    @FXML
    private void alarmActionCreationProcess(ActionEvent event) {
        showInput();
        hBoxFileChooser.setVisible(true);

        /*It allows to choose the audio file that will be played when the rule becomes active."*/
        fileButton.setOnAction(e -> {
            selectFile("Select an audio file", new FileChooser.ExtensionFilter("File audio (*.mp3, *.wav, *.ogg)", "*.mp3", "*.wav", "*.ogg"));
        });
        addActionButton.disableProperty().bind(chosenFile.textProperty().isEmpty());
        /* 
            Function that creates the new action of type AlarmAction, adds it to the
            list of actions for the display, and sets the action field of the rule to 
            the newly created rule
         */
        addActionButton.setOnAction(e -> {
            AlarmAction aa = new AlarmAction(selectedFile);
            aa.addObserver(new AlarmActionController());
            createdAction.add(aa);
            
            goPage1();
            clear();
        });
    }

    @FXML
    private void fileAppendActionCreationProcess(ActionEvent event) {
        showInput();
        hBoxFileChooser.setVisible(true);
        vBoxAppendFile.setVisible(true);
        /*It allows to choose the text file.*/
        fileButton.setOnAction(e -> {
            selectFile("Select a text file", new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt"));
        });
        addActionButton.disableProperty().bind(appendArea.textProperty().isEmpty().or(chosenFile.textProperty().isEmpty()));
        /* 
            Function that creates the new action of type FileAppendAction, adds it to the
            list of actions for the display, and sets the action field of the rule to 
            the newly created rule
         */
        addActionButton.setOnAction(e -> {
            createdAction.add(new FileAppendAction(appendArea.getText(), selectedFile));
            
            goPage1();
            clear();
        });
    }

    /*
        Shows the pane related to the creation of an action of type FileMove
        and at the end of the process create the action.
     */
    @FXML
    private void fileMoveActionCreationProcess(ActionEvent event) {
        desc.setText("Select Directory to move in");
        directoryChooser.setText("Move in");
        moveAndCopy();
        /* creates the new action of type FileMoveAction, adds it to the
            list of actions for the display, and sets the action field of the rule to 
            the newly created rule
         */
        addActionButton.setOnAction(e -> {
            createdAction.add(new FileMoveAction(selectedFile, selectedDirectory));
            
            goPage1();
            clear();
        });
    }

    /*
        Shows the pane related to the creation of an action of type FileMove
        and at the end of the process create the action.
     */
    @FXML
    private void fileCopyActionCreationProcess(ActionEvent event) {
        desc.setText("Select Directory to copy in");
        directoryChooser.setText("Copy in");
        moveAndCopy();

        /* creates the new action of type FileCopyAction, adds it to the
            list of actions for the display, and sets the action field of the rule to 
            the newly created rule
         */
        addActionButton.setOnAction(e -> {
            createdAction.add(new FileCopyAction(selectedFile, selectedDirectory));
            
            goPage1();
            clear();
        });
    }

    @FXML
    private void fileDeleteActionCreationProcess(ActionEvent event) {
        showInput();
        hBoxFileChooser.setVisible(true);

        /*It allows to choose the file.*/
        fileButton.setOnAction(e -> {
            selectFile("Select a file", new FileChooser.ExtensionFilter("All Files", "*.*"));
        });
        addActionButton.disableProperty().bind((chosenFile.textProperty().isEmpty()));
        /* creates the new action of type FileDeleteAction, adds it to the
            list of actions for the display, and sets the action field of the rule to 
            the newly created rule
         */
        addActionButton.setOnAction(e -> {
            createdAction.add(new FileDeleteAction(selectedFile));
            
            goPage1();
            clear();
        });
    }

    @FXML
    private void externalProgramExecutionActionCreationProcess(ActionEvent event) {
        showInput();
        vBoxProgram.setVisible(true);
        
        // addButton
        addActionButton.disableProperty().bind(argumentsText.textProperty().isEmpty());
        programButton.setOnAction(e -> {
            selectFile("Select a Program or a file as argument", new FileChooser.ExtensionFilter("All Files", "*"));
            if (selectedFile != null) {
                // Create a StringBuilder to store file paths
                StringBuilder filePaths = new StringBuilder();
                // Append the selected file path to the StringBuilder
                filePaths.append(selectedFile.getAbsolutePath()).append(" ");
                // Update the text in argumentsText
                argumentsText.appendText(filePaths.toString() + " ");
            }
        });

        addActionButton.setOnAction(e -> {
            // Split the accumulated file paths into an array of arguments
            String[] arguments = argumentsText.getText().split("\\s+");
            createdAction.add(new ExternalProgramExecutionAction(arguments[0], Arrays.copyOfRange(arguments, 1, arguments.length)));
            
            goPage1();
            clear();
        });
    }


    //USEFUL FUNCTIONS
    private void goPage1() {
        inputChoicePane.setVisible(false);
        actionPage1.setVisible(true);
        actionPage2.setVisible(false);
    }

    private void goPage2() {
        actionPage1.setVisible(false);
        actionPage2.setVisible(true);
        menuActions.setDisable(false);
    }

    private void showInput() {
        menuActions.setDisable(true);
        inputChoicePane.setVisible(true);
    }

    /*Cleans up the fields*/
    private void clear() {

        chosenFile.setText("");
        chosenDirectory.setText("");
        appendArea.setText("");
        messageToDisplay.setText("");
        hBoxFileChooser.setVisible(false);
        vBoxDisplayMessage.setVisible(false);
        vBoxProgram.setVisible(false);
        vBoxAppendFile.setVisible(false);
        vBoxMCFile.setVisible(false);
        vBoxProgram.setVisible(false);

    }

    //FileAction method
    private void selectFile(String title, FileChooser.ExtensionFilter filter) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().add(filter);
        Window ownerWindow = null;
        selectedFile = fileChooser.showOpenDialog(ownerWindow);
        if (selectedFile != null) {
            chosenFile.setText(selectedFile.getName());
        }
    }

    public void moveAndCopy() {
        hBoxFileChooser.setVisible(true);
        showInput();
        vBoxMCFile.setVisible(true);

        /*Opens a dialog window allowing the selection of the file.*/
        fileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Source File to copy");
            Window ownerWindow = null;
            selectedFile = fileChooser.showOpenDialog(ownerWindow);
            if (selectedFile != null) {
                chosenFile.setText(selectedFile.getName());
            }
        });
        /*Opens a dialog window allowing the selection of the destination directory.*/
        directoryChooser.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select the destination directory");
            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            Window ownerWindow = null;
            selectedDirectory = directoryChooser.showDialog(ownerWindow);
            if (selectedDirectory != null) {
                chosenDirectory.setText(selectedDirectory.getName());
            }
        });
        addActionButton.disableProperty().bind(chosenDirectory.textProperty().isEmpty().or(chosenFile.textProperty().isEmpty()));
    }

    //create a composite actions if necessary
    private Action createCompositeAction(){
        Action a;
        if(createdAction.size() == 1){
            a = createdAction.get(0);
        }
        else{
            a = new CompositeAction();
            for(Action action : createdAction){
                a.addAction(action);
            }
        }
        return a;
    }
}