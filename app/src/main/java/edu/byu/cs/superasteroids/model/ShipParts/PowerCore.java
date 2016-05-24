package edu.byu.cs.superasteroids.model.ShipParts;

import android.content.ContentValues;
import android.graphics.PointF;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.model.GameImage;

/**
 * Created by audakel on 5/16/16.
 */
public class PowerCore extends ShipPart{
    /**
     * Special power for ships cannon - how much of an increase
     */
    private int cannonBoost;
    /**
     * Special power for ships engine - how much of an increase
     */
    private int engineBoost;


    /**
     * @param image       gameImage for shipPart
     * @param speed       defaults to 0, how fast item is moving
     * @param rotation    defaults to 0, rotation of item
     * @param position    defaults to 0,0 where item is
     * @param attachPoint the attach point for all parts that are not mainBody objects
     * @param scale       scale of item to draw to - taken from game constants
     */
    public PowerCore(GameImage image, int speed, double rotation, PointF position, PointF attachPoint, float scale) {
        super(image, speed, rotation, position, attachPoint, scale);
    }

    @Override
    public ContentValues getContentValues(){
        contentValues = new ContentValues();
        contentValues.put(Contract.POWER_CORE_CANNON_BOOST, getCannonBoost());
        contentValues.put(Contract.POWER_CORE_ENGINE_BOOST, getEngineBoost());
        contentValues.put(Contract.POWER_CORE_IMAGE, getGameImage().getFilePath());

        return contentValues;
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
}
