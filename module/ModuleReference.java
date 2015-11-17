package com._604robotics.robotnik.module;

import com._604robotics.robotnik.action.ActionManager;
import com._604robotics.robotnik.action.ActionReference;
import com._604robotics.robotnik.data.DataManager;
import com._604robotics.robotnik.data.DataSource;
import com._604robotics.robotnik.network.IndexedTable;
import com._604robotics.robotnik.trigger.TriggerManager;
import com._604robotics.robotnik.trigger.TriggerSource;

public class ModuleReference {
    private final Module module;
    
    private final DataManager dataManager;
    private final TriggerManager triggerManager;
    
    private final ActionManager actionManager;
    
    public ModuleReference (String name, Module module, IndexedTable table) {
        this.dataManager = new DataManager(name, module.getDataMap(), table.getSubTable("data"));
        this.triggerManager = new TriggerManager(name, module.getTriggerMap(), table.getSubTable("triggers"));
        
        this.actionManager = new ActionManager(this, name, module.getActionController(), table.getSubTable("actions"), table.getSubTable("data"));
        
        this.module = module;
    }
    
    public DataSource getData (String name) {
        return this.dataManager.getData(name);
    }
    
    public TriggerSource getTrigger (String name) {
        return this.triggerManager.getTrigger(name);
    }
    
    public ActionReference getAction (String name) {
        return this.actionManager.getAction(name);
    }
    
    // FIXME: Run this through a Proxy.
    protected void begin () {
        this.module.begin();
    }
    
    protected void update () {
        this.dataManager.update();
        this.triggerManager.update();
        
        this.actionManager.reset();
    }
    
    protected void execute () {
        // FIXME: Run this through a proxy.
        this.module.run();
        
        this.actionManager.update();
        this.actionManager.execute();
    }
    
    // FIXME: Run this through a Proxy.
    protected void end () {
        this.actionManager.end();
        this.module.end();
    }
}
