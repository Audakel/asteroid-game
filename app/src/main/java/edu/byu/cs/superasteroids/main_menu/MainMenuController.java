package edu.byu.cs.superasteroids.main_menu;

import android.content.Context;

import java.util.concurrent.ConcurrentHashMap;

import edu.byu.cs.superasteroids.interfaces.IView;
import edu.byu.cs.superasteroids.interfaces.IMainMenuController;

/**
 * Created by audakel on 5/14/16.
 */
public class MainMenuController implements IMainMenuController {
    Context context;

    public MainMenuController(Context context){
        this.context = context;
    }
    @Override
    public void onQuickPlayPressed() {

    }

    @Override
    public IView getView() {
        return null;
    }

    @Override
    public void setView(IView view) {

    }
}
