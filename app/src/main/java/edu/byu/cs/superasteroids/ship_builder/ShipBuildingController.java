package edu.byu.cs.superasteroids.ship_builder;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.game.GameHolder;
import edu.byu.cs.superasteroids.helper.content.ContentManager;
import edu.byu.cs.superasteroids.importer.GameDataExtractor;
import edu.byu.cs.superasteroids.interfaces.IShipBuildingController;
import edu.byu.cs.superasteroids.interfaces.IShipBuildingView;
import edu.byu.cs.superasteroids.interfaces.IView;
import edu.byu.cs.superasteroids.model.AsteroidsGame;
import edu.byu.cs.superasteroids.model.Ship;
import edu.byu.cs.superasteroids.model.shipParts.Cannon;
import edu.byu.cs.superasteroids.model.shipParts.Engine;
import edu.byu.cs.superasteroids.model.shipParts.ExtraPart;
import edu.byu.cs.superasteroids.model.shipParts.MainBody;
import edu.byu.cs.superasteroids.model.shipParts.PowerCore;

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
    private  ShipBuilderShipView shipBuildingView;
    private Context context;
    private ShipBuildingActivity shipBuildingActivity;
    private String TAG = getClass().getSimpleName();
    private int shipBuildingBoardPosition = 5;
    private List<Boolean> board;
    private ContentManager content;
    private AsteroidsGame asteroidsGame;
    private boolean shipComplete = false;

    private Ship ship;
    GameHolder gameHolder;

    public ShipBuildingController(ShipBuildingActivity shipBuildingActivity, Context context) {
        this.shipBuildingActivity = shipBuildingActivity;
        this.context = context;
        this.ship = GameHolder.getAsteroidsGame().getShip();
        Log.d(TAG, "ShipBuildingController: shipBuildingActivity- " + shipBuildingActivity);

    }

