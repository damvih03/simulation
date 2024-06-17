package com.damvih.simulation.actions.spawns;

import com.damvih.simulation.actions.SpawnAction;
import com.damvih.simulation.world_map.WorldMap;
import com.damvih.simulation.entities.Entity;
import com.damvih.simulation.entities.Rock;

public class RockSpawnAction extends SpawnAction {

    public RockSpawnAction(WorldMap worldMap, int rate) {
        super(worldMap, rate);
    }

    @Override
    public Entity createEntity() {
        return new Rock();
    }

}
