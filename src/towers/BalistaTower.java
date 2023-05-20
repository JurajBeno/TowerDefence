package towers;

/**
 * Balista defence tower class
 */
public class BalistaTower extends DefenceTower {
    /**
     * Creates ballista tower prepared to be placed
     */
    public BalistaTower() {
        super("Balista tower", "assets\\towers\\balista.png", 3, 128);
    }

    /**
     * when in range returns BLEED effeect
     * @param x
     * @param y
     */
    @Override
    public TowerEffect getAttackEffect(int x, int y) {
        return TowerEffect.BLEED;
        //TODO add EFFECT animation possibly to everything
    }
}
