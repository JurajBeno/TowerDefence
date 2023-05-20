package towers;

/**
 * Defence freezing tower class
 */
public class FreezingTower extends DefenceTower {
    /**
     * Creates freezing tower prepared to be placed
     */
    public FreezingTower() {
        super("Freezing tower", "assets\\towers\\freezing.png", 4, 150);
    }

    /**
     * when in range returns freeze effect
     * @param x
     * @param y
     */
    @Override
    public TowerEffect getAttackEffect(int x, int y) {
        return TowerEffect.FREEZE;
    }
}
