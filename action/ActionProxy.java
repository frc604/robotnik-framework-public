package com._604robotics.robotnik.action;

import com._604robotics.robotnik.logging.InternalLogger;

public class ActionProxy {
    private static boolean active = true;
    
    public static void disable () { active = false; }
    
    public static void begin (String moduleName, ActionReference action) {
        if (active) {
            try {
                action.begin();
            } catch (Exception ex) {
                InternalLogger.caught(moduleName, "Action", action != null ? action.getName() : "[null]", "begin", ex);
            }
        } else {
            action.begin();
        }
    }
    
    public static void run (String moduleName, ActionReference action) {
        if (active) {
            try {
                action.run();
            } catch (Exception ex) {
                InternalLogger.caught(moduleName, "Action", action != null ? action.getName() : "[null]", "run", ex);
            }
        } else {
            action.run();
        }
    }
    
    public static void end (String moduleName, ActionReference action) {
        if (active) {
            try {
                action.end();
            } catch (Exception ex) {
                InternalLogger.caught(moduleName, "Action", action != null ? action.getName() : "[null]", "end", ex);
            }
        } else {
            action.end();
        }
    }
}
