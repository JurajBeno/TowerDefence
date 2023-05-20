package towers;

/**
 * Defence posison tower class
 */
public class PoisonTower extends DefenceTower {
    /**
     * Creates poison tower prepared to be placed
     */
    public PoisonTower() {
        super("Poison tower", "assets\\towers\\poison.png", 10, 50);
    }

    /**
     * when in range returns poison effect
     * @param x
     * @param y
     */
    @Override
    public TowerEffect getAttackEffect(int x, int y) {
        return TowerEffect.POISSON;
    }
}
