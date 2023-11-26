/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.RulesManager;
import model.ScenesManager;

/**
 *
 * @author Andre
 */
public class Main extends Application {

    private static final int PERIOD_SECONDS = 5;

    @Override
    public void start(Stage stage) throws Exception {
        ScenesManager s = ScenesManager.getInstance();
        s.setPrimaryStage(stage);
        initializeLoopCheckRules();
        s.changeScene("/view/homePage.fxml", "Home Page");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void initializeLoopCheckRules(){
        RulesManager r = RulesManager.getInstance();
        //initializes the rule checking loop
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                r.checkRules(); 
            });
        }, 0, PERIOD_SECONDS, TimeUnit.SECONDS);
        //when you close the window, the scheduler stops
        ScenesManager.getInstance().getPrimaryStage().setOnCloseRequest(windowEvent -> {
            scheduler.shutdown();
            Platform.exit();
            System.exit(0);
        });
    }
}