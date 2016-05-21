package edu.byu.cs.superasteroids.base;

import android.content.Context;
import android.view.SurfaceView;

import edu.byu.cs.superasteroids.interfaces.IController;
import edu.byu.cs.superasteroids.interfaces.IView;

/**
 * A base class with the functionality of an android view and a generic view/controller view
 */
public class BaseSurfaceView extends SurfaceView implements IView {

	private IController controller;

	public BaseSurfaceView(Context context) {
		super(context);
	}
	
	@Override
	public IController getController() {
		return controller;
	}

	@Override
	public void setController(IController controller) {
		this.controller = controller;
	}
}
