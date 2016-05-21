package edu.byu.cs.superasteroids.model;

import android.content.ContentValues;

import edu.byu.cs.superasteroids.database.Contract;

/**
 * Created by audakel on 5/16/16.
 */
public class ExtraPart {
    private String attachPoint;
    private String image;
    private int imageWidth;
    private int imageHeight;

    private ContentValues values;

    public ContentValues getContentValues(){
        values = new ContentValues();

        values.put(Contract.EXTRA_PART_ATTATCH_POINT, getAttachPoint());
        values.put(Contract.EXTRA_PART_IMAGE, getImage());
        values.put(Contract.EXTRA_PART_IMAGE_WIDTH, getImageWidth());
        values.put(Contract.EXTRA_PART_IMAGE_HEIGHT, getImageHeight());

        return values;
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
