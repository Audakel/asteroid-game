package edu.byu.cs.superasteroids.model;

import android.content.Context;
import android.graphics.PointF;

import edu.byu.cs.superasteroids.Constants;
import edu.byu.cs.superasteroids.game.GameHolder;
import edu.byu.cs.superasteroids.game.InputManager;
import edu.byu.cs.superasteroids.helper.GraphicsUtils;
import edu.byu.cs.superasteroids.model.shipParts.Cannon;
import edu.byu.cs.superasteroids.model.shipParts.Engine;
import edu.byu.cs.superasteroids.model.shipParts.ExtraPart;
import edu.byu.cs.superasteroids.model.shipParts.MainBody;
import edu.byu.cs.superasteroids.model.shipParts.PowerCore;

import static edu.byu.cs.superasteroids.Constants.STARTING_SHIP_LIVES;
import static edu.byu.cs.superasteroids.Constants.OPACITY_FULL;
import static edu.byu.cs.superasteroids.Constants.STARTING_SHIP_ROTATION;
import static edu.byu.cs.superasteroids.Constants.STARTING_SHIP_SAFE;
import static edu.byu.cs.superasteroids.Constants.STARTING_SHIP_SAFE_TIME;
import static edu.byu.cs.superasteroids.helper.DrawingHelper.getGameViewHeight;
import static edu.byu.cs.superasteroids.helper.DrawingHelper.getGameViewWidth;
import static edu.byu.cs.superasteroids.helper.content.ContentManager.*;

/**
 * Created by audakel on 5/23/16.
 */

/**
 * Holds all the ship peices for easy drawing etc..
 */
public class Ship extends MovableObject {
    private  Level level;
    /**
     * Mainbody of ship
     */
    private MainBody mainBody;
    /**
     * Cannon of ship
     */
    private Cannon cannon;
    /**
     * Extra part of ship
     */
    private ExtraPart extraPart;
    /**
     * Engine of ship
     */
    private Engine engine;
    /**
     * Power core of ship
     */
    private PowerCore powerCore;
    /**
     * Degree of rotaion for ship
     */
    private float rotationDegrees;
    /**
     * Number of lives for ship, decreases when hit
     */
    private int lives;
    /**
     * True means ship will not be hurt if hit
     */
    private boolean safe;
    /**
     * True if ship has no more lives, starts out false
     */
    private boolean dead = false;
    /**
     * Amount of time ship is safe after being hit
     */
    private double safeTime;
    /**
     * Opacity value of ship
     */
    private int shipOpacity = OPACITY_FULL;
    /**
     * Context of activity calling ship
     */
    private Context context;


    public Ship() {
        super(new GameImage(0,0,""), 1, 1, new PointF(getGameViewWidth()/2, getGameViewHeight()/2));
//        this.level = level;
        rotationDegrees = STARTING_SHIP_ROTATION;
        lives = STARTING_SHIP_LIVES;
        safe = STARTING_SHIP_SAFE;
        safeTime = STARTING_SHIP_SAFE_TIME;

        mainBody = new MainBody();
        engine = new Engine();
        cannon = new Cannon();
        extraPart = new ExtraPart();
        powerCore = new PowerCore();


    }

    /**
     * If the ship is hit by an asteroid it will update lives and the opacity if it dies
     * @param time the update time
     */
    public void hit(double time) {
        safeTime = time;
        if(--lives > 0) return;

        setDead(true);
        setShipOpacity(100);
    }

    @Override
    public void update(double time) {
        generateRectangle(getPosition(), getGameImage());

        if(InputManager.movePoint != null) {
            updateRotation();
        }
        updatePosition(time);
        calcSpeed();

        updateSafetyTime(time);

        if(InputManager.firePressed) {
            shoot();
        }
    }

    private void updateRotation() {
        PointF difference = new PointF(
                InputManager.movePoint.x - getViewPosition().x,
                InputManager.movePoint.y - getViewPosition().y
        );

        double rotation = 90;

        if(difference.x != 0 && difference.y != 0) {
            rotation = Math.atan2(difference.y, difference.x);
        }
        else if(difference.x != 0) {
            rotation = ((difference.x > 0) ? 0 : Math.PI);
        }
        else if(difference.y != 0) {
            rotation = ((difference.y > 0) ? GraphicsUtils.HALF_PI : GraphicsUtils.THREE_HALF_PI);
        }

        rotationDegrees = (float) GraphicsUtils.radiansToDegrees(rotation) + 90;
    }


    private void updatePosition(double time) {
        GraphicsUtils.MoveObjectResult result = GraphicsUtils.moveObject(getPosition(),
                getRectangle(), getSpeed(), GraphicsUtils.degreesToRadians(rotationDegrees - 90 ),time);

        PointF newPosition = result.getNewObjPosition();
        GameImage image =  getGameDelegate().getLevel().getGameImage();
        if (newPosition.x < 0) newPosition.x = 0;
        if (newPosition.y < 0) newPosition.y = 0;
        if (newPosition.x > image.getWidth()) newPosition.x = image.getWidth();
        if (newPosition.y > image.getHeight()) newPosition.y = image.getHeight();
        setPosition(newPosition);
    }

    private void updateSafetyTime(double time) {
        safe = false;

        if(safe && safeTime < 5) {
            safeTime += time;
            safe = true;
        }
    }

    private void calcSpeed() {
        if(InputManager.movePoint != null && getSpeed() < 500) {
            setSpeed(getSpeed() + 15);
        }
        else if(InputManager.movePoint == null && getSpeed() > 0) {
            setSpeed(getSpeed() - 15);
        }
    }

    private void shoot() {
        if(lives == 0) return;

        Bullet bullet = new Bullet(cannon.getAttackImage(), Constants.BULLET_SPEED,getRotation(), cannon.getEmitPoint());
        bullet.setGameDelegate(getGameDelegate());
        getGameDelegate().getLevel().getBullets().add(bullet);
        getInstance().playSound(cannon.getAttackSoundId(), Constants.VOLUME, 2);

    }

    @Override
    public void draw(){
        mainBody.draw();
        engine.drawAttachedToMainBody(engine.getImageIndex(), mainBody);
        cannon.drawAttachedToMainBody(cannon.getImageIndex(), mainBody);
        extraPart.drawAttachedToMainBody(extraPart.getImageIndex(), mainBody);
    }

    public MainBody getMainBody() {
        return mainBody;
    }

    public void setMainBody(MainBody mainBody) {
        this.mainBody = mainBody;

        // for ship builder only
        if (getMainBody().getPosition().x == 0 && getMainBody().getPosition().y ==0){
            getMainBody().setPosition(new PointF(getGameViewWidth() / 2, getGameViewHeight() / 2));
        }
    }

    public Cannon getCannon() {
        return cannon;
    }

    public void setCannon(Cannon cannon) {
        this.cannon = cannon;
    }

    public ExtraPart getExtraPart() {
        return extraPart;
    }

    public void setExtraPart(ExtraPart extraPart) {
        this.extraPart = extraPart;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public PowerCore getPowerCore() {
        return powerCore;
    }

    public void setPowerCore(PowerCore powerCore) {
        this.powerCore = powerCore;
    }

    public float getRotationDegrees() {
        return rotationDegrees;
    }

    public void setRotationDegrees(float rotationDegrees) {
        this.rotationDegrees = rotationDegrees;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public double getSafeTime() {
        return safeTime;
    }

    public void setSafeTime(double safeTime) {
        this.safeTime = safeTime;
    }

    public int getShipOpacity() {
        return shipOpacity;
    }

    public void setShipOpacity(int shipOpacity) {
        this.shipOpacity = shipOpacity;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
