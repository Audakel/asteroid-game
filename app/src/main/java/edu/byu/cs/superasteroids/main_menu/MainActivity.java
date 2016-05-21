package edu.byu.cs.superasteroids.main_menu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import edu.byu.cs.superasteroids.R;
import edu.byu.cs.superasteroids.base.ActionBarActivityView;
import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.helper.content.ContentManager;
import edu.byu.cs.superasteroids.database.DatabaseHelper;
import edu.byu.cs.superasteroids.game.GameActivity;
import edu.byu.cs.superasteroids.importer.GameDataImporter;
import edu.byu.cs.superasteroids.importer.ImportActivity;
import edu.byu.cs.superasteroids.interfaces.IMainMenuController;
import edu.byu.cs.superasteroids.interfaces.IMainMenuView;
import edu.byu.cs.superasteroids.ship_builder.ShipBuildingActivity;

public class MainActivity extends ActionBarActivityView implements IMainMenuView {
    private final String TAG = this.getClass().getSimpleName();
    private DatabaseHelper mDatabase;
    GameDataImporter gameDataImporter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = new DatabaseHelper(this);


        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        //Set this activity's controller to an instance of your MainMenuController
        //Pass the MainMenuController's constructor a reference to its IMainMenuView (this)
        IMainMenuController controller = new MainMenuController(this);
        setController(controller);


        //Initialize your database
        String init = Contract.AUTHORITY;
        testDB();

        gameDataImporter = new GameDataImporter(this);


        gameDataImporter.getLevelInfoFromDb(1);

        ContentManager.getInstance().setResources(getResources());
        ContentManager.getInstance().setAssets(getAssets());
    }

    private void testDB() {
        ContentValues[] valueList = new ContentValues[10];

        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int maxSize = 999999999;
            int minSize = 111111111;

            String name = "billy_"+i;
            String image = "image_"+i;
            String width = (random.nextInt(maxSize - minSize + 1) + minSize)+"";
            String height = (random.nextInt(maxSize - minSize + 1) + minSize)+"";
            String type = "type_"+i;

            ContentValues values = new ContentValues();
            values.put(Contract.ASTEROID_NAME, name);
            values.put(Contract.ASTEROID_IMAGE, image);
            values.put(Contract.ASTEROID_IMAGE_WIDTH, width);
            values.put(Contract.ASTEROID_IMAGE_HEIGHT, height);
            values.put(Contract.ASTEROID_TYPE, type);

            valueList[i] = values;
        }

        Cursor cursor = getContentResolver().query(Contract.URI_ASTEROID, null, null, null, null);
        Log.d(TAG, "onHandleIntent: cursor size b4 " + cursor.getCount());
        if (cursor.getCount() == 0){
            Log.d(TAG, "onHandleIntent: insert " + getContentResolver().bulkInsert(Contract.URI_ASTEROID, valueList));
        }
        Log.d(TAG, "onHandleIntent: cursor size after " + cursor.getCount());

        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public void startGame(View v) {
        Intent intent = new Intent(this, ShipBuildingActivity.class);
        startActivity(intent);
    }

    public void quickPlay(View v) {
        if (getController() != null) {
            ((IMainMenuController) getController()).onQuickPlayPressed();
        }
    }

    public void startGame() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.setFlags(android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.startActivity(intent);
    }

    public void importData(View v) {
        Intent intent = new Intent(this, ImportActivity.class);
        startActivity(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
