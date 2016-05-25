package edu.byu.cs.superasteroids.base;

import android.content.Context;

import edu.byu.cs.superasteroids.game.GameHolder;
import edu.byu.cs.superasteroids.helper.content.ContentManager;
import edu.byu.cs.superasteroids.importer.GameDataExtractor;
import edu.byu.cs.superasteroids.interfaces.IGameDelegate;
import edu.byu.cs.superasteroids.model.AsteroidsGame;
import edu.byu.cs.superasteroids.model.GameImage;
import edu.byu.cs.superasteroids.model.Level;
import edu.byu.cs.superasteroids.model.Ship;
import edu.byu.cs.superasteroids.model.Space;
import edu.byu.cs.superasteroids.model.ViewPort;

/**
 * Created by audakel on 5/16/16.
 */

/**
 * Controls the game
 */

public class GameDelegate implements IGameDelegate {
    private ViewPort viewPort;
    private Level level;
    private Ship ship;
    private Space space;
    private AsteroidsGame asteroidsGame;
    private Integer levelImageId;
    private Integer backgroundObjectId;
    private int levelNumber = 1;
    private GameImage levelImage;

    /**
     * Init the game delegate from the gameHolder, also load the AsteroidGame with info from the db
     */
    public GameDelegate(Context context){
        GameDataExtractor gde = new GameDataExtractor(context);
        GameHolder.setAsteroidsGame(gde.getAsteroidsGameFromDb());

        this.asteroidsGame = GameHolder.getAsteroidsGame();
        this.ship = GameHolder.getAsteroidsGame().getShip();
        this.viewPort = GameHolder.getViewPort();
        this.space = GameHolder.getViewPort().getSpace();

    }

    /**
     * Loads all the needed content on a per level basis. All visiable objects contain a lazy add to get their
     * image to the ContentManager, ie they will not be loaded untill they are called
     *
     * @param content An instance of the content manager. This should be used to load images and sound
     */
    @Override
    public void loadContent(ContentManager content) {
        level = GameHolder.getAsteroidsGame().getLevels().get(levelNumber);
        level.getGameImage().setFilePath(asteroidsGame.getObjectImages().get(levelNumber).getImage());
        levelNumber++;
        level.initAsteroids();
    }



    @Override
    public void unloadContent(ContentManager content) {
        // TODO:: unload the non-ship info that will change from level to level
    }


    @Override
    public void draw() {
        viewPort.draw();
        level.draw();
        ship.draw();
    }


    @Override
    public void update(double elapsedTime) {
        viewPort.update(elapsedTime);
        level.update(elapsedTime);
        ship.update(elapsedTime);
    }

}