/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.util.Observable;

public class DisplayMessageAction extends Observable implements Action {
    private final String message;

    public DisplayMessageAction(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        setChanged();
        notifyObservers(message);
    }

    @Override
    public String getDescription() {
        return "Display Message Action of: " + this.message;
    }

    @Override
    public void log(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Action> getAction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
