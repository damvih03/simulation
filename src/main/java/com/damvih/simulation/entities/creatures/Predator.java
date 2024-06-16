package com.damvih.simulation.entities.creatures;

import com.damvih.simulation.Coordinates;
import com.damvih.simulation.world_map.WorldMap;
import com.damvih.simulation.entities.Entity;

public class Predator extends Creature {

    public final int power;

    public Predator(Coordinates coordinates, int speed, int healthPoints, int hunger, Class<? extends Entity> target, int power) {
        super(coordinates, speed, healthPoints, hunger, target);
        this.power = power;
    }

    @Override
    public void attack(WorldMap worldMap, Entity target) {
        Herbivore herbivore = (Herbivore) target;
        herbivore.setHealthPoints(herbivore.getHealthPoints() - this.power);
        if (!herbivore.isAlive()) {
            herbivore.setHealthPoints(0);
            eat(herbivore.foodValue);
            worldMap.removeEntity(herbivore.getCoordinates());
        }
    }

}
