/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author 39327
 */
public class FileMoveAction extends FileAction{
    
    private final File selectedDirectory;
    public FileMoveAction (File selectedFile, File selectedDirectory){
       super(selectedFile);
       this.selectedDirectory=selectedDirectory;
        
    }
    @Override
    public String getDescription() {
    return super.toString() + "      moved into:       " + selectedDirectory;
    }

    @Override
    public void execute() {
     if (super.getFile() != null && selectedDirectory != null) {
            Path source = Paths.get(super.getFile().getAbsolutePath());
            Path destination = Paths.get(selectedDirectory.getAbsolutePath());

            try {
                // Sposta il file dalla cartella sorgente a quella di destinazione
                Files.move(source, destination.resolve(source.getFileName()));
                System.out.println("File moved ");
            } catch (IOException e) {
                System.err.println("Error " + e.getMessage());
            }
        } else {
            System.out.println("aborted");
        }
    }
    
}
