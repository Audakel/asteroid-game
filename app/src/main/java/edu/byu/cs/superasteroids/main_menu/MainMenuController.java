package edu.byu.cs.superasteroids.main_menu;

import android.content.Context;

import java.util.concurrent.ConcurrentHashMap;

import edu.byu.cs.superasteroids.game.GameHolder;
import edu.byu.cs.superasteroids.helper.content.ContentManager;
import edu.byu.cs.superasteroids.importer.GameDataExtractor;
import edu.byu.cs.superasteroids.interfaces.IView;
import edu.byu.cs.superasteroids.interfaces.IMainMenuController;
import edu.byu.cs.superasteroids.model.AsteroidsGame;
import edu.byu.cs.superasteroids.model.Ship;

import static java.lang.Math.*;
import static java.lang.Math.round;

/**
 * Created by audakel on 5/14/16.
 */
public class MainMenuController implements IMainMenuController {
    MainActivity mainActivity;
    private Context context;

    public MainMenuController(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onQuickPlayPressed(Context context) {
        this.context = context;
        GameHolder.setAsteroidsGame(new GameDataExtractor(context).getAsteroidsGameFromDb());
        makeRandomShip();

        mainActivity.startGame();
    }

    /**
     * Creates a randomly generated ship. It will use math.random and math.round to get either a 1 or 0
     * to use as the ship index of the part to get
     */
    private void makeRandomShip() {
        Ship ship = GameHolder.getAsteroidsGame().getShip();
        AsteroidsGame gameParts = GameHolder.getAsteroidsGame();

        ship.setMainBody(gameParts.getMainBodies().get( (int) round(random())) );
        ship.setEngine(gameParts.getEngines().get( (int) round(random())) );
        ship.setCannon(gameParts.getCannons().get( (int) round(random())) );
        ship.setExtraPart(gameParts.getExtraParts().get( (int) round(random())) );
        ship.setPowerCore(gameParts.getPowerCores().get( (int) round(random())) );

    }


    @Override
    public IView getView() {
        return null;
    }

    @Override
    public void setView(IView view) {

    }
}
