/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author gruppo_04
 */
public class DayOfWeekTrigger implements Trigger {

    private DayOfWeek day;

    public DayOfWeekTrigger(DayOfWeek day) {
        this.day = day;
    }
    
    //Checks if the current day pf the week matches the stored DayOfWeek value
    @Override
    public boolean check() {
        return LocalDate.now().getDayOfWeek().equals(day);
    }

    @Override
    public String getDescription() {
        return " Trigger Type: Day of Week:  "+ day.toString();
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
