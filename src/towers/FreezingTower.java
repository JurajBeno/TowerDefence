package towers;

public class FreezingTower extends DefenceTower implements java.io.Serializable {
    public FreezingTower(int xPos, int yPos) {
        super("Freezing tower", "towers\\freezing.png", 4, 8);
    }

    @Override
    void giveDamage() {
        //todo
    }
}
