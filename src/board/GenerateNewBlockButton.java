package board;

import fri.shapesge.Rectangle;

public class GenerateNewBlockButton {
    private int y;
    private int x;
    private final Rectangle rectangle;
    private boolean isVisible;

    public GenerateNewBlockButton(int y, int x) {
        this.y = y;
        this.x = x;
        this.rectangle = new Rectangle(x, y);
        this.rectangle.changeSize(64, 64);
        this.rectangle.changeColor("yellow");
        this.rectangle.makeVisible();
        this.isVisible = true;
    }
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void move(int y, int x) {
        this.rectangle.moveVertical(y);
        this.rectangle.moveHorizontal(x);
        this.x += x;
        this.y += y;
    }

    public void makeInvisible() {
        this.rectangle.makeInvisible();
        this.isVisible = false;
    }

    public boolean click(int y, int x) {
        return y >= this.y && y <= this.y + 64 &&
                x >= this.x && x <= this.x + 64 && this.isVisible;
    }
}
