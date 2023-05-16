package controls;

import towers.DefenceTower;
import towers.TowerEffect;

import java.util.ArrayList;

public class TowerManager {
    private final ArrayList<DefenceTower> defenceTowers;
    public TowerManager() {
        this.defenceTowers = new ArrayList<>();
    }

    public void addTower(DefenceTower tower) {
        this.defenceTowers.add(tower);
    }

    public ArrayList<TowerEffect> attackIfCan(int x, int y) {
        ArrayList<TowerEffect> effects = new ArrayList<>();
        for (DefenceTower defenceTower : this.defenceTowers) {
            if (Math.abs(defenceTower.getXPos() - x) < defenceTower.getRange() &&
                    Math.abs(defenceTower.getYPos() - y) < defenceTower.getRange()) {
                effects.add(defenceTower.getAttackEffect());
            }
        }
        return effects;
    }
}
