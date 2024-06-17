package com.damvih.simulation.actions.spawns;

import com.damvih.simulation.world_map.WorldMap;
import com.damvih.simulation.entities.Entity;
import com.damvih.simulation.entities.creatures.Herbivore;
import com.damvih.simulation.entities.creatures.Predator;

public class PredatorSpawnAction extends CreatureSpawnAction {

    public final int power;

    public PredatorSpawnAction(WorldMap worldMap, int rate, int healthPoints, int speed, int hunger, int power) {
        super(worldMap, rate, healthPoints, speed, hunger);
        this.power = power;
    }

    @Override
    public Entity createEntity() {
        Class<? extends Entity> target = Herbivore.class;
        return new Predator(speed, healthPoints, hunger, target, power);
    }

}
