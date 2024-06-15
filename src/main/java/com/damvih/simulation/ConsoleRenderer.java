package com.damvih.simulation;

import com.damvih.simulation.entities.Entity;
import com.damvih.simulation.world_map.WorldMap;

public class ConsoleRenderer {

    private void renderDelimiter(int length) {
        char delimiter = '-';
        System.out.println(String.valueOf(delimiter).repeat(Math.max(0, length)));
    }

    public void render(WorldMap worldMap) {
        clearConsole();
        renderDelimiter(worldMap.width);
        for (int i = 0; i < worldMap.width; i++) {
            String line = "";
            for (int j = 0; j < worldMap.height; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (worldMap.isCellEmpty(coordinates)) {
                    line += "\uD83D\uDFEB" + " ";
                } else {
                    Entity entity = worldMap.getEntity(coordinates);
                    line += selectSpriteForEntity(entity);
                }
            }
            System.out.println(line);
        }
    }

    private String selectSpriteForEntity(Entity entity) {
        switch (entity.getClass().getSimpleName()) {
            case "Herbivore":
                return "\uD83D\uDC30" + " ";
            case "Predator":
                return "\uD83E\uDD8A" + " ";
            case "Grass":
                return "\uD83C\uDF40" + " ";
            case "Tree":
                return "\uD83C\uDF32" + " ";
            case "Rock":
                return "⚫" + " ";
        }
        return "";
    }

    private static void clearConsole() {
        System.out.println("\033[H\033[2J");
    }

}
