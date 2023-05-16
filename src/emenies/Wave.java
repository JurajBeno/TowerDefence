package emenies;

import board.Road;

import java.util.ArrayList;


//TODO fix so enemies walk on path
public class Wave {
    private int numberOfLight;
    private final int y;
    private final int x;
    private int numberOfHeavy;
    private final ArrayList<Enemy> attackers;
    private final ArrayList<Road> path;
    public Wave(double roundMultiplier, ArrayList<Road> path) {
        this.y = path.get(path.size() - 1).getY();
        this.x = path.get(path.size() - 1).getX();
        this.numberOfHeavy = (int)(roundMultiplier * 1);
        this.numberOfLight = (int)(roundMultiplier * 2);
        this.attackers = new ArrayList<>();
        this.path = path;
    }

    public int getNumberOfAttackers() {
        return this.attackers.size();
    }

    //TODO FIX THAT ENDING OF THE PATH
    public void moveWave() {
        ArrayList<Integer> deleteIndexes = new ArrayList<>();
        int index = 0;
        for (Enemy enemy : this.attackers) {
            if (enemy.getIndexOfPath() < 0) {
                enemy.giveDamage();
            } else if (enemy.getHp() == 0) {
                enemy.die();
                deleteIndexes.add(index);
            } else if (enemy.getMoveIndex() == 0) {
                enemy.walk(
                        this.path.get(enemy.getIndexOfPath()).getY() + 16,
                        this.path.get(enemy.getIndexOfPath()).getX() + 16);
                enemy.resetMoveIndex();
            }
            enemy.decrementMoveIndex();
            index++;
        }
        for (Integer deleteIndex : deleteIndexes) {
            this.attackers.remove((int)deleteIndex);
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
