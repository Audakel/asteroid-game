package edu.byu.cs.superasteroids.model.asteroids;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.Constants;
import edu.byu.cs.superasteroids.model.GameImage;
import edu.byu.cs.superasteroids.model.Level;

import static edu.byu.cs.superasteroids.Constants.ASTEROID_TYPE_GROWING;
import static edu.byu.cs.superasteroids.Constants.ASTEROID_TYPE_OCTEROID;

/**
 * Created by audakel on 5/25/16.
 */
public class AsteroidOcteroid extends Asteroid {
    public AsteroidOcteroid(GameImage image, Level currentLevel) {
        super(image, 0, 0, null, ASTEROID_TYPE_OCTEROID, currentLevel);
    }

    public AsteroidOcteroid(Asteroid asteroid) {
        super(asteroid);
    }

    public AsteroidOcteroid(Level level) {
        super(new GameImage(169, 153, "images/asteroids/asteroid.png"),
                0, 0, null, ASTEROID_TYPE_OCTEROID, level);
    }
}
