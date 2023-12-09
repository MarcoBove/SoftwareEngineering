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
 *@author gruppo_04
 */
public class RulesManager {

    private static RulesManager instance = null;
    private LinkedList<Rule> rules;

    private RulesManager() {   // Private constructor to prevent external instantiation
        this.rules = new LinkedList();
    }
    
    public static RulesManager getInstance() {     //Implementation of the singleton pattern.
        if (instance == null) {
            instance = new RulesManager();
        }
        return instance;
    }

    public Rule getRule(Rule rule) {                    //Return the rule from the list.         
        if(!rules.contains(rule))
            return null;
        return rules.get(rules.indexOf(rule));
    }

    public LinkedList<Rule> getRules() {            //Return all the created rules.
        return rules;
    }

    public boolean addRule(Rule rule) {             //Add a rule to the list
        return rules.add(rule);
    }

    public Rule getLast() {                                 //Return the last created rule.
        if (!rules.isEmpty())
            return rules.getLast();
        else
            return null;
    }

    public boolean removeRule(Rule rule) {      //Remove a Rule from the list.
        return rules.remove(rule);
    }

    public void removeLast() {                      //Remove the last created rule.
        if(!rules.isEmpty())
            rules.removeLast();
    }

    public void checkRules() {                  //For each rule in the list, check whether it should be activated or not.
        if (!rules.isEmpty()) {
            for (Rule rule : rules) {
                rule.ruleActivation();
            }
        }
    }

    //Save the rules in the list to a file.
    public void saveRules(File file) {                               
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(rules);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Load the rules saved in the file into the list.
    public void uploadRules(File file) {                        
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
    
    public  List<Action> getActions(){              //Return the set of all actions associated with the present rules. Distinguish between simple and composite actions.
        if(rules.isEmpty()){
            return null;
        }
         ArrayList<Action> actions = new ArrayList();
         for(Rule rule : rules){
             Action a = rule.getAction();
             try{
                 actions.addAll(a.getAction());   //CompositeAction
             }
             catch(UnsupportedOperationException e){
                 actions.add(a);                       //Sample action
             }
        }
        return actions;
    }
}