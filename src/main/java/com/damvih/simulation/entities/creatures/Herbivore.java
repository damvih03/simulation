package com.damvih.simulation.entities.creatures;

import com.damvih.simulation.world_map.WorldMap;
import com.damvih.simulation.entities.Entity;
import com.damvih.simulation.entities.Grass;

public class Herbivore extends Creature {

    public final int foodValue;

    public Herbivore(int speed, int healthPoints, int hunger, Class<? extends Entity> target, int foodValue) {
        super(speed, healthPoints, hunger, target);
        this.foodValue = foodValue;
    }

    @Override
    public void attack(WorldMap worldMap, Entity target) {
        Grass grass = (Grass) target;
        eat(grass.foodValue);
        worldMap.removeEntity(grass.getCoordinates());
    }

}
