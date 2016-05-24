package edu.byu.cs.superasteroids.model.ShipParts;

import android.content.ContentValues;
import android.graphics.PointF;

import org.json.JSONException;
import org.json.JSONObject;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.helper.DrawingHelper;
import edu.byu.cs.superasteroids.helper.GraphicsUtils;
import edu.byu.cs.superasteroids.helper.content.ContentManager;
import edu.byu.cs.superasteroids.model.GameImage;
import edu.byu.cs.superasteroids.model.MovableObject;

import static edu.byu.cs.superasteroids.Constants.*;
import static edu.byu.cs.superasteroids.helper.DrawingHelper.getGameViewHeight;
import static edu.byu.cs.superasteroids.helper.DrawingHelper.getGameViewWidth;

/**
 * Created by audakel on 5/23/16.
 */
public class ShipPart extends MovableObject {
    protected ContentValues contentValues;
    private PointF attachPoint;
    private float scale;
    private Integer imageIndex;

    /**
     *
     * @param image gameImage for shipPart
     * @param speed defaults to 0, how fast item is moving
     * @param rotation defaults to 0, rotation of item
     * @param position defaults to 0,0 where item is
     * @param attachPoint the attach point for all parts that are not mainBody objects
     * @param scale scale of item to draw to - taken from game constants
     */
    public ShipPart(GameImage image, int speed, float rotation, PointF position, PointF attachPoint, float scale) {
        super(image, speed, rotation, position);
        this.attachPoint = attachPoint;
        this.scale = scale;
    }

    /**
     * Construct an object from a json object
     * @param jo
     * @throws JSONException
     */



//        (new GameImage(jo.getInt("imageWidth"), jo.getInt("imageHeight"), jo.getString("image")),
//                0, 0, new PointF(getGameViewWidth() / 2, getGameViewHeight() / 2), null, SCALE_FACTOR);




    public PointF extractPointFromString(String string){
         return new PointF(Float.valueOf(string.split(",")[0]),
                Float.valueOf(string.split(",")[1]));
    }

    public void drawAttachedToMainBody(int imageIndex, MainBody mainBody){
        super.draw();
        if(mainBody == null) return;

        DrawingHelper.drawImage(
                imageIndex,
                partCenteredOffMainBody(mainBody).x,
                partCenteredOffMainBody(mainBody).y,
                getRotation(),
                SCALE_FACTOR,
                SCALE_FACTOR,
                STARTING_SHIP_OPACITY
        );
    }

    /**
     *
     * @param mainBody takes a mainBody and will reference itself to get the part object
     * @return the point at which the part should be drawn to attach to the ship
     */
    private PointF partCenteredOffMainBody(MainBody mainBody) {

        PointF mainBodyAttachPoint = getMainBodyAttachPoint(mainBody);

        PointF mainBodyCenterPoint = new PointF(
                mainBody.getGameImage().getWidth() / 2,
                mainBody.getGameImage().getHeight() / 2
        );

        PointF partCenterPoint = new PointF(
                this.getGameImage().getWidth() / 2,
                this.getGameImage().getHeight() / 2
        );

        PointF centerPoint = new PointF(
                getViewPosition().x + SCALE_FACTOR * ((mainBodyAttachPoint.x - mainBodyCenterPoint.x)
                                        + (partCenterPoint.x - this.getAttachPoint().x)),
                getViewPosition().y + SCALE_FACTOR * ((mainBodyAttachPoint.y - mainBodyCenterPoint.y)
                                        + (partCenterPoint.y - this.getAttachPoint().y))
        );

        centerPoint = GraphicsUtils.rotate(
                GraphicsUtils.subtract(centerPoint, getViewPosition()),
                GraphicsUtils.degreesToRadians(getRotation())
        );

        return GraphicsUtils.add(centerPoint, getViewPosition());
    }

    /**
     * This will calculate the the attach point on the main body for the given part. it references
     * this.getClass() to find out what it is
     *
     * @param mainBody to reference all the different attach points
     * @return the attach point for the given part.
     */
    private PointF getMainBodyAttachPoint(MainBody mainBody) {
        switch(this.getClass().getSimpleName()) {
            case CANNON:		return mainBody.getCannonAttach();
            case ENGINE: 	    return mainBody.getEngineAttach();
            case EXTRA_PART:	return mainBody.getExtraAttach();
            default:            return null;
        }
    }



    protected void initContentValues(){
        contentValues = new ContentValues();
        contentValues.put(Contract.EXTRA_PART_ATTATCH_POINT, getAttachPoint().x + "," + getAttachPoint().y);
        contentValues.put(Contract.EXTRA_PART_IMAGE, getGameImage().getFilePath());
        contentValues.put(Contract.EXTRA_PART_IMAGE_WIDTH, getGameImage().getWidth());
        contentValues.put(Contract.EXTRA_PART_IMAGE_HEIGHT, getGameImage().getHeight());
    }


    public ContentValues getContentValues(){
        return contentValues;
    }

    public PointF getAttachPoint() {
        return attachPoint;
    }

    public void setAttachPoint(PointF attachPoint) {
        this.attachPoint = attachPoint;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getImageIndex() {
        if (imageIndex != null) return imageIndex;

        setImageIndex(ContentManager.getInstance().getImageId(getGameImage().getFilePath()));

        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }
}
