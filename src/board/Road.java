package board;

import controls.ClickResults.ClickOnBoardResult;
import controls.ClickResults.MissClick;
import fri.shapesge.Image;

public class Road implements Node, java.io.Serializable {

    private int y;
    private int x;
    private final Image img;

    public Road(int y, int x) {
        this.y = y;
        this.x = x;
        this.img = new Image("assets\\Tiles\\road0.png", x, y);
        this.img.makeVisible();
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public ClickOnBoardResult clicked() {
        return new MissClick();
    }

    @Override
    public void move(int y, int x) {
        this.y += y;
        this.x += x;
        this.img.moveHorizontal(x);
        this.img.moveVertical(y);
    }

    @Override
    public void makeInvisible() {
        this.img.makeInvisible();
    }
}
