package edu.byu.cs.superasteroids.interfaces;

import android.content.Context;

public interface IMainMenuController extends IController {

    /**
     * The MainActivity calls this function when the "quick play" button is pressed.
     */
    void onQuickPlayPressed(Context context);
}
