/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 *
 * @author gruppo_04
 */
public class ScenesController {

    private static ScenesController instance;
    private Stage primaryStage;  // Main stage of the application

    private ScenesController() {
        // Private constructor to prevent external instantiation
    }

    public static ScenesController getInstance() {  //singleton implementation
        if (instance == null) {
            instance = new ScenesController();
            
        }
        return instance;
    }

    public void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
    
    public Stage getPrimaryStage(){
        return primaryStage;
    }

    // Changes the current scene to the specified FXML file path and title
    public void changeScene(String fxmlPath, String title) {
        try {
        //save current dimensions    
        double oldWidth = primaryStage.getWidth();
        double oldHeight = primaryStage.getHeight();
        // Load the new layout with its controller
        FXMLLoader loader = new FXMLLoader(ScenesController.class.getResource(fxmlPath));
        Parent root = loader.load();

        // Set the new scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        
        // Set the current dimensions
        primaryStage.setWidth(oldWidth);
        primaryStage.setHeight(oldHeight);
        //show the stage
        primaryStage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Error");
        }
    }

    // Closes the current window
    public void closeCurrentScene() {
        Stage stage = (Stage) primaryStage.getScene().getWindow();
        stage.close();
    }
}

