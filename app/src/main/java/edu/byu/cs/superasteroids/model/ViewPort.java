package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.game.GameHolder;
import edu.byu.cs.superasteroids.helper.DrawingHelper;

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

    @Override
    public void draw() {
        space.draw();
    }

    @Override
    public void update(double time) {
        space.update(time);
        updatePosition();
        updateSize();
    }

    private void updatePosition() {
        if(ship.getPosition() == null) {
            return;
        }

        PointF levelDimensions = new PointF(getGameDelegate().getLevel().getGameImage().getWidth(),
                getGameDelegate().getLevel().getGameImage().getHeight()
        );

        PointF newPosition = new PointF(
                (ship.getPosition().x) - (DrawingHelper.getGameViewWidth() / 2),
                (ship.getPosition().y) - (DrawingHelper.getGameViewHeight() / 2)
        );
        if(newPosition.x <= 0) {
            newPosition.x = 0;
        }
        if(newPosition.x >= levelDimensions.x - DrawingHelper.getGameViewWidth()) {
            newPosition.x = levelDimensions.x - (DrawingHelper.getGameViewWidth());
        }
        if(newPosition.y >= levelDimensions.y - DrawingHelper.getGameViewHeight()) {
            newPosition.y = levelDimensions.y - (DrawingHelper.getGameViewHeight());
        }
        if(newPosition.y <= 0) {
            newPosition.y = 0;
        }

        setPosition(newPosition);
    }

    private void updateSize() {
        getGameImage().setWidth(DrawingHelper.getGameViewWidth());
        getGameImage().setHeight(DrawingHelper.getGameViewHeight());
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
