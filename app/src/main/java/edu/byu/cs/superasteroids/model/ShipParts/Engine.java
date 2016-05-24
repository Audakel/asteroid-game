package edu.byu.cs.superasteroids.model.ShipParts;

import android.content.ContentValues;
import android.graphics.PointF;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.model.GameImage;

/**
 * Created by audakel on 5/16/16.
 */
public class Engine extends ShipPart {
    /**
     * @param baseSpeed how fast the engine moves
     */
    private int baseSpeed;
    /**
     * @param baseTurnRate how fast the engine turns
     */
    private int baseTurnRate;


    /**
     * @param image       gameImage for shipPart
     * @param speed       defaults to 0, how fast item is moving
     * @param rotation    defaults to 0, rotation of item
     * @param position    defaults to 0,0 where item is
     * @param attachPoint the attach point for all parts that are not mainBody objects
     * @param scale       scale of item to draw to - taken from game constants
     */
    public Engine(GameImage image, int speed, double rotation, PointF position, PointF attachPoint, float scale,
                  int baseSpeed, int baseTurnRate) {
        super(image, speed, rotation, position, attachPoint, scale);
        this.baseSpeed = baseSpeed;
        this.baseTurnRate = baseTurnRate;
    }

    @Override
    public ContentValues getContentValues(){
        initContentValues();
        contentValues.put(Contract.ENGINE_BASE_SPEED, getBaseSpeed());
        contentValues.put(Contract.ENGINE_BASE_TURN_RATE, getBaseTurnRate());
        return contentValues;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public int getBaseTurnRate() {
        return baseTurnRate;
    }

    public void setBaseTurnRate(int baseTurnRate) {
        this.baseTurnRate = baseTurnRate;
    }
}
