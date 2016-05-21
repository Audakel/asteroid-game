package edu.byu.cs.superasteroids.model;

import android.content.ContentValues;

import edu.byu.cs.superasteroids.database.Contract;

/**
 * Created by audakel on 5/16/16.
 */
public class Engine {
    private int baseSpeed;
    private int baseTurnRate;
    private String attachPoint;
    private String image;
    private int imageWidth;
    private int imageHeight;

    private ContentValues values;

    public ContentValues getContentValues(){
        values = new ContentValues();

        values.put(Contract.ENGINE_BASE_SPEED, getBaseSpeed());
        values.put(Contract.ENGINE_BASE_TURN_RATE, getBaseTurnRate());
        values.put(Contract.ENGINE_ATTACH_POINT, getAttachPoint());
        values.put(Contract.ENGINE_IMAGE, getImage());
        values.put(Contract.ENGINE_IMAGE_WIDTH, getImageWidth());
        values.put(Contract.ENGINE_IMAGE_HEIGHT, getImageHeight());

        return values;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public int getBaseTurnRate() {
        return baseTurnRate;
    }

    public void setBaseTurnRate(int baseTurnRate) {
        this.baseTurnRate = baseTurnRate;
    }

    public String getAttachPoint() {
        return attachPoint;
    }

    public void setAttachPoint(String attachPoint) {
        this.attachPoint = attachPoint;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }
}
