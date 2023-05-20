package towers;

/**
 * Main tower class to track hp and image
 */
public class MainTower extends Tower {
    private int hp;
    private final int maxHp;

    /**
     * creates main tower to be placed
     * @param name
     * @param imgPath
     * @param hp level
     */
    public MainTower(String name, String imgPath, int hp) {
        super(name, imgPath);
        this.hp = hp;
        this.maxHp = hp;
    }

    /**
     * @return maximum hp of tower
     */
    public int getMaxHp() {
        return this.maxHp;
    }

    /**
     * @return current hp of tower
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * decreases hp of main tower
     * @param howMuch
     */
    public void decreaseHp(int howMuch) {
        this.hp -= howMuch;
    }
}
