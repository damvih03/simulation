package com.damvih.simulation.world_map;

import com.damvih.simulation.Coordinates;
import com.damvih.simulation.entities.Entity;

import java.util.*;

public class WorldMapRouter {

    private static List<Coordinates> getNeighbourCells(final WorldMap worldMap, Coordinates coordinates) {
        List<Coordinates> shifts = new ArrayList<>(Arrays.asList(
                new Coordinates(1, 0),
                new Coordinates(0, 1),
                new Coordinates(-1, 0),
                new Coordinates(0, -1)
        ));
        List<Coordinates> neighbours = new ArrayList<>();
        for (Coordinates shift : shifts) {
            Coordinates shiftedCell = Coordinates.shift(coordinates, shift);
            if (worldMap.isCellInMap(shiftedCell)) {
                neighbours.add(shiftedCell);
            }
        }
        return neighbours;
    }

    public static boolean isTarget(Coordinates currentCoordinates, Class<? extends Entity> target, final WorldMap worldMap) {
        if (worldMap.isCellEmpty(currentCoordinates)) {
            return false;
        }
        Entity entity = worldMap.getEntity(currentCoordinates);
        return entity.getClass().equals(target);
    }

    public static Stack<Coordinates> getPath(Coordinates start, Class<? extends Entity> target, final WorldMap worldMap) {
        Coordinates currentCoordinates;
        Deque<Coordinates> unexplored = new ArrayDeque<>();
        Set<Coordinates> visited = new HashSet<>();
        Map<Coordinates, Coordinates> toFrom = new HashMap<>();
        unexplored.addLast(start);
        while (!unexplored.isEmpty()) {
            currentCoordinates = unexplored.removeFirst();
            if (isTarget(currentCoordinates, target, worldMap)) {
                return findPath(toFrom, currentCoordinates);
            } else {
                for (Coordinates neighbour : getNeighbourCells(worldMap, currentCoordinates)) {
                    if (!visited.contains(neighbour) && !unexplored.contains(neighbour) &&
                            (worldMap.isCellEmpty(neighbour) || isTarget(neighbour, target, worldMap))) {
                        toFrom.put(neighbour, currentCoordinates);
                        unexplored.addLast(neighbour);
                    }
                }
            }
            visited.add(currentCoordinates);
        }
        return new Stack<>();
    }

    private static Stack<Coordinates> findPath(Map<Coordinates, Coordinates> toFrom, Coordinates currentCoordinates) {
        Stack<Coordinates> path = new Stack<>();
        while (currentCoordinates != null) {
            path.push(currentCoordinates);
            currentCoordinates = toFrom.get(currentCoordinates);
        }
        path.pop();
        return path;
    }

}
