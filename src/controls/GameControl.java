package controls;

import controls.clickResults.ClickOnBoardResult;
import controls.clickResults.StartWaveResult;
import fri.shapesge.Manager;
import mainPackage.Game;
import towers.BalistaTower;
import towers.DefenceTower;
import towers.FireTower;
import towers.FreezingTower;
import towers.PoisonTower;
import window.StartMenuWindow;
import window.StartMenuOption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

//TODO think out how will saving and loading games work

public class GameControl implements java.io.Serializable {
    private Game game;
    private final StartMenuWindow startGameWindow;
    private final Random random;
    private UserInterfaceMenu uim;
    private int cameraSpeed;
    private boolean placingTower;
    private DefenceTower towerToPlace;

    public GameControl(Random random) {
        this.random = random;
        Manager manager = new Manager();
        manager.manageObject(this);
        this.startGameWindow = new StartMenuWindow();
        this.game = null;
        this.cameraSpeed = 10;
        this.placingTower = false;
    }

    public void setCameraSpeed(int speed) {
        this.cameraSpeed = speed;
    }

    public void clickOnPosition(int x, int y) {
        if (this.uim != null) {
            TowerSelected towerSelected = this.uim.click(y, x);
            if (this.placingTower && towerSelected == TowerSelected.MISSCLICK) {
                this.game.placeTower(this.towerToPlace, y, x);
                this.uim.deselect();
                this.placingTower = false;
                this.towerToPlace = null;

            }
        }

        if (this.startGameWindow.isVisible() && this.startGameWindow.isClickedOn(y, x)) {
            StartMenuOption option = this.startGameWindow.click(y, x);
            if (option != StartMenuOption.MISSCLICK) {
                this.handleMenuChoice(option);
                return;
            }
        } else {
            ClickOnBoardResult clickResult = this.game.click(y, x);
            if (clickResult instanceof StartWaveResult startWave) {
                this.game.startWave();
                this.uim.makeVisible();
                return;
            }
        }
        if (this.uim != null) {
            this.uim.deselect();
        }
        TowerSelected towerSelected = this.uim.click(y, x);
        if (towerSelected != TowerSelected.MISSCLICK) {
            this.placingTower = true;
            this.chooseTowerToPlace(towerSelected);
        }
    }

    private void chooseTowerToPlace(TowerSelected towerSelected) {
        switch (towerSelected) {
            case POISON -> {
                this.towerToPlace = new PoisonTower();
            }
            case FREEZING -> {
                this.towerToPlace = new FreezingTower();
            }
            case FIRE -> {
                this.towerToPlace = new FireTower();
            }
            case BALISTA -> {
                this.towerToPlace = new BalistaTower();
            }
            default -> {
                this.towerToPlace = null;
            }
        }
    }

    public void spawnEnemy() {
        if (this.game != null) {
            this.game.moveWave();
            if (this.game.getSpawning()) {
                this.game.spawnEnemy();
            }
        }
    }

    private void handleMenuChoice(StartMenuOption option) {
        switch (option) {
            case STARTGAME -> {
                this.game = new Game(this.random, this);
                this.startGameWindow.setInvisible();
                this.uim = new UserInterfaceMenu(100);
            }
            case LOADGAME -> {
                if (!this.loadGame()) {
                    this.startGameWindow.informUnsuccesfulLoad();
                } else {
                    this.startGameWindow.setInvisible();
                    this.uim = new UserInterfaceMenu(100);
                }
            }
            case EXIT -> System.exit(0);
        }
    }

    private boolean saveGame() {
        try {
            //todo will be single save game, we'll see
            FileOutputStream fos = new FileOutputStream("gameSaves\\game.sav");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.game);
            oos.flush();
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean loadGame() {
        System.out.println("loading not yet added");
        try {
            FileInputStream fis = new FileInputStream("gameSaves\\game.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.game = (Game)ois.readObject();
            ois.close();
            return true;
        } catch (ClassNotFoundException | IOException e) {
            return false;
        }
    }

    public void moveUp() {
        if (this.game != null) {
            this.game.move(this.cameraSpeed, 0);
            this.uim.makeVisible();
        }
    }

    public void moveDown() {
        if (this.game != null) {
            this.game.move(-this.cameraSpeed, 0);
            this.uim.makeVisible();

        }
    }

    public void moveRight() {
        if (this.game != null) {
            this.game.move(0, -this.cameraSpeed);
            this.uim.makeVisible();

        }
    }

    public void moveLeft() {
        if (this.game != null) {
            this.game.move(0, this.cameraSpeed);
            this.uim.makeVisible();

        }
    }

    public void decreaseMainTowerHp(int damage) {
        this.uim.updateHpBar(damage);
    }
}
