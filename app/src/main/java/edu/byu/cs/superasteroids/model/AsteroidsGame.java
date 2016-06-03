package edu.byu.cs.superasteroids.model;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.model.asteroids.Asteroid;
import edu.byu.cs.superasteroids.model.shipParts.Cannon;
import edu.byu.cs.superasteroids.model.shipParts.Engine;
import edu.byu.cs.superasteroids.model.shipParts.ExtraPart;
import edu.byu.cs.superasteroids.model.shipParts.MainBody;
import edu.byu.cs.superasteroids.model.shipParts.PowerCore;

/**
 * Created by audakel on 5/17/16.
 */
public class AsteroidsGame {

    public AsteroidsGame(){
        ship = new Ship();
    }

    private ArrayList<Asteroid> asteroids = new ArrayList<>();
    private ArrayList<Cannon> cannons = new ArrayList<>();
    private ArrayList<Engine> engines = new ArrayList<>();
    private ArrayList<ExtraPart> extraParts = new ArrayList<>();
    private ArrayList<Level> levels = new ArrayList<>();
    private ArrayList<MainBody> mainBodies = new ArrayList<>();
    private ArrayList<PowerCore> powerCores = new ArrayList<>();
    private ArrayList<ObjectImage> objectImages = new ArrayList<>();
    private ArrayList<Asteroid> genericAsteroids = new ArrayList<>();
    private Ship ship;

    public ArrayList<ObjectImage> getObjectImages() {
        return objectImages;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(ArrayList<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    public ArrayList<Cannon> getCannons() {
        return cannons;
    }

    public ArrayList<Asteroid> getGenericAsteroids() {
        return genericAsteroids;
    }

    public void setGenericAsteroids(ArrayList<Asteroid> genericAsteroids) {
        this.genericAsteroids = genericAsteroids;
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

    public void setObjectImages(ArrayList<ObjectImage> objectImages) {
        this.objectImages = objectImages;
    }

    public Ship getShip() {
        return ship;
    }
}
