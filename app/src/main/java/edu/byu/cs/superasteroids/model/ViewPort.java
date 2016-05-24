package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.game.GameHolder;

/**
 * Created by audakel on 5/23/16.
 */

/**
 * Represents the game screen, the area of space that is currently visiable
 */
public class ViewPort extends MovableObject{
    private Ship ship;
    private Space space;


    /**
     * @param image    image to be displayed
     * @param position current position of obj in space
     */
    public ViewPort(GameImage image, PointF position) {
        super(image, 0, 0, position);

        this.ship = GameHolder.getAsteroidsGame().getShip();
        this.space = new Space(this);

    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }
}
