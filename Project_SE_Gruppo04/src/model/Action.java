/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author 39327
 */
public interface Action extends Serializable{
    public String getDescription();
    public void execute();
}
