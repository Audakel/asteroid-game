package edu.byu.cs.superasteroids.game;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

import edu.byu.cs.superasteroids.database.DatabaseHelper;
import edu.byu.cs.superasteroids.helper.DrawingHelper;
import edu.byu.cs.superasteroids.importer.GameDataExtractor;
import edu.byu.cs.superasteroids.importer.GameDataImporter;
import edu.byu.cs.superasteroids.main_menu.MainActivity;
import edu.byu.cs.superasteroids.model.AsteroidsGame;
import edu.byu.cs.superasteroids.model.GameImage;
import edu.byu.cs.superasteroids.model.Space;
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

    private static Context context;

    public static void init(MainActivity baseContext){
        context = baseContext;
        asteroidsGame = new AsteroidsGame();
//        asteroidsGame = new GameDataExtractor(baseContext).getAsteroidsGameFromDb();
        viewPort = new ViewPort(
                new GameImage(getGameViewWidth(), getGameViewHeight(), ""),
                new PointF(getGameViewWidth() / 2, getGameViewHeight() / 2));

        Log.d("GameHolder", "init: ");
    }

    public static void startGame(){
        MainActivity mainActivity = new MainActivity();
        mainActivity.startGame();

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
