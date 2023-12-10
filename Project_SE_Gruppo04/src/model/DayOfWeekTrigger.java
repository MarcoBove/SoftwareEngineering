/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * @author Andre
 */
public class DayOfWeekTrigger implements Trigger {

    private DayOfWeek day;

    public DayOfWeekTrigger(DayOfWeek day) {
        this.day = day;
    }
    
    @Override
    public boolean check() {
        return LocalDate.now().getDayOfWeek().equals(day);
    }

    @Override
    public String getDescription() {
        return " Trigger Type: Day of Week:  "+ day.toString();
    }
    
}
