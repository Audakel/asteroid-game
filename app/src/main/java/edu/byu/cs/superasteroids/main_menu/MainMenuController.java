package edu.byu.cs.superasteroids.main_menu;

import android.content.Context;

import java.util.concurrent.ConcurrentHashMap;

import edu.byu.cs.superasteroids.helper.content.ContentManager;
import edu.byu.cs.superasteroids.interfaces.IView;
import edu.byu.cs.superasteroids.interfaces.IMainMenuController;

/**
 * Created by audakel on 5/14/16.
 */
public class MainMenuController implements IMainMenuController {
    Context context;
    MainActivity mainActivity;

    public MainMenuController(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onQuickPlayPressed() {
        mainActivity.startGame();
    }

    @Override
    public IView getView() {
        return null;
    }

    @Override
    public void setView(IView view) {

    }
}
