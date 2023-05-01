package controls;

import controls.ClickResults.ClickOnBoardResult;
import controls.ClickResults.StartWaveResult;
import fri.shapesge.Manager;
import mainPackage.Game;
import window.StartMenuWindow;
import window.StartMenuOption;

import java.io.*;
import java.util.Random;

//TODO think out how will saving and loading games work

public class GameControl implements java.io.Serializable {
    private Game game;
    private final StartMenuWindow startGameWindow;
    private final Random random;
    private int cameraSpeed;

    public GameControl(Random random) {
        this.random = random;
        Manager manager = new Manager();
        manager.manageObject(this);
        this.startGameWindow = new StartMenuWindow();
        this.game = null;
        this.cameraSpeed = 10;

    }

    public void setCameraSpeed(int speed) {
        this.cameraSpeed = speed;
    }

    public void clickOnPosition(int x, int y) {
        if (this.startGameWindow.isVisible() && this.startGameWindow.isClickedOn(y, x)) {
            StartMenuOption option = this.startGameWindow.click(y, x);
            if (option != StartMenuOption.MISSCLICK) {
                this.handleMenuChoice(option);
            }
        } else {
            ClickOnBoardResult clickResult = this.game.click(y, x);
            if (clickResult instanceof StartWaveResult startWave) {
                this.game.startWave();
            }
        }
    }


    public void tik() {

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
                this.game = new Game(this.random);
                this.startGameWindow.setInvisible();
            }
            case LOADGAME -> {
                if (!this.loadGame()) {
                    this.startGameWindow.informUnsuccesfulLoad();
                } else {
                    this.startGameWindow.setInvisible();
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
            this.game = (Game) ois.readObject();
           ois.close();
           return true;
        } catch (ClassNotFoundException | IOException e) {
           return false;
        }
    }

    public void moveUp() {
        if (this.game != null) {
            this.game.move(this.cameraSpeed, 0);
        }
    }

    public void moveDown() {
        if (this.game != null) {
        this.game.move(-this.cameraSpeed, 0);
        }
    }

    public void moveRight() {
        if (this.game != null) {
            this.game.move(0, -this.cameraSpeed);
        }
    }

    public void moveLeft() {
        if (this.game != null) {
            this.game.move(0, this.cameraSpeed);
        }
    }
}