package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;
import android.provider.SyncStateContract;
import android.view.View;

import edu.byu.cs.superasteroids.Constants;

import static edu.byu.cs.superasteroids.Constants.GAME_HEIGHT;
import static edu.byu.cs.superasteroids.Constants.GAME_WIDTH;

/**
 * Created by audakel on 5/23/16.
 */

/**
 * Draws the background of the current level
 */
public class Space extends MovableObject {
    ViewPort viewPort;

    public Space(ViewPort viewPort) {
        super(new GameImage(GAME_WIDTH, GAME_HEIGHT, "images/space.bmp"), 0, 0, new PointF(0,0));

        this.viewPort = viewPort;
    }


}
