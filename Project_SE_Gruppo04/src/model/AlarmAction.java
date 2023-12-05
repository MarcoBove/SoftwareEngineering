/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;

/**
 *
 * @author 39327
 */



public class AlarmAction extends FileAction{
    public AlarmAction(File file) {
        super(file);
    }

    @Override
    public String getDescription() {
        return "Alarm Action: " + super.toString();
    }

    @Override
    public void execute() {
        if (getFile().exists()) {
            setChanged();
            notifyObservers(getFile()); 
        } else {
            
        }
    }

    @Override
    public void log(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
