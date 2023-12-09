/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.AndTrigger;
import model.DateTrigger;
import model.DayOfTheMonthTrigger;
import model.DayOfWeekTrigger;
import model.ExternalProgramTrigger;
import model.FilePresenceTrigger;
import model.FileSizeTrigger;
import model.NotTrigger;
import model.OrTrigger;
import model.RulesManager;
import model.TimeTrigger;
import model.Trigger;

/**
 * FXML Controller class
 *
 * @author Andre
 */
public class NewTriggerPageController implements Initializable {

    private static final int AND_TRIGGER_ELEMENT = 2;
    private static final int OR_TRIGGER_ELEMENT = 2;
    private static final int NOT_TRIGGER_ELEMENT = 1;
    private ScenesController sceneManager;
    private RulesManager ruleManager;
    private ObservableList<Trigger> createdTrigger;
    private File selectedFile;
    
    @FXML
    private AnchorPane triggerPage1;
    @FXML
    private Button deleteTrigger1Button;
    @FXML
    private Button nextTrigger1Button;
    @FXML
    private TableView<Trigger> trigger1Table;
    @FXML
    private TableColumn<Trigger, String> trigger1TableName;
    @FXML
    private AnchorPane triggerPage2;
    @FXML
    private ComboBox<String> hoursComboBox;
    @FXML
    private ComboBox<String> minutesComboBox;
    @FXML
    private ComboBox<String> dayOfWeekComboBox;
    @FXML
    private Button addTriggerButton;
    @FXML
    private AnchorPane inputPane;
    @FXML
    private VBox timeTriggerPane;
    @FXML
    private VBox dayOfWeekTriggerPane;
    @FXML
    private MenuButton menuTrigger;
    @FXML
    private VBox dateTriggerPane;
    @FXML
    private DatePicker datePickerTrigger;
    @FXML
    private VBox vBoxDayOfTheMonth;
    @FXML
    private ComboBox<String> dayofTheMonth;
    @FXML
    private Button andTriggerButton;
    @FXML
    private Button orTriggerButton;
    @FXML
    private Button notTriggerButton;
    @FXML
    private VBox filePresenceTriggerPane1;
    @FXML
    private VBox fileSizeTriggerPane;
    @FXML
    private Spinner<Integer> fileSizeTriggerSpinner;
    @FXML
    private TextField filePresenceText;
    @FXML
    private Button filePresenceTriggerButton;
    @FXML
    private Button fileSizeTriggerButton;
    @FXML
    private ComboBox<String> unitSizeComboBox;
    @FXML
    private Label chosenDirectory;
    @FXML
    private Label chosenFile;
    @FXML
    private Button externalProgramButton;
    @FXML
    private TextArea argumentsTextTrigger;
    @FXML
    private Spinner<Integer> exitCodeSpinner;
    @FXML
    private VBox vBoxExternalProgram;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneManager = ScenesController.getInstance();
        ruleManager = RulesManager.getInstance();

        // trigger visualization
        createdTrigger = FXCollections.observableArrayList();
        trigger1Table.setItems(createdTrigger);
        trigger1TableName.setCellValueFactory(new PropertyValueFactory<>("description"));
        deleteTrigger1Button.disableProperty().bind(trigger1Table.getSelectionModel().selectedItemProperty().isNull());
        nextTrigger1Button.disableProperty().bind(Bindings.size(createdTrigger).isNotEqualTo(1));
        fillComboBox();    
        
