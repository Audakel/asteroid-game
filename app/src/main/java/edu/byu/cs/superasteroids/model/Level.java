package edu.byu.cs.superasteroids.model;

import android.content.ContentValues;
import android.graphics.RectF;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.helper.DrawingHelper;
import edu.byu.cs.superasteroids.helper.content.ContentManager;
import edu.byu.cs.superasteroids.model.asteroids.Asteroid;
import edu.byu.cs.superasteroids.model.asteroids.AsteroidGrowing;
import edu.byu.cs.superasteroids.model.asteroids.AsteroidOcteroid;
import edu.byu.cs.superasteroids.model.asteroids.AsteroidRegular;

import static edu.byu.cs.superasteroids.Constants.*;
import static edu.byu.cs.superasteroids.Constants.SHIP_HIT_COLOR;
import static edu.byu.cs.superasteroids.Constants.SHIP_HIT_RADIUS;

/**
 * Created by audakel on 5/16/16.
 */
public class Level extends VisiableObject{
    /**
     * Logging tag
     */
    private final String TAG = this.getClass().getSimpleName();
    /**
     * The title of the level.
     */
    private String title;
    /**
     * The hint for the level.
     */
    private String hint;
    /**
     * The path to the music file for the level.
     */
    private String music;
    /**
     * The number of the level.
     */
    private int number;
    /**
     * The set of objects found in the background of the level.
     */
    private ArrayList<LevelObject> levelObjects;
    /**
     * Array full of level asteroids which hold info to make all the asteroids
     */
    private ArrayList<LevelAsteroid> levelAsteroids;
    /**
     * This represents the asteroids found in the level.
     */
    private ArrayList<Asteroid> asteroids;
    /**
     * This is the list of asteroids that need to be added.
     */
    private List<Asteroid> asteroidsToAdd;
    /**
     * This is a list of the current bullets on the map.
     */
    private List<Bullet> bullets;
    /**
     * This is the mini map for the level.
     */
    private MiniMap minimap;
    /**
     * ContenetValues for db insertion
     */
    private  ContentValues values;
    /**
     * width of the level
     */
    private int width;
    /**
     * height of the level
     */
    private int height;
    /**
     * Types of asteroids
     */
    private List<String> asteroidTypes = Arrays.asList();



    /**
     * Full constructor for a level
     * @param gameImage image for background
     * @param map a list of all the required items to init
     */
    public Level(GameImage gameImage, HashMap<String, String> map) {
        super(gameImage);
        this.number = Integer.valueOf(map.get(Contract.LEVEL_NUMBER));
        this.title = map.get(Contract.LEVEL_TITLE);
        this.hint = map.get(Contract.LEVEL_HINT);
        this.width = Integer.valueOf(map.get(Contract.LEVEL_WIDTH));
        this.height = Integer.valueOf(map.get(Contract.LEVEL_HEIGHT));
        this.music = map.get(Contract.LEVEL_MUSIC);
        this.minimap = new MiniMap(new GameImage(0, 0, ""), asteroids);

    }


    /**
     * Empty constructor
     */
    public Level(){
        super(new GameImage(0,0,""));
        this.minimap = new MiniMap(new GameImage(0, 0, ""), asteroids);

    }

