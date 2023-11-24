/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Rule;
import model.SceneManager;

/**
 * FXML Controller class
 *
 * @author Andre
 */
public class NewRulePageController implements Initializable {

    private static SceneManager sceneManager;
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneManager = SceneManager.getInstance();
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
        //NewTriggerPageController.setRule(new Rule(ruleNameField.getText(),ruleDescriptionField.getText(),
        //        Duration.ofDays(ruleDaysSpinner.getValue()).plusHours(ruleHoursSpinner.getValue()).plusMinutes(ruleMinutesSpinner.getValue())));
        NewTriggerPageController.setRule(new Rule(ruleNameField.getText(),ruleDescriptionField.getText(),Duration.ZERO));
        sceneManager.changeScene("/view/new_trigger_page.fxml","New Trigger Page");
    }
    
}
