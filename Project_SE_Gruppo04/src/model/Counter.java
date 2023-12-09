/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
@author gruppo_04
 */
public class Counter {
    private String name;
    private int value;

    public Counter(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // returns the name of the counter
    public String getName() {
        return name;
    }

    // sets the value of the counter
    public void setName(String nome) {
        this.name = nome;
    }

    // returns the value of the counter
    public int getValue() {
        return value;
    }

    // sets the value of the counter
    public void setValue(int valore) {
        this.value = valore;
    }
}