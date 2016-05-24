package edu.byu.cs.superasteroids.model.ShipParts;

import android.content.ContentValues;
import android.graphics.PointF;

import org.json.JSONObject;

import edu.byu.cs.superasteroids.model.GameImage;

import static edu.byu.cs.superasteroids.Constants.SCALE_FACTOR;

/**
 * Created by audakel on 5/16/16.
 */
public class ExtraPart extends ShipPart{
    /**
     * @param image       gameImage for shipPart
     * @param speed       defaults to 0, how fast item is moving
     * @param rotation    defaults to 0, rotation of item
     * @param position    defaults to 0,0 where item is
     * @param attachPoint the attach point for all parts that are not mainBody objects
     * @param scale       scale of item to draw to - taken from game constants
     */
    public ExtraPart(GameImage image, int speed, float rotation, PointF position, PointF attachPoint, float scale) {
        super(image, speed, rotation, position, attachPoint, scale);
    }

    /**
     * Helper json constructor
     */
    public ExtraPart(JSONObject jsonObject)  {
        super(jsonObject, null);
        setAttachPoint(extractCoordinatesPointFromJson(jsonObject, "attachPoint"));

    }


    /**
     * Helper empty constructor
     */
    public ExtraPart()  {
        super(new GameImage(0,0,""), 0, 0, null , null, SCALE_FACTOR);
    }

    @Override
    public ContentValues getContentValues(){
        initContentValues();
        return contentValues;
    }
}
