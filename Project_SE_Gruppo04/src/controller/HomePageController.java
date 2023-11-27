/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

/**
 * FXML Controller class
 *
 * @author Andre
 */
public class HomePageController implements Initializable {

    private ScenesManager sceneManager;
    private RulesManager ruleManager;

    @FXML
    private Button removeRuleButton;
    @FXML
    private TableView<Rule> rulesTable;
    @FXML
    private TableColumn<Rule, String> rulesTableName;
    @FXML
    private TableColumn<Rule, Boolean> rulesTableState;
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneManager = ScenesManager.getInstance();
        ruleManager = RulesManager.getInstance();
        updateTable();   
        rulesTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        removeRuleButton.disableProperty().bind(rulesTable.getSelectionModel().selectedItemProperty().isNull());
        rulesTableState.setCellValueFactory(new PropertyValueFactory<>("enable"));
        rulesTableState.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(false,true)));
        rulesTableState.setEditable(true);  
    }

    @FXML
    private void removeRuleButtonAction(ActionEvent event) {
        if(confirmRuleDelete()){
            ruleManager.removeRule(rulesTable.getSelectionModel().getSelectedItem());
            updateTable();
        }
    }
        

    @FXML
    private void addRuleButtonAction(ActionEvent event) {
        sceneManager.changeScene("/view/new_rule_page.fxml","New Rule Page");
    }

    @FXML
    private void rulesTableStateCommit(TableColumn.CellEditEvent<Rule,Boolean> event) {
        Rule rule = event.getRowValue();
        ruleManager.getRule(rule).setEnable(event.getNewValue());
        updateTable();
    }
    
    private void updateTable() {
        // Aggiorna la tabella con i nuovi valori dalla lista di regole
        rulesTable.setItems(FXCollections.observableArrayList(ruleManager.getRules()));
    }
    
    private boolean confirmRuleDelete(){
        // Create a CONFIRMATION alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the rule?");

        // Create Cancel and OK buttons
        ButtonType buttonTypeCancel = new ButtonType("Cancel");
        ButtonType buttonTypeOK = new ButtonType("Delete");

        // Add buttons to the alert
        alert.getButtonTypes().setAll(buttonTypeCancel, buttonTypeOK);

       // Show the alert and wait for the user's response
        return alert.showAndWait().filter(response -> response == buttonTypeOK).isPresent();
    }
}


