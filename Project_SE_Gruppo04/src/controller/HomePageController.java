/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import model.*;

/**
 * FXML Controller class
 *
 * @author Andre
 */
public class HomePageController implements Initializable {

    @FXML
    private AnchorPane homePage;
    @FXML
    private TableView<Rule> tableRules;
    @FXML
    private TableColumn<Rule, String> tableRuleName;
    @FXML
    private TableColumn<Rule, String> tableRuleState;
    @FXML
    private Button removeRuleButton;
    @FXML
    private Button modifyRuleButton;
    @FXML
    private Button addRuleButton;
    @FXML
    private TextArea loggerRules;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void tableRuleNameCancel(TableColumn.CellEditEvent<Rule, String> event) {
    }

    @FXML
    private void tableRuleNameCommit(TableColumn.CellEditEvent<Rule, String> event) {
    }

    @FXML
    private void tableRuleStateCancel(TableColumn.CellEditEvent<Rule, String> event) {
    }

    @FXML
    private void tableRuleStateCommit(TableColumn.CellEditEvent<Rule, String> event) {
    }

    @FXML
    private void removeRuleButtonAction(ActionEvent event) {
    }

    @FXML
    private void modifyRuleButtonAction(ActionEvent event) {
    }

    @FXML
    private void addRuleButtonAction(ActionEvent event) {
    }
    
}
