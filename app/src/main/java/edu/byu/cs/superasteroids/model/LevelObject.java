package edu.byu.cs.superasteroids.model;

import android.content.ContentValues;
import android.graphics.PointF;

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

    public LevelObject(String position, int objectId, float scale) {
        this.position = position;
        this.objectId = objectId;
        this.scale = scale;
    }

    public PointF extractPointFromString(String string){
        return new PointF(Float.valueOf(string.split(",")[0]),
                Float.valueOf(string.split(",")[1]));
    }

    public ContentValues getContentValues(){
        values = new ContentValues();

        values.put(Contract.LEVEL_OBJECT_POSITION, getPosition().x + "," + getPosition().y);
        values.put(Contract.LEVEL_OBJECT_OBJECT_ID, getObjectId());
        values.put(Contract.LEVEL_OBJECT_SCALE, getScale());

        return values;
    }

    public PointF getPosition() {

        return extractPointFromString(position);
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