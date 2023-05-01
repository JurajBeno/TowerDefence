package towers;

import fri.shapesge.Image;

public class Tower implements java.io.Serializable {
    private int xPos;
    private int yPos;
    private String name;
    private Image img;
    public Tower(int xPos, int yPos, String name, String imgPath) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.name = name;
        this.img = new Image(imgPath, xPos, yPos - 32);
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
}
