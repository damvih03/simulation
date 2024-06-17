package com.damvih.simulation.actions.spawns;

import com.damvih.simulation.actions.SpawnAction;
import com.damvih.simulation.world_map.WorldMap;

abstract public class CreatureSpawnAction extends SpawnAction {

    public final int healthPoints;

    public final int speed;

    public final int hunger;

    public CreatureSpawnAction(WorldMap worldMap, int rate, int healthPoints, int speed, int hunger) {
        super(worldMap, rate);
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.hunger = hunger;
    }

}
