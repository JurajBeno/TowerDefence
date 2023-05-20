package emenies;

import fri.shapesge.Rectangle;

/**
 * Manages and holds information about hp and its representation
 */
public class HpBar {
    private int hp;
    private int maxHp;
    private Rectangle outer;
    private Rectangle inner;


    /**
     * Creates hp bar of enemy on x and y
     * @param x position
     * @param y position
     * @param hp level
     */
    public HpBar(int x, int y, int hp) {
        this.outer = new Rectangle(x, y - 5);
        this.outer.changeSize(15, 4);
        this.outer.changeColor("black");
        this.outer.makeVisible();
        this.inner = new Rectangle(x + 1, y + 1 - 5);
        this.inner.changeColor("green");
        this.inner.changeSize(13, 2);
        this.inner.makeVisible();
        this.hp = hp;
        this.maxHp = hp;
    }

    /**
     * moves hp bar by y and x, useful for camera movement
     * @param y
     * @param x
     */
    public void move(int y, int x) {
        this.outer.moveHorizontal(x);
        this.inner.moveHorizontal(x);
        this.outer.moveVertical(y);
        this.inner.moveVertical(y);
    }

    /**
     * hides hp bar representation
     */
    public void hide() {
        this.outer.makeInvisible();
        this.inner.makeInvisible();
    }

    /**
     * lovers level of hp by damage
     * @param damage
     */
    public void lowerHp(int damage) {
        this.hp -= damage;
        this.inner.changeSize( this.hp * 13 / this.maxHp, 2);
    }

    /**
     * @return level of hp
     */
    public int getHp() {
        return this.hp;
    }
}
