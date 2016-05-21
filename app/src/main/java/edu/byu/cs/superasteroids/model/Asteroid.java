package edu.byu.cs.superasteroids.model;

import android.content.ContentValues;

import java.util.Scanner;

import edu.byu.cs.superasteroids.database.Contract;

/**
 * Created by audakel on 5/16/16.
 */
public class Asteroid {
    private String name;
    private String image;
    private String imageWidth;
    private String imageHeight;
    private String type;
    private  ContentValues values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getParam(String s) {
        return null;
    }

    public ContentValues getContentValues(){
        values = new ContentValues();

        values.put(Contract.ASTEROID_NAME, getName());
        values.put(Contract.ASTEROID_IMAGE, getImage());
        values.put(Contract.ASTEROID_IMAGE_WIDTH, getImageWidth());
        values.put(Contract.ASTEROID_IMAGE_HEIGHT, getImageHeight());
        values.put(Contract.ASTEROID_TYPE, getType());

        return values;
    }
}
