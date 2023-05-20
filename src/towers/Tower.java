package towers;

import fri.shapesge.Image;

public class Tower implements java.io.Serializable {
    private int xPos;
    private int yPos;
    private String name;
    private Image img;
    private String imgPath;
    public Tower(String name, String imgPath) {
        this.name = name;
        this.imgPath = imgPath;
    }

    public void setTowerPos(int y, int x) {
        this.xPos = x;
        this.yPos = y;
        this.img = new Image(this.imgPath, x, y - 32);
        this.img.makeVisible();
    }

    public void moveBy(int y, int x) {
        this.img.moveVertical(y);
        this.img.moveHorizontal(x);
        this.xPos += x;
        this.yPos += y;
    }

    public int getXPos() {
        return this.xPos;
    }

    public int getYPos() {
        return this.yPos;
    }

    public String getName() {
        return this.name;
    }

    public void makeInvisible() {
        this.img.makeInvisible();
    }
}
