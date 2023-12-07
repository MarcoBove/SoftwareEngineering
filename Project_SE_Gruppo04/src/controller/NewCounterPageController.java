/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.Counter;
import model.RulesManager;

/**
 * FXML Controller class
 *
 * @author Andre
 */
public class NewCounterPageController implements Initializable {

     private ScenesController sceneManager;
     private RulesManager ruleManager;
     private ObservableList<Counter> createdCounter;
    
    @FXML
    private TextField counterName;
    @FXML
    private Button createCounterButton;
    @FXML
    private TableView<Counter> countersTable;
    @FXML
    private TableColumn<Counter,String> countersTableName;
    @FXML
    private TableColumn<Counter,Integer> countersTableValue;
    @FXML
    private Spinner<Integer> valueCounterSpinner;
    @FXML
    private Button closeCounterPage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         sceneManager = ScenesController.getInstance();

        // counter visualization
        createdCounter = FXCollections.observableArrayList();
        countersTable.setItems(createdCounter);
        countersTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        countersTableValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        
        countersTableValue.setEditable(true);
        countersTable.setEditable(true);
        
        
        countersTableValue.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        countersTableValue.setOnEditCommit(event -> {
            Counter counter = event.getRowValue();
            counter.setValue(event.getNewValue());
        });
        
        createCounterButton.disableProperty().bind(Bindings.isEmpty(counterName.textProperty()));
    }    

    @FXML
    private void createCounterAction(ActionEvent event) {
        createdCounter.add(new Counter(counterName.getText(),valueCounterSpinner.getValue().intValue()));
        clear();
    }

    
    private void clear(){
        counterName.setText("");
        valueCounterSpinner.getValueFactory().setValue(0);
    }

    @FXML
    private void closeCounterPageAction(ActionEvent event) {
         sceneManager.changeScene("/view/homePage.fxml", "Home Page");
    }

}
