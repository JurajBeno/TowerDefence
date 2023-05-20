package mainPackage;


import board.Board;
import controls.TowerManager;
import controls.ClickOnBoardResult;
import emenies.Wave;
import towers.DefenceTower;
import towers.MainTower;
import towers.TowerEffect;

import java.util.ArrayList;
import java.util.Random;

/**
 * class holds and manages all game actions and logic
 */
public class Game {
    private final Board board;
    private final Random random;
    private boolean spawning;
    private Wave wave;
    private int waveNumber;
    private TowerManager towerManager;

    /**
     * create game instance
     * @param random to make randomness in game possible and consistent
     */
    public Game(Random random) {
        this.random = random;
        MainTower base = new MainTower( "Base", "assets\\towers\\base.png", 5);
        this.board = new Board(this.random, base);
        this.spawning = false;
        this.waveNumber = 0;
        this.towerManager = new TowerManager(base);
    }

    /**
     * makes invisible all displayed game elements
     */
    public void makeInvisible() {
        this.board.makeInvisible();
        this.wave.makeInvisible();
    }

    /**
     * forwards every click to board
     * @param y position
     * @param x position
     * @return result of clicking
     */
    public ClickOnBoardResult click(int y, int x) {
        return this.board.click(y, x);
    }

    /**
     * @return if enemies are being spawned
     */
    public boolean getSpawning() {
        return this.spawning;
    }

    /**
     * calls method to spawn enemy
     */
    public void spawnEnemy() {
        if (!this.wave.spawnEnemy()) {
            this.spawning = false;
        }
    }

    /**
     * decides if move wave or prepares for starting wave
     */
    public void tikWave() {
        if (this.wave == null) {
            return;
        }
        if (this.wave.getNumberOfAttackers() == 0 && !this.spawning) {
            this.board.setUpGenNewButton();
            this.wave = null;
        } else {
            this.wave.tikWave();
        }
    }

    /**
     * damages main tower
     * @param damage to decrease
     */
    public void hitMainTower(int damage) {
        this.towerManager.decreaseHpOfMain(damage);
    }

    /**
     * initialize and starts wave
     */
    public void startWave() {
        double multipier = 1 + 0.1 * this.waveNumber;
        var wholePath = this.board.getWholePath();
        this.wave = new Wave(multipier, wholePath, this);
        this.spawning = true;
        this.waveNumber += 1;
    }

    /**
     * moves all displayed elements of game
     * useful for camera movement
     * @param y position
     * @param x position
     */
    public void move(int y, int x) {
        this.board.move(y, x);
        if (this.wave != null) {
            this.wave.move(y, x);
        }
    }

    /**
     * calls method to place tower
     * @param tower defence tower
     * @param y position
     * @param x position
     */
    public void placeTower(DefenceTower tower, int y, int x) {
        if (this.board.placeTower(tower, y, x)) {
            this.towerManager.addTower(tower);
        }
    }

    /**
     * @return hp of main tower
     */
    public int getMainHp() {
        return this.towerManager.getHpOfMain();
    }

    /**
     * @return maximum value of main hp
     */
    public int getMaxMainHp() {
        return this.towerManager.getMaxMainHp();
    }

    /**
     * @param x position
     * @param y position
     * @return arrayList of effects from tower on give position
     */
    public ArrayList<TowerEffect> getEfectFromTower(int x, int y) {
        return this.towerManager.attackIfCan(x, y);
    }
}
