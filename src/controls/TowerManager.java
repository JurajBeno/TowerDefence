package controls;

import towers.DefenceTower;
import towers.MainTower;
import towers.TowerEffect;

import java.util.ArrayList;

public class TowerManager {
    private final ArrayList<DefenceTower> defenceTowers;
    private MainTower base;
    public TowerManager(MainTower base) {
        this.base = base;
        this.defenceTowers = new ArrayList<>();
    }

    public void decreaseHpOfMain(int howMuch) {
        this.base.decreaseHp(howMuch);
    }

    public int getHpOfMain() {
        return this.base.getHp();
    }

    public int getMaxMainHp() {
        return this.base.getMaxHp();
    }

    public void addTower(DefenceTower tower) {
        this.defenceTowers.add(tower);
    }

    public ArrayList<TowerEffect> attackIfCan(int x, int y) {
        ArrayList<TowerEffect> effects = new ArrayList<>();
        for (DefenceTower defenceTower : this.defenceTowers) {
            if (Math.abs(defenceTower.getXPos() - x) < defenceTower.getRange() &&
                    Math.abs(defenceTower.getYPos() - y) < defenceTower.getRange()) {
                effects.add(defenceTower.getAttackEffect(x, y));
            }
        }
        return effects;
    }
}
