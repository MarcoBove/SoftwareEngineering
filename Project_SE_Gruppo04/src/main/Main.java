/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.SceneManager;
/**
 *
 * @author Andre
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        SceneManager s = SceneManager.getInstance();
        s.setPrimaryStage(stage);
        s.changeScene("/view/homePage.fxml","Home Page");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
