package edu.byu.cs.superasteroids.model.asteroids;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.Constants;
import edu.byu.cs.superasteroids.model.GameImage;
import edu.byu.cs.superasteroids.model.Level;

import static edu.byu.cs.superasteroids.Constants.ASTEROID_TYPE_REGULAR;

/**
 * Created by audakel on 5/25/16.
 */
public class AsteroidRegular extends Asteroid {
    public AsteroidRegular(GameImage image, Level currentLevel) {
        super(image, 0, 0, null, ASTEROID_TYPE_REGULAR, currentLevel);
    }

    public AsteroidRegular(Asteroid asteroid) {
        super(asteroid);
    }

    public AsteroidRegular(Level level) {
        super(new GameImage(169, 153, "images/asteroids/asteroid.png"),
                0, 0, null, ASTEROID_TYPE_REGULAR, level);
    }
}
