package emenies;

import fri.shapesge.Rectangle;

public class HpBar implements java.io.Serializable {
    private int hp;
    private Rectangle outer;
    private Rectangle inner;

    public HpBar(int x, int y, int hp) {
        this.outer = new Rectangle(x, y - 5);
        this.outer.changeSize(15,4);
        this.outer.changeColor("black");
        this.outer.makeVisible();
        this.inner = new Rectangle(x + 1, y + 1 - 5);
        this.inner.changeColor("green");
        this.inner.changeSize(13, 2);
        this.inner.makeVisible();
        this.hp = hp;
    }

    public void move(int y, int x) {
        this.outer.moveHorizontal(x);
        this.inner.moveHorizontal(x);
        this.outer.moveVertical(y);
        this.inner.moveVertical(y);
    }

    public void hide() {
        this.outer.makeInvisible();
        this.inner.makeInvisible();
    }

    public void damaged(int damage)  {
        this.hp -= damage;
    }

    public int getHp() {
        return this.hp;
    }
}