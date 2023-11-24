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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Rule;
import model.SceneManager;
import model.Trigger;

/**
 * FXML Controller class
 *
 * @author Andre
 */
public class NewTriggerPageController implements Initializable {

    private static SceneManager sceneManager;
    private static Rule rule;
    
    @FXML
    private AnchorPane triggerPage;
    @FXML
    private StackPane createTrigger;
    @FXML
    private AnchorPane triggerPage1;
    @FXML
    private Button deleteTrigger1Button;
    @FXML
    private Button addTrigger1Button;
    @FXML
    private Button cancelTrigger1Button;
    @FXML
    private Button nextTrigger1Button;
    @FXML
    private TableView<Trigger> trigger1Table;
    @FXML
    private TableColumn<Trigger, String> trigger1TableName;
    @FXML
    private AnchorPane triggerPage2;
    @FXML
    private ChoiceBox<?> choiceBoxTrigger2;
    @FXML
    private VBox vBoxInputTrigger2;
    @FXML
    private Button cancelTrigger2Button;
    @FXML
    private Button nextTrigger2Button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneManager = SceneManager.getInstance();
    }    

    @FXML
    private void deleteTrigger1ButtonAction(ActionEvent event) {
    }

    @FXML
    private void addTrigger1ButtonAction(ActionEvent event) {
    }

    @FXML
    private void cancelTrigger1ButtonAction(ActionEvent event) {
        rule = null;
        sceneManager.changeScene("/view/new_rule_page.fxml","New Rule Page");
    }

    @FXML
    private void nextTrigger1ButtonAction(ActionEvent event) {
    }

    @FXML
    private void trigger1TableNameCancel(TableColumn.CellEditEvent<Trigger,String> event) {
    }

    @FXML
    private void trigger1TableNameCommit(TableColumn.CellEditEvent<Trigger,String> event) {
    }

    @FXML
    private void choiceBoxTrigger2Done(DragEvent event) {
    }

    @FXML
    private void cancelTrigger2ButtonAction(ActionEvent event) {
    }

    @FXML
    private void nextTrigger2ButtonAction(ActionEvent event) {
    }
    
    public static void setRule(Rule r){
        rule=r;
    }
    
}
