/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;

/**
 *
 * @author schet
 */
public class FileDeleteAction extends FileAction{

    public FileDeleteAction(File file) {
        super(file);
    }
    

    @Override
    public String getDescription() {
        return "File Delete Action of:  " + super.toString();
    }

    @Override
    public void execute() {
        if(super.getFile().exists()){
            super.getFile().delete();
        }
    }
    
}
