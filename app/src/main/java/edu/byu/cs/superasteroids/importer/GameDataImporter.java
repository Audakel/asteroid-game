package edu.byu.cs.superasteroids.importer;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.interfaces.IGameDataImporter;
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

public class GameDataImporter implements IGameDataImporter {
    private final String TAG = this.getClass().getSimpleName();
    private Context context;

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
            asteroidsGame = extractGameInfo(jsonObject);
            return importGameDataToDataBase(asteroidsGame, context);

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean importGameDataToDataBase(AsteroidsGame asteroidsGame, Context context) {
        ContentValues values = new ContentValues();

        for (Asteroid asteroid : asteroidsGame.getAsteroids()){
            context.getContentResolver().insert(Contract.URI_ASTEROID, asteroid.getContentValues());
        }

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

            }

            if (level.getLevelObjects().length > 0){

            }

        }

        return true;
    }

    /**
     *
     * @param jsonObject file containing json info about all needed game stats to start
     * @return AsteroidsGame object with model objects (cannon, engine, etc.) of all the json info
     */
    private AsteroidsGame extractGameInfo(JSONObject jsonObject) {
        AsteroidsGame asteroidsGame = new AsteroidsGame();
        ArrayList<Asteroid> asteroids;
        ArrayList<Level> levels;
        ArrayList<MainBody> mainBodies;
        ArrayList<Cannon> cannons;
        ArrayList<Engine> engines;
        ArrayList<PowerCore> powerCores;
        ArrayList<ExtraPart> extraParts;
        Gson gson = new Gson();
        JSONArray tempArray;

        try {
            if (jsonObject.has(Contract.ASTEROIDS)) {
                tempArray = jsonObject.getJSONArray(Contract.ASTEROIDS);
                asteroids = new ArrayList<>();

                for (int i = 0; i < tempArray.length(); i++) {
                    Asteroid asteroid = gson.fromJson(tempArray.getJSONObject(i).toString(), Asteroid.class);
                    asteroids.add(asteroid);
                }
                asteroidsGame.setAsteroids(asteroids);
            }

            if (jsonObject.has(Contract.LEVELS)) {
                tempArray = jsonObject.getJSONArray(Contract.LEVELS);
                levels = new ArrayList<>();

                for (int i = 0; i < tempArray.length(); i++) {
                    Level level = gson.fromJson(tempArray.getJSONObject(i).toString(), Level.class);

                    levels.add(level);
                }
                asteroidsGame.setLevels(levels);
            }

            if (jsonObject.has(Contract.MAIN_BODIES)) {
                tempArray = jsonObject.getJSONArray(Contract.MAIN_BODIES);
                mainBodies = new ArrayList<>();

                for (int i = 0; i < tempArray.length(); i++) {
                    MainBody mainBody = gson.fromJson(tempArray.getJSONObject(i).toString(), MainBody.class);
                    mainBodies.add(mainBody);
                }
                asteroidsGame.setMainBodies(mainBodies);
            }

            if (jsonObject.has(Contract.CANNONS)) {
                tempArray = jsonObject.getJSONArray(Contract.CANNONS);
                cannons = new ArrayList<>();

                for (int i = 0; i < tempArray.length(); i++) {
                    Cannon cannon = gson.fromJson(tempArray.getJSONObject(i).toString(), Cannon.class);
                    cannons.add(cannon);
                }
                asteroidsGame.setCannons(cannons);
            }

            if (jsonObject.has(Contract.ENGINES)) {
                tempArray = jsonObject.getJSONArray(Contract.ENGINES);
                engines = new ArrayList<>();

                for (int i = 0; i < tempArray.length(); i++) {
                    Engine engine = gson.fromJson(tempArray.getJSONObject(i).toString(), Engine.class);
                    engines.add(engine);
                }
                asteroidsGame.setEngines(engines);
            }

            if (jsonObject.has(Contract.POWER_CORES)) {
                tempArray = jsonObject.getJSONArray(Contract.POWER_CORES);
                powerCores = new ArrayList<>();

                for (int i = 0; i < tempArray.length(); i++) {
                    PowerCore powerCore = gson.fromJson(tempArray.getJSONObject(i).toString(), PowerCore.class);
                    powerCores.add(powerCore);
                }
                asteroidsGame.setPowerCores(powerCores);
            }

            if (jsonObject.has(Contract.EXTRA_PARTS)) {
                tempArray = jsonObject.getJSONArray(Contract.EXTRA_PARTS);

                extraParts = new ArrayList<>();

                for (int i = 0; i < tempArray.length(); i++) {
                    ExtraPart extraPart = gson.fromJson(tempArray.getJSONObject(i).toString(), ExtraPart.class);
                    extraParts.add(extraPart);
                }
                asteroidsGame.setExtraParts(extraParts);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return asteroidsGame;
    }
}
