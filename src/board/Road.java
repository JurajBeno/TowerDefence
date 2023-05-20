package board;


import fri.shapesge.Image;

/**
 * Handles displaying and moving Road node
 */
public class Road implements Node {

    private int y;
    private int x;
    private final Image img;

    /**
     * creates road node on y and x
     * @param y position
     * @param x position
     */
    public Road(int y, int x) {
        this.y = y;
        this.x = x;
        this.img = new Image("assets\\Tiles\\road0.png", x, y);
        this.img.makeVisible();
    }

    /**
     * @return x possition of node
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * @return y possiotion of node
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * moves road node by y and x values
     * @param y
     * @param x
     */
    @Override
    public void move(int y, int x) {
        this.y += y;
        this.x += x;
        this.img.moveHorizontal(x);
        this.img.moveVertical(y);
    }

    /**
     * hides Road node
     */
    @Override
    public void makeInvisible() {
        this.img.makeInvisible();
    }
}
