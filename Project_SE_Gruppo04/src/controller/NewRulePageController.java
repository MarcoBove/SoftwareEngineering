/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
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
import model.Rule;
import model.RulesManager;

/**
 * FXML Controller class
 *
 * @author gruppo_04
 */
public class NewRulePageController implements Initializable {
    
    private ScenesController scenesController;
    private RulesManager rulesManager;
    
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
        scenesController = ScenesController.getInstance();
        rulesManager = RulesManager.getInstance();
        //buttons settings
        nextRuleButton.disableProperty().bind(Bindings.createBooleanBinding(
                () -> ruleNameField.getText().isEmpty() || ruleDescriptionField.getText().isEmpty(),
                ruleNameField.textProperty(),
                ruleDescriptionField.textProperty()
        ));
        // sleepingPeriodVBox settings
        sleepingPeriodVBox.disableProperty().bind(sleepingPeriodCheckBox.selectedProperty().not());
        Tooltip.install(sleepingPeriodHBox, new Tooltip("Enter the sleeping period\nSetting it to 0 means that the rule will not be repeated."));
        setUpSpinners();
    }    

    //BUTTONS
    @FXML
    private void cancelRuleButtonAction(ActionEvent event) {
        scenesController.changeScene("/view/homePage.fxml","Home Page");
    }

    @FXML
    private void nextRuleButtonAction(ActionEvent event) {
        // Create a Rule
        if(sleepingPeriodVBox.isDisable())
            rulesManager.addRule(new Rule(ruleNameField.getText(),ruleDescriptionField.getText(),
                 Duration.ZERO));
        else
            rulesManager.addRule(new Rule(ruleNameField.getText(),ruleDescriptionField.getText(),
                 Duration.ofDays(ruleDaysSpinner.getValue()).plusHours(ruleHoursSpinner.getValue()).plusMinutes(ruleMinutesSpinner.getValue())));
        
        scenesController.changeScene("/view/new_trigger_page.fxml","New Trigger Page");
    }
    
    //USEFUL FUNCTIONS

    private void setUpSpinners() {
       
        // Add a listener to filter inputs in the editor field of the hours Spinner
        TextField minutesEditor = ruleMinutesSpinner.getEditor();
        minutesEditor.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                if (!newValue.isEmpty()) {
                    minutesEditor.setText(oldValue); // Restore the previous value if the new value is not empty but contains non-numeric characters
                }
            }
        });

        
        // Add a listener to filter inputs in the editor field of the hours Spinner
        TextField hoursEditor = ruleHoursSpinner.getEditor();
        hoursEditor.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                if (!newValue.isEmpty()) {
                    hoursEditor.setText(oldValue); // Restore the previous value if the new value is not empty but contains non-numeric characters
                }
            }
        });

        
        // Add a listener to filter inputs in the editor field of the Days Spinner
        TextField daysEditor = ruleDaysSpinner.getEditor();
        daysEditor.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                if (!newValue.isEmpty()) {
                    daysEditor.setText(oldValue); // Restore the previous value if the new value is not empty but contains non-numeric characters 
                }
            }
        });

    }

}


