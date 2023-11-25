/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
    private TableColumn<Rule, String> rulesTableState;
    @FXML
    private TextArea LogArea;
    private ObservableList<Rule> rules;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneManager = SceneManager.getInstance();
        ruleManager = RuleManager.getInstance();
        
        rules = FXCollections.observableArrayList(ruleManager.getRules());
        rulesTable.setItems(rules);    
        rulesTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        removeRuleButton.setDisable(true);
        
    }    


    @FXML
    private void removeRuleButtonAction(ActionEvent event) {
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
    private void rulesTableStateCancel(TableColumn.CellEditEvent<Rule,Rule> event) {
    }

    @FXML
    private void rulesTableStateCommit(TableColumn.CellEditEvent<Rule,Rule> event) {
    }
    
    
}
