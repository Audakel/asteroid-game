package edu.byu.cs.superasteroids.model;

import android.content.Context;
import android.graphics.PointF;

import edu.byu.cs.superasteroids.helper.DrawingHelper;
import edu.byu.cs.superasteroids.helper.content.ContentManager;
import edu.byu.cs.superasteroids.model.ShipParts.Cannon;
import edu.byu.cs.superasteroids.model.ShipParts.Engine;
import edu.byu.cs.superasteroids.model.ShipParts.ExtraPart;
import edu.byu.cs.superasteroids.model.ShipParts.MainBody;
import edu.byu.cs.superasteroids.model.ShipParts.PowerCore;
import edu.byu.cs.superasteroids.model.ShipParts.ShipPart;

import static edu.byu.cs.superasteroids.Constants.STARTING_SHIP_LIVES;
import static edu.byu.cs.superasteroids.Constants.STARTING_SHIP_OPACITY;
import static edu.byu.cs.superasteroids.Constants.STARTING_SHIP_ROTATION;
import static edu.byu.cs.superasteroids.Constants.STARTING_SHIP_SAFE;
import static edu.byu.cs.superasteroids.Constants.STARTING_SHIP_SAFE_TIME;

/**
 * Created by audakel on 5/23/16.
 */

/**
 * Holds all the ship peices for easy drawing etc..
 */
public class Ship extends MovableObject {
    /**
     * Mainbody of ship
     */
    private MainBody mainBody;
    /**
     * Cannon of ship
     */
    private Cannon cannon;
    /**
     * Extra part of ship
     */
    private ExtraPart extraPart;
    /**
     * Engine of ship
     */
    private Engine engine;
    /**
     * Power core of ship
     */
    private PowerCore powerCore;
    /**
     * Degree of rotaion for ship
     */
    private float rotationDegrees;
    /**
     * Number of lives for ship, decreases when hit
     */
    private int lives;
    /**
     * True means ship will not be hurt if hit
     */
    private boolean safe;
    /**
     * True if ship has no more lives, starts out false
     */
    private boolean dead = false;
    /**
     * Amount of time ship is safe after being hit
     */
    private double safeTime;
    /**
     * Opacity value of ship
     */
    private int shipOpacity = STARTING_SHIP_OPACITY;
    /**
     * Context of activity calling ship
     */
    private Context context;


    public Ship() {
        super(new GameImage(0,0,""), 0, 0, new PointF(0,0));
        rotationDegrees = STARTING_SHIP_ROTATION;
        lives = STARTING_SHIP_LIVES;
        safe = STARTING_SHIP_SAFE;
        safeTime = STARTING_SHIP_SAFE_TIME;

    }

    @Override
    public void draw(){
        mainBody.draw();
        engine.drawAttachedToMainBody(engine.getImageIndex(), mainBody);
        cannon.drawAttachedToMainBody(cannon.getImageIndex(), mainBody);
        extraPart.drawAttachedToMainBody(extraPart.getImageIndex(), mainBody);
    }

    public MainBody getMainBody() {
        return mainBody;
    }

    public void setMainBody(MainBody mainBody) {
        this.mainBody = mainBody;
    }

    public Cannon getCannon() {
        return cannon;
    }

    public void setCannon(Cannon cannon) {
        this.cannon = cannon;
    }

    public ExtraPart getExtraPart() {
        return extraPart;
    }

    public void setExtraPart(ExtraPart extraPart) {
        this.extraPart = extraPart;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public PowerCore getPowerCore() {
        return powerCore;
    }

    public void setPowerCore(PowerCore powerCore) {
        this.powerCore = powerCore;
    }

    public float getRotationDegrees() {
        return rotationDegrees;
    }

    public void setRotationDegrees(float rotationDegrees) {
        this.rotationDegrees = rotationDegrees;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public double getSafeTime() {
        return safeTime;
    }

    public void setSafeTime(double safeTime) {
        this.safeTime = safeTime;
    }

    public int getShipOpacity() {
        return shipOpacity;
    }

    public void setShipOpacity(int shipOpacity) {
        this.shipOpacity = shipOpacity;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
