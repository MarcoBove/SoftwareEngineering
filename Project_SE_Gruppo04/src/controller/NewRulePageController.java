/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;
import model.Rule;
import model.RulesManager;

/**
 * FXML Controller class
 *
 * @author Andre
 */
public class NewRulePageController implements Initializable {
    
    private ScenesController sceneManager;
    private RulesManager ruleManager;
    
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
    private Button nextRuleButton;
    @FXML
    private CheckBox sleepingPeriodCheckBox;
    @FXML
    private VBox sleepingPeriodVBox;
    @FXML
    private HBox sleepingPeriodHBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneManager = ScenesController.getInstance();
        ruleManager = RulesManager.getInstance();
        nextRuleButton.disableProperty().bind(Bindings.createBooleanBinding(
                () -> ruleNameField.getText().isEmpty() || ruleDescriptionField.getText().isEmpty(),
                ruleNameField.textProperty(),
                ruleDescriptionField.textProperty()
        ));
        sleepingPeriodVBox.disableProperty().bind(sleepingPeriodCheckBox.selectedProperty().not());
        // Aggiungere il Tooltip all'HBox
        Tooltip.install(sleepingPeriodHBox, new Tooltip("Enter the sleeping period\nSetting it to 0 means that the rule will not be repeated."));
    }    

    @FXML
    private void cancelRuleButtonAction(ActionEvent event) {
        sceneManager.changeScene("/view/homePage.fxml","Home Page");
    }

    @FXML
    private void nextRuleButtonAction(ActionEvent event) {
        // Create a Rule
        if(sleepingPeriodVBox.isDisable())
            ruleManager.addRule(new Rule(ruleNameField.getText(),ruleDescriptionField.getText(),
                 Duration.ZERO));
        else
            ruleManager.addRule(new Rule(ruleNameField.getText(),ruleDescriptionField.getText(),
                 Duration.ofDays(ruleDaysSpinner.getValue()).plusHours(ruleHoursSpinner.getValue()).plusMinutes(ruleMinutesSpinner.getValue())));
        sceneManager.changeScene("/view/new_trigger_page.fxml","New Trigger Page");
    }
}