        datePickerTrigger.setDayCellFactory(picker -> new DateCell() {
        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            // Disabilita le date precedenti
            setDisable(date.isBefore(LocalDate.now()));
        }
        });
        
        //AND, OR, NOT TRIGGER BUTTONS
        trigger1Table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Tooltip.install(trigger1Table, new Tooltip("To select multiple triggers from the table, hold down the CTRL key while clicking on the items.\n"
                + "Alternatively, hold down the SHIFT key and click the start and end of the range to select."));
        orTriggerButton.disableProperty().bind(Bindings.size(trigger1Table.getSelectionModel().getSelectedItems()).lessThan(AND_TRIGGER_ELEMENT));
        andTriggerButton.disableProperty().bind(Bindings.size(trigger1Table.getSelectionModel().getSelectedItems()).lessThan(OR_TRIGGER_ELEMENT));
        notTriggerButton.disableProperty().bind(Bindings.size(trigger1Table.getSelectionModel().getSelectedItems()).isNotEqualTo(NOT_TRIGGER_ELEMENT));
    }

    //OTHER BUTTONS
    
    //The button press delets the selected trigger from the list
    @FXML
    private void deleteTrigger1ButtonAction(ActionEvent event) {
        createdTrigger.removeAll(trigger1Table.getSelectionModel().getSelectedItems());
    }

    //The button press takes you to the page for selecting the trigger to create
    @FXML
    private void addTrigger1ButtonAction(ActionEvent event) {
        goPage2();
    }

    //The button press takes you to the page for creating a new rule and removes the last created rule
    @FXML
    private void cancelTrigger1ButtonAction(ActionEvent event) {
        ruleManager.removeLast();
        sceneManager.changeScene("/view/new_rule_page.fxml", "New Rule Page");
    }

    // he button press navigates you to the page for creating actions
    @FXML
    private void nextTrigger1ButtonAction(ActionEvent event) {
        ruleManager.getLast().setTrigger(createdTrigger.get(0));
        sceneManager.changeScene("/view/new_action_page.fxml", "New Action Page");
    }
    
    
    //The button press takes you back to page 1
    @FXML
    private void retryTriggerCreation(ActionEvent event) {
        clear();
        goPage1();
    }
    
    //ACTIONS PERFORMED by the AND, OR, and NOT BUTTON FOR TRIGGERS.
    @FXML
    private void andTriggerButtonAction(ActionEvent event) {
        Trigger andTrigger = new AndTrigger();
        ObservableList<Trigger> selectedTriggers = trigger1Table.getSelectionModel().getSelectedItems();
        for(Trigger t : selectedTriggers){
            andTrigger.addTrigger(t);
        }
        createdTrigger.removeAll(selectedTriggers);
        createdTrigger.add(andTrigger);
    }

    @FXML
    private void orTriggerButtonAction(ActionEvent event) {
        Trigger orTrigger = new OrTrigger();
        ObservableList<Trigger> selectedTriggers = trigger1Table.getSelectionModel().getSelectedItems();
        for(Trigger t : selectedTriggers){
            orTrigger.addTrigger(t);
        }
        createdTrigger.removeAll(selectedTriggers);
        createdTrigger.add(orTrigger);
    }

    @FXML
    private void notTriggerButtonAction(ActionEvent event) {
        Trigger selectedTrigger = trigger1Table.getSelectionModel().getSelectedItem();
        createdTrigger.remove(selectedTrigger);
        createdTrigger.add(new NotTrigger(selectedTrigger));
    }

    //TRIGGER CREATION FUNCTIONS
    
    
    // Displays the panel for Time selection
    // and instantiates a Trigger object of type TimeTrigger
    @FXML
    private void timeTriggerCreationProcess(ActionEvent event) {
        showInput();
        timeTriggerPane.setVisible(true);
        //add button
        addTriggerButton.disableProperty().bind(hoursComboBox.valueProperty().isEqualTo("hh") .or(minutesComboBox.valueProperty().isEqualTo("mm")));
        addTriggerButton.setOnAction(e -> {
            createdTrigger.add(new TimeTrigger(LocalTime.of(Integer.parseInt(hoursComboBox.getValue()), Integer.parseInt(minutesComboBox.getValue()))));
            goPage1();
            clear();
        });
    }
    
    
    
    // Displays the panel for the day of the week selection
    // and instantiates a Trigger object of type DayOfWeekTrigger
    @FXML
    private void dayOfWeekTriggerCreationProcess(ActionEvent event) {
        showInput();
        dayOfWeekTriggerPane.setVisible(true);
        //add button
        addTriggerButton.disableProperty().bind(dayOfWeekComboBox.valueProperty().isEqualTo("days"));
        addTriggerButton.setOnAction(e -> {
            createdTrigger.add(new DayOfWeekTrigger(DayOfWeek.valueOf(dayOfWeekComboBox.getValue().toUpperCase())));
            goPage1();
            clear();
        });
    }
    
    
    
    // Displays the panel for date selection
    // and instantiates a Trigger object of type DateTrigger
    @FXML
    private void dateTriggerCreationProcess(ActionEvent event) {
        showInput();
        dateTriggerPane.setVisible(true);
        //add button
        addTriggerButton.disableProperty().bind(datePickerTrigger.valueProperty().isNull());
        addTriggerButton.setOnAction(e -> {
            createdTrigger.add(new DateTrigger(datePickerTrigger.getValue()));
            goPage1();
            clear();
        });
        
        
    }
    
    

    // Displays the panel for day selection
    // and instantiates a Trigger object of type DayOfTheMonthTrigger
    @FXML
    private void dayOfTheMonthTriggerCreationProcess(ActionEvent event) {
        showInput();
        vBoxDayOfTheMonth.setVisible(true);
        //add button
        addTriggerButton.disableProperty().bind(dayofTheMonth.valueProperty().isEqualTo("day"));
        addTriggerButton.setOnAction(e -> {
            createdTrigger.add(new DayOfTheMonthTrigger(Integer.parseInt(dayofTheMonth.getValue())));
            goPage1();
            clear();
        });
    }
    
    
    // Displays the panel for file and folder selection
    // and instantiates a Trigger object of type FilePresenceTrigger
    @FXML
    private void filePresenceTriggerCreationProcess(ActionEvent event) {
        showInput();
        filePresenceTriggerPane1.setVisible(true);
        
        addTriggerButton.disableProperty().bind(Bindings.isEmpty(filePresenceText.textProperty()).or(chosenDirectory.textProperty().isEmpty()));
        filePresenceTriggerButton.setOnAction(e -> {
            selectDirectory("Select a directory" );
        });
        addTriggerButton.setOnAction(e -> {
            createdTrigger.add(new FilePresenceTrigger(filePresenceText.getText(),selectedFile));
            goPage1();
            clear();
        });
    }

    // Checks the size of a file
    @FXML
    private void fileSizeTriggerCreationProcess(ActionEvent event) {
        showInput();
        fileSizeTriggerPane.setVisible(true);
        
       addTriggerButton.disableProperty().bind(chosenFile.textProperty().isEmpty()); 
        fileSizeTriggerButton.setOnAction(e -> {
            selectFile("Select a file",new FileChooser.ExtensionFilter("All Files", "*.*") );
        });
        addTriggerButton.setOnAction(e -> {
            createdTrigger.add(new FileSizeTrigger(selectedFile,unitSizeComboBox.getValue(),fileSizeTriggerSpinner.getValue()));
            goPage1();
            clear();
        });
    }
    
    // Checks the exit status of an external program
    @FXML
    private void externalProgramTriggerProcess(ActionEvent event) {
        showInput();
        vBoxExternalProgram.setVisible(true);
        
        addTriggerButton.disableProperty().bind(argumentsTextTrigger.textProperty().isEmpty()); 
        externalProgramButton.setOnAction(e -> {
            selectFile("Select a Program or a file as argument",new FileChooser.ExtensionFilter("All Files", "*") );
            if (selectedFile != null) {
                StringBuilder filePaths = new StringBuilder();
                // to Append the selected file path to the StringBuilder
                filePaths.append(selectedFile.getAbsolutePath()).append(" ");
                // to Update the text in argumentsText
                argumentsTextTrigger.appendText(filePaths.toString() + " ");

                chosenFile.setText(selectedFile.getName());
            }
        });
         addTriggerButton.setOnAction(e -> {
            String[] arguments = argumentsTextTrigger.getText().split("\\s+");
            createdTrigger.add(new ExternalProgramTrigger(exitCodeSpinner.getValue(),arguments[0],Arrays.copyOfRange(arguments, 1, arguments.length)));
            goPage1();
            clear();
        });
    }
    
    //USEFUL FUNCTIONS 
    private void goPage1(){
        triggerPage1.setVisible(true);
        triggerPage2.setVisible(false);
    }
    
    private void goPage2(){
        triggerPage1.setVisible(false);
        triggerPage2.setVisible(true);
        menuTrigger.setDisable(false);
    }
    
    
    // cleanses all the fields 
    private void clear(){
        chosenFile.setText("");
        chosenDirectory.setText("");
        hoursComboBox.setValue("hh");
        minutesComboBox.setValue("mm");
        dayOfWeekComboBox.setValue("days");
        dayofTheMonth.setValue("day");
        unitSizeComboBox.setValue("KB");
        filePresenceText.setText("");
        inputPane.setVisible(false);
        timeTriggerPane.setVisible(false);
        dayOfWeekTriggerPane.setVisible(false);
        dateTriggerPane.setVisible(false);
        vBoxDayOfTheMonth.setVisible(false);
        datePickerTrigger.setValue(null);
        fileSizeTriggerPane.setVisible(false);
        filePresenceTriggerPane1.setVisible(false);
        vBoxExternalProgram.setVisible(false);
    }
    
    // Initializing the values in the ComboBoxes
    private void fillComboBox(){
        // time trigger section 
        for (int i = 0; i < 24; i++) {
            hoursComboBox.getItems().add(String.format("%02d", i)); // adds hours (00-23)
        }
        hoursComboBox.setValue("hh"); // Imposta un valore predefinito

        for (int i = 0; i < 60; i++) {
            minutesComboBox.getItems().add(String.format("%02d", i)); // adds minutes(00-59)
        }
        minutesComboBox.setValue("mm");
        
        //DayOfWeekTrigger section
        for (DayOfWeek c : DayOfWeek.values())
            dayOfWeekComboBox.getItems().add(c.toString().toLowerCase());
        dayOfWeekComboBox.setValue("days");
        
        //DayOfMonthTrigger section
        for (int i=1;i <=31; i++)
            dayofTheMonth.getItems().add(String.format("%02d", i));
        dayofTheMonth.setValue("day");    
        
        //FileSize Trigger section
        String[] byteUnits = {"B", "KB", "MB", "GB"};
        unitSizeComboBox.getItems().addAll(byteUnits);
        unitSizeComboBox.setValue("KB");
        
    }
    
    private void showInput(){
        menuTrigger.setDisable(true);
        inputPane.setVisible(true);
    }
    
    // Reusable functionality for selecting the file
    private void selectFile(String title, FileChooser.ExtensionFilter filter){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().add(filter);
        Window ownerWindow = null;
        selectedFile = fileChooser.showOpenDialog(ownerWindow);
        if(selectedFile != null)
            chosenFile.setText(selectedFile.getName());
    }
    
    // Reusable functionality for selecting the directory
    private void selectDirectory(String title){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(title);
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Window ownerWindow = null;
         selectedFile = directoryChooser.showDialog(ownerWindow);
         if(selectedFile != null)
            chosenDirectory.setText(selectedFile.getName());
    }

}