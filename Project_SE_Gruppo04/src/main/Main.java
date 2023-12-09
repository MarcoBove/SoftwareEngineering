/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package main;

import controller.AlarmActionController;
import controller.DisplayMessageController;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.RulesManager;
import controller.ScenesController;
import java.io.File;
import java.util.List;
import model.Action;
import model.AlarmAction;
import model.DisplayMessageAction;

/**
 *
 * @author gruppo_04
 */
public class Main extends Application {

    private static final int PERIOD_SECONDS = 5;
    private static File save_file;

    @Override
    public void start(Stage stage) throws Exception {
        ScenesController s = ScenesController.getInstance();
        RulesManager r = RulesManager.getInstance();
        stage.setWidth(1000);
        stage.setHeight(700);
        s.setPrimaryStage(stage);
        setSaveFile();
        r.uploadRules(save_file);
        addObserver();
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
        ScenesController.getInstance().getPrimaryStage().setOnCloseRequest(windowEvent -> {
             if(!r.isEmpty()){
                    if(r.getLast().getTrigger() == null || r.getLast().getAction() == null)                   //remove empty rule (if the user closes the application while creating a rule)
                        r.removeLast();}
            r.saveRules(save_file);
            scheduler.shutdown();
            Platform.exit();
            System.exit(0);
        });
    }
    
    private void addObserver(){
        RulesManager r = RulesManager.getInstance();
        List<Action> actions = r.getActions();
        for(Action a : actions){
            if(a instanceof AlarmAction){
                AlarmAction aa=  (AlarmAction) a;
                aa.addObserver(new AlarmActionController());
            }
            if(a instanceof DisplayMessageAction){
                DisplayMessageAction da=  (DisplayMessageAction) a;
                da.addObserver(new DisplayMessageController());
            }
        }
    }
    private void setSaveFile(){
        File dataFolder = new File("data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        save_file = new File(dataFolder, "memory.dat");
    }
}
