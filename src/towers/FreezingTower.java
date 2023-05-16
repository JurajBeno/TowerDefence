package towers;

public class FreezingTower extends DefenceTower implements java.io.Serializable {
    public FreezingTower() {
        super("Freezing tower", "assets\\towers\\freezing.png", 4, 8);
    }

    @Override
    public TowerEffect getAttackEffect() {
        return TowerEffect.FREEZE;
    }
}
