package edu.byu.cs.superasteroids.interfaces;

import edu.byu.cs.superasteroids.helper.content.ContentManager;

/**
 * The ShipBuildingView uses this interface to call the functions found below.
 */
public interface IShipBuildingController extends IController, IGameDelegate {

    /**
     * The ship building view calls this function when a part selection view is loaded. This function
     * should be used to configure the part selection view. Example: Set the arrows for the view in
     * this function.
     * @param partView
     */
    void onViewLoaded(IShipBuildingView.PartSelectionView partView);

    /**
     * The ShipBuildingView calls this function as it is created. Load ship building content in this function.
     * @param content An instance of the content manager. This should be used to load images and sound.
     */
	void loadContent(ContentManager content);

    /**
     * The ShipBuildingView calls this function when the user makes a swipe/fling motion in the
     * screen. Respond to the user's swipe/fling motion in this function.
     * @param direction The direction of the swipe/fling.
     */
	void onSlideView(IShipBuildingView.ViewDirection direction);

    /**
     * The part selection fragments call this function when a part is selected from the parts list. Respond
     * to the part selection in this function.
     * @param index The list index of the selected part.
     */
	void onPartSelected(int index);

    /**
     * The ShipBuildingView calls this function is called when the start game button is pressed.
     */
	void onStartGamePressed();

    /**
     * The ShipBuildingView calls this function when ship building has resumed. Reset the Camera and
     * the ship position as needed when this is called.
     */
    void onResume();
}
