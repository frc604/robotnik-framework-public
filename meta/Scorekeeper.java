package com._604robotics.robotnik.meta;

public class Scorekeeper {
    public Object victor = null;
    public int count = 0;
    
    private final int base;
    public int score;
    
    public Scorekeeper (int base) {
        this.base = this.score = base;
    }
    
    public void consider (Object item, int score) {
        if (this.victor == null || score > this.score) {
            this.victor = item;
            this.score = score;
        }
        
        this.count++;
    }
    
    public void reset () {
        this.victor = null;
        this.count = 0;
        
        this.score = this.base;
    }
}
