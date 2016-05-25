package edu.byu.cs.superasteroids.model.shipParts;

import android.content.ContentValues;
import android.graphics.PointF;

import org.json.JSONException;
import org.json.JSONObject;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.model.GameImage;

import static edu.byu.cs.superasteroids.Constants.SCALE_FACTOR;

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
    public PowerCore(GameImage image, int speed, float rotation, PointF position, PointF attachPoint, float scale) {
        super(image, speed, rotation, position, attachPoint, scale);
    }

    /**
     * Helper json constructor
     */
    public PowerCore(JSONObject jsonObject)  {
        super(jsonObject, null);
        try {
            this.cannonBoost = (jsonObject.getInt("cannonBoost"));
            this.engineBoost = (jsonObject.getInt("engineBoost"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        setAttachPoint(extractCoordinatesPointFromJson(jsonObject, "attachPoint"));

    }


    /**
     * Helper empty constructor
     */
    public PowerCore()  {
        super(new GameImage(0,0,""), 0, 0, null , null, SCALE_FACTOR);
    }

    @Override
    public ContentValues getContentValues(){
        contentValues = new ContentValues();
        contentValues.put(Contract.POWER_CORE_CANNON_BOOST, getCannonBoost());
        contentValues.put(Contract.POWER_CORE_ENGINE_BOOST, getEngineBoost());

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
