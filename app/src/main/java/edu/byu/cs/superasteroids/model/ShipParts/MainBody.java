package edu.byu.cs.superasteroids.model.ShipParts;

import android.content.ContentValues;
import android.graphics.PointF;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.model.GameImage;

/**
 * Created by audakel on 5/16/16.
 */
public class MainBody extends ShipPart {
    /**
     * Coordinates for atatching cannon picture to main picture
     */
    private PointF cannonAttach;
    /**
     * Coordinates for atatching engine picture to main picture
     */
    private PointF engineAttach;
    /**
     * Coordinates for atatching extra picture to main picture
     */
    private PointF extraAttach;


    /**
     * @param image       gameImage for shipPart
     * @param speed       defaults to 0, how fast item is moving
     * @param rotation    defaults to 0, rotation of item
     * @param position    defaults to 0,0 where item is
     * @param attachPoint the attach point for all parts that are not mainBody objects
     * @param scale       scale of item to draw to - taken from game constants
     */
    public MainBody(GameImage image, int speed, double rotation, PointF position, PointF attachPoint, float scale) {
        super(image, speed, rotation, position, attachPoint, scale);
    }

    @Override
    public ContentValues getContentValues(){
        initContentValues();
        contentValues.put(Contract.MAIN_BODY_CANNON_ATTATCH, getCannonAttach().x + "," + getCannonAttach().y);
        contentValues.put(Contract.MAIN_BODY_ENGINE_ATTATCH, getEngineAttach().x + "," + getEngineAttach().y);
        contentValues.put(Contract.MAIN_BODY_EXTRA_ATTATCH, getExtraAttach().x + "," + getExtraAttach().y);

        return contentValues;
    }

    public PointF getCannonAttach() {
        return cannonAttach;
    }

    public void setCannonAttach(PointF cannonAttach) {
        this.cannonAttach = cannonAttach;
    }

    public PointF getEngineAttach() {
        return engineAttach;
    }

    public void setEngineAttach(PointF engineAttach) {
        this.engineAttach = engineAttach;
    }

    public PointF getExtraAttach() {
        return extraAttach;
    }

    public void setExtraAttach(PointF extraAttach) {
        this.extraAttach = extraAttach;
    }
}
