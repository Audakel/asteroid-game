package edu.byu.cs.superasteroids;

import android.graphics.Color;

/**
 * Created by audakel on 5/23/16.
 */
public class Constants {
    // Game Variables
    public static float SCALE_FACTOR = 0.3f;
    public final static int GAME_WIDTH = 4000;
    public final static int GAME_HEIGHT = 4000;

    public static float LEVEL_OBJECT_SPEED = 90;
    public static float SHIP_HIT_RADIUS = 60;
    public static int SHIP_HIT_COLOR = Color.RED;
    public static int BULLET_SPEED = 1000;
    public static float VOLUME = 50f;

    // Swtitch case on asteroid type
    public enum AsteroidType {REGULAR, GROWING, OCTEROID}
    public static final String ASTEROID_TYPE_REGULAR = "regular";
    public static final String ASTEROID_TYPE_GROWING = "growing";
    public static final String ASTEROID_TYPE_OCTEROID = "octeroid";

    // Starting values
    public static int STARTING_SHIP_LIVES = 5;

    public enum Opacity {
        FULL(255), PARTIAL(200);
        private int value;
        private Opacity(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }
    public static int OPACITY_FULL = 255;
    public static int OPACITY_PARTIAL = 200;
    public static double STARTING_SHIP_SAFE_TIME = 0;
    public static boolean STARTING_SHIP_SAFE = false;
    public static float STARTING_SHIP_ROTATION = 0;

    // Switch case on ship parts
    public final static String CANNON = "Cannon";
    public final static String ENGINE = "Engine";
    public final static String EXTRA_PART= "ExtraPart";

    // Minimap stuff
    public static float MINIMAP_SHIP_WIDTH = 3;
    public static float MINIMAP_ASTEROIDS_WIDTH = 5;
    public static int COLOR_MINIMAP = Color.WHITE;
    public static int MINIMAP_SHIP_COLOR = Color.BLUE;
    public static int MINIMAP_ASTEROID_COLOR = Color.MAGENTA;
    public static int ASTEROID_HEALTH = 2;
    public static float ASTEROID_SCALE = 0.75f;
}
