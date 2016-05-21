package edu.byu.cs.superasteroids.base;

import android.support.v7.app.ActionBarActivity;

import edu.byu.cs.superasteroids.interfaces.IController;
import edu.byu.cs.superasteroids.interfaces.IView;

public class ActionBarActivityView extends ActionBarActivity implements IView {

    private IController controller;

    @Override
    public IController getController() {
        return controller;
    }

    @Override
    public void setController(IController controller) {
        this.controller = controller;
    }
}
