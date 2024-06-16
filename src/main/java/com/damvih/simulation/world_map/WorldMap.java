package com.damvih.simulation.world_map;

import com.damvih.simulation.Coordinates;
import com.damvih.simulation.entities.Entity;

import java.util.*;

public class WorldMap {

    public final int width;

    public final int height;

    private final HashMap<Coordinates, Entity> entities = new HashMap<>();

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Entity getEntity(Coordinates coordinates) {
        Entity entity = entities.get(coordinates);
        if (entity != null) {
            return entity;
        }
        throw new IllegalArgumentException("There is no entity on this coordinates");
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        Entity entity = getEntity(from);
        removeEntity(from);
        setEntity(to, entity);
    }

    public boolean isCellInMap(Coordinates coordinates) {
        return coordinates.x >= 0 && coordinates.x < width && coordinates.y >= 0 && coordinates.y < height;
    }

    public <T extends Entity> List<Entity> getEntitiesByType(Class<T> type) {
        List<Entity> result = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (type.isInstance(entity)) {
                result.add(entity);
            }
        }
        return result;
    }

}
