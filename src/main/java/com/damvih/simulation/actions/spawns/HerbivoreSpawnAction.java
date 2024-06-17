package com.damvih.simulation.actions.spawns;

import com.damvih.simulation.Coordinates;
import com.damvih.simulation.world_map.WorldMap;
import com.damvih.simulation.entities.Entity;
import com.damvih.simulation.entities.Grass;
import com.damvih.simulation.entities.creatures.Herbivore;

public class HerbivoreSpawnAction extends CreatureSpawnAction {

    public final int foodValue;

    public HerbivoreSpawnAction(WorldMap worldMap, int rate, int healthPoints, int speed, int hunger, int foodValue) {
        super(worldMap, rate, healthPoints, speed, hunger);
        this.foodValue = foodValue;
    }

    @Override
    public Entity createEntity() {
        Class<? extends Entity> target = Grass.class;
        return new Herbivore(speed, healthPoints, hunger, target, foodValue);
    }

}
