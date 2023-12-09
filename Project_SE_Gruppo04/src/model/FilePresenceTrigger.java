/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;

/**
 *
 * @author gruppo04
 */
public class FilePresenceTrigger implements Trigger{
    
    // Attributes
    private final String fileName;
    private final File directory;

    // Constructor
    public FilePresenceTrigger(String fileName,File directory) {
        this.fileName = fileName;
        this.directory = directory;
    }

    // Method that cheks the condition of the trigger
    @Override
    public boolean check() {
        File file = new File(directory, fileName);
        return file.exists();
    }

    // Method to get the description of the trigger
    @Override
    public String getDescription() {
        return " Trigger Type: File Presence Trigger:  File: " + fileName  + ", Directory: "+ directory.getName();
    }

    //Only for composite triggers.
    @Override
    public void addTrigger(Trigger t) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
