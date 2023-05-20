package board;

import controls.clickResults.ClickOnBoardResult;
import controls.clickResults.PlaceTowerResult;
import fri.shapesge.Image;
import towers.Tower;

public class Land implements Node, java.io.Serializable {
    private int y;
    private int x;
    private final Image img;
    private Tower tower;
    private final int height;
    //todo height mechanic
    public Land(int y, int x, int height) {
        this.y = y;
        this.x = x;
        this.img = new Image(("assets\\Tiles\\miniLand0.png"), x, y);
        this.img.makeVisible();
        this.height = height;
    }

    public boolean setTower(Tower tower) {
        if (this.tower == null) {
            this.tower = tower;
            this.tower.setTowerPos(this.y, this.x);
            return true;
        }
        return false;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public ClickOnBoardResult clicked() {
        return new PlaceTowerResult();
    }

    @Override
    public void move(int y, int x) {
        this.x += x;
        this.y += y;
        this.img.moveHorizontal(x);
        this.img.moveVertical(y);
        if (this.tower != null) {
            this.tower.moveBy(y, x);
        }
    }

    @Override
    public void makeInvisible() {
        if (this.tower != null) {
            this.tower.makeInvisible();
        }
        this.img.makeInvisible();
    }
}
