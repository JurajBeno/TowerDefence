package controls.userInterface;

import controls.userInterface.TowerSelected;
import fri.shapesge.Image;
import fri.shapesge.Rectangle;

/**
 * Displays and controls all buttons so player can choose tower
 */
public class TowersMenu {
    private Image balistIcon;
    private Rectangle backGroundBalista;
    private Image fireIcon;
    private Rectangle backGroundFire;
    private Image freezingIcon;
    private Rectangle backGroundFrost;
    private Image poisonIcon;
    private Rectangle backGroundPoison;

    /**
     * creates tower menu
     */
    public TowersMenu() {
        this.backGroundBalista = new Rectangle(650, 750);
        this.backGroundBalista.changeSize(50, 50);
        this.backGroundBalista.changeColor("yellow");
        this.backGroundBalista.makeVisible();

        this.balistIcon = new Image("assets/towers/towerIcons/balistaTowerIco.png", 650, 750);
        this.balistIcon.makeVisible();

        this.backGroundFire = new Rectangle(700, 750);
        this.backGroundFire.changeSize(50, 50);
        this.backGroundFire.changeColor("yellow");
        this.backGroundFire.makeVisible();

        this.fireIcon = new Image("assets/towers/towerIcons/fireTowerIco.png", 700, 750);
        this.fireIcon.makeVisible();

        this.backGroundFrost = new Rectangle(750, 750);
        this.backGroundFrost.changeSize(50, 50);
        this.backGroundFrost.changeColor("yellow");
        this.backGroundFrost.makeVisible();

        this.freezingIcon = new Image("assets/towers/towerIcons/frostTowerIco.png", 750, 750);
        this.freezingIcon.makeVisible();

        this.backGroundPoison = new Rectangle(800, 750);
        this.backGroundPoison.changeSize(50, 50);
        this.backGroundPoison.changeColor("yellow");
        this.backGroundPoison.makeVisible();

        this.poisonIcon = new Image("assets/towers/towerIcons/poisonTowerIco.png", 800, 750);
        this.poisonIcon.makeVisible();
    }

    /**
     * deselects selected button
     */
    public void deselect() {
        this.backGroundBalista.changeColor("yellow");
        this.backGroundFire.changeColor("yellow");
        this.backGroundFrost.changeColor("yellow");
        this.backGroundPoison.changeColor("yellow");
    }

    /**
     * @return enum instance of which type of tower will be built
     */
    public TowerSelected clickTower(int y, int x) {
        if (y < 750 || y > 800) {
            return TowerSelected.MISSCLICK;
        }

        if (x > 650 && x < 700) {
            this.backGroundBalista.changeColor("selectedYellow");
            return TowerSelected.BALISTA;
        } else if (x > 700 && x < 750) {
            this.backGroundFire.changeColor("selectedYellow");
            return TowerSelected.FIRE;
        } else if (x > 750 && x < 800) {
            this.backGroundFrost.changeColor("selectedYellow");
            return TowerSelected.FREEZING;
        } else if (x > 800 && x < 850) {
            this.backGroundPoison.changeColor("selectedYellow");
            return TowerSelected.POISON;
        }
        return TowerSelected.MISSCLICK;
    }

    /**
     * makes menu invisible
     */
    public void makeInvisible() {
        this.backGroundBalista.makeInvisible();
        this.backGroundPoison.makeInvisible();
        this.backGroundFire.makeInvisible();
        this.backGroundFrost.makeInvisible();

        this.poisonIcon.makeInvisible();
        this.fireIcon.makeInvisible();
        this.freezingIcon.makeInvisible();
        this.balistIcon.makeInvisible();
    }

    /**
     * shows menu on top of display
     */
    public void makeVisible() {
        this.makeInvisible();
        this.backGroundBalista.makeVisible();
        this.backGroundPoison.makeVisible();
        this.backGroundFire.makeVisible();
        this.backGroundFrost.makeVisible();

        this.poisonIcon.makeVisible();
        this.fireIcon.makeVisible();
        this.freezingIcon.makeVisible();
        this.balistIcon.makeVisible();
    }
}
