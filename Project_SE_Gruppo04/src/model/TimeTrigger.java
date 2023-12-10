/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author gruppo_04
 */
public class TimeTrigger implements Trigger{
    private LocalTime timeOfDay;

    public TimeTrigger(LocalTime timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    // Check if the current time matches the stored LocalTime value
    @Override
    public boolean check() {
        if((timeOfDay.getHour() == LocalTime.now().getHour()) && (timeOfDay.getMinute() == LocalTime.now().getMinute()) ){
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return " Trigger Type: Time "+ timeOfDay.toString();
    }

    @Override //Only for composite triggers."
    public void addTrigger(Trigger t) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Trigger> getTriggers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
