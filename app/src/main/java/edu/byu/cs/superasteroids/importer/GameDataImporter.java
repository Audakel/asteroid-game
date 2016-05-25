package edu.byu.cs.superasteroids.importer;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.interfaces.IGameDataImporter;
import edu.byu.cs.superasteroids.model.asteroids.Asteroid;
import edu.byu.cs.superasteroids.model.AsteroidsGame;
import edu.byu.cs.superasteroids.model.shipParts.Cannon;
import edu.byu.cs.superasteroids.model.shipParts.Engine;
import edu.byu.cs.superasteroids.model.shipParts.ExtraPart;
import edu.byu.cs.superasteroids.model.Level;
import edu.byu.cs.superasteroids.model.LevelAsteroid;
import edu.byu.cs.superasteroids.model.LevelObject;
import edu.byu.cs.superasteroids.model.shipParts.MainBody;
import edu.byu.cs.superasteroids.model.ObjectImage;
import edu.byu.cs.superasteroids.model.shipParts.PowerCore;

/**
 * Created by audakel on 5/16/16.
 */

public class GameDataImporter implements IGameDataImporter {
    private final String TAG = this.getClass().getSimpleName();
    private Context context;
    public AsteroidsGame mAsteroidsGame;

    public GameDataImporter(Context context) {
        this.context = context;
    }

