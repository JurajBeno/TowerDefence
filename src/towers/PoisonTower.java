package towers;

public class PoisonTower extends DefenceTower implements java.io.Serializable {
    public PoisonTower() {
        super("Poison tower", "assets\\towers\\poison.png", 10, 5);
    }

    @Override
    public TowerEffect getAttackEffect() {
        return TowerEffect.POISSON;
    }
}
