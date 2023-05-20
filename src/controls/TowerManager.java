package controls;

import towers.DefenceTower;
import towers.MainTower;
import towers.TowerEffect;

import java.util.ArrayList;

/**
 * Manages all tower activity
 */
public class TowerManager {
    private final ArrayList<DefenceTower> defenceTowers;
    private MainTower base;

    /**
     * creates new tower manager
     * @param base main tower
     */
    public TowerManager(MainTower base) {
        this.base = base;
        this.defenceTowers = new ArrayList<>();
    }

    /**
     * decreases hp value of main tower
     * @param howMuch
     */
    public void decreaseHpOfMain(int howMuch) {
        this.base.decreaseHp(howMuch);
    }

    /**
     * @return hp of main tower
     */
    public int getHpOfMain() {
        return this.base.getHp();
    }

    /**
     * @return maximal possible hp level of main tower
     */
    public int getMaxMainHp() {
        return this.base.getMaxHp();
    }

    /**
     * adds tower to tower manager
     * @param tower DefenceTower
     */
    public void addTower(DefenceTower tower) {
        this.defenceTowers.add(tower);
    }

    /**
     * if tower is in range of x and y it sends its effect
     * @param x position
     * @param y position
     * @return array list of all effect that could hit enemy
     */
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
