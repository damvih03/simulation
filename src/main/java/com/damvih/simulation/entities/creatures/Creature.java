package com.damvih.simulation.entities.creatures;

import com.damvih.simulation.Coordinates;
import com.damvih.simulation.world_map.WorldMapRouter;
import com.damvih.simulation.world_map.WorldMap;
import com.damvih.simulation.entities.Entity;

import java.util.Stack;

abstract public class Creature extends Entity {

    public final int speed;

    public final int maxHealthPoints;

    private int healthPoints;

    public final int hunger;

    public final Class<? extends Entity> target;

    public Creature(Coordinates coordinates, int speed, int healthPoints, int hunger, Class<? extends Entity> target) {
        super(coordinates);
        this.speed = speed;
        this.healthPoints = healthPoints;
        this.hunger = hunger;
        this.target = target;
        this.maxHealthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    abstract protected void attack(WorldMap worldMap, Entity target);

    public void makeMove(WorldMap worldMap) {
        Stack<Coordinates> path = WorldMapRouter.getPath(this.coordinates, target, worldMap);
        if (!path.isEmpty()) {
            for (int i = 0; i < speed; i++) {
                Coordinates nextCoordinates = path.peek();
                if (isCellAvailableForAttack(worldMap, nextCoordinates)) {
                    Entity nextEntity = worldMap.getEntity(nextCoordinates);
                    attack(worldMap, nextEntity);
                    return;
                }
                else {
                    worldMap.moveEntity(this.coordinates, nextCoordinates);
                }
                path.pop();
            }
        }
        decreaseHealthPointsDueToHunger();
    }

    private boolean isCellAvailableForAttack(WorldMap worldMap, Coordinates coordinates) {
        if (!worldMap.isCellEmpty(coordinates)) {
            Entity entity = worldMap.getEntity(coordinates);
            return entity != null && entity.getClass().equals(target);
        }
        return false;
    }

    protected void eat(int foodValue) {
        healthPoints += foodValue;
        if (healthPoints > maxHealthPoints) {
            healthPoints = maxHealthPoints;
        }
    }

    protected void decreaseHealthPointsDueToHunger() {
        healthPoints -= hunger;
        if (healthPoints < 0) {
            healthPoints = 0;
        }
    }

}
