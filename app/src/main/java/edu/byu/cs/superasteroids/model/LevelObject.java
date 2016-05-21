package edu.byu.cs.superasteroids.model;

import android.content.ContentValues;

import java.util.HashMap;

import edu.byu.cs.superasteroids.database.Contract;

/**
 * Created by audakel on 5/21/16.
 */

public class LevelObject {
    private String position;
    private int objectId;
    private float scale;

    private ContentValues values;

    public LevelObject() {}
    public LevelObject(HashMap<String, String> map) {
        this.position = map.get(Contract.LEVEL_OBJECT_POSITION);
        this.objectId = Integer.valueOf(map.get(Contract.LEVEL_OBJECT_OBJECT_ID));
        this.scale = Float.valueOf(map.get(Contract.LEVEL_OBJECT_SCALE));
    }

    public ContentValues getContentValues(){
        values = new ContentValues();

        values.put(Contract.LEVEL_OBJECT_POSITION, getPosition());
        values.put(Contract.LEVEL_OBJECT_OBJECT_ID, getObjectId());
        values.put(Contract.LEVEL_OBJECT_SCALE, getScale());

        return values;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}