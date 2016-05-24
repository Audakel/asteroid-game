package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;
import android.graphics.RectF;

import edu.byu.cs.superasteroids.game.GameHolder;
import edu.byu.cs.superasteroids.helper.GraphicsUtils;

import static edu.byu.cs.superasteroids.Constants.SCALE_FACTOR;
/**
 * Created by audakel on 5/23/16.
 */

/**
 * All objects that can move in the game
 */
public class MovableObject extends VisiableObject {
    protected RectF rectangle;
    protected int speed;
    protected double rotation;
    protected PointF position;

    /**
     * @param image image to be displayed
     * @param speed current spped of obj
     * @param rotation current rotation of obj
     * @param position current position of obj in space
     */
    public MovableObject(GameImage image, int speed, double rotation, PointF position) {
        super(image);
        this.speed = speed;
        this.rotation = rotation;
        this.position = position;
    }


    private RectF generateRectangle(PointF position, GameImage image){
        float scaledCenteredImageHeight = SCALE_FACTOR * image.getHeight() / 2;
        float scaledCenteredImageWidth = SCALE_FACTOR * image.getWidth() / 2;

        return new RectF(
                position.x - scaledCenteredImageWidth,
                position.x + scaledCenteredImageWidth,
                position.y - scaledCenteredImageHeight,
                position.y + scaledCenteredImageHeight
        );
    }

    /**
     *
     * @return point on view port of the object
     */
    public PointF getViewPosition() {
        return GraphicsUtils.subtract(
                getPosition(),
                GameHolder.getViewPort().getPosition()
        );
    }

    /**
     * get correct rectangle bounderies for object
     */
    public RectF getRectangle() {
        if (rectangle != null) return rectangle;

        rectangle = generateRectangle(getPosition(), getGameImage());

        return rectangle;
    }

    public void setRectangle(RectF rectangle) {
        this.rectangle = rectangle;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public PointF getPosition() {
        return position;
    }

    public void setPosition(PointF position) {
        this.position = position;
    }
}
