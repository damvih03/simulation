package com.damvih.simulation.actions.spawns;

import com.damvih.simulation.Coordinates;
import com.damvih.simulation.world_map.WorldMap;
import com.damvih.simulation.entities.Entity;
import com.damvih.simulation.entities.Tree;

public class TreeSpawnAction extends SpawnAction {

    public TreeSpawnAction(WorldMap worldMap, int rate) {
        super(worldMap, rate);
    }

    @Override
    public Entity createEntity(Coordinates coordinates) {
        return new Tree(coordinates);
    }

}
