package towers;

public class PoisonTower extends DefenceTower implements java.io.Serializable {
    public PoisonTower(int xPos, int yPos) {
        super(xPos, yPos, "Poison tower", "towers//Poison.png", 10, 5);
    }

    @Override
    void giveDamage() {
        //TODO
    }
}
