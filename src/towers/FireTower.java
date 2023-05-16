package towers;

public class FireTower extends DefenceTower implements java.io.Serializable {
    public FireTower() {
        super("Fire tower", "assets\\towers\\fire.png", 8, 4);
    }

    @Override
    public TowerEffect getAttackEffect() {
        return TowerEffect.FIRE;
    }
}
