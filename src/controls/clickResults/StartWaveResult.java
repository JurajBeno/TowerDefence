package controls.clickResults;


public class StartWaveResult implements ClickOnBoardResult {

    private final int y;
    private final int x;

    public StartWaveResult(int y, int x) {
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
