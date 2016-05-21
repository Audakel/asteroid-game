package edu.byu.cs.superasteroids.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by audakel on 5/14/16.
 */

public class Provider extends ContentProvider {

    // URI matcher codes
    private static final int URI_MATCHER_ASTEROID = 1;
    private static final int URI_MATCHER_LEVEL = 2;
    private static final int URI_MATCHER_MAIN_BODY = 3;
    private static final int URI_MATCHER_CANNON = 4;
    private static final int URI_MATCHER_EXTRA_PART = 5;
    private static final int URI_MATCHER_ENGINE = 6;
    private static final int URI_MATCHER_POWER_CORE = 7;

    // MIME type codes
    private static final String MIME_COLLECTION = "vnd.android.cursor.dir/";
    private static final String MIME_ITEM = "vnd.android.cursor.item/";
    private static final String MIME_BASE = "vnd.helloandroid.";

    private final String TAG = this.getClass().getSimpleName();

    /**
     * Handle to our underlying database.
     */
    private DatabaseHelper mDatabase = null;

    /**
     * URI matcher to identify what kind of request we're receiving.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // A static initializer executes when the Java VM first loads this class.
    // We use it to perform one-time initialization of static members like sUriMatcher.
    public Provider(){
        String auth = Contract.AUTHORITY;
        String ast = Contract.ASTEROIDS;
        sUriMatcher.addURI(auth, ast, URI_MATCHER_ASTEROID);
//        sUriMatcher.addURI(Contract.AUTHORITY, Contract.FOUNDER + "/#", URI_MATCHER_FOUNDER_ID);
//        sUriMatcher.addURI(Contract.AUTHORITY, Contract.FULL_TEXT_SEARCH, URI_MATCHER_FTS);
    }

    @Override
    public int delete( Uri uri, String where, String[] whereArgs) {
        return modify(uri, null, where, whereArgs);
    }

    @Override
    public String getType( Uri uri) {
        switch (sUriMatcher.match(uri)) {
            // This could be an if/else, but with more tables we'll want a switch instead.
            case URI_MATCHER_ASTEROID:
                return MIME_ITEM + MIME_BASE + Contract.ASTEROIDS;
            default:
                return MIME_COLLECTION + MIME_BASE + Contract.ASTEROIDS;
        }
    }

    @Override
    public Uri insert( Uri uri, ContentValues initialValues) {
        long rowId = mDatabase.getWritableDatabase().insert(tableForUri(uri), Contract.NULL_COLUMN_HACK, initialValues);

        if (rowId <= 0) {
            throw new SQLException("Failed insert: " + uri);
        }

        // Now notify the resolver of the change so it can inform any listeners.
        Uri insertUri = ContentUris.withAppendedId(Contract.URI_ASTEROID, rowId);
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
        mDatabase = new DatabaseHelper(getContext());

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

//        if (sUriMatcher.match(uri) == URI_MATCHER_FOUNDER_ID) {
//            qb.appendWhere(Contract._ID + "=" + uri.getLastPathSegment());
//        }

        // Note that we're not really performing the query per se, but rather building a Cursor that will
        // iterate over all the query results.  We can use a read-only database here.
        Cursor cursor = qb.query(mDatabase.getReadableDatabase(), projection, selection, selectionArgs, null, null, orderBy);

        // The cursor needs to know about the resolver so it can be informed of any changes while
        // the cursor is active because changes could impact cursor results.
        Context context = getContext();

        if (context != null) {
//            cursor.setNotificationUri(context.getContentResolver(), uri);
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
            case URI_MATCHER_ASTEROID:
                return Contract.ASTEROIDS;
            case URI_MATCHER_CANNON:
                return Contract.CANNONS;
            case URI_MATCHER_ENGINE:
                return Contract.ENGINES;
            case URI_MATCHER_EXTRA_PART:
                return Contract.EXTRA_PARTS;
            case URI_MATCHER_LEVEL:
                return Contract.LEVELS;
            case URI_MATCHER_MAIN_BODY:
                return Contract.MAIN_BODIES;
            case URI_MATCHER_POWER_CORE:
                return Contract.POWER_CORES;

            default:
                return Contract.ASTEROIDS;
        }
    }

    @Override
    public int update( Uri uri, ContentValues values, String where, String[] whereArgs) {
        return modify(uri, values, where, whereArgs);
    }




}








