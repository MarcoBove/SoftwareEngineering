/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.util.HashMap;

/**
 *
 * @author Andre
 */
public class FileSizeTrigger implements Trigger {

    private static final HashMap<String, Long> unitFactors = initUnitFactors();

    private File file;
    private long bytes;
    private int max_dim;
    private String unit;

    public FileSizeTrigger(File file, String unit, int max_dim) {
        this.file = file;
        this.max_dim = max_dim;
        this.unit = unit;
        this.bytes = toByte(unit, max_dim);
    }

    private static HashMap<String, Long> initUnitFactors() {
        HashMap<String, Long> factors = new HashMap<>();
        factors.put("B", 1L);
        factors.put("KB", 1024L);
        factors.put("MB", 1024L * 1024L);
        factors.put("GB", 1024L * 1024L * 1024L);
        return factors;
    }

    @Override
    public boolean check() {
        return file.exists() && (file.length() >= bytes);
    }

    @Override
    public String getDescription() {
        return " Trigger Type: File Size Trigger:  File: " + file.getName() + ", Max " + unit + ": " + max_dim;
    }

    @Override
    public void addTrigger(Trigger t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static long toByte(String unit, int size) {
        // Check if the specified unit is valid
        if (!unitFactors.containsKey(unit)) {
            throw new IllegalArgumentException("Invalid unit of measurement: " + unit);
        }

        // Calculate the value in bytes
        long factor = unitFactors.get(unit);
        return size * factor;
    }
}