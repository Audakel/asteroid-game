package edu.byu.cs.superasteroids.model.asteroids;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.Constants;
import edu.byu.cs.superasteroids.model.GameImage;
import edu.byu.cs.superasteroids.model.Level;

import static edu.byu.cs.superasteroids.Constants.ASTEROID_TYPE_GROWING;
import static edu.byu.cs.superasteroids.Constants.ASTEROID_TYPE_REGULAR;

/**
 * Created by audakel on 5/25/16.
 */
public class AsteroidGrowing extends Asteroid {
    public AsteroidGrowing(GameImage image, Level currentLevel) {
        super(image, 0, 0, null, ASTEROID_TYPE_GROWING, currentLevel);
    }

    public AsteroidGrowing(Asteroid asteroid) {
        super(asteroid);
    }

    public AsteroidGrowing(Level level) {
        super(new GameImage(161, 178, "images/asteroids/blueasteroid.png"),
                0, 0, null, ASTEROID_TYPE_GROWING, level);
    }
}
