package board;

import fri.shapesge.Rectangle;
import fri.shapesge.Text;

/**
 * displays button to generate new board block and start next wave
 */
public class GenerateNewBlockButton {  //TODO to spawn button in proper direction of path
    private int y;
    private int x;
    private final Rectangle rectangle;
    private boolean isVisible;
    private final Text textField;

    /**
     * creates "start wave" button on y and x position
     * @param y
     * @param x
     */
    public GenerateNewBlockButton(int y, int x) {
        this.y = y;
        this.x = x;
        this.rectangle = new Rectangle(x, y);
        this.rectangle.changeSize(64, 64);
        this.rectangle.changeColor("yellow");
        this.rectangle.makeVisible();
        this.textField = new Text("Start wave", x + 6, y + 30);
        this.textField.makeVisible();
        this.isVisible = true;

    }

    /**
     * @return x position
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return y position
     */
    public int getY() {
        return this.y;
    }

    /**
     * moves button by y and x best for came movement
     * @param y value
     * @param x value
     */
    public void move(int y, int x) {
        this.textField.moveVertical(y);
        this.textField.moveHorizontal(x);
        this.rectangle.moveVertical(y);
        this.rectangle.moveHorizontal(x);
        this.x += x;
        this.y += y;
    }

    /**
     * makes invisible whole button
     */
    public void makeInvisible() {
        this.rectangle.makeInvisible();
        this.textField.makeInvisible();
        this.isVisible = false;
    }

    /**
     * checks if button was clicked
     * @param y position
     * @param x position
     * @return boolean value true if button was clicked
     */
    public boolean click(int y, int x) {
        return y >= this.y && y <= this.y + 64 &&
                x >= this.x && x <= this.x + 64 && this.isVisible;
    }
}
