package controls.windows;

import fri.shapesge.Image;
import fri.shapesge.Rectangle;
import fri.shapesge.Text;

/**
 * Displays and check clicking of buttons in menu
 */
public class InGameMenu {

    private final Text message;
    private Image leave;
    private Image restart;
    private Rectangle backRound;

    public InGameMenu(String messageOfGame) {
        this.backRound = new Rectangle(405, 200);
        this.backRound.changeColor("yellow");
        this.backRound.changeSize(600, 500);
        this.backRound.makeVisible();

        this.message = new Text(messageOfGame, 630, 450);
        this.message.makeVisible();

        this.leave = new Image("assets/inGameMenu/exit.png", 660, 650);
        this.leave.makeVisible();

        this.restart = new Image("assets/inGameMenu/restart.png", 660, 600);
        this.restart.makeVisible();
    }

    /**
     * Hides all displayed elements
     */
    public void makeInvisible() {
        this.backRound.makeInvisible();
        this.message.makeInvisible();
        this.leave.makeInvisible();
        this.restart.makeInvisible();
    }

    /**
     * handles clicking of buttons in menu
     * @param x position
     * @param y position
     * @return result of clicking
     */
    public InGameMenuClickResult click(int x, int y) {
        if (x > 660 && x < 750) {
            if (y > 600 && y < 635) {
                return InGameMenuClickResult.RESTART;
            } else if (y > 650 && y < 685) {
                return InGameMenuClickResult.EXIT;
            }
        }
        return InGameMenuClickResult.MISS_CLICK;
    }

}
