package edu.byu.cs.superasteroids.model;

import android.content.ContentValues;

import java.util.HashMap;

import edu.byu.cs.superasteroids.database.Contract;

/**
 * Created by audakel on 5/21/16.
 */
public class LevelAsteroid{
    private int number;
    private int asteroidId;
    private ContentValues values;

    public LevelAsteroid(){}
    public LevelAsteroid(HashMap<String, String> map){
        this.number = Integer.valueOf(map.get(Contract.LEVEL_ASTEROID_NUMBER));
        this.asteroidId = Integer.valueOf(map.get(Contract.LEVEL_ASTEROID_ASTEROID_ID));
    }

    public ContentValues getContentValues(){
        values = new ContentValues();

        values.put(Contract.LEVEL_ASTEROID_NUMBER, getNumber());
        values.put(Contract.LEVEL_ASTEROID_ASTEROID_ID, getAsteroidId());

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