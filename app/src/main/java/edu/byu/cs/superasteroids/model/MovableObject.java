package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;
import android.graphics.RectF;

import edu.byu.cs.superasteroids.game.GameHolder;
import edu.byu.cs.superasteroids.helper.DrawingHelper;
import edu.byu.cs.superasteroids.helper.GraphicsUtils;
import edu.byu.cs.superasteroids.helper.content.ContentManager;

import static edu.byu.cs.superasteroids.Constants.SCALE_FACTOR;
import static edu.byu.cs.superasteroids.Constants.STARTING_SHIP_OPACITY;
/**
 * Created by audakel on 5/23/16.
 */

/**
 * All objects that can move in the game
 */
public class MovableObject extends VisiableObject {
    /**
     * Rectangle around an object
     */
    protected RectF rectangle;
    /**
     * How fast an object is going through space
     */
    protected int speed;
    /**
     * Rotation with respect to screen
     */
    protected float rotation;
    /**
     * Position in space
     */
    protected PointF position;
    /**
     * Keep track of image id
     */
    private Integer imageIndex;


    /**
     * @param image image to be displayed
     * @param speed current spped of obj
     * @param rotation current rotation of obj
     * @param position current position of obj in space
     */
    public MovableObject(GameImage image, int speed, float rotation, PointF position) {
        super(image);
        this.speed = speed;
        this.rotation = rotation;
        this.position = position;
    }

    @Override
    public void draw() {
        super.draw();
        if(getImageIndex() < 0 ) return;

        DrawingHelper.drawImage(
                imageIndex,
                position.x,
                position.y,
                getRotation(),
                SCALE_FACTOR,
                SCALE_FACTOR,
                STARTING_SHIP_OPACITY
        );
    }

    /**
     * get correct rectangle bounderies for object
     */
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


    public int getImageIndex() {
        if (imageIndex != null) return imageIndex;

        if (getGameImage() == null) return -1;

        setImageIndex(ContentManager.getInstance().getImageId(getGameImage().getFilePath()));

        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }


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

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public PointF getPosition() {
        return position;
    }

    public void setPosition(PointF position) {
        this.position = position;
    }
}
