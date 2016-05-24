package edu.byu.cs.superasteroids.model.ShipParts;


import android.content.ContentValues;
import android.graphics.PointF;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.model.GameImage;

/**
 * Created by audakel on 5/16/16.
 */
public class Cannon extends ShipPart{
    private PointF emitPoint;
    private GameImage attackImage;
    private String attackSound;
    private String damage;

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
                  PointF emitPoint, GameImage attackImage, String attackSound, String damage) {

        super(image, speed, rotation, position, attachPoint, scale);
        this.emitPoint = emitPoint;
        this.attackImage = attackImage;
        this.attackSound = attackSound;
        this.damage = damage;
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

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }
}
