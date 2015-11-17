package com._604robotics.robotnik.meta;

public class Scorekeeper {
    private Object victor = null;
    private int score = Integer.MIN_VALUE;
    
    public void consider (Object item, int score) {
        if (score >= this.score) {
            this.victor = item;
            this.score = score;
        }
    }
    
    public Object getVictor () {
        return this.victor;
    }
    
    public void reset () {
        this.victor = null;
        this.score = Integer.MAX_VALUE;
    }
}
