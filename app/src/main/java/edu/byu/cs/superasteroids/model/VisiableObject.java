package edu.byu.cs.superasteroids.model;

/**
 * Created by audakel on 5/23/16.
 */

import android.media.Image;

import edu.byu.cs.superasteroids.base.GameDelegate;

/**
 * All objects that are visiable
 */
public class VisiableObject {
    protected GameImage image;
    protected long objectId;
    /**
     * Optional reference to the game delagate which holds all info. Has to be set with a setter
     */
    private GameDelegate gameDelegate;


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

    public GameImage getImage() {
        return image;
    }

    public void setImage(GameImage image) {
        this.image = image;
    }

    public GameDelegate getGameDelegate() {
        return gameDelegate;
    }

    public void setGameDelegate(GameDelegate gameDelegate) {
        this.gameDelegate = gameDelegate;
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
