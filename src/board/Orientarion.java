package board;

/**
 * Enumerator that holds orientations to determine positions of board blocks
 */
public enum Orientarion {
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1);


    private final int y;
    private final int x;

    Orientarion(int y, int x) {
        this.y = y;
        this.x = x;
    }
    /**
     * @return x orientation
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return y orientation
     */
    public int getY() {
        return this.y;
    }

    /**
     * @return "geographycally" oposite direction of this orientation
     */
    public Orientarion getOposite() {
        if (this == WEST) {
            return EAST;
        } else if (this == EAST) {
            return WEST;
        } else if ( this == NORTH) {
            return SOUTH;
        } else {
            return  NORTH;
        }
    }
}
