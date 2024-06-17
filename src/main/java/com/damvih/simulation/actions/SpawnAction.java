package com.damvih.simulation.actions;

import com.damvih.simulation.Coordinates;
import com.damvih.simulation.world_map.WorldMap;
import com.damvih.simulation.entities.Entity;

import java.util.Random;
import java.util.function.Supplier;

public class SpawnAction extends Action {

    protected final int entitiesNumber;
    private final Supplier<Entity> supplier;


    public SpawnAction(WorldMap worldMap, int rate, Supplier<Entity> supplier) {
        super(worldMap);
        this.entitiesNumber = worldMap.width * worldMap.height / rate;
        this.supplier = supplier;
    }

    protected void spawnEntity() {
        Coordinates coordinates = getRandomEmptyCell();
        Entity entity = supplier.get();
        entity.setCoordinates(coordinates);
        worldMap.setEntity(coordinates, entity);
    }

    private Coordinates getRandomEmptyCell() {
        Coordinates coordinates;
        Random random = new Random();
        do {
            coordinates = new Coordinates(random.nextInt(worldMap.width), random.nextInt(worldMap.height));
        } while (!worldMap.isCellEmpty(coordinates));
        return coordinates;
    }

    @Override
    public void perform() {
        Class<? extends Entity> entityType = supplier.get().getClass();
        while (worldMap.getEntitiesByType(entityType).size() < entitiesNumber) {
            spawnEntity();
        }
    }

}
