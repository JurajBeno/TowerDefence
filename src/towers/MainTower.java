package towers;

public class MainTower extends Tower {
    private int hp;
    private final int maxHp;
    public MainTower(String name, String imgPath, int hp) {
        super(name, imgPath);
        this.hp = hp;
        this.maxHp = hp;
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public int getHp() {
        return this.hp;
    }

    public void decreaseHp(int howMuch) {
        this.hp -= howMuch;
    }
}
