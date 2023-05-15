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
        this.backRound.makeVisible();
        this.hpBar = new Rectangle(48, 248);
        this.hpBar.changeColor("green");
        this.hpBar.makeVisible();
        this.hp = hp;
        this.originalHp = hp;
    }

    public void updateBar(int howMuch) {
        this.hp -= howMuch;
    }

    public void resetBar() {
        this.hp = this.originalHp;
    }
}
