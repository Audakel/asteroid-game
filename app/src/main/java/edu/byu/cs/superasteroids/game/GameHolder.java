package edu.byu.cs.superasteroids.game;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.database.DatabaseHelper;
import edu.byu.cs.superasteroids.helper.DrawingHelper;
import edu.byu.cs.superasteroids.model.AsteroidsGame;
import edu.byu.cs.superasteroids.model.GameImage;
import edu.byu.cs.superasteroids.model.ViewPort;

import static edu.byu.cs.superasteroids.helper.DrawingHelper.getGameViewHeight;
import static edu.byu.cs.superasteroids.helper.DrawingHelper.getGameViewWidth;

/**
 * Created by audakel on 5/23/16.
 */

/**
 * Holds game level info for all classes to access
 */
public class GameHolder {
    /**
     * Singleton instance of Asteroide game loaded from db
     */
    private static AsteroidsGame asteroidsGame;
    /**
     * Singleton instance of Viewport
     */
    private static ViewPort viewPort;


    public static void init(){
        asteroidsGame = new AsteroidsGame();
        viewPort = new ViewPort(
                new GameImage(getGameViewWidth(), getGameViewHeight(), ""), 0,0,
                new PointF(getGameViewWidth() / 2, getGameViewHeight() / 2));
    }


    public static AsteroidsGame getAsteroidsGame() {
        return asteroidsGame;
    }

    public static void setAsteroidsGame(AsteroidsGame asteroidsGame) {
        GameHolder.asteroidsGame = asteroidsGame;
    }

    public static ViewPort getViewPort() {
        return viewPort;
    }

    public static void setViewPort(ViewPort viewPort) {
        GameHolder.viewPort = viewPort;
    }
}
