/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.util.Observable;

/**
 *
 * @author gruppo_04
 */

//Abstract class of actions that operate on files.
public abstract class FileAction extends Observable implements Action{

    private File file;

    public FileAction(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "\nFile= " + file.getName();
    }
}
