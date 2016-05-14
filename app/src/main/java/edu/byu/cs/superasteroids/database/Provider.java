package edu.byu.cs.superasteroids.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

/**
 * Created by audakel on 5/14/16.
 */

public class Provider extends ContentProvider {
    // URI matcher codes
    private static final int URI_MATCHER_FOUNDERS = 1;
    private static final int URI_MATCHER_FOUNDER_ID = 2;
    private static final int URI_MATCHER_FTS = 3;

    // MIME type codes
    private static final String MIME_COLLECTION = "vnd.android.cursor.dir/";
    private static final String MIME_ITEM = "vnd.android.cursor.item/";
    private static final String MIME_BASE = "vnd.helloandroid.";

    /**
     * Handle to our underlying database.
     */
    private FounderDatabaseHelper mDatabase = null;

    /**
     * URI matcher to identify what kind of request we're receiving.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // A static initializer executes when the Java VM first loads this class.
    // We use it to perform one-time initialization of static members like sUriMatcher.
    static {
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.FOUNDER, URI_MATCHER_FOUNDERS);
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.FOUNDER + "/#", URI_MATCHER_FOUNDER_ID);
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.FULL_TEXT_SEARCH, URI_MATCHER_FTS);
    }

    @Override
    public int delete( Uri uri, String where, String[] whereArgs) {
        return modify(uri, null, where, whereArgs);
    }

    @Override
    public String getType( Uri uri) {
        switch (sUriMatcher.match(uri)) {
            // This could be an if/else, but with more tables we'll want a switch instead.
            case URI_MATCHER_FOUNDER_ID:
                return MIME_ITEM + MIME_BASE + Contract.FOUNDER;
            default:
                return MIME_COLLECTION + MIME_BASE + Contract.FOUNDER;
        }
    }

    @Override
    public Uri insert( Uri uri, ContentValues initialValues) {
        long rowId = mDatabase.getWritableDatabase().insert(tableForUri(uri), Contract.IMAGE_URL, initialValues);

        if (rowId <= 0) {
            throw new SQLException("Failed insert: " + uri);
        }

        // Now notify the resolver of the change so it can inform any listeners.
        Uri insertUri = ContentUris.withAppendedId(Contract.CONTENT_URI, rowId);
        Context context = getContext();

        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertUri;
    }

    private int modify(Uri uri, ContentValues values, String where, String[] whereArgs) {
        int count;
        SQLiteDatabase database = mDatabase.getWritableDatabase();
        String table = tableForUri(uri);

        // First attempt the delete or update operation.
        if (values == null) {
            // With no values this is a delete operation.
            count = database.delete(table, where, whereArgs);
        } else {
            count = database.update(table, values, where, whereArgs);
        }

        // Then notify the resolver of the change so it can inform any listeners.
        Context context = getContext();

        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return count;
    }

    @Override
    public boolean onCreate() {
        // We delegate creation to the helper.
        mDatabase = new FounderDatabaseHelper(getContext());

        return true;
    }

    @Override
    public Cursor query( Uri uri, String[] projection, String selection, String[] selectionArgs, String sort) {
        // A best practice is to use a query builder to construct an actual query from the URI.
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String orderBy = null;

        qb.setTables(tableForUri(uri));

        if (!TextUtils.isEmpty(sort)) {
            orderBy = sort;
        }

        if (sUriMatcher.match(uri) == URI_MATCHER_FOUNDER_ID) {
            qb.appendWhere(Contract._ID + "=" + uri.getLastPathSegment());
        }

        // Note that we're not really performing the query per se, but rather building a Cursor that will
        // iterate over all the query results.  We can use a read-only database here.
        Cursor cursor = qb.query(mDatabase.getReadableDatabase(), projection, selection, selectionArgs, null, null, orderBy);

        // The cursor needs to know about the resolver so it can be informed of any changes while
        // the cursor is active because changes could impact cursor results.
        Context context = getContext();

        if (context != null) {
            cursor.setNotificationUri(context.getContentResolver(), uri);
        }

        return cursor;
    }

    /**
     * Determine which table we should use for a given URI.
     *
     * @param uri A URI corresponding to this provider
     * @return The table name needed for a CRUD operation based on this URI
     */
    private String tableForUri(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case URI_MATCHER_FTS:
                return Contract.FULL_TEXT_SEARCH;
            case URI_MATCHER_FOUNDER_ID:
            default:
                return Contract.FOUNDER;
        }
    }

    @Override
    public int update( Uri uri, ContentValues values, String where, String[] whereArgs) {
        return modify(uri, values, where, whereArgs);
    }

    /**
     * Database helper to create a CET Founders SQLite3 database.
     */
    public class FounderDatabaseHelper extends SQLiteOpenHelper {
        /**
         * Database filename.
         */
        private static final String DATABASE_NAME = "founders.db";

        /**
         * Database version.
         */
        private static final int DATABASE_VERSION = 5;

        /**
         * Normal constructor.
         *
         * @param context The context in which this content provider operates
         */
        public FounderDatabaseHelper(Context context) {
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
                    Contract.ASTEROID_IMAGE_WIDTH + " INTEGER, " + //
                    Contract.ASTEROID_TYPE + " TEXT, " + //
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
                    Contract.LEVEL_ASTEROID_ASTEROID_ID + " TEXT, " + //
            ");");

            // MAIN_BODY
            db.execSQL(create + Contract.MAIN_BODY + " (" + idField + //
                    Contract.MAIN_BODY_CANNON_ATTATCH + " TEXT, " + //
                    Contract.MAIN_BODY_ENGINE_ATTATCH + " TEXT, " + //
                    Contract.MAIN_BODY_EXTRA_ATTATCH + " TEXT, " + //
                    Contract.MAIN_BODY_IMAGE + " TEXT, " + //
                    Contract.MAIN_BODY_IMAGE_WIDTH + " TEXT, " + //
                    Contract.MAIN_BODY_IMAGE_HEIGHT + " TEXT, " + //
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
                    Contract.CANNON_DAMAGE+ " TEXT, " + //
            ");");

            // EXTRA_PART
            db.execSQL(create + Contract.EXTRA_PART + " (" + idField + //
                    Contract.EXTRA_PART_ATTATCH_POINT + " TEXT, " + //
                    Contract.EXTRA_PART_IMAGE + " TEXT, " + //
                    Contract.EXTRA_PART_IMAGE_WIDTH + " TEXT, " + //
                    Contract.EXTRA_PART_IMAGE_HEIGHT + " TEXT, " + //
            ");");

            // ENGINE
            db.execSQL(create + Contract.ENGINE + " (" + idField + //
                    Contract.ENGINE_BASE_SPEED + " TEXT, " + //
                    Contract.ENGINE_BASE_TURN_RATE + " TEXT, " + //
                    Contract.ENGINE_ATTACH_POINT + " TEXT, " + //
                    Contract.ENGINE_IMAGE + " TEXT, " + //
                    Contract.ENGINE_IMAGE_WIDTH + " TEXT, " + //
                    Contract.ENGINE_IMAGE_HEIGHT + " TEXT, " + //
            ");");

            // POWER_CORE
            db.execSQL(create + Contract.POWER_CORE + " (" + idField + //
                    Contract.POWER_CORE_CANNON_BOOST + " TEXT, " + //
                    Contract.POWER_CORE_ENGINE_BOOST + " TEXT, " + //
                    Contract.POWER_CORE_IMAGE + " TEXT, " + //
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

    /**
     * This class is the contract that describes tables and fields in the
     * CET Founders database.
     */
    public static final class Contract implements BaseColumns {

        // Table names
        public static final String ASTEROIDS_GAME = "asteroids_game";
        public static final String ASTEROID = "asteroid";
        public static final String LEVEL= "level";
        public static final String MAIN_BODY= "main_body";
        public static final String CANNON = "cannon";
        public static final String EXTRA_PART = "extra_part";
        public static final String ENGINE = "engine";
        public static final String POWER_CORE = "power_core";

        public static final String ASTEROID_NAME = "asteroid_name";
        public static final String ASTEROID_IMAGE = "asteroid_image";
        public static final String ASTEROID_IMAGE_WIDTH = "asteroid_image_width";
        public static final String ASTEROID_IMAGE_HEIGHT = "asteroid_image_height";
        public static final String ASTEROID_TYPE= "asteroid_type";

        public static final String LEVEL_NUMBER= "level_number";
        public static final String LEVEL_TITLE= "level_title";
        public static final String LEVEL_HINT= "level_hint";
        public static final String LEVEL_WIDTH= "level_width";
        public static final String LEVEL_HEIGHT= "level_height";
        public static final String LEVEL_MUSIC= "level_music";
        public static final String LEVEL_OBJECT= "level_object";
        public static final String LEVEL_OBJECT_POSITION= "level_object_position";
        public static final String LEVEL_OBJECT_OBJECT_ID= "level_object_position_id";
        public static final String LEVEL_OBJECT_SCALE= "level_object_scale";
        public static final String LEVEL_ASTEROID= "level_asteroid";
        public static final String LEVEL_ASTEROID_NUMBER= "level_asteroi_numberd";
        public static final String LEVEL_ASTEROID_ASTEROID_ID= "level_asteroid_asteroid_id";

        public static final String MAIN_BODY_CANNON_ATTATCH= "main_body_cannon_attatch";
        public static final String MAIN_BODY_ENGINE_ATTATCH= "main_body_engine_attatch";
        public static final String MAIN_BODY_EXTRA_ATTATCH= "main_body_extra_attatch";
        public static final String MAIN_BODY_IMAGE= "main_body_image";
        public static final String MAIN_BODY_IMAGE_WIDTH= "main_body_image_width";
        public static final String MAIN_BODY_IMAGE_HEIGHT= "main_body_image_height";

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
        public static final String ENGINE_ATTACH_POINT= "engine_attach_point";
        public static final String ENGINE_IMAGE= "engine_image";
        public static final String ENGINE_IMAGE_WIDTH= "engine_image_width";
        public static final String ENGINE_IMAGE_HEIGHT= "engine_image_height";

        public static final String POWER_CORE_CANNON_BOOST = "power_core_cannon_boost";
        public static final String POWER_CORE_ENGINE_BOOST = "power_core_engine_boost";
        public static final String POWER_CORE_IMAGE = "power_core_image";


        /**
         * The authority name for this ContentProvider.
         */
        public static final String AUTHORITY = "edu.byu.cs.superasteroids.database";

        /**
         * Main URI pattern for CET Founders content.
         */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY );


        /**
         * Gives an array of fields in the Founder record, including ID and version fields,
         * together with all content fields.
         *
         * @return List of fields in the Founder record.
         */
        public static String[] allAteroidsTables() {
            return new String[] {
                    ASTEROID, LEVEL, MAIN_BODY, CANNON, EXTRA_PART, ENGINE, POWER_CORE,
            };
        }
    }
}








