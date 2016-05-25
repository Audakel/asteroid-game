package edu.byu.cs.superasteroids.importer;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.byu.cs.superasteroids.R;
import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.interfaces.IGameDataImporter;
import edu.byu.cs.superasteroids.model.AsteroidsGame;
import edu.byu.cs.superasteroids.model.Level;
import edu.byu.cs.superasteroids.model.LevelAsteroid;
import edu.byu.cs.superasteroids.model.LevelObject;

import static edu.byu.cs.superasteroids.database.Contract.allAteroidUris;

/**
 * Created by audakel on 5/21/16.
 */
public class GameDataExtractor {
    private final String TAG = this.getClass().getSimpleName();
    private Context context;

    public GameDataExtractor(Context context){
        this.context = context;
    }

    /**
     *
     * @return the game info for everything, and the level info for just that level
     */
    public AsteroidsGame getAsteroidsGameFromDb() {
//        Cursor cursor;
//        Object gameObject;
//        AsteroidsGame asteroidsGame = new AsteroidsGame();
//
//        for (int i = 0; i < allAteroidUris().length; i++) {
//            cursor = context.getContentResolver().query(allAteroidUris()[i], null, null, null, null);
//
//            if (cursor.moveToFirst()) {
////                while (cursor.moveToNext()){
////                    gameObject = Contract.allAteroidGameObjects()[i];
////
////                    levelAsteroids[row++] = new LevelAsteroid(map);
////                }
////                Log.d(TAG, "getLevelInfoFromDb: got level info from db!");
////                gameLevel = new Level(map);
////            }
//
//            }
//
//            cursor.close();
//        }
        ListView listView;
        Resources res;
        AssetManager am;
        List<String> fileList;
        AsteroidsGame mAsteroidsGame;

        res = context.getResources();
        am = res.getAssets();

        String[] files = null;
        try {
            files = am.list("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileList = null;
        if(files != null) {
            fileList = new ArrayList<String>();
            for(String file : files) {
                if(file.endsWith(".json"))
                    fileList.add(file);
            }

        }

        GameDataImporter dataImporter = new GameDataImporter(context);
        try {
            dataImporter.importData(new InputStreamReader(
                    new BufferedInputStream(am.open(fileList.get(2)))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataImporter.getTempAsteroidsGame();

    }

    /**
     *
     * @param level takes a level number
     * @return the info for just that level
     */
    public Level getLevelInfoFromDb(int level){
        int count;
        HashMap<String,String> map;
        Cursor cursor;
        Level gameLevel = null;

        // Level stuff
        cursor = context.getContentResolver().query(Contract.URI_LEVEL, null, "_id="+level, null, null);

        if (cursor.moveToFirst()){
            count = cursor.getColumnCount();
            map = new HashMap<>();

            for (int i =0 ; i< count; i++) {
                String data = cursor.getString(i);
                String column_name = cursor.getColumnName(i);
                map.put(column_name, data+"");
            }
            Log.d(TAG, "getLevelInfoFromDb: got level info from db!");
            gameLevel = new Level(map);
        }
        cursor.close();


        // Level Asteroid stuff
        cursor = context.getContentResolver().query(Contract.URI_LEVEL_ASTEROID, null, Contract.LEVEL_ID+"="+level, null, null);

        if (cursor.moveToFirst()){
            count = cursor.getColumnCount();
            LevelAsteroid[] levelAsteroids = new LevelAsteroid[cursor.getCount()];

            while (cursor.moveToNext()){
                map = new HashMap<>();
                String columnName;
                String data;
                int row = 0;

                for (int i =0 ; i< count; i++) {
                    data = cursor.getString(i);
                    columnName = cursor.getColumnName(i);
                    map.put(columnName, data+"");
                }
                levelAsteroids[row++] = new LevelAsteroid(map);
            }
            gameLevel.setLevelAsteroids(levelAsteroids);
            Log.d(TAG, "getLevelInfoFromDb: got level Asteroids! (" + levelAsteroids.length+")");
        }
        cursor.close();


        // Level ObjectImage stuff
        cursor = context.getContentResolver().query(Contract.URI_LEVEL_OBJECT, null, Contract.LEVEL_ID+"="+level, null, null);

        if (cursor.moveToFirst()){
            count = cursor.getColumnCount();
            LevelObject[] levelObjects = new LevelObject[cursor.getCount()];

            while (cursor.moveToNext()){
                map = new HashMap<String, String>();
                String columnName;
                String data;
                int row = 0;

                for (int i =0 ; i< count-1; i++) {
                    data = cursor.getString(i);
                    columnName = cursor.getColumnName(i);
                    map.put(columnName, data+"");
                }
                levelObjects[row++] = new LevelObject(map);
            }
            gameLevel.setLevelObjects(levelObjects);
            Log.d(TAG, "getLevelInfoFromDb: got level Objects! (" + levelObjects.length+")");
        }
        cursor.close();

        return gameLevel;
    }
}
