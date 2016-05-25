package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;

/**
 * Created by audakel on 5/24/16.
 */
public class Projectile extends MovableObject{
    /**
     * @param image    image to be displayed
     * @param speed    current spped of obj
     * @param rotation current rotation of obj
     * @param position current position of obj in space
     */
    public Projectile(GameImage image, int speed, float rotation, PointF position) {
        super(image, speed, rotation, position);
    }
}
