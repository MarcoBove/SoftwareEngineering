/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;

/**
 *
 * @author Andre
 */
public class FilePresenceTrigger implements Trigger{
    
     private String fileName;
     private File directory;

    public FilePresenceTrigger(String fileName,File directory) {
        this.fileName = fileName;
        this.directory = directory;
    }

    @Override
    public boolean check() {
        File file = new File(directory, fileName);
        return file.exists();
    }

    @Override
    public String getDescription() {
        return " Trigger Type: File Presence Trigger:  File: " + fileName  + ", Directory: "+ directory.getName();
    }

    @Override
    public void addTrigger(Trigger t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}