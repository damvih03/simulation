package com.damvih.simulation;

import com.damvih.simulation.actions.Action;
import com.damvih.simulation.actions.CreatureAction;
import com.damvih.simulation.actions.SpawnAction;
import com.damvih.simulation.entities.Grass;
import com.damvih.simulation.entities.Rock;
import com.damvih.simulation.entities.Tree;
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

    private final ConsoleMessage consoleMessage = new ConsoleMessage();

    public Simulation(WorldMap worldMap, ConsoleRenderer renderer) {
        this.worldMap = worldMap;
        this.renderer = renderer;
        createActions();
    }

    private void createActions() {
        SpawnAction grassSpawnAction = new SpawnAction(worldMap, 15, () -> new Grass(1));
        initActions.add(new SpawnAction(worldMap, 25, Rock::new));
        initActions.add(new SpawnAction(worldMap, 30, Tree::new));
        initActions.add(new SpawnAction(worldMap, 20, () -> new Herbivore(3, 20, 2, Grass.class, 5)));
        initActions.add(new SpawnAction(worldMap, 50, () -> new Predator(2, 25, 3, Herbivore.class ,5)));
        initActions.add(grassSpawnAction);
        turnActions.add(new CreatureAction(worldMap));
        turnActions.add(grassSpawnAction);
    }

    private void runActions(List<Action> actions) {
        for (Action action : actions) {
            action.perform();
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

    private SimulationStates determineState() {
        if (worldMap.getEntitiesByType(Herbivore.class).isEmpty()) {
            return SimulationStates.HERBIVORE_DEAD;
        } else if (worldMap.getEntitiesByType(Predator.class).isEmpty()) {
            return SimulationStates.PREDATOR_DEAD;
        }
        return SimulationStates.CONTINUE;
    }

}
