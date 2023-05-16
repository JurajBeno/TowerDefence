package towers;

public enum TowerEffect {
    FIRE(0, 3, 3),
    POISSON(1, 2, 2),
    BLEED(0, 2, 4),
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
