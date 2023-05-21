package emenies;

import board.Road;
import mainPackage.Game;

import java.util.ArrayList;

/**
 * Class tracks and controls movement and actions of enemies
 */
public class Wave {
    private int numberOfLight;
    private final int y;
    private final int x;
    private int numberOfHeavy;
    private final ArrayList<Enemy> attackers;
    private final ArrayList<Road> path;
    private final Game parent;

    /**
     * create wave instance
     * @param roundMultiplier to spawn more enemies
     * @param path from start to main tower
     * @param parent to communicate with game
     */
    public Wave(double roundMultiplier, ArrayList<Road> path, Game parent) {
        this.y = path.get(path.size() - 1).getY();
        this.x = path.get(path.size() - 1).getX();
        this.numberOfHeavy = (int)(roundMultiplier * 1);
        this.numberOfLight = (int)(roundMultiplier * 2);
        this.attackers = new ArrayList<>();
        this.path = path;
        this.parent = parent;
    }

    /**
     * kills all enemies in wave
     */
    public void makeInvisible() {
        for (Enemy attacker : this.attackers) {
            attacker.die();
        }
    }

    /**
     * @return number of enemies
     */
    public int getNumberOfAttackers() {
        return this.attackers.size();
    }

    /**
     * manages all actions of enemies
     */
    public void tikWave() {
        for (Enemy enemy : this.attackers) {
            if (enemy.getIndexOfPath() < 0) {
                this.parent.hitMainTower(enemy.getDamage());
                enemy.die();
            } else if (enemy.getHp() <= 0) {
                enemy.die();
            } else if (enemy.getMoveIndex() == 0) {
                enemy.walk(
                        this.path.get(enemy.getIndexOfPath()).getY() + 16,
                        this.path.get(enemy.getIndexOfPath()).getX() + 16);
                enemy.resetMoveIndex();
            }
            enemy.decrementMoveIndex();
            enemy.addTowerEffects(this.parent.getEfectFromTower(enemy.getX(), enemy.getY()));
            enemy.dealWithEffects();
        }
    }

    /**
     * moves every displayed enemy, useful for camera movement
     * @param y
     * @param x
     */
    public void move(int y, int x) {
        for (Enemy enemy : this.attackers) {
            enemy.move(y, x);
        }
    }

    /**
     * spawns enemy every call
     * @return true if enemy was spawned
     */
    public boolean spawnEnemy() {
        if (this.numberOfHeavy > 0) {
            this.attackers.add(new Enemy("assets\\Enemies\\heavy.png",
                    this.x + 16, this.y + 16, 120,
                    this.path.size() - 1, 2, 8));
            this.numberOfHeavy--;
            return true;
        } else if (this.numberOfLight > 0) {
            this.attackers.add(new Enemy("assets\\Enemies\\light.png",
                    this.x + 16, this.y + 16, 60,
                    this.path.size() - 1, 1, 2));
            this.numberOfLight--;
            return true;
        }
        return false;
    }
}
