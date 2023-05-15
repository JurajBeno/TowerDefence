package towers;

public class BalistaTower extends DefenceTower implements java.io.Serializable {
    public BalistaTower(int xPos, int yPos) {
        super("Balista tower", "towers\\balista.png", 3, 9);
    }

    @Override
    void giveDamage() {
        //todo
    }
}
