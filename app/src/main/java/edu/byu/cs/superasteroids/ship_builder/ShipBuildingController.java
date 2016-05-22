package edu.byu.cs.superasteroids.ship_builder;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import edu.byu.cs.superasteroids.helper.content.ContentManager;
import edu.byu.cs.superasteroids.importer.GameDataExtractor;
import edu.byu.cs.superasteroids.importer.GameDataImporter;
import edu.byu.cs.superasteroids.interfaces.IShipBuildingController;
import edu.byu.cs.superasteroids.interfaces.IShipBuildingView;
import edu.byu.cs.superasteroids.interfaces.IView;
import edu.byu.cs.superasteroids.model.Asteroid;
import edu.byu.cs.superasteroids.model.AsteroidsGame;
import edu.byu.cs.superasteroids.model.Cannon;
import edu.byu.cs.superasteroids.model.Engine;
import edu.byu.cs.superasteroids.model.ExtraPart;
import edu.byu.cs.superasteroids.model.Level;
import edu.byu.cs.superasteroids.model.MainBody;
import edu.byu.cs.superasteroids.model.PowerCore;

/**
 * Created by audakel on 5/16/16.
 */
public class ShipBuildingController implements IShipBuildingController{
    private Context context;
    private ShipBuildingActivity shipBuildingActivity;
    private String TAG = getClass().getSimpleName();

    public ShipBuildingController(ShipBuildingActivity shipBuildingActivity) {
        context = shipBuildingActivity.getApplicationContext();
        this.shipBuildingActivity = shipBuildingActivity;
        Log.d(TAG, "ShipBuildingController: shipBuildingActivity- " + shipBuildingActivity);

    }

    public ShipBuildingController(Context context) {
        Log.d(TAG, "ShipBuildingController: context- " + context);
        this.context = context;
    }

    /**
     * The ship building view calls this function when a part selection view is loaded. This function
     * should be used to configure the part selection view. Example: Set the arrows for the view in
     * this function.
     * @param partView
     */
    @Override
    public void onViewLoaded(IShipBuildingView.PartSelectionView partView) {
        Log.d(TAG, "onViewLoaded: partView- " + partView);

    }

    @Override
    public void update(double elapsedTime) {
        // Leave empty?
    }

    /**
     * The ShipBuildingView calls this function as it is created. Load ship building content in this function.
     * @param content An instance of the content manager. This should be used to load images and sound.
     */
    @Override
    public void loadContent(ContentManager content) {
        Log.d(TAG, "loadContent: content- " + content);

        GameDataImporter gameDataImporter;
        GameDataExtractor gameDataExtractor;
        Level levelObject;
        int level;


        level = 1;
        gameDataExtractor = new GameDataExtractor(context);

        //TODO:: fix asteroids game for real
        AsteroidsGame asteroidsGame = gameDataExtractor.getAsteroidsGameFromDb(level);

        ArrayList<Integer> pictureIdList = new ArrayList<>();
        for (MainBody mainBody : asteroidsGame.getMainBodies()) {
            Integer id = content.loadImage(mainBody.getImage());
            pictureIdList.add(id);
        }
        shipBuildingActivity.setPartViewImageList(IShipBuildingView.PartSelectionView.MAIN_BODY, pictureIdList);

        pictureIdList = new ArrayList<>();
        for (Cannon cannon : asteroidsGame.getCannons()) {
            Integer id = content.loadImage(cannon.getImage());
            pictureIdList.add(id);
        }
        shipBuildingActivity.setPartViewImageList(IShipBuildingView.PartSelectionView.CANNON, pictureIdList);

        pictureIdList = new ArrayList<>();
        for (Engine engine: asteroidsGame.getEngines()) {
            Integer id = content.loadImage(engine.getImage());
            pictureIdList.add(id);
        }
        shipBuildingActivity.setPartViewImageList(IShipBuildingView.PartSelectionView.ENGINE, pictureIdList);

        pictureIdList = new ArrayList<>();
        for (ExtraPart extraPart : asteroidsGame.getExtraParts()) {
            Integer id = content.loadImage(extraPart.getImage());
            pictureIdList.add(id);
        }
        shipBuildingActivity.setPartViewImageList(IShipBuildingView.PartSelectionView.EXTRA_PART, pictureIdList);

        pictureIdList = new ArrayList<>();
        for (PowerCore powerCore : asteroidsGame.getPowerCores()) {
            Integer id = content.loadImage(powerCore.getImage());
            pictureIdList.add(id);
        }
        shipBuildingActivity.setPartViewImageList(IShipBuildingView.PartSelectionView.POWER_CORE, pictureIdList);
    }

    @Override
    public void unloadContent(ContentManager content) {
        Log.d(TAG, "unloadContent: ");
    }

    @Override
    public void draw() {
//        if ()
//        Log.d(TAG, "draw: ");

    }

    /**
     * The ShipBuildingView calls this function when the user makes a swipe/fling motion in the
     * screen. Respond to the user's swipe/fling motion in this function.
     * @param direction The direction of the swipe/fling.
     */
    @Override
    public void onSlideView(IShipBuildingView.ViewDirection direction) {
        Log.d(TAG, "onSlideView: direction- " + direction);
    }

    /**
     * The part selection fragments call this function when a part is selected from the parts list. Respond
     * to the part selection in this function.
     * @param index The list index of the selected part.
     */
    @Override
    public void onPartSelected(int index) {
        Log.d(TAG, "onPartSelected: index " + index);

    }

    /**
     * The ShipBuildingView calls this function is called when the start game button is pressed.
     */
    @Override
    public void onStartGamePressed() {
        Log.d(TAG, "onStartGamePressed: ");
    }

    /**
     * The ShipBuildingView calls this function when ship building has resumed. Reset the Camera and
     * the ship position as needed when this is called.
     */
    @Override
    public void onResume() {

    }

    @Override
    public IView getView() {
        return null;
    }

    @Override
    public void setView(IView view) {
        Log.d(TAG, "setView: " + view);

    }
}
