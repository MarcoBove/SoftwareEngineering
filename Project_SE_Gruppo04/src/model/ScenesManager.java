/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Andre
 */
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScenesManager {

    private static ScenesManager instance;
    private Stage primaryStage;  // Main stage of the application

    private ScenesManager() {
        // Private constructor to prevent external instantiation
    }

    public static ScenesManager getInstance() {  //singleton implementation
        if (instance == null) {
            instance = new ScenesManager();
        }
        return instance;
    }

    public void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
    
    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public void changeScene(String fxmlPath, String title) {
        try {
            // Load the new layout with its controller
            FXMLLoader loader = new FXMLLoader(ScenesManager.class.getResource(fxmlPath));
            Parent root = loader.load();

            // Set the new scene
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Error");
        }
    }

    public void closeCurrentScene() {
        Stage stage = (Stage) primaryStage.getScene().getWindow();
        stage.close();
    }
}

