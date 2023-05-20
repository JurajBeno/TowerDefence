package towers;

/**
 * Enumerator hold different efeect from all tower types
 */
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

    /**
     * @return how much the effect slows
     */
    public int getMoventSlowedTicks() {
        return this.moventSlowedTicks;
    }

    /**
     * @return how much damage it gives
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * @return how many ticks it will last
     */
    public int getEfectTicks() {
        return this.efectTicks;
    }

    /**
     * decreases ticks of effect
     */
    public void decrementEfectTicks() {
        this.efectTicks--;
    }
}
