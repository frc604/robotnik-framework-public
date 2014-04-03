package com._604robotics.robotnik.prefabs.sinks;

import com._604robotics.robotnik.data.DataSink;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardSink {
    private DashboardSink () {}
    
    public static class Number implements DataSink {
        private final String name;

        public Number (String name) {
            this.name = name;
        }

        public void write (double value) {
            SmartDashboard.putNumber(name, value);
        }
    }
    
    public static class Boolean implements DataSink {
        private final String name;

        public Boolean (String name) {
            this.name = name;
        }

        public void write (double value) {
            SmartDashboard.putBoolean(name, value > 0);
        }
    }
}
