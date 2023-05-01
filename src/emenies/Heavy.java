package emenies;

import fri.shapesge.Image;

public class Heavy implements Attacker, java.io.Serializable {
    private int y;
    private int x;
    private final HpBar hpBar;
    private final Image spirit;
    private int indexOfPath;


    public Heavy(int x, int y, int hp, int lastIndexOfPath) {
        this.y = y;
        this.x = x;
        this.hpBar = new HpBar(x, y, hp);
        this.spirit = new Image("assets\\Enemies\\heavy.png", x, y);
        this.spirit.makeVisible();
        this.indexOfPath = lastIndexOfPath;


    }

    @Override
    public int getHp() {
        return this.hpBar.getHp();
    }

    @Override
    public int giveDamage() {
        return 8;
    }

    @Override
    public void move(int y, int x) {
        this.spirit.moveHorizontal(x);
        this.spirit.moveVertical(y);
        this.hpBar.move(y, x);
    }

    @Override
    public void walk(int y, int x) {
        int yMove = y - this.y;
        int xMove = x - this.x;

        this.x += xMove;
        this.y += yMove;

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
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
