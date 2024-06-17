package com.damvih.simulation.actions;

import com.damvih.simulation.Coordinates;
import com.damvih.simulation.world_map.WorldMap;
import com.damvih.simulation.actions.Action;
import com.damvih.simulation.entities.Entity;

import java.util.Random;

abstract public class SpawnAction implements Action {

    public final int entitiesNumber;

    public SpawnAction(WorldMap worldMap, int rate) {
        this.entitiesNumber = worldMap.width * worldMap.height / rate;
    }

    abstract public Entity createEntity();

    public void spawnEntity(WorldMap worldMap) {
        Coordinates coordinates = getRandomEmptyCell(worldMap);
        Entity entity = createEntity();
        entity.setCoordinates(coordinates);
        worldMap.setEntity(coordinates, entity);
    }

    private Coordinates getRandomEmptyCell(WorldMap worldMap) {
        Coordinates coordinates;
        Random random = new Random();
        do {
            coordinates = new Coordinates(random.nextInt(worldMap.width), random.nextInt(worldMap.height));
        } while (!worldMap.isCellEmpty(coordinates));
        return coordinates;
    }

    @Override
    public void perform(WorldMap worldMap) {
        for (int i = 0; i < entitiesNumber; i++) {
            spawnEntity(worldMap);
        }
    }

}
