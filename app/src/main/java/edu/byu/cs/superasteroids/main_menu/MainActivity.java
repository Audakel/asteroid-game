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
import edu.byu.cs.superasteroids.game.GameHolder;
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

        // Init the game
        GameHolder.init(this.getBaseContext());

        // Initialize your database
        String init = Contract.AUTHORITY;

        gameDataImporter = new GameDataImporter(this);


//        gameDataImporter.getLevelInfoFromDb(1);

        ContentManager.getInstance().setResources(getResources());
        ContentManager.getInstance().setAssets(getAssets());
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
