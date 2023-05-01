package towers;

public abstract class DefenceTower extends Tower {
    private final int price;
    private final int range;

    public DefenceTower(int xPos, int yPos, String name, String imgPath, int price, int range) {
        super(xPos, yPos, name, imgPath);
        this.price = price;
        this.range = range;
    }

    public int getPrice() {
        return this.price;
    }

    public int getRange() {
        return this.range;
    }

    abstract void giveDamage();
}
