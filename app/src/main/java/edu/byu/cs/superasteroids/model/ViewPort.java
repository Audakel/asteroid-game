package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;

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
     * @param speed    current spped of obj
     * @param rotation current rotation of obj
     * @param position current position of obj in space
     */
    public ViewPort(GameImage image, int speed, double rotation, PointF position) {
        super(image, speed, rotation, position);

    }


}
