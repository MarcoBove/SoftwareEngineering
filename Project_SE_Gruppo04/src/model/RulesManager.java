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
public class RulesManager {

    private static RulesManager instance = null;
    private ArrayList<Rule> rules;

    public RulesManager() {
        this.rules = new ArrayList();
    }

     public Rule getRule(Rule rule){
      return rules.get(rules.indexOf(rule));
    }
    
    public ArrayList<Rule> getRules() {
        return rules;
    }

    public boolean addRule(Rule rule) {
        return rules.add(rule);

    }

    public Rule getLast() {
        return rules.get(rules.size() - 1);
    }

    public boolean removeRule(Rule rule) {

        return rules.remove(rule);
    }

    public void removeLast() {

        rules.remove(rules.size() - 1);
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

}