package controls;


public class UserInterfaceMenu {
    private MainHpBar hpBar;
    private TowersMenu towersMenu;

    public UserInterfaceMenu(int hpOfTower) {
        this.hpBar = new MainHpBar(hpOfTower);
        this.towersMenu = new TowersMenu();
    }

    public TowerSelected click(int y, int x) {
        return this.towersMenu.clickTower(y, x);
    }

    public void updateHpBar(int howMuch) {
        this.hpBar.updateBar(howMuch);
    }

    public void resetHpBar() {
        this.hpBar.resetBar();
    }
}
