package mainPackage;


import board.Board;
import controls.GameControl;
import controls.TowerManager;
import controls.clickResults.ClickOnBoardResult;
import emenies.Wave;
import towers.DefenceTower;
import towers.TowerEffect;

import java.util.ArrayList;
import java.util.Random;

public class Game implements java.io.Serializable {
    private final Board board;
    private final Random random;
    private boolean spawning;
    private Wave wave;
    private int waveNumber;
    private TowerManager towerManager;
    private GameControl parent;
    public Game(Random random, GameControl parent) {
        this.random = random;
        this.parent = parent;
        this.board = new Board(this.random);
        this.spawning = false;
        this.waveNumber = 0;
        this.towerManager = new TowerManager();
    }

    public ClickOnBoardResult click(int y, int x) {
        return this.board.click(y, x);
    }

    public boolean getSpawning() {
        return this.spawning;
    }

    public void spawnEnemy() {
        if (!this.wave.spawnEnemy()) {
            this.spawning = false;
        }
    }

    public void moveWave() {
        if (this.wave == null) {
            return;
        }
        if (this.wave.getNumberOfAttackers() == 0 && !this.spawning) {
            this.wave = null;
        } else {
            this.wave.moveWave();
        }
    }

    public void hitMainTower(int damage) {
        //this.towerManager.hitMainTower(damage);
        this.parent.decreaseMainTowerHp(damage);
    }

    public void startWave() {
        double multipier = 1 + 0.1 * this.waveNumber;
        var wholePath = this.board.getWholePath();
        this.wave = new Wave(multipier, wholePath, this);
        this.spawning = true;
        this.waveNumber += 1;
    }

    public void move(int y, int x) {
        this.board.move(y, x);
        if (this.wave != null) {
            this.wave.move(y, x);
        }
    }

    public void placeTower(DefenceTower tower, int y, int x) {
        if (this.board.placeTower(tower, y, x)) {
            this.towerManager.addTower(tower);
        }
    }

    public ArrayList<TowerEffect> getEfectFromTower(int x, int y) {
        return  this.towerManager.attackIfCan(x, y);
    }
}
