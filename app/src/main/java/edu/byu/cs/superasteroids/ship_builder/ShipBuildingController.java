package edu.byu.cs.superasteroids.ship_builder;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.byu.cs.superasteroids.database.Contract;
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

import static edu.byu.cs.superasteroids.interfaces.IShipBuildingView.PartSelectionView.CANNON;
import static edu.byu.cs.superasteroids.interfaces.IShipBuildingView.PartSelectionView.ENGINE;
import static edu.byu.cs.superasteroids.interfaces.IShipBuildingView.PartSelectionView.EXTRA_PART;
import static edu.byu.cs.superasteroids.interfaces.IShipBuildingView.PartSelectionView.MAIN_BODY;
import static edu.byu.cs.superasteroids.interfaces.IShipBuildingView.PartSelectionView.POWER_CORE;
import static edu.byu.cs.superasteroids.interfaces.IShipBuildingView.ViewDirection.DOWN;
import static edu.byu.cs.superasteroids.interfaces.IShipBuildingView.ViewDirection.LEFT;
import static edu.byu.cs.superasteroids.interfaces.IShipBuildingView.ViewDirection.RIGHT;
import static edu.byu.cs.superasteroids.interfaces.IShipBuildingView.ViewDirection.UP;

/**
 * Created by audakel on 5/16/16.
 */
public class ShipBuildingController implements IShipBuildingController{
    private Context context;
    private ShipBuildingActivity shipBuildingActivity;
    private String TAG = getClass().getSimpleName();
    private int shipBuildingBoardPosition = 5;
    private List<Boolean> board;

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
//        setArrow(PartSelectionView partView, ViewDirection arrow, boolean visible, String text);

        /*
        *   case 2:
                return EXTRA_PART;
            case 4:
                return ENGINE;
            case 5:
                return MAIN_BODY;
            case 6:
                return CANNON;
            case 8:
                return POWER_CORE;
                */

        switch(partView) {
            case EXTRA_PART:
                shipBuildingActivity.setArrow(partView, UP, false, "");
                shipBuildingActivity.setArrow(partView, RIGHT, false, "");
                shipBuildingActivity.setArrow(partView, DOWN, true, Contract.MAIN_BODIES);
                shipBuildingActivity.setArrow(partView, LEFT, false, "");
                break;
            case ENGINE:
                shipBuildingActivity.setArrow(partView, UP, false, "");
                shipBuildingActivity.setArrow(partView, RIGHT, true, Contract.MAIN_BODIES);
                shipBuildingActivity.setArrow(partView, DOWN, false, "");
                shipBuildingActivity.setArrow(partView, LEFT, false, "");
                break;
            case MAIN_BODY:
                shipBuildingActivity.setArrow(partView, UP, true, Contract.EXTRA_PARTS);
                shipBuildingActivity.setArrow(partView, RIGHT, true, Contract.CANNONS);
                shipBuildingActivity.setArrow(partView, DOWN, true, Contract.POWER_CORES);
                shipBuildingActivity.setArrow(partView, LEFT, true, Contract.ENGINES);
                break;
            case CANNON:
                shipBuildingActivity.setArrow(partView, UP, false, "");
                shipBuildingActivity.setArrow(partView, RIGHT, false, "");
                shipBuildingActivity.setArrow(partView, DOWN, false, "");
                shipBuildingActivity.setArrow(partView, LEFT, true, Contract.MAIN_BODIES);
                break;
            case POWER_CORE:
                shipBuildingActivity.setArrow(partView, UP, true, Contract.MAIN_BODIES);
                shipBuildingActivity.setArrow(partView, RIGHT, false, "");
                shipBuildingActivity.setArrow(partView, DOWN, false, "");
                shipBuildingActivity.setArrow(partView, LEFT, false, "");
                break;
        }
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
        shipBuildingActivity.setPartViewImageList(MAIN_BODY, pictureIdList);

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
        direction = shipBuildingActivity.getOppositeDirection(direction);
        board = Arrays.asList(false, false, true, false, true, true, true, false, true, false);

        int flag = 0;
        switch(direction) {
            case LEFT:
                if(left()) flag++; break;
            case RIGHT:
                if(right()) flag++; break;
            case UP:
                if(up()) flag++; break;
            case DOWN:
                if(down()) flag++; break;
        }

        Log.d(TAG, "onSlideView: direction- " + direction + " can move = " + flag);

        if (flag > 0){
            shipBuildingActivity.animateToView( getViewForPosition(shipBuildingBoardPosition), direction);
        }


    }

    private IShipBuildingView.PartSelectionView getViewForPosition(int position){
        switch(position) {
            case 2:
                return EXTRA_PART;
            case 4:
                return ENGINE;
            case 5:
                return MAIN_BODY;
            case 6:
                return CANNON;
            case 8:
                return POWER_CORE;
        }
        return null;
    }

    private boolean up(){
        if (shipBuildingBoardPosition - 3 <= 0) return false;
        boolean position = board.get(shipBuildingBoardPosition - 3);
        if (!position) return false;
        shipBuildingBoardPosition = shipBuildingBoardPosition - 3;
        return true;
    }

    private boolean down(){
        if (shipBuildingBoardPosition + 3 > board.size()) return false;
        boolean position = board.get(shipBuildingBoardPosition + 3);
        if (!position) return false;
        shipBuildingBoardPosition = shipBuildingBoardPosition + 3;
        return true;
    }

    private boolean right(){
        if (shipBuildingBoardPosition + 1 > board.size()) return false;
        boolean position = board.get(shipBuildingBoardPosition + 1);
        if (!position) return false;
        shipBuildingBoardPosition = shipBuildingBoardPosition + 1;
        return true;
    }

    private boolean left(){
        if (shipBuildingBoardPosition - 1 <= 0) return false;
        boolean position = board.get(shipBuildingBoardPosition - 1);
        if (!position) return false;
        shipBuildingBoardPosition = shipBuildingBoardPosition - 1;
        return true;
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
