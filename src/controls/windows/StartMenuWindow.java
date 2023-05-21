package controls.windows;
import fri.shapesge.Image;
import fri.shapesge.Rectangle;
import fri.shapesge.Text;

/**
 * dispays and manages start menu
 */
public class StartMenuWindow {
    private final Rectangle backRound;
    private final Image firstButton;
    private final Text  firstText;

    private final Image exitButton;
    private boolean isVisible;

    private final int xPos = 678;
    private final int yPos = 240;

    private final int width = 110;
    private final int height = 180;
    public StartMenuWindow() {
        this.backRound = new Rectangle(this.xPos, this.yPos);
        this.backRound.changeSize(this.width, this.height);
        this.backRound.changeColor("yellow");

        this.firstButton = new Image("assets\\startWindow\\start.png", 690, 260);
        this.firstText = new Text("NEW GAME", 690, 310);

        this.exitButton = new Image("assets\\startWindow\\exit.png", 690, 350);
        this.setVisible();
    }

    /**
     *
     * @param y position
     * @param x position
     * @return what button was clicked from x and y positions
     */
    public StartMenuOption click(int y, int x) {
        if (y > 260 && y < 295 && x > 690 && x < 790) {
            return StartMenuOption.STARTGAME;
        } else if (y > 350 && y < 385 && x > 690 && x < 790) {
            return StartMenuOption.EXIT;
        }
        return StartMenuOption.MISSCLICK;
    }

    /**
     * @param y position
     * @param x position
     * @return boolean value true if y and x are in bounds of menu
     */
    public boolean isClickedOn(int y, int x) {
        return y > this.yPos && y < (this.yPos + this.height)
                && x > this.xPos && x < (this.xPos + this.width);
    }

    /**
     * makes whole menu visible
     */
    public void setVisible() {
        this.backRound.makeVisible();
        this.firstButton.makeVisible();
        this.firstText.makeVisible();

        this.exitButton.makeVisible();
        this.isVisible = true;
    }

    /**
     * hides whole menu
     */
    public void setInvisible() {
        this.backRound.makeInvisible();
        this.firstButton.makeInvisible();
        this.firstText.makeInvisible();

        this.exitButton.makeInvisible();
        this.isVisible = false;
    }

    /**
     * @return boolean value true if menu is visible
     */
    public boolean isVisible() {
        return this.isVisible;
    }
}
