package com.damvih.simulation.actions;

import com.damvih.simulation.world_map.WorldMap;

abstract public class Action {

    protected final WorldMap worldMap;

    public Action(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    abstract public void perform();

}
