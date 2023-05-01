package emenies;

import fri.shapesge.Image;

public class Light implements Attacker, java.io.Serializable {
    private int y;
    private int x;

    private final HpBar hpBar;
    private final Image spirit;
    private int indexOfPath;


    public Light(int x, int y, int hp, int lastIndexOfPath) {
        this.y = y;
        this.x = x;
        this.hpBar = new HpBar(x, y, hp);
        this.spirit = new Image("assets\\Enemies\\light.png", x - 10, y);
        this.spirit.makeVisible();
        this.indexOfPath = lastIndexOfPath;
    }

    @Override
    public int getHp() {
        return this.hpBar.getHp();
    }

    @Override
    public int giveDamage() {
        return 4;
    }

    @Override
    public void walk(int y, int x) {
        int yMove = y - this.y;
        int xMove = x - this.x;

        this.x = x;
        this.y = y;

        this.spirit.moveVertical(yMove);
        this.spirit.moveHorizontal(xMove);
        this.hpBar.move(yMove, xMove);
        this.indexOfPath--;
    }

    @Override
    public void die() {
        this.spirit.makeInvisible();
    }

    @Override
    public int getIndexOfPath() {
        return this.indexOfPath;
    }

    @Override
    public void move(int y, int x) {
        this.y += y;
        this.x += x;
        this.spirit.moveHorizontal(x);
        this.spirit.moveVertical(y);
        this.hpBar.move(y, x);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
