package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.List;

import edu.byu.cs.superasteroids.Constants;
import edu.byu.cs.superasteroids.helper.DrawingHelper;

import static edu.byu.cs.superasteroids.Constants.GAME_HEIGHT;
import static edu.byu.cs.superasteroids.Constants.GAME_WIDTH;

/**
 * Created by audakel on 5/23/16.
 */

/**
 * Draws the background of the current level
 */
public class Space extends MovableObject {

    public Space(ViewPort viewPort) {
        super(new GameImage(GAME_WIDTH, GAME_HEIGHT, "images/space.bmp"), 0, 0, new PointF(0,0));
    }

    public ViewPort _getViewPort() {
        return getGameDelegate().getViewPort();
    }

    public Level _getLevel() {
        return getGameDelegate().getLevel();
    }

    @Override
    public void draw() {
        drawSpace();

        drawBackgroundImages();
    }

    /**
     * This is a helper method to draw the space background to scale.
     */
    private void drawSpace() {
        PointF scale = new PointF(2048f / getGameImage().getWidth(),2048f / getGameImage().getHeight());

        if(_getViewPort() == null) {
            return;
        }

        Rect rect  = new Rect(
                (int) (_getViewPort().getPosition().x * scale.x),
                (int) (_getViewPort().getPosition().y * scale.y),
                (int) ((_getViewPort().getPosition().x + DrawingHelper.getGameViewWidth()) * scale.x),
                (int) ((_getViewPort().getPosition().y + DrawingHelper.getGameViewHeight()) * scale.y)
        );

        DrawingHelper.drawImage(getImageIndex(),rect, null);

    }

    private void drawBackgroundImages() {
        if(_getLevel() == null || _getLevel().getLevelObjects() != null) {
            return;
        }

        List<LevelObject> levelObjects = _getLevel().getLevelObjects();

        for (LevelObject levelObject : levelObjects) {
            DrawingHelper.drawImage(
                    levelObject.getObjectId(),
                    levelObject.getPosition().x - _getViewPort().getPosition().x,
                    levelObject.getPosition().y - _getViewPort().getPosition().y,
                    Constants.LEVEL_OBJECT_SPEED,
                    levelObject.getScale(),
                    levelObject.getScale(),
                    Constants.OPACITY_PARTIAL
            );
        }
    }


    @Override
    public void update(double time) {
        if(_getLevel() == null) {
            return;
        }

        getGameImage().setWidth(_getLevel().getGameImage().getWidth());
        getGameImage().setHeight(_getLevel().getGameImage().getHeight());
        setRectangle(new RectF(0, 0, getGameImage().getWidth(), getGameImage().getHeight()));

    }

    public boolean inBounds(PointF pointF) {
        return (getRectangle().contains(pointF.x, pointF.y));
    }

}
