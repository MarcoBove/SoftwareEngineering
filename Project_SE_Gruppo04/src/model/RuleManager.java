/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class RuleManager {

    static private RuleManager instance = null;
    private ArrayList<Rule> rules;

    public RuleManager() {
        this.rules = new ArrayList();
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }

    public boolean addRule(Rule rule) {
        return rules.add(rule);

    }
    
    public Rule getLast(){
        return rules.get(rules.size()-1);
    }

    public boolean removeRule(Rule rule) {

        return rules.remove(rule);
    }
    
     public void removeLast() {

        rules.remove(rules.size() -1);
    }

    public static RuleManager getInstance() {
        if (instance == null) {
            instance = new RuleManager();
        }
        return instance;
    }

    public void checkRules() {
        
//forse si deve fare nel main 
        Thread t = new Thread(() -> {
            
                for (Rule r : rules) {
                    r.ruleActivation();
                }

                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            
        });

        t.start();
        try {
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}