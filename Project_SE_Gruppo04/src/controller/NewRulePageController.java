/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Rule;
import model.RulesManager;
import model.ScenesManager;

/**
 * FXML Controller class
 *
 * @author Andre
 */
public class NewRulePageController implements Initializable {

    private ScenesManager sceneManager;
    private RulesManager ruleManager;
    
    @FXML
    private AnchorPane RulePage;
    @FXML
    private TextField ruleNameField;
    @FXML
    private TextArea ruleDescriptionField;
    @FXML
    private Spinner<Integer> ruleDaysSpinner;
    @FXML
    private Spinner<Integer> ruleHoursSpinner;
    @FXML
    private Spinner<Integer> ruleMinutesSpinner;
    @FXML
    private Button cancelRuleButton;
    @FXML
    private Button nextRuleButton;
    
    private static HomePageController pagina1Controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneManager = ScenesManager.getInstance();
        ruleManager = RulesManager.getInstance();
        nextRuleButton.disableProperty().bind(Bindings.createBooleanBinding(
                () -> ruleNameField.getText().isEmpty() || ruleDescriptionField.getText().isEmpty(),
                ruleNameField.textProperty(),
                ruleDescriptionField.textProperty()
        ));
    
       
    }    

    @FXML
    private void fieldRuleNameAction(ActionEvent event) {
    }

    @FXML
    private void cancelRuleButtonAction(ActionEvent event) {
        sceneManager.changeScene("/view/homePage.fxml","Home Page");
    }

    @FXML
    private void nextRuleButtonAction(ActionEvent event) {
        // Create a Rule
        ruleManager.addRule(new Rule(ruleNameField.getText(),ruleDescriptionField.getText(),
                Duration.ofDays(ruleDaysSpinner.getValue()).plusHours(ruleHoursSpinner.getValue()).plusMinutes(ruleMinutesSpinner.getValue())));
      
        
        sceneManager.changeScene("/view/new_trigger_page.fxml","New Trigger Page");
    }
    
}
