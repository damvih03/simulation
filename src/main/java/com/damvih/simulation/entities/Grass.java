package com.damvih.simulation.entities;

import com.damvih.simulation.Coordinates;

public class Grass extends Entity {

    public final int foodValue;

    public Grass(Coordinates coordinates, int foodValue) {
        super(coordinates);
        this.foodValue = foodValue;
    }

}
