package towers;

/**
 * Abstract class, unites all defence towers
 */
public abstract class DefenceTower extends Tower {
    private final int price;
    private final int range;

    /**
     * Creates tower on given postion
     * @param name
     * @param imgPath path to image
     * @param price
     * @param range
     */
    public DefenceTower(String name, String imgPath, int price, int range) {
        super(name, imgPath);
        this.price = price;
        this.range = range;
    }

    /**
     * @return price of tower
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * @return range of tower
     */
    public int getRange() {
        return this.range;
    }

    public abstract TowerEffect getAttackEffect(int x, int y);
}
