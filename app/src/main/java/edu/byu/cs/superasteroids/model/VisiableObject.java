package edu.byu.cs.superasteroids.model;

/**
 * Created by audakel on 5/23/16.
 */

import android.media.Image;

/**
 * All objects that are visiable
 */
public class VisiableObject {
    protected GameImage image;
    protected long objectId;

    public VisiableObject(GameImage image) {
        this.image = image;
    }

    /**
     *  Draw the object on the screen
     */
    public void draw(){

    }

    /**
     * Update the object on the screen
     * @param time
     */
    public void update(double time){

    }

    public GameImage getGameImage() {
        return image;
    }

    public void setGameImage(GameImage image) {
        this.image = image;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }
}
