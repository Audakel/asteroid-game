package edu.byu.cs.superasteroids.interfaces;

public interface IView {

    /**
     * Gets the view's controller
     * @return The view's controller
     */
	IController getController();

    /**
     * Sets the view's controller
     * @param controller The controller to set
     */
	void setController(IController controller);
}
