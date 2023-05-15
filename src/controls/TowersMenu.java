package controls;

import fri.shapesge.Image;
import fri.shapesge.Rectangle;

public class TowersMenu {
    private Image balistIcon;
    private Rectangle backGroundBalista;
    private Image fireIcon;
    private Rectangle backGroundFire;
    private Image freezingIcon;
    private Rectangle backGroundFrost;
    private Image poisonIcon;
    private Rectangle backGroundPoison;

    public TowersMenu() {
        this.backGroundBalista = new Rectangle(150, 500);
        this.backGroundBalista.changeSize(50, 50);
        this.backGroundBalista.changeColor("yellow");
        this.backGroundBalista.makeVisible();

        this.balistIcon = new Image("towers\\towerIcons\\balistaTowerIco.png", 150, 500);
        this.balistIcon.makeVisible();

        this.backGroundFire = new Rectangle(200, 500);
        this.backGroundFire.changeSize(50, 50);
        this.backGroundFire.changeColor("yellow");
        this.backGroundFire.makeVisible();

        this.fireIcon = new Image("towers\\towerIcons\\fireTowerIco.png", 200, 500);
        this.fireIcon.makeVisible();

        this.backGroundFrost = new Rectangle(250, 500);
        this.backGroundFrost.changeSize(50, 50);
        this.backGroundFrost.changeColor("yellow");
        this.backGroundFrost.makeVisible();

        this.freezingIcon = new Image("towers\\towerIcons\\frostTowerIco.png", 250, 500);
        this.freezingIcon.makeVisible();

        this.backGroundPoison = new Rectangle(200, 500);
        this.backGroundPoison.changeSize(50, 50);
        this.backGroundPoison.changeColor("yellow");
        this.backGroundPoison.makeVisible();

        this.poisonIcon = new Image("towers\\towerIcons\\poisonTowerIco.png", 300, 500);
        this.poisonIcon.makeVisible();
    }

    public TowerSelected clickTower(int y, int x) {
        if (y < 500 || y > 550) {
            return TowerSelected.MISSCLICK;
        }

        if (x > 150 && x < 200) {
            return TowerSelected.BALISTA;
        } else if (x > 200 && x < 250) {
            return TowerSelected.FIRE;
        } else if (x > 250 && x < 300) {
            return TowerSelected.FREEZING;
        } else if (x > 300 && x < 350) {
            return TowerSelected.POISON;
        }
        return TowerSelected.MISSCLICK;
    }
}
