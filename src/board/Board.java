package board;

import controls.ClickOnBoardResult;
import towers.DefenceTower;
import towers.MainTower;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that controls whole board, it's actions and displaying.
 */
public class Board {
    private final ArrayList<BoardBlock> blocks;
    private final Random random;
    private GenerateNewBlockButton genButton;

    /**
     * @param random random to determine random path
     * @param base main tower
     */
    public Board(Random random, MainTower base) {
        this.random = random;
        this.blocks = new ArrayList<>();
        this.blocks.add(new BoardBlock(1, 1, random, base));
        this.setUpGenNewButton();
    }

    /**
     * Makes invisible every displayed part of board
     */
    public void makeInvisible() {
        for (BoardBlock block : this.blocks) {
            block.makeInvisible();
        }
        this.genButton.makeInvisible();
    }

    /**
     * @return path built from every building block together
     */
    public ArrayList<Road> getWholePath() {
        ArrayList<Road> path = new ArrayList<>();
        for (BoardBlock block : this.blocks) {
            path.addAll(block.getPathOnBlock());
        }
        return path;
    }

    /**
     * hides button
     */
    public void discardGenNewButton() {
        this.genButton.makeInvisible();
    }

    /**
     * sets up new button
     */
    public void setUpGenNewButton() {
        int x = this.blocks.get(this.blocks.size() - 1).getX();
        int y = this.blocks.get(this.blocks.size() - 1).getY();
        Orientarion orientation = this.blocks.get(this.blocks.size() - 1).getOrientation();
        if (orientation == Orientarion.EAST) {
            this.genButton = new GenerateNewBlockButton(y + 64 * 4, x + 64 * 10);
        } else if (orientation == Orientarion.WEST) {
            this.genButton = new GenerateNewBlockButton(y + 64 * 4, x + (-64) * 2);
        } else if (orientation == Orientarion.NORTH) {
            this.genButton = new GenerateNewBlockButton(y + (-64) * 2, x + 64 * 4);
        } else {
            this.genButton = new GenerateNewBlockButton(y + 64 * 10, x + 64 * 4);
        }
    }

    /**
     * based on position of last board block and its shape and orientation
     * creates new board block
     */
    public void addBoardBlock() {
        this.discardGenNewButton();
        BoardBlock last = this.blocks.get(this.blocks.size() - 1);
        int y = last.getY() + last.getOrientation().getY() * 9 * 64;
        int x = last.getX() + last.getOrientation().getX() * 9 * 64;
        this.blocks.add(new BoardBlock(y, x, last.getTemplate(), last.getOrientation(), this.random));
    }

    /**
     * moves whole map by y,x best for camera movement
     * @param y
     * @param x
     */
    public void move(int y, int x) {
        this.genButton.move(y, x);
        for (BoardBlock block : this.blocks) {
            block.move(y, x);
        }
    }

    /**
     * handles interaction with board with clicking
     * @param y
     * @param x
     * @return result of clicking action
     */
    public ClickOnBoardResult click(int y, int x) {
        if (this.genButton.click(y, x)) {
            this.addBoardBlock();
            this.discardGenNewButton();
            return ClickOnBoardResult.START_WAWE;
        }

        return ClickOnBoardResult.MISS_CLICK;
    }

    /**
     * clicking with chosen tower will place it if clicked on land
     * @param tower
     * @param y
     * @param x
     * @return boolean value true if placing was successful
     */
    public boolean placeTower(DefenceTower tower, int y, int x) {
        for (BoardBlock block : this.blocks) {
            if (block.placeTower(tower, y, x)) {
                return true;
            }
        }
        return false;
    }
}
