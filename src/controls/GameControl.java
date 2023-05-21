package controls;

import controls.userInterface.TowerSelected;
import controls.userInterface.UserInterfaceMenu;
import controls.windows.InGameMenu;
import controls.windows.InGameMenuClickResult;
import fri.shapesge.Manager;
import mainPackage.Game;
import towers.BalistaTower;
import towers.DefenceTower;
import towers.FireTower;
import towers.FreezingTower;
import towers.PoisonTower;
import controls.windows.StartMenuWindow;
import controls.windows.StartMenuOption;

import java.util.Random;


/**
 * Class handles communication between user and game.
 */
public class GameControl {
    private boolean gameIsLive;
    private Game game;
    private final StartMenuWindow startGameWindow;
    private final Random random;
    private UserInterfaceMenu uim;
    private final int cameraSpeed;
    private boolean placingTower;
    private DefenceTower towerToPlace;
    private InGameMenu igm;
    private final int mainHp;

    /**
     * creates instance of game control
     * @param random
     */
    public GameControl(Random random) {
        this.gameIsLive = false;
        this.random = random;
        Manager manager = new Manager();
        manager.manageObject(this);
        this.startGameWindow = new StartMenuWindow();
        this.game = null;
        this.cameraSpeed = 10;
        this.placingTower = false;
        this.mainHp = 100;
    }

    /**
     * Stops running game.
     */
    public void cancel() {
        if (this.gameIsLive) {
            this.gameIsLive = false;
            this.igm = new InGameMenu("Game paused, pres esc to continue");
        } else {
            this.gameIsLive = true;
            this.igm.makeInvisible();
            this.igm = null;
        }
    }


    /**
     * Handles mouse clicks.
     * @param x
     * @param y
     */
    public void clickOnPosition(int x, int y) {
        if (this.igm != null) {
            InGameMenuClickResult res = this.igm.click(x, y);
            if (res == InGameMenuClickResult.EXIT) {
                System.exit(0);
            } else if (res == InGameMenuClickResult.RESTART) {
                this.igm.makeInvisible();
                this.game.makeInvisible();
                this.uim.makeInvisible();

                this.game = new Game(this.random, this.mainHp);
                this.uim = new UserInterfaceMenu(5);
                this.gameIsLive = true;
            }
            

        }

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
            if (clickResult == ClickOnBoardResult.START_WAWE) {
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

    /**
     * handles tik action
     */
    public void moveGame() {
        if (this.gameIsLive) {
            this.game.tikWave();
            if (this.game.getSpawning()) {
                this.game.spawnEnemy();
            }

            this.changeMainHpIndicator();

            if (this.game.getMainHp() <= 0) {
                this.gameIsLive = false;
                this.igm = new InGameMenu("Game over");
            }

        }
    }

    private void handleMenuChoice(StartMenuOption option) {
        switch (option) {
            case STARTGAME -> {
                this.game = new Game(this.random, this.mainHp);
                this.gameIsLive = true;
                this.startGameWindow.setInvisible();
                this.uim = new UserInterfaceMenu(100);
            }
            case EXIT -> System.exit(0);
        }
    }

    /**
     * moves camera up by camera speed value
     */
    public void moveUp() {
        if (this.gameIsLive) {
            this.game.move(this.cameraSpeed, 0);
            this.uim.makeVisible();
        }
    }

    /**
     * moves camera down by camera speed value
     */
    public void moveDown() {
        if (this.gameIsLive) {
            this.game.move(-this.cameraSpeed, 0);
            this.uim.makeVisible();

        }
    }

    /**
     * moves camera right by camera speed value
     */
    public void moveRight() {
        if (this.gameIsLive) {
            this.game.move(0, -this.cameraSpeed);
            this.uim.makeVisible();

        }
    }

    /**
     * moves camera left by camera speed value
     */
    public void moveLeft() {
        if (this.gameIsLive) {
            this.game.move(0, this.cameraSpeed);
            this.uim.makeVisible();

        }
    }

    private void changeMainHpIndicator() {
        int currentHp = this.game.getMainHp();
        int maxHp = this.game.getMaxMainHp();
        this.uim.updateHpBar(currentHp, maxHp);
    }
}
