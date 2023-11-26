/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;

/**
 * FXML Controller class
 *
 * @author Andre
 */
public class HomePageController implements Initializable {

    private SceneManager sceneManager;
    private RuleManager ruleManager;
    
    @FXML
    private AnchorPane homePage;
    @FXML
    private Button removeRuleButton;
    @FXML
    private Button addRuleButton;
    @FXML
    private TableView<Rule> rulesTable;
    @FXML
    private TableColumn<Rule, String> rulesTableName;
    @FXML
    private TableColumn<Rule, Boolean> rulesTableState;
    @FXML
    private TextArea LogArea;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneManager = SceneManager.getInstance();
        ruleManager = RuleManager.getInstance();
        updateTable();   
        rulesTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        removeRuleButton.disableProperty().bind(rulesTable.getSelectionModel().selectedItemProperty().isNull());
        rulesTableState.setCellValueFactory(new PropertyValueFactory<>("enable"));
        rulesTableState.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(false,true)));
        rulesTableState.setEditable(true);
       
    }


    @FXML
    private void removeRuleButtonAction(ActionEvent event) {
        ruleManager.removeRule(rulesTable.getSelectionModel().getSelectedItem());
        updateTable();
    }


    @FXML
    private void addRuleButtonAction(ActionEvent event) {
        sceneManager.changeScene("/view/new_rule_page.fxml","New Rule Page");
    }

    @FXML
    private void rulesTableNameCancel(TableColumn.CellEditEvent<Rule,Rule> event) {
    }

    @FXML
    private void rulesTableNameCommit(TableColumn.CellEditEvent<Rule,Rule> event) {
    }

    @FXML
    private void rulesTableStateCancel(TableColumn.CellEditEvent<Rule,Boolean> event) {
        
        
    }

    @FXML
    private void rulesTableStateCommit(TableColumn.CellEditEvent<Rule,Boolean> event) {
        Rule rule= event.getRowValue();
        ruleManager.getRule(rule).setEnable(event.getNewValue());
        updateTable();
        System.out.println(ruleManager.getRule(rule).isEnable());
       
    }
    
    private void updateTable() {
        // Aggiorna la tabella con i nuovi valori dalla lista di regole
        rulesTable.setItems(FXCollections.observableArrayList(ruleManager.getRules()));
    }
}
