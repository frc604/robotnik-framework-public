package com._604robotics.robotnik.action.controllers;

import com._604robotics.robotnik.action.ActionController;
import com._604robotics.robotnik.action.ActionReference;

public class ElasticController extends ActionController {
    protected ActionReference pickAction (ActionReference defaultAction, ActionReference lastAction, ActionReference triggeredAction) {
        if (triggeredAction == null) {
            return defaultAction;
        } else {
            return triggeredAction;
        }
    }
}
