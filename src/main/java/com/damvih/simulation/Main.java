package com.damvih.simulation;

import com.damvih.simulation.world_map.WorldMap;

public class Main {

    public static void main(String[] args) {
        ConsoleRenderer renderer = new ConsoleRenderer();
        WorldMap worldMap = new WorldMap(10, 10);
        Simulation simulation = new Simulation(worldMap, renderer);
        simulation.start();
    }

}

