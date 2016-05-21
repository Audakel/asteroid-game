package edu.byu.cs.superasteroids.model;

import android.content.ContentValues;

import edu.byu.cs.superasteroids.database.Contract;

/**
 * Created by audakel on 5/16/16.
 */
public class MainBody {
    private String cannonAttach;
    private String engineAttach;
    private String extraAttach;
    private String image;
    private int imageWidth;
    private int imageHeight;

    private ContentValues values;

    public ContentValues getContentValues(){
        values = new ContentValues();

        values.put(Contract.MAIN_BODY_CANNON_ATTATCH, getCannonAttach());
        values.put(Contract.MAIN_BODY_ENGINE_ATTATCH, getEngineAttach());
        values.put(Contract.MAIN_BODY_EXTRA_ATTATCH, getExtraAttach());
        values.put(Contract.MAIN_BODY_IMAGE, getImage());
        values.put(Contract.MAIN_BODY_IMAGE_WIDTH, getImageWidth());
        values.put(Contract.MAIN_BODY_IMAGE_HEIGHT, getImageHeight());

        return values;
    }

    public String getCannonAttach() {
        return cannonAttach;
    }

    public void setCannonAttach(String cannonAttach) {
        this.cannonAttach = cannonAttach;
    }

    public String getEngineAttach() {
        return engineAttach;
    }

    public void setEngineAttach(String engineAttach) {
        this.engineAttach = engineAttach;
    }

    public String getExtraAttach() {
        return extraAttach;
    }

    public void setExtraAttach(String extraAttach) {
        this.extraAttach = extraAttach;
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
