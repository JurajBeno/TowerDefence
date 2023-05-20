package emenies;


import fri.shapesge.Image;
import towers.TowerEffect;

import java.util.ArrayList;

/**
 * Class hold adn manages displayed representation,
 *  position and other information about enemy.
 */
public class Enemy {
    private final int moveNumber;
    private int y;
    private int x;
    private final int damage;
    private final HpBar hpBar;
    private final Image spirit;
    private int indexOfPath;
    private int moveIndex;
    private final ArrayList<TowerEffect> activeEffects;
    private boolean isSloved;


    /**
     * creates new enemy starting on x and y
     * @param imgPath path to image of enemy
     * @param x position
     * @param y postion
     * @param hp level
     * @param lastIndexOfPath lengt of path
     * @param moveNumber how many tiks it will tak to ake step
     * @param damage of enemy
     */
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
        this.activeEffects = new ArrayList<>();
    }

    /**
     * adds tower effects to enemy which it doesn't have
     */
    public void addTowerEffects(ArrayList<TowerEffect> effects) {
        effects.removeIf(this.activeEffects::contains);
        this.activeEffects.addAll(effects);
    }

    /**
     * get hit by effect which it has
     */
    public void dealWithEffects() {
        if (this.activeEffects.size() == 0) {
            this.isSloved = false;
        }

        ArrayList<Integer> allIndexes = new ArrayList<>();
        int deleteOnIndex = 0;
        for (TowerEffect activeEffect : this.activeEffects) {
            activeEffect.decrementEfectTicks();
            this.hpBar.lowerHp(activeEffect.getDamage());
            if (!this.isSloved) {
                this.moveIndex += activeEffect.getMoventSlowedTicks();
                this.isSloved = true;
            }
            if (activeEffect.getEfectTicks() <= 0) {
                allIndexes.add(deleteOnIndex);
            }
        }
        for (Integer index : allIndexes) {
            this.activeEffects.remove((int)index);
        }
    }


    /**
     * @return hp level of enemy
     */
    public int getHp() {
        return this.hpBar.getHp();
    }

    /**
     * @return damage that enemy has
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * moves enemy by y and x, useful for camera movement
     * @param y
     * @param x
     */
    public void move(int y, int x) {
        this.y += y;
        this.x += x;
        this.spirit.moveHorizontal(x);
        this.spirit.moveVertical(y);
        this.hpBar.move(y, x);
    }

    /**
     * make enemy move to given x and y position
     * @param y
     * @param x
     */
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

    /**
     * makes enemy invisible
     */
    public void die() {
        this.spirit.makeInvisible();
        this.hpBar.hide();
    }

    /**
     * @return index of node in path where enemy is positioned in moment
     */
    public int getIndexOfPath() {
        return this.indexOfPath;
    }

    /**
     * @return x position
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return y position
     */
    public int getY() {
        return this.y;
    }

    /**
     * resets move index after enemy made step
     */
    public void resetMoveIndex() {
        this.moveIndex = this.moveNumber;
    }

    /**
     * decrements move index to wait for next step
     */
    public void decrementMoveIndex() {
        this.moveIndex--;
    }

    /**
     * @return move index of enemy
     */
    public int getMoveIndex() {
        return this.moveIndex;
    }
}
