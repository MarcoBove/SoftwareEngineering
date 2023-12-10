/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author gruppo_04
 */
public interface Trigger extends Serializable {
    
    public boolean check();
    public String getDescription();
    public void addTrigger(Trigger t);
    public List<Trigger> getTriggers();
}
