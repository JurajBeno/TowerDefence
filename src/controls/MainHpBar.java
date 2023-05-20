package controls;

import fri.shapesge.Rectangle;

public class MainHpBar {
    private final Rectangle backRound;
    private final Rectangle hpBar;
    public MainHpBar() {
        this.backRound = new Rectangle(50, 250);
        this.backRound.changeColor("black");
        this.backRound.changeSize(50, 500);
        this.backRound.makeVisible();

        this.hpBar = new Rectangle(50, 250);
        this.hpBar.changeSize(46, 496);
        this.hpBar.moveHorizontal(2);
        this.hpBar.moveVertical(2);
        this.hpBar.changeColor("green");
        this.hpBar.makeVisible();
    }

    public void updateBar(int hp, int maxHp) {
        this.hpBar.changeSize(46, hp * 496 / maxHp);
    }

    public void makeInvisible() {
        this.backRound.makeInvisible();
        this.hpBar.makeInvisible();
    }

    public void makeVisible() {
        this.makeInvisible();
        this.backRound.makeVisible();
        this.hpBar.makeVisible();
    }
}
