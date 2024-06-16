package com.damvih.simulation;

import com.damvih.simulation.entities.Entity;
import com.damvih.simulation.entities.creatures.Creature;
import com.damvih.simulation.entities.creatures.Predator;
import com.damvih.simulation.world_map.WorldMap;

import java.util.List;

public class ConsoleMessage {

    public void showGreeting() {
        System.out.println("Начинаем симуляцию!");
    }

    public void showFinishMessage(int count, SimulationStates state) {
        System.out.println("Симуляция закончена!\nКоличество ходов: " + count);
        String stateMessage = (state == SimulationStates.HERBIVORE_DEAD) ? "Травоядных" : "Хищников";
        System.out.println(stateMessage + " не осталось на карте!");
    }

    public void showCreaturesInfo(final WorldMap worldMap) {
        List<Entity> creatures = worldMap.getEntitiesByType(Creature.class);
        for (Entity entity : creatures) {
            Creature creature = (Creature) entity;
            Coordinates creatureCoordinates = creature.getCoordinates();
            String coordinates = "[X: " + creatureCoordinates.x + " | Y: " + creatureCoordinates.y + "] ";
            String creatureName = creature instanceof Predator ? "Хищник" : "Травоядный";
            String healthPoints = " | Здоровье: " + creature.getHealthPoints() + "/" + creature.maxHealthPoints;
            String line = coordinates + creatureName + healthPoints;
            System.out.println(line);
        }
    }

}