    /**
     * Json constructor for a level. This is called when loading data from json file
     * @param jsonObject
     */
    public Level(JSONObject jsonObject) {
        super(new GameImage(0,0,""));

        try {
            this.number = jsonObject.getInt("number");
            this.title = jsonObject.getString("title");
            this.hint = jsonObject.getString("hint");
            this.width = jsonObject.getInt("width");
            this.height = jsonObject.getInt("height");
            this.music = jsonObject.getString("music");

            this.levelAsteroids = extractLevelAsteroids(jsonObject);
            this.levelObjects = extractLevelObjects(jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (asteroids == null){
            initAsteroids();
        }

        this.minimap = new MiniMap(new GameImage(0, 0, ""), asteroids);
        this.asteroids = new ArrayList<>();
        this.asteroidsToAdd = new ArrayList<>();
        this.bullets = new ArrayList<>();
    }

    /**
     * Helps the level constructor parse out the level asteroids and makes them into objects
     * @param jsonObject a json level object
     * @return array of levelAsteroids
     */
    private ArrayList<LevelAsteroid> extractLevelAsteroids(JSONObject jsonObject) {
        ArrayList<LevelAsteroid> asteroids = new ArrayList<>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("levelAsteroids");
            for (int i = 0; i < jsonArray.length(); i++) {
                LevelAsteroid levelAsteroid = new LevelAsteroid(
                        jsonArray.getJSONObject(i).getInt("number"),
                        jsonArray.getJSONObject(i).getInt("asteroidId")
                );
                asteroids.add(levelAsteroid);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return asteroids;

    }


    /**
     * Helps the level constructor parse out the levelObjects and makes them into objects
     * @param jsonObject a json level object
     * @return array of levelObjects
     */
    private ArrayList<LevelObject> extractLevelObjects(JSONObject jsonObject) {
        ArrayList<LevelObject> objects = new ArrayList<>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("levelObjects");

            for (int i = 0; i < jsonArray.length(); i++) {
                LevelObject levelObject = new LevelObject(
                        jsonArray.getJSONObject(i).getString("position"),
                        jsonArray.getJSONObject(i).getInt("objectId"),
                        Float.valueOf(jsonArray.getJSONObject(i).getString("scale"))
                );
                objects.add(levelObject);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return objects;
    }


    /**
     * This method will initialize the asteroids in each LevelAsteroid object.
     */
    public void initAsteroids() {
        asteroids = new ArrayList<>();
        for(LevelAsteroid levelAsteroid : levelAsteroids) {
            for(int i = 0; i < levelAsteroid.getNumber(); i++) {
                asteroids.add(makeAsteroidOfType(levelAsteroid.getAsteroidId()));
            }
        }
    }


    /**
     * Takes an id and will make the correct type of asteroid
     * @param asteroidId the id from the the db that coresponds to the type [1,2,3]
     * @return a new asteroid
     */
    private Asteroid makeAsteroidOfType(int asteroidId) {
        switch (asteroidId) {
            case 1: return new AsteroidRegular(this);
            case 2: return new AsteroidGrowing(this);
            case 3: return new AsteroidOcteroid(this);

            default: return null;
        }
    }


    /**
     * Will cause all the things in the level to get drawn
     */
    @Override
    public void draw() {
        super.draw();
        // Draw all the asteroids
        for (Asteroid asteroid : asteroids){
            asteroid.draw();
        }

        // Draw all the bullets
        for (Bullet bullet: bullets){
            bullet.draw();
        }
        // Draw the minimap
        minimap.draw();
    }


    /**
     * update all the game items
     * @param time
     */
    @Override
    public void update(double time) {
        updateCollisions(time);
        updateBullets(time);
        updateAsteroids(time);
        getMinimap().update(time);

    }

    /**
     * Will go through a list of all the possible collosions (bullets, asteroids, ship) and do the
     * appropriate updates if there are collisions
     * @param time update time
     */
    private void updateCollisions(double time) {
        List<Asteroid> deadAsteroids = new ArrayList<>();
        List<Bullet> deadBullets = new ArrayList<>();

        for(Bullet bullet: bullets) {
            if(bullet.isDead()) {
                Log.d(TAG, "updateCollisions: removing dead bullet");
                deadBullets.add(bullet);
            }

            for(Asteroid asteroid : asteroids) {
                if(asteroid.getRectangle().intersect(bullet.getRectangle())){
                    Log.d(TAG, "updateCollisions: asteroid hit");
                    asteroid.hit();
                    bullet.setDead(true);
                }

                // Get all the dead asteroids
                if(asteroid.isDead()) {
                    deadAsteroids.add(asteroid);
                }

                // Move to next level
                if((asteroids.size() + asteroidsToAdd.size()) == 0) {
                    getGameDelegate().loadContent(ContentManager.getInstance());
                }
            }
            asteroids.addAll(asteroidsToAdd);
        }
        asteroidsToAdd.clear();
        asteroids.removeAll(deadAsteroids);
        bullets.removeAll(deadBullets);
    }


    private void updateBullets(double time) {
        for(Bullet bullet : bullets) {
            bullet.update(time);
        }
    }

    /**
     * will update all the asteroids, and check if they hit the ship
     * @param time passed
     */
    private void updateAsteroids(double time) {
        for(Asteroid asteroid : asteroids) {
            asteroid.update(time);

            if(getGameDelegate().getShip().getRectangle() == null) return;

            // No intersection of ship and asteroid
            if (!RectF.intersects(getGameDelegate().getShip().getRectangle(), asteroid.getRectangle())) return;

            // Ship is safe so no damage can be done
            if (getGameDelegate().getShip().isSafe()) return;

            // Ship is hit!!
            asteroid.setHealth(asteroid.getHealth() - 1);
            getGameDelegate().getShip().hit(time);
            getGameDelegate().getShip().setSafe(true);
            DrawingHelper.drawFilledCircle(
                    getGameDelegate().getShip().getViewPosition(), SHIP_HIT_RADIUS, SHIP_HIT_COLOR, OPACITY_PARTIAL);

        }
    }

    public ContentValues getContentValues(){
        values = new ContentValues();

        values.put(Contract.LEVEL_NUMBER, getNumber());
        values.put(Contract.LEVEL_TITLE, getTitle());
        values.put(Contract.LEVEL_HINT, getHint());
        values.put(Contract.LEVEL_WIDTH, getWidth());
        values.put(Contract.LEVEL_HEIGHT, getHeight());
        values.put(Contract.LEVEL_MUSIC, getMusic());

        return values;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<LevelObject> getLevelObjects() {
        return levelObjects;
    }

    public ArrayList<LevelAsteroid> getLevelAsteroids() {
        return levelAsteroids;
    }

    public void setLevelAsteroids(ArrayList<LevelAsteroid> levelAsteroids) {
        this.levelAsteroids = levelAsteroids;
    }

    public List<String> getAsteroidTypes() {
        return asteroidTypes;
    }

    public void setAsteroidTypes(List<String> asteroidTypes) {
        this.asteroidTypes = asteroidTypes;
    }

    public void setLevelObjects(ArrayList<LevelObject> levelObjects) {
        this.levelObjects = levelObjects;
    }

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(ArrayList<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    public List<Asteroid> getAsteroidsToAdd() {
        return asteroidsToAdd;
    }

    public void setAsteroidsToAdd(List<Asteroid> asteroidsToAdd) {
        this.asteroidsToAdd = asteroidsToAdd;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public MiniMap getMinimap() {
        return minimap;
    }

    public void setMinimap(MiniMap minimap) {
        this.minimap = minimap;
    }

    public ContentValues getValues() {
        return values;
    }

    public void setValues(ContentValues values) {
        this.values = values;
    }
}
