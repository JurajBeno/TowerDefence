package towers;

public enum TowerEffect {
    FIRE(0, 5, 3),
    POISSON(1, 10, 2),
    BLEED(0, 15, 4),
    FREEZE(3, 0, 3);

    private int moventSlowedTicks;
    private int damage;
    private int efectTicks;

    TowerEffect(int moventSlowedTicks, int damage, int efectTicks) {

        this.moventSlowedTicks = moventSlowedTicks;
        this.damage = damage;
        this.efectTicks = efectTicks;
    }

    public int getMoventSlowedTicks() {
        return this.moventSlowedTicks;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getEfectTicks() {
        return this.efectTicks;
    }

    public void decrementEfectTicks() {
        this.efectTicks--;
    }
}
