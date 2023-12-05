/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalTime;

/**
 *
 * @author schet
 */
public class TimeTrigger implements Trigger{
    private LocalTime timeOfDay;
    

    public TimeTrigger(LocalTime timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    
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

    @Override
    public void addTrigger(Trigger t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
