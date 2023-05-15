package towers;

public class FireTower extends DefenceTower implements java.io.Serializable {
    public FireTower(int xPos, int yPos) {
        super("Fire tower", "towers\\fire.png", 8, 4);
    }

    @Override
    void giveDamage() {
        //todo
    }
}
