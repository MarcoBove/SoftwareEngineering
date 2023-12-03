/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
}
