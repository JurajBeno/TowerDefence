package window;
//TODO center it, change TEXT FONT
import fri.shapesge.Image;
import fri.shapesge.Rectangle;
import fri.shapesge.Text;

public class StartMenuWindow {
    private final Rectangle backRound;
    private final Image firstButton;
    private final Text  firstText;
    private final Image secondButton;
    private final Text secondText;
    private final Image exitButton;
    private boolean isVisible;

    private final int xPos = 40;
    private final int yPos = 50;

    private final int width = 110;
    private final int height = 200;
    public StartMenuWindow() {
        this.backRound = new Rectangle(this.xPos, this.yPos);
        this.backRound.changeSize(this.width, this.height);
        this.backRound.changeColor("yellow");

        this.firstButton = new Image("assets\\startWindow\\start.png", 50, 60);
        this.firstText = new Text("NEW GAME", 50, 110);
        this.secondButton = new Image("assets\\startWindow\\load.png", 50, 110);
        this.secondText = new Text("RESUME\nPLAYED GAME", 50, 160);
        this.exitButton = new Image("assets\\startWindow\\exit.png", 50 , 200);
        this.setVisible();
    }

    public StartMenuOption click(int y, int x) {
        if (y > 60 && y < 95 && x > 50 && x < 150) {
            return StartMenuOption.STARTGAME;
        } else if (y > 110 && y < 145 && x > 50 && x < 150) {
            return StartMenuOption.LOADGAME;
        } else if (y > 200 && y < 235 && x > 50 && x < 150) {
            return StartMenuOption.EXIT;
        }
        return StartMenuOption.MISSCLICK;
    }

    public boolean isClickedOn(int y, int x) {
        return y > this.yPos && y < (this.yPos + this.height)
                && x > this.xPos && x < (this.xPos + this.width);
    }

    public void informUnsuccesfulLoad() {
        this.secondText.changeText("GAME CAN'T BE\nLOADED CREATE\nNEW GAME");
    }

    public void setVisible() {
        this.backRound.makeVisible();
        this.firstButton.makeVisible();
        this.firstText.makeVisible();
        this.secondButton.makeVisible();
        this.secondText.makeVisible();
        this.exitButton.makeVisible();
        this.isVisible = true;
    }

    public void setInvisible() {
        this.backRound.makeInvisible();
        this.firstButton.makeInvisible();
        this.firstText.makeInvisible();
        this.secondButton.makeInvisible();
        this.secondText.makeInvisible();
        this.exitButton.makeInvisible();
        this.isVisible = false;
    }

    public boolean isVisible() {
        return this.isVisible;
    }
}
