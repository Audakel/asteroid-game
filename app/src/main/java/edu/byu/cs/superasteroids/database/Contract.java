package edu.byu.cs.superasteroids.database;

/**
 * Created by audakel on 5/16/16.
 */

import android.net.Uri;
import android.provider.BaseColumns;

import edu.byu.cs.superasteroids.model.Asteroid;
import edu.byu.cs.superasteroids.model.ShipParts.Cannon;
import edu.byu.cs.superasteroids.model.ShipParts.Engine;
import edu.byu.cs.superasteroids.model.ShipParts.ExtraPart;
import edu.byu.cs.superasteroids.model.Level;
import edu.byu.cs.superasteroids.model.LevelAsteroid;
import edu.byu.cs.superasteroids.model.LevelObject;
import edu.byu.cs.superasteroids.model.ShipParts.MainBody;
import edu.byu.cs.superasteroids.model.ShipParts.PowerCore;

/**
 * This class is the contract that describes tables and fields in the
 * CET Founders database.
 */
public final class Contract implements BaseColumns {

    // Table names
    public static final String ASTEROIDS_GAME = "asteroidsGame";
    public static final String ASTEROIDS = "asteroids";
    public static final String LEVELS = "levels";
    public static final String MAIN_BODIES = "mainBodies";
    public static final String CANNONS = "cannons";
    public static final String EXTRA_PARTS = "extraParts";
    public static final String ENGINES = "engines";
    public static final String POWER_CORES = "powerCores";
    public static final String OBJECTS = "objects";


    public static final String ASTEROID_NAME = "asteroid_name";
    public static final String ASTEROID_IMAGE = "asteroid_image";
    public static final String ASTEROID_IMAGE_WIDTH = "asteroid_image_width";
    public static final String ASTEROID_IMAGE_HEIGHT = "asteroid_image_height";
    public static final String ASTEROID_TYPE = "asteroid_type";

    public static final String LEVEL_NUMBER = "level_number";
    public static final String LEVEL_TITLE = "level_title";
    public static final String LEVEL_HINT = "level_hint";
    public static final String LEVEL_WIDTH = "level_width";
    public static final String LEVEL_HEIGHT = "level_height";
    public static final String LEVEL_MUSIC = "level_music";
    public static final String LEVEL_OBJECT = "level_object";
    public static final String LEVEL_ID = "level_id";
    public static final String LEVEL_OBJECT_POSITION = "level_object_position";
    public static final String LEVEL_OBJECT_OBJECT_ID = "level_object_position_id";
    public static final String LEVEL_OBJECT_SCALE = "level_object_scale";
    public static final String LEVEL_ASTEROID = "level_asteroid";
    public static final String LEVEL_ASTEROID_NUMBER = "level_asteroi_numberd";
    public static final String LEVEL_ASTEROID_ASTEROID_ID = "level_asteroid_asteroid_id";

    public static final String MAIN_BODY_CANNON_ATTATCH = "main_body_cannon_attatch";
    public static final String MAIN_BODY_ENGINE_ATTATCH = "main_body_engine_attatch";
    public static final String MAIN_BODY_EXTRA_ATTATCH = "main_body_extra_attatch";
    public static final String MAIN_BODY_IMAGE = "main_body_image";
    public static final String MAIN_BODY_IMAGE_WIDTH = "main_body_image_width";
    public static final String MAIN_BODY_IMAGE_HEIGHT = "main_body_image_height";

    public static final String CANNON_ATTATCH_POINT = "cannon_attatch_point";
    public static final String CANNON_EMIT_POINT = "cannon_emit_point";
    public static final String CANNON_IMAGE = "cannon_image";
    public static final String CANNON_IMAGE_WIDTH = "cannon_image_width";
    public static final String CANNON_IMAGE_HEIGHT = "cannon_image_height";
    public static final String CANNON_ATTACK_IMAGE_WIDTH = "cannon_attack_image_width";
    public static final String CANNON_ATTACK_IMAGE_HEIGHT = "cannon_attack_image_height";
    public static final String CANNON_ATTACK_SOUND = "cannon_attack_sound";
    public static final String CANNON_DAMAGE = "cannon_damage";

