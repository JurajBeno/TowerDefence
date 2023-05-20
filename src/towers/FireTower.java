package towers;

/**
 * Defence fire tower class
 */
public class FireTower extends DefenceTower {
    /**
     * Creates fire tower prepared to be placed
     */
    public FireTower() {
        super("Fire tower", "assets\\towers\\fire.png", 8, 10);
    }

    /**
     * when in range returns fire effect
     * @param x
     * @param y
     */
    @Override
    public TowerEffect getAttackEffect(int x, int y) {
        return TowerEffect.FIRE;
    }
}
