package emenies;

import board.Road;
import mainPackage.Game;

import java.util.ArrayList;


public class Wave {
    private int numberOfLight;
    private final int y;
    private final int x;
    private int numberOfHeavy;
    private final ArrayList<Enemy> attackers;
    private final ArrayList<Road> path;
    private final Game parent;
    public Wave(double roundMultiplier, ArrayList<Road> path, Game parent) {
        this.y = path.get(path.size() - 1).getY();
        this.x = path.get(path.size() - 1).getX();
        this.numberOfHeavy = (int)(roundMultiplier * 0);
        this.numberOfLight = (int)(roundMultiplier * 2);
        this.attackers = new ArrayList<>();
        this.path = path;
        this.parent = parent;
    }

    public void makeInvisible() {
        for (Enemy attacker : this.attackers) {
            attacker.die();
        }
    }

    public int getNumberOfAttackers() {
        return this.attackers.size();
    }

    public void moveWave() {
        ArrayList<Integer> deleteOnIndexes = new ArrayList<>();
        int index = 0;
        for (Enemy enemy : this.attackers) {
            if (enemy.getIndexOfPath() < 0) {
                this.parent.hitMainTower(enemy.getDamage());
                enemy.die();
                deleteOnIndexes.add(index);
            } else if (enemy.getHp() <= 0) {
                enemy.die();
                deleteOnIndexes.add(index);
            } else if (enemy.getMoveIndex() == 0) {
                enemy.walk(
                        this.path.get(enemy.getIndexOfPath()).getY() + 16,
                        this.path.get(enemy.getIndexOfPath()).getX() + 16);
                enemy.resetMoveIndex();
            }
            enemy.decrementMoveIndex();
            enemy.addTowerEffects(this.parent.getEfectFromTower(enemy.getX(), enemy.getY()));
            enemy.dealWithEffects();
            index++;
        }
        for (Integer deleteIndex : deleteOnIndexes) {
            if (this.attackers.size() > 0) {
                this.attackers.remove((int)deleteIndex);
            }
        }
    }

    public void move(int y, int x) {
        for (Enemy enemy : this.attackers) {
            enemy.move(y, x);
        }
    }

    public boolean spawnEnemy() {
        if (this.numberOfHeavy > 0) {
            this.attackers.add(new Enemy("assets\\Enemies\\heavy.png",
                    this.x + 16, this.y + 16, 100,
                    this.path.size() - 1, 2, 8));
            this.numberOfHeavy--;
            return true;
        } else if (this.numberOfLight > 0) {
            this.attackers.add(new Enemy("assets\\Enemies\\light.png",
                    this.x + 16, this.y + 16, 35,
                    this.path.size() - 1, 1, 2));
            this.numberOfLight--;
            return true;
        }
        return false;
    }
}
