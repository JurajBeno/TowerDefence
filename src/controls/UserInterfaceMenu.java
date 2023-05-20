package controls;


public class UserInterfaceMenu {
    private MainHpBar hpBar;
    private TowersMenu towersMenu;

    public UserInterfaceMenu(int hpOfTower) {
        this.hpBar = new MainHpBar();
        this.towersMenu = new TowersMenu();
    }

    public TowerSelected click(int y, int x) {
        return this.towersMenu.clickTower(y, x);
    }

    public void updateHpBar(int hp, int maxHp) {
        this.hpBar.updateBar(hp, maxHp);
    }


    public void deselect() {
        this.towersMenu.deselect();
    }

    public void makeInvisible() {
        this.hpBar.makeInvisible();
        this.towersMenu.makeInvisible();
    }

    public void makeVisible() {
        this.hpBar.makeVisible();
        this.towersMenu.makeVisible();
    }
}
