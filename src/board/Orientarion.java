package board;

public enum Orientarion {
    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0);


    private final int y;
    private final int x;

    Orientarion(int y, int x) {
        this.y = y;
        this.x = x;
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

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
