/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author marco
 */
public class RulesManager {

    private static RulesManager instance = null;
    private LinkedList<Rule> rules;
    private File file;

    public RulesManager() {
        this.rules = new LinkedList();
        File dataFolder = new File("data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        file = new File(dataFolder, "memory.dat");

    }

    public Rule getRule(Rule rule) {
        return rules.get(rules.indexOf(rule));
    }

    public LinkedList<Rule> getRules() {
        return rules;
    }

    public boolean addRule(Rule rule) {
        return rules.add(rule);

    }

    public Rule getLast() {
        return rules.getLast();
    }

    public boolean removeRule(Rule rule) {

        return rules.remove(rule);
    }

    public void removeLast() {

        rules.removeLast();
    }

    public static RulesManager getInstance() {
        if (instance == null) {
            instance = new RulesManager();
        }
        return instance;
    }

    public void checkRules() {

        if (!rules.isEmpty()) {
            for (Rule rule : rules) {

                rule.ruleActivation();

            }
        }
    }
    
    public void setFile(File file){
        this.file = file;
    }
    
    public File getFile(){
        return this.file;
    }

    public void saveRules() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(rules);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadRules() {

        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

                rules = (LinkedList<Rule>) ois.readObject();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isEmpty(){
        return this.rules.isEmpty();
    }
    
    public  List<Action> getActions(){
         ArrayList<Action> actions = new ArrayList();
         for(Rule rule : rules){
             Action a = rule.getAction();
             try{
                 actions.addAll(a.getAction());
             }
             catch(UnsupportedOperationException e){
                 actions.add(a);
             }
        }
        return actions;
    }
}