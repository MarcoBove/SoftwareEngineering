/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    private Trigger t;
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
    private AnchorPane triggerTimePane;
    @FXML
    private Button addTimeTrigger;
    @FXML
    private ComboBox<String> hoursComboBox;
    @FXML
    private ComboBox<String> minutesComboBox;

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
        addTimeTrigger.disableProperty().bind(hoursComboBox.valueProperty().isEqualTo("hh")
                .or(minutesComboBox.valueProperty().isEqualTo("mm")));
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
    private void timeTriggerCreationProcess(ActionEvent event) {
        triggerTimePane.setVisible(true);
    }

    @FXML
    private void retryTimeTriggerCreation(ActionEvent event) {
        triggerPage1.setVisible(true);
        triggerTimePane.setVisible(false);
        triggerPage2.setVisible(false);
    }

    @FXML
    private void addTimeTrigger(ActionEvent event) {
        t = new TimeTrigger(LocalTime.of(Integer.parseInt(hoursComboBox.getValue()), Integer.parseInt(minutesComboBox.getValue())));
        createdTrigger.add(t);

        hoursComboBox.setValue("hh");
        minutesComboBox.setValue("mm");
        triggerTimePane.setVisible(false);
        triggerPage1.setVisible(true);
        triggerPage2.setVisible(false);
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
    }
}
