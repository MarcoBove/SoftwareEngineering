package model;

import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 39327
 */
public class DayOfTheMonthTrigger implements Trigger{
    
    private int dayOfTheMonth;
    
    public DayOfTheMonthTrigger(int dayOfTheMonth ){
        this.dayOfTheMonth= dayOfTheMonth;
    }
    
    //Checks if the current day of the month matches the stored dayOfTheMonth value
    @Override
    public boolean check() {
        return LocalDate.now().getDayOfMonth()==dayOfTheMonth;
    }

    @Override
    public String getDescription() {
       return "Trigger Type: Day of the Month:   " + dayOfTheMonth; 
    }

    @Override
    public void addTrigger(Trigger t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
