/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;

/**
 *
 * @author Andre
 */
public class DateTrigger implements Trigger {

    private LocalDate date;

    public DateTrigger(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean check() {
        return LocalDate.now().equals(date);
    }

    @Override
    public String getDescription() {
        return " Trigger Type: Date :  "+ date.toString();
    }   
}