    /**
     *
     * @param dataInputReader The InputStreamReader connected to the .json file needing to be imported.
     * @return boolean on status of successful import
     */
    @Override
    public boolean importData(InputStreamReader dataInputReader) {
        StringBuilder stringBuilder = new StringBuilder();
        JSONObject jsonObject = null;
        AsteroidsGame asteroidsGame;

        Scanner scanner = new Scanner(dataInputReader);
        while (scanner.hasNext()){
            stringBuilder.append(scanner.next());
        }

        try {
            jsonObject = new JSONObject(stringBuilder.toString());
            jsonObject = jsonObject.getJSONObject(Contract.ASTEROIDS_GAME);
            asteroidsGame = extractJsonGameInfo(jsonObject);
            mAsteroidsGame = asteroidsGame;
            return importGameDataToDataBase(asteroidsGame, context);

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public AsteroidsGame getTempAsteroidsGame() {
        return mAsteroidsGame;
    }

    private boolean importGameDataToDataBase(AsteroidsGame asteroidsGame, Context context) {
        ContentValues values = new ContentValues();

        for (Asteroid asteroid : asteroidsGame.getAsteroids()){
            context.getContentResolver().insert(Contract.URI_ASTEROID, asteroid.getContentValues());
        }

//        TODO:: Refactor out duplicate code and figure out how to make list of different types
//        for (ShipPart shipPart : asteroidsGame.getShipParts()) {
//
//        }

        for (Cannon cannon : asteroidsGame.getCannons()){
            context.getContentResolver().insert(Contract.URI_CANNON, cannon.getContentValues());
        }

        for (Engine engine: asteroidsGame.getEngines()){
            context.getContentResolver().insert(Contract.URI_ENGINE, engine.getContentValues());
        }

        for (ExtraPart extraPart : asteroidsGame.getExtraParts()){
            context.getContentResolver().insert(Contract.URI_EXTRA_PART, extraPart.getContentValues());
        }

        for (MainBody mainBody : asteroidsGame.getMainBodies()){
            context.getContentResolver().insert(Contract.URI_MAIN_BODY, mainBody.getContentValues());
        }

        for (PowerCore powerCore : asteroidsGame.getPowerCores()){
            context.getContentResolver().insert(Contract.URI_POWER_CORE, powerCore.getContentValues());
        }

        for (Level level : asteroidsGame.getLevels()){
            Uri levelUri = context.getContentResolver().insert(Contract.URI_LEVEL, level.getContentValues());

            if (level.getLevelAsteroids().length > 0){
                for (LevelAsteroid levelAsteroid : level.getLevelAsteroids()){
                    ContentValues contentValues = new ContentValues();
                    contentValues = levelAsteroid.getContentValues();
                    contentValues.put(Contract.LEVEL_ID, level.getNumber());
                    Uri uriAsteroid = context.getContentResolver().insert(Contract.URI_LEVEL_ASTEROID, contentValues);
                    Log.d(TAG, "importGameDataToDataBase: inserting levelAsteroid:  levelId -"
                            + levelUri.getLastPathSegment() + " level: " + level.getNumber());
                }
             }

            if (level.getLevelObjects().length > 0){
                for (LevelObject levelObject : level.getLevelObjects()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues = levelObject.getContentValues();
                    contentValues.put(Contract.LEVEL_ID, level.getNumber());
                    Uri uriObject = context.getContentResolver().insert(Contract.URI_LEVEL_OBJECT, contentValues);
                    Log.d(TAG, "importGameDataToDataBase: inserting levelObject: levelId "
                            + uriObject.getLastPathSegment() + " level: " + level.getNumber());
                }
            }

        }

        return true;
    }

    /**
     *
     * @param jsonObject file containing json info about all needed game stats to start
     * @return AsteroidsGame object with model objects (cannon, engine, etc.) of all the json info
     */
    private AsteroidsGame extractJsonGameInfo(JSONObject jsonObject) {
        AsteroidsGame asteroidsGame = new AsteroidsGame();
        ArrayList<Asteroid> asteroids = new ArrayList<>();
        ArrayList<Level> levels= new ArrayList<>();
        ArrayList<MainBody> mainBodies= new ArrayList<>();
        ArrayList<Cannon> cannons= new ArrayList<>();
        ArrayList<Engine> engines= new ArrayList<>();
        ArrayList<PowerCore> powerCores= new ArrayList<>();
        ArrayList<ExtraPart> extraParts= new ArrayList<>();
        ArrayList<ObjectImage> objectImages= new ArrayList<>();
        Gson gson = new Gson();
        JSONArray tempArray;

        try {
            Iterator<?> keys = jsonObject.keys();

            while( keys.hasNext() ) {
                String key = (String)keys.next();
                tempArray = jsonObject.getJSONArray(key);

                for (int i = 0; i < tempArray.length(); i++) {
                    switch (key){
                        case Contract.CANNONS:
                            Cannon cannon = new Cannon(tempArray.getJSONObject(i));
                            cannons.add(cannon);
                            break;
                        case Contract.ENGINES:
                            Engine engine = new Engine(tempArray.getJSONObject(i));
                            engines.add(engine);
                            break;
                        case Contract.EXTRA_PARTS:
                            ExtraPart extraPart = new ExtraPart(tempArray.getJSONObject(i));
                            extraParts.add(extraPart);
                            break;
                        case Contract.MAIN_BODIES:
                            MainBody mainBody = new MainBody(tempArray.getJSONObject(i));
                            mainBodies.add(mainBody);
                            break;
                        case Contract.POWER_CORES:
                            PowerCore powerCore = new PowerCore(tempArray.getJSONObject(i));
                            powerCores.add(powerCore);
                            break;
                        case Contract.OBJECTS:
                            objectImages.add(new ObjectImage(tempArray.getString(i)));
                            break;
                        case Contract.ASTEROIDS:
                            Asteroid asteroid = gson.fromJson(tempArray.getJSONObject(i).toString(), Asteroid.class);
                            asteroids.add(asteroid);
                            break;
                        case Contract.LEVELS:
                            Level level = gson.fromJson(tempArray.getJSONObject(i).toString(), Level.class);
                            levels.add(level);
                            break;
                    }
                }
            }
            asteroidsGame.setMainBodies(mainBodies);
            asteroidsGame.setCannons(cannons);
            asteroidsGame.setEngines(engines);
            asteroidsGame.setPowerCores(powerCores);
            asteroidsGame.setExtraParts(extraParts);
            asteroidsGame.setObjectImages(objectImages);
            asteroidsGame.setAsteroids(asteroids);
            asteroidsGame.setLevels(levels);


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return asteroidsGame;
    }
}
