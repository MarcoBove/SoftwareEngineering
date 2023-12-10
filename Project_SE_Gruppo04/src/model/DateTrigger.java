/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author gruppo_04
 */
public class DateTrigger implements Trigger {

    private LocalDate date;

    public DateTrigger(LocalDate date) {
        this.date = date;
    }

    //Checks if the current date matches the stored LocalDate value
    @Override
    public boolean check() {
        return LocalDate.now().equals(date);
    }

    @Override
    public String getDescription() {
        return " Trigger Type: Date :  "+ date.toString();
    }   

    @Override
    public void addTrigger(Trigger t) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Trigger> getTriggers() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
