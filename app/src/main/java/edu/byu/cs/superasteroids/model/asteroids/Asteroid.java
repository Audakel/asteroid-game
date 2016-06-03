package edu.byu.cs.superasteroids.model.asteroids;

import android.content.ContentValues;
import android.graphics.PointF;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import edu.byu.cs.superasteroids.Constants;
import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.helper.GraphicsUtils;
import edu.byu.cs.superasteroids.model.GameImage;
import edu.byu.cs.superasteroids.model.Level;
import edu.byu.cs.superasteroids.model.MovableObject;

import static edu.byu.cs.superasteroids.Constants.*;

/**
 * Created by audakel on 5/16/16.
 */
public class Asteroid extends MovableObject {
    /**
     * The type of the asteroid.
     */
    private String type;
    /**
     * Name of asteroid - same as type
     */
    private String name;
    /**
     * Booelan on if the asteroid has been hit of not.
     */
    private boolean dead;
    /**
     * Number of times an asteroid can be hit.
     */
    private int health;
    /**
     * Current level that the player is on.
     */
    private Level currentLevel;
    /**
     * Scale of the asteroid.
     */
    private float scale;
    /**
     * This is the number of asteroids that are split when the asteroid is hit.
     */
    private int numberMiniAsteroids;
    /**
     * Helper to prep vavlues for db
     */
    private  ContentValues values;


    /**
     * @param image    image to be displayed
     * @param speed    current spped of obj
     * @param rotation current rotation of obj
     * @param position current position of obj in space
     */
    public Asteroid (GameImage image, int speed, float rotation, PointF position, String type, Level currentLevel) {
        super(image, speed, rotation, position);
        this.type = type;
        this.dead = false;
        this.currentLevel = currentLevel;
        this.health = ASTEROID_HEALTH;
        this.scale = ASTEROID_SCALE;

        if (getSpeed() == 0){
            setSpeed(getRandomSpeed());
        }

        if (rotation == 0){
            setRotation(getRandomRotation());
        }

        if (position == null){
            setPosition(getRandomPosition());
        }
    }


    /**
     * Asteroid constoructor from another asteroid
     * @param asteroid existing asteroid
     */
    public Asteroid(Asteroid asteroid) {
        super(asteroid.getGameImage(), asteroid.getSpeed(), asteroid.getRotation(), asteroid.getPosition());
        this.currentLevel = asteroid.getCurrentLevel();
        this.type = asteroid.getType();
        this.dead = false;
        this.health = ASTEROID_HEALTH;
        this.scale = ASTEROID_SCALE;
    }

    /**
     * Helper json constructor
     */
    public Asteroid(JSONObject jo)  {
        super(null, 0,0, null);

        try {
            this.setGameImage(new GameImage(jo.getInt("imageWidth"),  jo.getInt("imageHeight"), jo.getString("image")));
            this.name = jo.getString("name");
            this.type = jo.getString("type");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    /**
     * What happens when an asteroid is hit
     */
    public void hit() {
        switch (this.getType()){
            case ASTEROID_TYPE_REGULAR:         setNumberMiniAsteroids(2); break;
            case ASTEROID_TYPE_GROWING:         setNumberMiniAsteroids(2); break;
            case ASTEROID_TYPE_OCTEROID:        setNumberMiniAsteroids(8); break;
        }
        setDead(true);

        if (health > 1) makeMiniAsteroids();
    }

    @Override
    public void update(double time) {
        super.update(time);
    }



    /**
     * Creates the correct number of small asteroids and adds them to the current level
     */
    private void makeMiniAsteroids() {
        for(int i = 0; i < numberMiniAsteroids; i++) {
            Asteroid babyAsteroid = new Asteroid(this.image, this.getSpeed(), getRandomRotation(),
                    getPosition(), this.getType(), getCurrentLevel());

            babyAsteroid.setHealth(1);
            babyAsteroid.setScale(getScale() / getNumberMiniAsteroids());
            getCurrentLevel().getAsteroidsToAdd().add(babyAsteroid);
        }
    }


    @Override
    public PointF getPosition() {
        if (position == null){
            setPosition(getRandomPosition());
        }
        return super.getPosition();
    }

    @Override
    public float getRotation() {
        if (rotation == 0.0){
            setRotation(getRandomRotation());
        }
        return super.getRotation();
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }



    public ContentValues getValues() {
        return values;
    }

    public void setValues(ContentValues values) {
        this.values = values;
    }

    public ContentValues getContentValues(){
        values = new ContentValues();

//        values.put(Contract.ASTEROID_NAME, getName());
        values.put(Contract.ASTEROID_IMAGE, getGameImage().getFilePath());
        values.put(Contract.ASTEROID_IMAGE_WIDTH, getGameImage().getWidth());
        values.put(Contract.ASTEROID_IMAGE_HEIGHT, getGameImage().getHeight());
        values.put(Contract.ASTEROID_TYPE, getType());

        return values;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberMiniAsteroids() {
        return numberMiniAsteroids;
    }

    public void setNumberMiniAsteroids(int numberMiniAsteroids) {
        this.numberMiniAsteroids = numberMiniAsteroids;
    }
}
