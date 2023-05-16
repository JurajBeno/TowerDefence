package towers;

public class BalistaTower extends DefenceTower implements java.io.Serializable {
    public BalistaTower() {
        super("Balista tower", "assets\\towers\\balista.png", 3, 9);
    }

    @Override
    public TowerEffect getAttackEffect() {
        return TowerEffect.BLEED;//TODO add EFFECT animation possibly to everything
    }
}
