package edu.byu.cs.superasteroids.model;

import android.content.ContentValues;

import edu.byu.cs.superasteroids.database.Contract;

/**
 * Created by audakel on 5/16/16.
 */
public class Level {
    private int number;
    private String title;
    private String hint;
    private int width;
    private int height;
    private String music;

    private LevelObject[] levelObjects;
    private LevelAsteroid[] levelAsteroids;

    private  ContentValues values;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public LevelObject[] getLevelObjects() {
        return levelObjects;
    }

    public void setLevelObjects(LevelObject[] levelObjects) {
        this.levelObjects = levelObjects;
    }

    public LevelAsteroid[] getLevelAsteroids() {
        return levelAsteroids;
    }

    public void setLevelAsteroids(LevelAsteroid[] levelAsteroids) {
        this.levelAsteroids = levelAsteroids;
    }

    public class LevelObject {
        private String position;
        private int objectId;
        private float scale;

        private  ContentValues values;

        public ContentValues getContentValues(){
            values = new ContentValues();

            values.put(Contract.LEVEL_OBJECT_POSITION, getPosition());
            values.put(Contract.LEVEL_OBJECT_OBJECT_ID, getObjectId());
            values.put(Contract.LEVEL_OBJECT_SCALE, getScale());

            return values;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getObjectId() {
            return objectId;
        }

        public void setObjectId(int objectId) {
            this.objectId = objectId;
        }

        public float getScale() {
            return scale;
        }

        public void setScale(float scale) {
            this.scale = scale;
        }
    }

    public class LevelAsteroid{
        private int number;
        private int asteroidId;

        private  ContentValues values;

        public ContentValues getContentValues(){
            values = new ContentValues();

            values.put(Contract.LEVEL_ASTEROID_NUMBER, getNumber());
            values.put(Contract.LEVEL_ASTEROID_ID, getAsteroidId());

            return values;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getAsteroidId() {
            return asteroidId;
        }

        public void setAsteroidId(int asteroidId) {
            this.asteroidId = asteroidId;
        }
    }
}