    public static final String EXTRA_PART_ATTATCH_POINT = "extra_part_attatch_point";
    public static final String EXTRA_PART_IMAGE = "extra_part_image";
    public static final String EXTRA_PART_IMAGE_WIDTH = "extra_part_image_width";
    public static final String EXTRA_PART_IMAGE_HEIGHT = "extra_part_image_height";

    public static final String ENGINE_BASE_SPEED = "engine_base_speed";
    public static final String ENGINE_BASE_TURN_RATE = "engine_base_turn_rate";
    public static final String ENGINE_ATTACH_POINT = "engine_attach_point";
    public static final String ENGINE_IMAGE = "engine_image";
    public static final String ENGINE_IMAGE_WIDTH = "engine_image_width";
    public static final String ENGINE_IMAGE_HEIGHT = "engine_image_height";

    public static final String POWER_CORE_CANNON_BOOST = "power_core_cannon_boost";
    public static final String POWER_CORE_ENGINE_BOOST = "power_core_engine_boost";
    public static final String POWER_CORE_IMAGE = "power_core_image";

    public static final String OBJECT_IMAGES = "object_image";

    /**
     * Protect against null inserts
     */
    public static final String NULL_COLUMN_HACK = "null_column_hack";


    /**
     * The authority name for this ContentProvider.
     */
    public static final String AUTHORITY = "edu.byu.cs.superasteroids.database";

    /**
     * Main URI pattern for different tables
     */
    public static final Uri URI_ASTEROID = Uri.parse("content://" + AUTHORITY + "/" + ASTEROIDS);
    public static final Uri URI_CANNON = Uri.parse("content://" + AUTHORITY + "/" + CANNONS);
    public static final Uri URI_ENGINE = Uri.parse("content://" + AUTHORITY + "/" + ENGINES);
    public static final Uri URI_EXTRA_PART = Uri.parse("content://" + AUTHORITY + "/" + EXTRA_PARTS);
    public static final Uri URI_LEVEL = Uri.parse("content://" + AUTHORITY + "/" + LEVELS);
    public static final Uri URI_LEVEL_OBJECT = Uri.parse("content://" + AUTHORITY + "/" + LEVEL_OBJECT);
    public static final Uri URI_LEVEL_ASTEROID = Uri.parse("content://" + AUTHORITY + "/" + LEVEL_ASTEROID);
    public static final Uri URI_MAIN_BODY = Uri.parse("content://" + AUTHORITY + "/" + MAIN_BODIES);
    public static final Uri URI_POWER_CORE = Uri.parse("content://" + AUTHORITY + "/" + POWER_CORES);


    /**
     * Gives an array of fields in the Founder record, including ID and version fields,
     * together with all content fields.
     *
     * @return List of fields in the Founder record.
     */
    public static String[] allAsteroidGameNames() {
        return new String[]{
                ASTEROIDS, LEVELS, MAIN_BODIES, CANNONS, EXTRA_PARTS, ENGINES, POWER_CORES
        };
    }

    public static String[] allAsteroidFields() {
        return new String[]{
            ASTEROID_NAME, ASTEROID_IMAGE, ASTEROID_IMAGE_WIDTH, ASTEROID_IMAGE_HEIGHT, ASTEROID_TYPE
        };
    }

//    public static Object[] allAteroidGameObjects() {
//        return new Object[]{
//            new Asteroid(), new Cannon(), new Engine(), new ExtraPart(), new Level(), new LevelObject(),
//                new LevelAsteroid(), new MainBody(), new PowerCore()
//        };
//    }

    public static Uri[] allAteroidUris() {
        return new Uri[]{
            URI_ASTEROID, URI_CANNON, URI_ENGINE, URI_EXTRA_PART, URI_LEVEL, URI_LEVEL_OBJECT, URI_LEVEL_ASTEROID,
                URI_MAIN_BODY, URI_POWER_CORE
        };
    }


}
