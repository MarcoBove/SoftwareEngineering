/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 39327
 */
public class Counter {
    private String name;
    private int value;

    public Counter(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // Metodi per accedere e modificare l'attributo 'nome'
    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    // Metodi per accedere e modificare l'attributo 'valore'
    public int getValue() {
        return value;
    }

    public void setValue(int valore) {
        this.value = valore;
    }
}