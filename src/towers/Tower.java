package towers;

import fri.shapesge.Image;

/**
 * Holds information on tower and manages it
 */
public class Tower {
    private int xPos;
    private int yPos;
    private String name;
    private Image img;
    private String imgPath;

    /**
     * Creates tower prepared to be placed
     * @param name
     * @param imgPath path to file with image
     */
    public Tower(String name, String imgPath) {
        this.name = name;
        this.imgPath = imgPath;
    }

    /**
     * set tower position
     * @param y position
     * @param x position
     */
    public void setTowerPos(int y, int x) {
        this.xPos = x;
        this.yPos = y;
        this.img = new Image(this.imgPath, x, y - 32);
        this.img.makeVisible();
    }

    /**
     * moves tower by x and y, useful for camera movement
     * @param y
     * @param x
     */
    public void moveBy(int y, int x) {
        this.img.moveVertical(y);
        this.img.moveHorizontal(x);
        this.xPos += x;
        this.yPos += y;
    }

    /**
     * @return x position of tower
     */
    public int getXPos() {
        return this.xPos;
    }

    /**
     * @return y position of tower
     */
    public int getYPos() {
        return this.yPos;
    }

    /**
     * hides displayed tower
     */
    public void makeInvisible() {
        this.img.makeInvisible();
    }
}
