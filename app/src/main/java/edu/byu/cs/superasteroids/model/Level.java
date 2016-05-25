package edu.byu.cs.superasteroids.model;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.byu.cs.superasteroids.Constants;
import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.game.GameHolder;
import edu.byu.cs.superasteroids.model.asteroids.Asteroid;
import edu.byu.cs.superasteroids.model.asteroids.AsteroidRegular;

/**
 * Created by audakel on 5/16/16.
 */
public class Level extends VisiableObject{
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
     * This is a list of the current projectiles on the map.
     */
    private List<Projectile> projectiles;
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
     * Empty constructor
     */
    public Level(){
        super(new GameImage(0,0,""));
    }

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
    }


    /**
     * This method will initialize the asteroids in each LevelAsteroid object.
     */
    private void initAsteroids() {
        for(LevelAsteroid levelAsteroid : levelAsteroids) {
            for(int i = 0; i < levelAsteroid.getNumber(); i++) {
                asteroids.add(getAsteroidType(levelAsteroid.getAsteroidId()));
            }
        }
    }

    /**
     * Takes an id and will make the correct type of asteroid
     * @param asteroidId the id from the the db that coresponds to the type [1,2,3]
     * @return a new asteroid
     */
    private Asteroid getAsteroidType(int asteroidId) {
        switch (asteroidId){
            case 1: return new AsteroidRegular(
                    GameHolder.getAsteroidsGame().getAsteroids().get(0).getGameImage(),
            )
    }



    /**
     * Will cause all the things in the level to get drawn
     */
    @Override
    public void draw() {
        super.draw();

        for (Asteroid asteroid : asteroids){
            asteroid.draw();
        }

        for (Projectile projectile : projectiles){
            projectile.draw();
        }

        minimap.draw();
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

    public void setLevelObjects(ArrayList<LevelObject> levelObjects) {
        this.levelObjects = levelObjects;
    }

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(ArrayList<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

}

    public List<Asteroid> getAsteroidsToAdd() {
        return asteroidsToAdd;
    }

    public void setAsteroidsToAdd(List<Asteroid> asteroidsToAdd) {
        this.asteroidsToAdd = asteroidsToAdd;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(List<Projectile> projectiles) {
        this.projectiles = projectiles;
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
