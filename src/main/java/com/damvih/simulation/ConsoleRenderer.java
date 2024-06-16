package com.damvih.simulation;

import com.damvih.simulation.entities.Entity;
import com.damvih.simulation.world_map.WorldMap;

public class ConsoleRenderer {

    public static final String GROUND_SPRITE = "\uD83D\uDFEB";
    public static final String HERBIVORE_SPRITE = "\uD83D\uDC30";
    public static final String PREDATOR_SPRITE = "\uD83E\uDD8A";
    public static final String GRASS_SPRITE = "\uD83C\uDF40";
    public static final String TREE_SPRITE = "\uD83C\uDF32";
    public static final String ROCK_SPRITE = "⚫";

    private void renderDelimiter(int length) {
        String delimiter = "-";
        System.out.println(delimiter.repeat(Math.max(0, length)));
    }

    public void render(WorldMap worldMap) {
        clearConsole();
        renderDelimiter(worldMap.width);
        for (int i = 0; i < worldMap.width; i++) {
            String line = "";
            for (int j = 0; j < worldMap.height; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (worldMap.isCellEmpty(coordinates)) {
                    line += GROUND_SPRITE;
                } else {
                    Entity entity = worldMap.getEntity(coordinates);
                    line += selectSpriteForEntity(entity);
                }
                line += " ";
            }
            System.out.println(line);
        }
    }

    private String selectSpriteForEntity(Entity entity) {
        switch (entity.getClass().getSimpleName()) {
            case "Herbivore":
                return HERBIVORE_SPRITE;
            case "Predator":
                return PREDATOR_SPRITE;
            case "Grass":
                return GRASS_SPRITE;
            case "Tree":
                return TREE_SPRITE;
            case "Rock":
                return ROCK_SPRITE;
            default:
                throw new IllegalArgumentException("Unknown entity");
        }
    }

    private static void clearConsole() {
        System.out.println("\033[H\033[2J");
    }

}
