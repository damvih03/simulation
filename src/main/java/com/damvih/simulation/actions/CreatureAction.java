package com.damvih.simulation.actions;

import com.damvih.simulation.world_map.WorldMap;
import com.damvih.simulation.entities.Entity;
import com.damvih.simulation.entities.creatures.Creature;

import java.util.List;

public class CreatureAction extends Action {

    public CreatureAction(WorldMap worldMap) {
        super(worldMap);
    }

    @Override
    public void perform() {
        List<Entity> creatures = worldMap.getEntitiesByType(Creature.class);
        for (var entity : creatures) {
            Creature creature = (Creature)entity;
            if (creature.isAlive()) {
                creature.makeMove(worldMap);
            }
            else {
                worldMap.removeEntity(creature.getCoordinates());
            }
        }
    }

}
