package controls;

import fri.shapesge.Rectangle;

public class MainHpBar {
    private final Rectangle backRound;
    private final Rectangle hpBar;
    private int hp;
    private final int originalHp;
    public MainHpBar(int hp) {
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

        this.hp = hp;
        this.originalHp = hp;
    }

    public void updateBar(int howMuch) {
        this.hp -= howMuch;
        this.hpBar.changeSize(46, this.hp * 496 / 100);
    }

    public void resetHp() {
        this.hp = this.originalHp;
    }

    public void makeVisible() {
        this.backRound.makeInvisible();
        this.hpBar.makeInvisible();

        this.backRound.makeVisible();
        this.hpBar.makeVisible();
    }
}
