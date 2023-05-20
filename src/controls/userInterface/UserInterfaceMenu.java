package controls.userInterface;

/**
 * Class displays hp bar and buttons to choose from towers
 */
public class UserInterfaceMenu {
    private MainHpBar hpBar;
    private TowersMenu towersMenu;

    /**
     * creates whole user interface while in game
     * @param hpOfTower
     */
    public UserInterfaceMenu(int hpOfTower) {
        this.hpBar = new MainHpBar();
        this.towersMenu = new TowersMenu();
    }

    /**
     * @return what tower was selected
     */
    public TowerSelected click(int y, int x) {
        return this.towersMenu.clickTower(y, x);
    }

    /**
     * updates main hp bar on left side
     */
    public void updateHpBar(int hp, int maxHp) {
        this.hpBar.updateBar(hp, maxHp);
    }

    /**
     * removes highlighting of button
     */
    public void deselect() {
        this.towersMenu.deselect();
    }

    /**
     * hides menu buttons
     */
    public void makeInvisible() {
        this.hpBar.makeInvisible();
        this.towersMenu.makeInvisible();
    }

    /**
     * makes everything visible on top
     */
    public void makeVisible() {
        this.makeInvisible();
        this.hpBar.makeVisible();
        this.towersMenu.makeVisible();
    }
}
