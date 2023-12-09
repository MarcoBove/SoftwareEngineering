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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Counter;
import model.CustomIntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author gruppo_04
 */
public class NewCounterPageController implements Initializable {

     private ScenesController sceneManager;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         sceneManager = ScenesController.getInstance();

        // counter visualization
        createdCounter = FXCollections.observableArrayList();
        countersTable.setItems(createdCounter);
        
        // to initialize the value of the colunms
        countersTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        countersTableValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        
        countersTableValue.setEditable(true);
        countersTable.setEditable(true);
        
        //In the moment of modification, only numerical values are accepted for the column.
        countersTableValue.setCellFactory(TextFieldTableCell.forTableColumn(new CustomIntegerStringConverter()));
        
        createCounterButton.disableProperty().bind(Bindings.isEmpty(counterName.textProperty()));
    }    

    // button that creates the counter with the selected value
    @FXML
    private void createCounterAction(ActionEvent event) {
        createdCounter.add(new Counter(counterName.getText(),valueCounterSpinner.getValue().intValue()));
        clear();
    }

    
    //to Cleanse the fields
    private void clear(){
        counterName.setText("");
        valueCounterSpinner.getValueFactory().setValue(0);
    }

    // to  Close the counter visualization page
    @FXML
    private void closeCounterPageAction(ActionEvent event) {
         sceneManager.changeScene("/view/homePage.fxml", "Home Page");
    }

}


