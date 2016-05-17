package edu.byu.cs.superasteroids.database;

/**
 * Created by audakel on 5/14/16.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import edu.byu.cs.superasteroids.database.Provider.Contract;

/**
 * Database helper to create a CET Founders SQLite3 database.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * Database filename.
     */
    private static final String DATABASE_NAME = "asteroid.db";

    /**
     * Database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Normal constructor.
     *
     * @param context The context in which this content provider operates
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void initDatabase(SQLiteDatabase db) {
        String create = "CREATE TABLE ";
        String idField = BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";

        // ASTEROID
        db.execSQL(create + Contract.ASTEROID + " (" + idField + //
                Contract.ASTEROID_NAME + " TEXT, " + //
                Contract.ASTEROID_IMAGE + " TEXT, " + //
                Contract.ASTEROID_IMAGE_WIDTH + " INTEGER, " + //
                Contract.ASTEROID_IMAGE_HEIGHT + " INTEGER, " + //
                Contract.NULL_COLUMN_HACK + " TEXT, " + //
                Contract.ASTEROID_TYPE + " TEXT " + //
                ");");


        // LEVEL
        db.execSQL(create + Contract.LEVEL + " (" + idField + //
                Contract.LEVEL_NUMBER + " INTEGER, " + //
                Contract.LEVEL_TITLE + " TEXT, " + //
                Contract.LEVEL_HINT + " TEXT, " + //
                Contract.LEVEL_WIDTH + " INTEGER, " + //
                Contract.LEVEL_HEIGHT + " INTEGER, " + //
                Contract.LEVEL_MUSIC + " TEXT, " + //
                Contract.LEVEL_OBJECT_POSITION + " TEXT, " + //
                Contract.LEVEL_OBJECT_OBJECT_ID + " INTEGER, " + //
                Contract.LEVEL_OBJECT_SCALE + " TEXT, " + //
                Contract.LEVEL_ASTEROID + " TEXT, " + //
                Contract.LEVEL_ASTEROID_NUMBER + " TEXT, " + //
                Contract.NULL_COLUMN_HACK + " TEXT, " + //
                Contract.LEVEL_ASTEROID_ASTEROID_ID + " TEXT " + //
                ");");

        // MAIN_BODY
        db.execSQL(create + Contract.MAIN_BODY + " (" + idField + //
                Contract.MAIN_BODY_CANNON_ATTATCH + " TEXT, " + //
                Contract.MAIN_BODY_ENGINE_ATTATCH + " TEXT, " + //
                Contract.MAIN_BODY_EXTRA_ATTATCH + " TEXT, " + //
                Contract.MAIN_BODY_IMAGE + " TEXT, " + //
                Contract.MAIN_BODY_IMAGE_WIDTH + " TEXT, " + //
                Contract.NULL_COLUMN_HACK + " TEXT, " + //
                Contract.MAIN_BODY_IMAGE_HEIGHT + " TEXT " + //
                ");");

        // CANNON
        db.execSQL(create + Contract.CANNON + " (" + idField + //
                Contract.CANNON_ATTATCH_POINT + " TEXT, " + //
                Contract.CANNON_EMIT_POINT + " TEXT, " + //
                Contract.CANNON_IMAGE + " TEXT, " + //
                Contract.CANNON_IMAGE_WIDTH + " TEXT, " + //
                Contract.CANNON_IMAGE_HEIGHT + " TEXT, " + //
                Contract.CANNON_ATTACK_IMAGE_WIDTH + " TEXT, " + //
                Contract.CANNON_ATTACK_IMAGE_HEIGHT + " TEXT, " + //
                Contract.CANNON_ATTACK_SOUND + " TEXT, " + //
                Contract.NULL_COLUMN_HACK + " TEXT, " + //
                Contract.CANNON_DAMAGE+ " TEXT " + //
                ");");

        // EXTRA_PART
        db.execSQL(create + Contract.EXTRA_PART + " (" + idField + //
                Contract.EXTRA_PART_ATTATCH_POINT + " TEXT, " + //
                Contract.EXTRA_PART_IMAGE + " TEXT, " + //
                Contract.EXTRA_PART_IMAGE_WIDTH + " TEXT, " + //
                Contract.NULL_COLUMN_HACK + " TEXT, " + //
                Contract.EXTRA_PART_IMAGE_HEIGHT + " TEXT " + //
                ");");

        // ENGINE
        db.execSQL(create + Contract.ENGINE + " (" + idField + //
                Contract.ENGINE_BASE_SPEED + " TEXT, " + //
                Contract.ENGINE_BASE_TURN_RATE + " TEXT, " + //
                Contract.ENGINE_ATTACH_POINT + " TEXT, " + //
                Contract.ENGINE_IMAGE + " TEXT, " + //
                Contract.ENGINE_IMAGE_WIDTH + " TEXT, " + //
                Contract.NULL_COLUMN_HACK + " TEXT, " + //
                Contract.ENGINE_IMAGE_HEIGHT + " TEXT " + //
                ");");

        // POWER_CORE
        db.execSQL(create + Contract.POWER_CORE + " (" + idField + //
                Contract.POWER_CORE_CANNON_BOOST + " TEXT, " + //
                Contract.POWER_CORE_ENGINE_BOOST + " TEXT, " + //
                Contract.NULL_COLUMN_HACK + " TEXT, " + //
                Contract.POWER_CORE_IMAGE + " TEXT " + //
                ");");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initDatabase(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < DATABASE_VERSION) {
            // NEEDSWORK: this is a very naive upgrade technique

            db.execSQL("DROP DATABASE " + Contract.ASTEROIDS_GAME);
            initDatabase(db);
        }
    }
}



