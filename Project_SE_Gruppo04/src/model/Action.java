/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 39327
 */
public interface Action extends Serializable{
    public String getDescription();
    public void execute();
    public void log(String filePath);
    public void addAction(Action action);
    public void removeAction(Action actionToRemove);
    public  List<Action> getAction();
}
