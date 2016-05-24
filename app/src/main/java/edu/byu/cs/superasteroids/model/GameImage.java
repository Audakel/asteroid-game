package edu.byu.cs.superasteroids.model;

/**
 * Created by audakel on 5/23/16.
 */

/**
 * represents an image for the game
 */
public class GameImage {
    private int width = 0;
    private int height = 0;
    private String filePath;

    /**
     *
     * @param width of image
     * @param height of image
     * @param filePath relative path to asset
     */
    public GameImage(int width, int height, String filePath) {
        this.width = width;
        this.height = height;
        this.filePath = filePath;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFilePath() {
        return (filePath == null) ? "" : filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
