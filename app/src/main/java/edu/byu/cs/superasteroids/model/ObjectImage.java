package edu.byu.cs.superasteroids.model;

import android.content.ContentValues;

import java.util.HashMap;

import edu.byu.cs.superasteroids.database.Contract;

/**
 * Created by audakel on 5/21/16.
 */

public class ObjectImage {
    private String image;
    private ContentValues values;

    public ObjectImage() {}

    public ObjectImage(String image) {
        this.image = image;
    }

    public ContentValues getContentValues(){
        values = new ContentValues();
        values.put(Contract.OBJECT_IMAGES, getImage());
        return values;
    }

    public String getImage() {
        return image;
    }

    public void setImages(String images) {
        this.image = images;
    }
}