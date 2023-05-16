package towers;

public class PoisonTower extends DefenceTower implements java.io.Serializable {
    public PoisonTower() {
        super("Poison tower", "assets\\towers\\poison.png", 10, 50);
    }

    @Override
    public TowerEffect getAttackEffect(int x, int y) {
        return TowerEffect.POISSON;
    }
}
