package edu.byu.cs.superasteroids.model;

import java.util.ArrayList;

/**
 * Created by audakel on 5/17/16.
 */
public class AsteroidsGame {
    private ArrayList<Asteroid> asteroids = new ArrayList<>();
    private ArrayList<Cannon> cannons = new ArrayList<>();
    private ArrayList<Engine> engines = new ArrayList<>();
    private ArrayList<ExtraPart> extraParts = new ArrayList<>();
    private ArrayList<Level> levels = new ArrayList<>();
    private ArrayList<MainBody> mainBodies = new ArrayList<>();
    private ArrayList<PowerCore> powerCores = new ArrayList<>();

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(ArrayList<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    public ArrayList<Cannon> getCannons() {
        return cannons;
    }

    public void setCannons(ArrayList<Cannon> cannons) {
        this.cannons = cannons;
    }

    public ArrayList<Engine> getEngines() {
        return engines;
    }

    public void setEngines(ArrayList<Engine> engines) {
        this.engines = engines;
    }

    public ArrayList<ExtraPart> getExtraParts() {
        return extraParts;
    }

    public void setExtraParts(ArrayList<ExtraPart> extraParts) {
        this.extraParts = extraParts;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }

    public ArrayList<MainBody> getMainBodies() {
        return mainBodies;
    }

    public void setMainBodies(ArrayList<MainBody> mainBodies) {
        this.mainBodies = mainBodies;
    }

    public ArrayList<PowerCore> getPowerCores() {
        return powerCores;
    }

    public void setPowerCores(ArrayList<PowerCore> powerCores) {
        this.powerCores = powerCores;
    }
}
