package edu.byu.cs.superasteroids.model;

import android.graphics.Color;
import android.graphics.PointF;

import edu.byu.cs.superasteroids.Constants;
import edu.byu.cs.superasteroids.helper.DrawingHelper;
import edu.byu.cs.superasteroids.helper.GraphicsUtils;

/**
 * Created by audakel on 5/25/16.
 */
public class Bullet extends MovableObject {

    /**
     * This is the power that the bullet contains.
     */
    private int power;
    /**
     * Bool to tell if bullet is in game bounderies. True when out of bounds
     */
    private boolean dead;

    /**
     * @param image    image to be displayed
     * @param speed    current spped of obj
     * @param rotation current rotation of obj
     * @param position current position of obj in space
     */
    public Bullet(GameImage image, int speed, float rotation, PointF position) {
        super(image, speed, rotation, position);
    }

    @Override
    public void draw() {
        if(dead) return;

        super.draw();
        DrawingHelper.drawRectangle( getRectangle(), Color.MAGENTA, Constants.OPACITY_FULL);
    }

    @Override
    public void update(double time) {
        GraphicsUtils.MoveObjectResult result =
                GraphicsUtils.moveObject(getPosition(),getRectangle(), getSpeed(),
                        GraphicsUtils.degreesToRadians(getRotation() - 90),time);

        if(getGameDelegate().getViewPort().getSpace().inBounds(getPosition())) {
            setPosition(result.getNewObjPosition());
            setRectangle(result.getNewObjBounds());
        }
        else {
            dead = true;
        }
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
