package emenies;


import fri.shapesge.Image;

public class Enemy {
    private final int moveNumber;
    private int y;
    private int x;
    private final int damage;
    private final HpBar hpBar;
    private final Image spirit;
    private int indexOfPath;
    private int moveIndex;

    public Enemy(String imgPath, int x, int y, int hp,
                 int lastIndexOfPath, int moveNumber, int damage) {
        this.y = y;
        this.x = x;
        this.damage = damage;
        this.hpBar = new HpBar(x, y, hp);
        this.spirit = new Image(imgPath, x, y);
        this.spirit.makeVisible();
        this.indexOfPath = lastIndexOfPath;
        this.moveNumber = moveNumber;
        this.moveIndex = moveNumber;
    }


    public int getHp() {
        return this.hpBar.getHp();
    }

    public int giveDamage() {
        return this.damage;
    }

    public void move(int y, int x) {
        this.y += y;
        this.x += x;
        this.spirit.moveHorizontal(x);
        this.spirit.moveVertical(y);
        this.hpBar.move(y, x);
    }

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

    public void die() {
        this.spirit.makeInvisible();
    }

    public int getIndexOfPath() {
        return this.indexOfPath;
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void resetMoveIndex() {
        this.moveIndex = this.moveNumber;
    }

    public void decrementMoveIndex() {
        this.moveIndex--;
    }

    public int getMoveIndex() {
        return this.moveIndex;
    }

    public void getDamage(int damage) {
        this.hpBar.damaged(damage);
    }
}
