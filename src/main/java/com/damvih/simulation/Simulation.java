package com.damvih.simulation;

import com.damvih.simulation.actions.Action;
import com.damvih.simulation.actions.CreatureMoveAction;
import com.damvih.simulation.actions.spawns.*;
import com.damvih.simulation.entities.creatures.Herbivore;
import com.damvih.simulation.entities.creatures.Predator;
import com.damvih.simulation.world_map.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final WorldMap worldMap;

    private final ConsoleRenderer renderer;

    private SimulationStates state;

    private final int DELAY_TIME = 750;

    private final List<Action> initActions = new ArrayList<>();

    private final List<Action> turnActions = new ArrayList<>();

    public final ConsoleMessage consoleMessage = new ConsoleMessage();

    public Simulation(WorldMap worldMap, ConsoleRenderer renderer) {
        this.worldMap = worldMap;
        this.renderer = renderer;
        createActions();
    }

    public void createActions() {
        GrassSpawnAction grassSpawnAction = new GrassSpawnAction(worldMap, 15, 1);
        initActions.add(new RockSpawnAction(worldMap, 25));
        initActions.add(new TreeSpawnAction(worldMap, 30));
        initActions.add(new HerbivoreSpawnAction(worldMap, 20, 20, 3, 2, 5));
        initActions.add(new PredatorSpawnAction(worldMap, 50, 25, 2, 3, 5));
        initActions.add(grassSpawnAction);
        turnActions.add(new CreatureMoveAction());
        turnActions.add(grassSpawnAction);
    }

    public void runActions(List<Action> actions) {
        for (Action action : actions) {
            action.perform(worldMap);
        }
    }

    public void start() {
        consoleMessage.showGreeting();
        runActions(initActions);
        int movesCount = 0;
        while (true) {
            renderer.render(worldMap);
            consoleMessage.showCreaturesInfo(worldMap);
            state = determineState();
            if (state != SimulationStates.CONTINUE) {
                break;
            }
            runActions(turnActions);
            movesCount += 1;
            try {
                Thread.sleep(DELAY_TIME);
            } catch (InterruptedException ignored) {}
        }
        consoleMessage.showFinishMessage(movesCount, state);
    }

    public SimulationStates determineState() {
        if (worldMap.getEntitiesByType(Herbivore.class).isEmpty()) {
            return SimulationStates.HERBIVORE_DEAD;
        } else if (worldMap.getEntitiesByType(Predator.class).isEmpty()) {
            return SimulationStates.PREDATOR_DEAD;
        }
        return SimulationStates.CONTINUE;
    }

}
