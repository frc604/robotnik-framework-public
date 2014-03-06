package com._604robotics.robotnik.network;

import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class Slice {
    private final ITable source;
    private final String key;
    
    public Slice (ITable source, String key) {
        this.source = source;
        this.key = key;
    }
        
    public String  getString  (String  defaultValue) { return this.source.getString (this.key, defaultValue); }
    public double  getNumber  (double  defaultValue) { return this.source.getNumber (this.key, defaultValue); }
    public boolean getBoolean (boolean defaultValue) { return this.source.getBoolean(this.key, defaultValue); }
    public Object  getValue   () throws TableKeyNotDefinedException { return this.source.getValue(this.key); }
        
    public void putString  (String  value) { this.source.putString (this.key, value); }
    public void putNumber  (double  value) { this.source.putNumber (this.key, value); }
    public void putBoolean (boolean value) { this.source.putBoolean(this.key, value); }
    public void putValue   (Object  value) { this.source.putValue  (this.key, value); }
}