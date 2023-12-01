/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.DateTrigger;
import model.DayOfWeekTrigger;
import model.RulesManager;
import model.ScenesManager;
import model.TimeTrigger;
import model.Trigger;

/**
 * FXML Controller class
 *
 * @author Andre
 */
public class NewTriggerPageController implements Initializable {

    private ScenesManager sceneManager;
    private RulesManager ruleManager;
    private ObservableList<Trigger> createdTrigger;

    @FXML
    private AnchorPane triggerPage1;
    @FXML
    private Button deleteTrigger1Button;
    @FXML
    private Button addTrigger1Button;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneManager = ScenesManager.getInstance();
        ruleManager = RulesManager.getInstance();

        // trigger visualization
        createdTrigger = FXCollections.observableArrayList();
        trigger1Table.setItems(createdTrigger);
        trigger1TableName.setCellValueFactory(new PropertyValueFactory<>("description"));
        deleteTrigger1Button.disableProperty().bind(trigger1Table.getSelectionModel().selectedItemProperty().isNull());
        nextTrigger1Button.disableProperty().bind(Bindings.isEmpty(createdTrigger));
        fillComboBox();
        addTrigger1Button.disableProperty().bind(Bindings.isNotEmpty(createdTrigger));
    }

    @FXML
    private void deleteTrigger1ButtonAction(ActionEvent event) {
        createdTrigger.remove(trigger1Table.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void addTrigger1ButtonAction(ActionEvent event) {
        triggerPage1.setVisible(false);
        triggerPage2.setVisible(true);
        menuTrigger.setDisable(false);
        addTriggerButton.setDisable(true);
    }

    @FXML
    private void cancelTrigger1ButtonAction(ActionEvent event) {
        ruleManager.removeLast();
        sceneManager.changeScene("/view/new_rule_page.fxml", "New Rule Page");
    }

    @FXML
    private void nextTrigger1ButtonAction(ActionEvent event) {
        ruleManager.getLast().setTrigger(createdTrigger.get(0));
        sceneManager.changeScene("/view/new_action_page.fxml", "New Action Page");
    }
    
    
    @FXML
    private void retryTriggerCreation(ActionEvent event) {
        clear();
        inputPane.setVisible(false);
        triggerPage2.setVisible(false);
        triggerPage1.setVisible(true);
    }

    @FXML
    private void timeTriggerCreationProcess(ActionEvent event) {
        menuTrigger.setDisable(true);
        inputPane.setVisible(true);
        timeTriggerPane.setVisible(true);
        //add button
        addTriggerButton.disableProperty().bind(hoursComboBox.valueProperty().isEqualTo("hh") .or(minutesComboBox.valueProperty().isEqualTo("mm")));
        addTriggerButton.setOnAction(e -> {
            createdTrigger.add(new TimeTrigger(LocalTime.of(Integer.parseInt(hoursComboBox.getValue()), Integer.parseInt(minutesComboBox.getValue()))));
            triggerPage1.setVisible(true);
            triggerPage2.setVisible(false);
            clear();
        });
    }
    
    @FXML
    private void dayOfWeekTriggerCreationProcess(ActionEvent event) {
        menuTrigger.setDisable(true);
        inputPane.setVisible(true);
        dayOfWeekTriggerPane.setVisible(true);
        //add button
        addTriggerButton.disableProperty().bind(dayOfWeekComboBox.valueProperty().isEqualTo("days"));
        addTriggerButton.setOnAction(e -> {
            createdTrigger.add(new DayOfWeekTrigger(DayOfWeek.valueOf(dayOfWeekComboBox.getValue().toUpperCase())));
            triggerPage1.setVisible(true);
            triggerPage2.setVisible(false);
            clear();
        });
    }
    
    @FXML
    private void dateTriggerCreationProcess(ActionEvent event) {
        menuTrigger.setDisable(true);
        inputPane.setVisible(true);
        dateTriggerPane.setVisible(true);
        //add button
        addTriggerButton.disableProperty().bind(datePickerTrigger.valueProperty().isNull());
        addTriggerButton.setOnAction(e -> {
            createdTrigger.add(new DateTrigger(datePickerTrigger.getValue()));
            triggerPage1.setVisible(true);
            triggerPage2.setVisible(false);
            clear();
        });
        
        
    }
    
    private void clear(){
        hoursComboBox.setValue("hh");
        minutesComboBox.setValue("mm");
        dayOfWeekComboBox.setValue("days");
        inputPane.setVisible(false);
        timeTriggerPane.setVisible(false);
        dayOfWeekTriggerPane.setVisible(false);
        dateTriggerPane.setVisible(true);
    }
    
    private void fillComboBox(){
        // time trigger section 
        for (int i = 0; i < 24; i++) {
            hoursComboBox.getItems().add(String.format("%02d", i)); // Aggiungi le ore (00-23)
        }
        hoursComboBox.setValue("hh"); // Imposta un valore predefinito

        for (int i = 0; i < 60; i++) {
            minutesComboBox.getItems().add(String.format("%02d", i)); // Aggiungi i minuti (00-59)
        }
        minutesComboBox.setValue("mm");
        
        for (DayOfWeek c : DayOfWeek.values())
            dayOfWeekComboBox.getItems().add(c.toString().toLowerCase());
        dayOfWeekComboBox.setValue("days");
    } 
}
