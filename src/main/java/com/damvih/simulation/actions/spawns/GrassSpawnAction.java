package com.damvih.simulation.actions.spawns;

import com.damvih.simulation.Coordinates;
import com.damvih.simulation.world_map.WorldMap;
import com.damvih.simulation.entities.Entity;
import com.damvih.simulation.entities.Grass;

public class GrassSpawnAction extends SpawnAction {

    public final int foodValue;

    public GrassSpawnAction(WorldMap worldMap, int rate, int foodValue) {
        super(worldMap, rate);
        this.foodValue = foodValue;
    }

    @Override
    public Entity createEntity() {
        return new Grass(foodValue);
    }

    @Override
    public void perform(WorldMap worldMap) {
        while (worldMap.getEntitiesByType(Grass.class).size() < entitiesNumber) {
            spawnEntity(worldMap);
        }
    }

}
