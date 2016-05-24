package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.helper.DrawingHelper;
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


    // Database index of corresponding item
    private int mainBodyIndex;
    private int cannonIndex;
    private int extraPartIndex;
    private int engineIndex;
    private int powerCoreIndex;

    public Ship() {
        super(new GameImage(0,0,""), 0, 0, new PointF(0,0));
        rotationDegrees = STARTING_SHIP_ROTATION;
        lives = STARTING_SHIP_LIVES;
        safe = STARTING_SHIP_SAFE;
        safeTime = STARTING_SHIP_SAFE_TIME;

    }

    @Override
    public void draw(){
        drawMainBody();
        drawEngine();
        drawCannon();
        drawExtraPart();
    }

    private void drawCannon() {

    }

    private void drawEngine() {

    }

    private void drawExtraPart() {

    }

    private void drawMainBody() {
    }

    private PointF getMainBodyAttatchPoint(ShipPart part){

    }

    private PointF getCenterPoint(ShipPart part){

    }
}
