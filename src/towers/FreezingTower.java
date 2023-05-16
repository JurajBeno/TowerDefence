package towers;

public class FreezingTower extends DefenceTower implements java.io.Serializable {
    public FreezingTower() {
        super("Freezing tower", "assets\\towers\\freezing.png", 4, 150);
    }

    @Override
    public TowerEffect getAttackEffect(int x, int y) {
        return TowerEffect.FREEZE;
    }
}