//    public ShipBuildingController(ShipBuilderShipView shipBuilderShipView) {
//        Log.d(TAG, "ShipBuildingController: context- " + context);
//        this.context = context;
//    }

    /**
     * The ship building view calls this function when a part selection view is loaded. This function
     * should be used to configure the part selection view. Example: Set the arrows for the view in
     * this function.
     * @param partView
     */
    @Override
    public void onViewLoaded(IShipBuildingView.PartSelectionView partView) {
        Log.d(TAG, "onViewLoaded: partView- " + partView);

        switch(partView) {
            case EXTRA_PART:
                shipBuildingActivity.setArrow(partView, UP, false, Contract.EXTRA_PARTS);
                shipBuildingActivity.setArrow(partView, DOWN, false, "");
                shipBuildingActivity.setArrow(partView, RIGHT, true, Contract.MAIN_BODIES);
                shipBuildingActivity.setArrow(partView, LEFT, false, "");
                break;
            case ENGINE:
                shipBuildingActivity.setArrow(partView, UP, true, Contract.MAIN_BODIES);
                shipBuildingActivity.setArrow(partView, RIGHT, false, Contract.MAIN_BODIES);
                shipBuildingActivity.setArrow(partView, DOWN, false, "");
                shipBuildingActivity.setArrow(partView, LEFT, false, "");
                break;
            case MAIN_BODY:
                shipBuildingActivity.setArrow(partView, UP, true, Contract.POWER_CORES);
                shipBuildingActivity.setArrow(partView, RIGHT, true, Contract.CANNONS);
                shipBuildingActivity.setArrow(partView, DOWN, true, Contract.ENGINES);
                shipBuildingActivity.setArrow(partView, LEFT, true, Contract.EXTRA_PARTS);
                break;
            case CANNON:
                shipBuildingActivity.setArrow(partView, UP, false, Contract.CANNONS);
                shipBuildingActivity.setArrow(partView, RIGHT, false, "");
                shipBuildingActivity.setArrow(partView, DOWN, false, "");
                shipBuildingActivity.setArrow(partView, LEFT, true, Contract.MAIN_BODIES);
                break;
            case POWER_CORE:
                shipBuildingActivity.setArrow(partView, UP, false, Contract.POWER_CORES);
                shipBuildingActivity.setArrow(partView, RIGHT, false, "");
                shipBuildingActivity.setArrow(partView, DOWN, true, Contract.POWER_CORES);
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
        this.content = content;
        Log.d(TAG, "loadContent: content- " + content);


        //TODO:: fix asteroids game for real
        if (asteroidsGame != null){
            return;
        }

        asteroidsGame = new GameDataExtractor(context).getAsteroidsGameFromDb();

        ArrayList<Integer> pictureIdList = new ArrayList<>();
        for (MainBody mainBody : asteroidsGame.getMainBodies()) {
            Integer id = content.loadImage(mainBody.getGameImage().getFilePath());
            pictureIdList.add(id);
        }
        shipBuildingActivity.setPartViewImageList(MAIN_BODY, pictureIdList);

        pictureIdList = new ArrayList<>();
        for (Cannon cannon : asteroidsGame.getCannons()) {
            Integer id = content.loadImage(cannon.getGameImage().getFilePath());
            pictureIdList.add(id);
        }
        shipBuildingActivity.setPartViewImageList(IShipBuildingView.PartSelectionView.CANNON, pictureIdList);

        pictureIdList = new ArrayList<>();
        for (Engine engine: asteroidsGame.getEngines()) {
            Integer id = content.loadImage(engine.getGameImage().getFilePath());
            pictureIdList.add(id);
        }
        shipBuildingActivity.setPartViewImageList(IShipBuildingView.PartSelectionView.ENGINE, pictureIdList);

        pictureIdList = new ArrayList<>();
        for (ExtraPart extraPart : asteroidsGame.getExtraParts()) {
            Integer id = content.loadImage(extraPart.getGameImage().getFilePath());
            pictureIdList.add(id);
        }
        shipBuildingActivity.setPartViewImageList(IShipBuildingView.PartSelectionView.EXTRA_PART, pictureIdList);

        pictureIdList = new ArrayList<>();
        for (PowerCore powerCore : asteroidsGame.getPowerCores()) {
            Integer id = content.loadImage(powerCore.getGameImage().getFilePath());
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
        ship.draw();
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
        int temp = shipBuildingBoardPosition;

        switch(direction) {
            case LEFT:
                if (shipBuildingBoardPosition - 1 <= 0) break;
                if (!board.get(shipBuildingBoardPosition - 1)) break;
                shipBuildingBoardPosition = shipBuildingBoardPosition - 1;break;
            case RIGHT:
                if (shipBuildingBoardPosition + 1 > board.size()) break;
                if (!board.get(shipBuildingBoardPosition + 1)) break;
                shipBuildingBoardPosition = shipBuildingBoardPosition + 1;break;
            case UP:
                if (shipBuildingBoardPosition - 3 <= 0) break;
                if (!board.get(shipBuildingBoardPosition - 3)) break;
                shipBuildingBoardPosition = shipBuildingBoardPosition - 3;break;
            case DOWN:
                if (shipBuildingBoardPosition + 3 > board.size()) break;
                if (!board.get(shipBuildingBoardPosition + 3)) break;
                shipBuildingBoardPosition = shipBuildingBoardPosition + 3; break;
        }

        if (shipBuildingBoardPosition != temp){
            shipBuildingActivity.animateToView( getViewForPosition(shipBuildingBoardPosition), direction);
            Log.d(TAG, "onSlideView: direction- " + direction + " can move");
        }
    }

    private IShipBuildingView.PartSelectionView getViewForPosition(int position) {
        Log.d(TAG, "getViewForPosition: position = " + position);
        switch (position) {
            case 2:         return POWER_CORE;
            case 4:         return EXTRA_PART;
            case 5:         return MAIN_BODY;
            case 6:         return CANNON;
            case 8:         return ENGINE;
        }
        return null;
    }


    /**
     * The part selection fragments call this function when a part is selected from the parts list. Respond
     * to the part selection in this function.
     * @param index The list index of the selected part.
     */
    @Override
    public void onPartSelected(int index) {
        switch(getViewForPosition(shipBuildingBoardPosition)) {
            case EXTRA_PART:        ship.setExtraPart(asteroidsGame.getExtraParts().get(index)); break;
            case ENGINE:            ship.setEngine(asteroidsGame.getEngines().get(index)); break;
            case MAIN_BODY:         ship.setMainBody(asteroidsGame.getMainBodies().get(index)); break;
            case CANNON:            ship.setCannon(asteroidsGame.getCannons().get(index)); break;
            case POWER_CORE:        ship.setPowerCore(asteroidsGame.getPowerCores().get(index)); break;
        }
        Log.d(TAG, "onPartSelected: index " + index);
        shipComplete();
    }

    private void shipComplete() {
        if (ship.getExtraPart().getGameImage().getFilePath() == "") return;
        if (ship.getEngine().getGameImage().getFilePath() == "") return;
        if (ship.getMainBody().getGameImage().getFilePath() == "") return;
        if (ship.getCannon().getGameImage().getFilePath() == "") return;
        if (ship.getPowerCore().getGameImage().getFilePath() == "") return;

        shipBuildingActivity.setStartGameButton(true);



    }

    /**
     * The ShipBuildingView calls this function is called when the start game button is pressed.
     */
    @Override
    public void onStartGamePressed() {
        Log.d(TAG, "onStartGamePressed: ");
        shipBuildingActivity.startGame();

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
