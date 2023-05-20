package board;

import fri.shapesge.Image;
import towers.Tower;

/**
 * class handles displaying and managing land
 */
public class Land implements Node {
    private int y;
    private int x;
    private final Image img;
    private Tower tower;

    /**
     * creates land node on y and x
     * @param y position
     * @param x position
     */
    public Land(int y, int x) {
        this.y = y;
        this.x = x;
        this.img = new Image(("assets\\Tiles\\miniLand0.png"), x, y);
        this.img.makeVisible();
    }

    /**
     * if there is no tower on given place new is placed
     * @param tower
     * @return true if tower has been placed
     */
    public boolean setTower(Tower tower) {
        if (this.tower == null) {
            this.tower = tower;
            this.tower.setTowerPos(this.y, this.x);
            return true;
        }
        return false;
    }


    /**
     * @return x position of land
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * @return y position of land
     */
    @Override
    public int getY() {
        return this.y;
    }


    /**
     * move tower and land by given y and x values
     * @param y
     * @param x
     */
    @Override
    public void move(int y, int x) {
        this.x += x;
        this.y += y;
        this.img.moveHorizontal(x);
        this.img.moveVertical(y);
        if (this.tower != null) {
            this.tower.moveBy(y, x);
        }
    }

    /**
     * hides tower and land
     */
    @Override
    public void makeInvisible() {
        if (this.tower != null) {
            this.tower.makeInvisible();
        }
        this.img.makeInvisible();
    }
}
