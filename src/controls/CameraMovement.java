package controls;

public enum CameraMovement implements java.io.Serializable {
    MOVEUP(1, 0),
    MOVEDOWN(-1, 0),
    MOVERIGHT(0, -1),
    MOVELEFT(0, 1),
    STOP(0, 0);

    private final int y;
    private final int x;

    CameraMovement(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
