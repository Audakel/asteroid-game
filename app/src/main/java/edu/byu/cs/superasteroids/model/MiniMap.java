package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;
import android.graphics.Rect;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.Constants;
import edu.byu.cs.superasteroids.helper.DrawingHelper;
import edu.byu.cs.superasteroids.helper.GraphicsUtils;
import edu.byu.cs.superasteroids.model.asteroids.Asteroid;

import static edu.byu.cs.superasteroids.Constants.MINIMAP_ASTEROIDS_WIDTH;
import static edu.byu.cs.superasteroids.Constants.MINIMAP_ASTEROID_COLOR;
import static edu.byu.cs.superasteroids.Constants.MINIMAP_SHIP_COLOR;
import static edu.byu.cs.superasteroids.Constants.MINIMAP_SHIP_WIDTH;
import static edu.byu.cs.superasteroids.Constants.OPACITY_MINIMAP;

/**
 * Created by audakel on 5/24/16.
 */
public class MiniMap extends MovableObject{
    private Ship ship;
    private ArrayList<Asteroid> asteroids;

    /**
     * @param image    image to be displayed
     * @param speed    current spped of obj
     * @param rotation current rotation of obj
     * @param position current position of obj in space
     */
    public MiniMap(GameImage image, int speed, float rotation, PointF position, ArrayList<Asteroid> asteroids) {
        super(image, speed, rotation, position);
        this.asteroids = asteroids;
    }

    @Override
    public void draw() {
        // Draw map
        int x = (int) getPosition().x;
        int y = (int) getPosition().y;
        DrawingHelper.drawFilledRectangle(
                new Rect(x,y, x + getGameImage().getWidth(), y + getGameImage().getHeight()),
                Constants.COLOR_MINIMAP,
                Constants.OPACITY_MINIMAP
        );

        // Draw ship
        drawHelper(ship.getPosition(), MINIMAP_SHIP_WIDTH, MINIMAP_SHIP_COLOR, OPACITY_MINIMAP);

        // Draw asteroid
        for (Asteroid asteroid : asteroids){
            drawHelper(asteroid.getPosition(), MINIMAP_ASTEROIDS_WIDTH, MINIMAP_ASTEROID_COLOR, OPACITY_MINIMAP);
        }
    }

    /**
     * Helper for minimap to draw points on
     * @param position position on map
     * @param width desired width of new minimap object
     * @param color of minimap object
     * @param alpha of minmap object
     */
    private void drawHelper(PointF position, float width, int color, int alpha){
        PointF scaledPosition = new PointF(position.x * .3f, position.y * .3f);
        position = GraphicsUtils.add(getPosition(), scaledPosition);
        DrawingHelper.drawPoint(position, width, color, alpha);
    }
}
