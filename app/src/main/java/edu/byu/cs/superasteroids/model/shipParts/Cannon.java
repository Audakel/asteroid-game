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
public class Cannon extends ShipPart{
    private PointF emitPoint;
    private GameImage attackImage;
    private String attackSound;
    private int damage;

    /**
     *
     * @param image picture of cannon
     * @param speed how fast is it going, should match body
     * @param rotation rotation of cannon, should match body
     * @param position of cannon, should match body
     * @param attachPoint of cannon, should connect with mainbody
     * @param scale from constants
     * @param emitPoint where is the cannon going to fire
     * @param attackSound sound to play on firing of gun
     * @param damage how much damage does the cannon do
     */
    public Cannon(GameImage image, int speed, float rotation, PointF position, PointF attachPoint, float scale,
                  PointF emitPoint, GameImage attackImage, String attackSound, int damage) {

        super(image, speed, rotation, position, attachPoint, scale);
        this.emitPoint = emitPoint;
        this.attackImage = attackImage;
        this.attackSound = attackSound;
        this.damage = damage;
    }

    /**
     * Helper json constructor
     */
    public Cannon(JSONObject jsonObject)  {
        super(jsonObject, null);
        try {
            this.emitPoint = extractPointFromString(jsonObject.getString("emitPoint"));
            this.attackSound = (jsonObject.getString("attackSound"));
            this.attackImage = getAttackImageFromJson(jsonObject);
            this.damage = (jsonObject.getInt("damage"));
            setAttachPoint(extractCoordinatesPointFromJson(jsonObject, "attachPoint"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper empty constructor
     */
    public Cannon()  {
        super(new GameImage(0,0,""), 0, 0, null , null, SCALE_FACTOR);
    }


    /**
     * Helper to easlily get attackimage from json db string.
     * @param jo json to extract from.
     * @return new image
     */
    private GameImage getAttackImageFromJson(JSONObject jo){
        int width = 0;
        int height = 0;
        String file = null;

        try {
            width = jo.getInt("attackImageWidth");
            height = jo.getInt("attackImageHeight");
            file = jo.getString("attackImage");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new GameImage(width,height,file);
    }


    @Override
    public void draw(){

    }

    @Override
    public ContentValues getContentValues(){
        initContentValues();
        contentValues.put(Contract.CANNON_EMIT_POINT, getEmitPoint().x + "," + getEmitPoint().y);
        contentValues.put(Contract.CANNON_ATTACK_IMAGE_HEIGHT, getAttackImage().getHeight());
        contentValues.put(Contract.CANNON_ATTACK_IMAGE_WIDTH, getAttackImage().getFilePath());
        contentValues.put(Contract.CANNON_ATTACK_SOUND, getAttackSound());
        contentValues.put(Contract.CANNON_DAMAGE, getDamage());
        return contentValues;
    }

    public PointF getEmitPoint() {
        return emitPoint;
    }

    public void setEmitPoint(PointF emitPoint) {
        this.emitPoint = emitPoint;
    }

    public GameImage getAttackImage() {
        return attackImage;
    }

    public void setAttackImage(GameImage attackImage) {
        this.attackImage = attackImage;
    }

    public String getAttackSound() {
        return attackSound;
    }

    public void setAttackSound(String attackSound) {
        this.attackSound = attackSound;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
