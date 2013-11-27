package com._604robotics.robotnik;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.CoordinatorList;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.logging.Logger;

public class RobotProxy {
    private static boolean active = true;
    
    protected static void disable () { active = false; }
    
    public static void tick (Coordinator mode, ModuleManager modules, CoordinatorList coordinators) {
        if (active) {
            try {
                process(mode, modules, coordinators);
            } catch (Exception ex) {
                Logger.error("Caught error in main loop", ex);
            }
        } else {
            process(mode, modules, coordinators);
        }
    }
    
    private static void process (Coordinator mode, ModuleManager modules, CoordinatorList coordinators) {
        modules.update();

        coordinators.update();
        mode.update();

        modules.execute();
    }
    
    public static void update (ModuleManager modules) {
        if (active) {
            try {
                modules.update();
            } catch (Exception ex) {
                Logger.error("Caught error in main loop", ex);
            }
        } else {
            modules.update();
        }
    }
    
    public static void end (ModuleManager modules) {
        if (active) {
            try {
                modules.end();
            } catch (Exception ex) {
                Logger.error("Caught error in main loop", ex);
            }
        } else {
            modules.end();
        }
    }
}
