package edu.byu.cs.superasteroids;

import edu.byu.cs.superasteroids.model.Ship;
import edu.byu.cs.superasteroids.model.ShipParts.Cannon;
import edu.byu.cs.superasteroids.model.ShipParts.Engine;
import edu.byu.cs.superasteroids.model.ShipParts.ExtraPart;

/**
 * Created by audakel on 5/23/16.
 */
public class Constants {
    public static float SCALE_FACTOR = 0.5f;


    // Starting values
    public static int STARTING_SHIP_LIVES = 5;
    public static int STARTING_SHIP_OPACITY = 255;
    public static double STARTING_SHIP_SAFE_TIME = 0;
    public static boolean STARTING_SHIP_SAFE = false;
    public static float STARTING_SHIP_ROTATION = 0;

    // Switch case on ship parts
    public final static String CANNON = "Cannon";
    public final static String ENGINE = "Engine";
    public final static String EXTRA_PART= "ExtraPart";

    // Game Variables
    public final static int GAME_WIDTH = 4000;
    public final static int GAME_HEIGHT = 4000;


}
