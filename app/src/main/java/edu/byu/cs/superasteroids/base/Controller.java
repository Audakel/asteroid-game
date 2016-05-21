package edu.byu.cs.superasteroids.base;

import edu.byu.cs.superasteroids.interfaces.IController;
import edu.byu.cs.superasteroids.interfaces.IView;

public class Controller implements IController {

	private IView view;
	
	public Controller(IView view) {
		this.view = view;
	}

	@Override
	public IView getView() {
		return view;
	}

	@Override
	public void setView(IView view) {
		this.view = view;
	}
	
	
}
