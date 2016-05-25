package edu.byu.cs.superasteroids.model.asteroids;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.model.GameImage;
import edu.byu.cs.superasteroids.model.Level;

/**
 * Created by audakel on 5/25/16.
 */
public class AsteroidGrowing extends Asteroid {
    public AsteroidGrowing(GameImage image, int speed, float rotation, PointF position, String type, Level currentLevel) {
        super(image, speed, rotation, position, type, currentLevel);
    }

    public AsteroidGrowing(Asteroid asteroid) {
        super(asteroid);
    }
}
