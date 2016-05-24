package edu.byu.cs.superasteroids.model.ShipParts;

import android.content.ContentValues;
import android.graphics.PointF;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.helper.DrawingHelper;
import edu.byu.cs.superasteroids.helper.GraphicsUtils;
import edu.byu.cs.superasteroids.model.GameImage;
import edu.byu.cs.superasteroids.model.MovableObject;

import static edu.byu.cs.superasteroids.Constants.SCALE_FACTOR;
import static edu.byu.cs.superasteroids.Constants.STARTING_SHIP_OPACITY;

/**
 * Created by audakel on 5/23/16.
 */
public class ShipPart extends MovableObject {
    protected ContentValues contentValues;
    protected PointF attachPoint;
    protected float scale;
    protected int imageIndex;

    /**
     *
     * @param image gameImage for shipPart
     * @param speed defaults to 0, how fast item is moving
     * @param rotation defaults to 0, rotation of item
     * @param position defaults to 0,0 where item is
     * @param attachPoint the attach point for all parts that are not mainBody objects
     * @param scale scale of item to draw to - taken from game constants
     */
    public ShipPart(GameImage image, int speed, double rotation, PointF position, PointF attachPoint, float scale) {
        super(image, speed, rotation, position);
        this.attachPoint = attachPoint;
        this.scale = scale;
    }

    public void drawAttachedToMainBody(int imageIndex, float rotationDegrees, MainBody mainBody){
        super.draw();
        if(mainBody == null) return;

        DrawingHelper.drawImage(
                imageIndex,
                partCenterPoint(imageIndex, mainBody).x,
                partCenterPoint(imageIndex, mainBody).y,
                rotationDegrees,
                SCALE_FACTOR,
                SCALE_FACTOR,
                STARTING_SHIP_OPACITY
        );
    }

    /**
     *
     * @param mainBody takes a mainBody and will reference itself to get the part object
     * @return the point at which
     */
    private PointF partCenterPoint(MainBody mainBody) {

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
                GraphicsUtils.degreesToRadians(rotationDegrees)
        );
        centerPoint = GraphicsUtils.add(centerPoint, getViewPosition());

        return centerPoint;
    }

    /**
     * This will calculate the the attach point on the main body for the given part.
     * @param part the part that the attach point is needed.
     * @return the attach point for the given part.
     */
    private PointF getMainBodyAttachPoint(MainBody mainBody) {
        switch(this.getClass().getSimpleName()) {
            case "Cannon":		return mainBody.getCannonAttach();
            case "Engine": 	    return mainBody.getEngineAttach();
            case "ExtraPart":	return mainBody.getExtraAttach();
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
        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }
}
