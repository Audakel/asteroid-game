package edu.byu.cs.superasteroids.model;

import android.content.ContentValues;

import edu.byu.cs.superasteroids.database.Contract;

/**
 * Created by audakel on 5/16/16.
 */
public class PowerCore {
    private int cannonBoost;
    private int engineBoost;
    private String image;

    private ContentValues values;

    public ContentValues getContentValues(){
        values = new ContentValues();

        values.put(Contract.POWER_CORE_CANNON_BOOST, getCannonBoost());
        values.put(Contract.POWER_CORE_ENGINE_BOOST, getEngineBoost());
        values.put(Contract.POWER_CORE_IMAGE, getImage());

        return values;
    }

    public int getCannonBoost() {
        return cannonBoost;
    }

    public void setCannonBoost(int cannonBoost) {
        this.cannonBoost = cannonBoost;
    }

    public int getEngineBoost() {
        return engineBoost;
    }

    public void setEngineBoost(int engineBoost) {
        this.engineBoost = engineBoost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
