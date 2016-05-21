package edu.byu.cs.superasteroids.model;


import android.content.ContentValues;

import edu.byu.cs.superasteroids.database.Contract;

/**
 * Created by audakel on 5/16/16.
 */
public class Cannon {
    private String attachPoint;
    private String emitPoint;
    private String image;
    private int imageWidth;
    private int imageHeight;
    private int attackImageHeight;
    private int attackImageWidth;
    private String attackSound;
    private String damage;
    private ContentValues values;

    public ContentValues getContentValues(){
        values = new ContentValues();

        values.put(Contract.CANNON_ATTATCH_POINT, getAttachPoint());
        values.put(Contract.CANNON_EMIT_POINT, getEmitPoint());
        values.put(Contract.CANNON_IMAGE, getImage());
        values.put(Contract.CANNON_IMAGE_WIDTH, getImageWidth());
        values.put(Contract.CANNON_IMAGE_HEIGHT, getImageHeight());
        values.put(Contract.CANNON_ATTACK_IMAGE_HEIGHT, getAttackImageHeight());
        values.put(Contract.CANNON_ATTACK_IMAGE_WIDTH, getAttackImageWidth());
        values.put(Contract.CANNON_ATTACK_SOUND, getAttackSound());
        values.put(Contract.CANNON_DAMAGE, getDamage());

        return values;
    }

    public String getAttachPoint() {
        return attachPoint;
    }

    public void setAttachPoint(String attachPoint) {
        this.attachPoint = attachPoint;
    }

    public String getEmitPoint() {
        return emitPoint;
    }

    public void setEmitPoint(String emitPoint) {
        this.emitPoint = emitPoint;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getAttackImageHeight() {
        return attackImageHeight;
    }

    public void setAttackImageHeight(int attackImageHeight) {
        this.attackImageHeight = attackImageHeight;
    }

    public int getAttackImageWidth() {
        return attackImageWidth;
    }

    public void setAttackImageWidth(int attackImageWidth) {
        this.attackImageWidth = attackImageWidth;
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
