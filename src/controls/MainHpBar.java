package controls;

import fri.shapesge.Rectangle;

public class MainHpBar {
    private Rectangle backRound;
    private Rectangle hpBar;
    private int hp;
    private int originalHp;
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

    public void resetBar() {
        this.hp = this.originalHp;
    }

    public void makeVisible() {
        this.backRound.makeVisible();
        this.hpBar.makeVisible();
    }
}
