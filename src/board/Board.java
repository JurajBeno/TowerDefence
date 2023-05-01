package board;

import controls.clickResults.ClickOnBoardResult;
import controls.clickResults.MissClick;
import controls.clickResults.PlaceTowerResult;
import controls.clickResults.StartWaveResult;

import java.util.ArrayList;
import java.util.Random;

public class Board implements java.io.Serializable {
    private final ArrayList<BoardBlock> blocks;
    private final Random random;
    private GenerateNewBlockButton genButt;

    public Board(Random random) {
        this.random = random;
        this.blocks = new ArrayList<>();
        this.blocks.add(new BoardBlock(1, 1, random));
        this.setUpGenNewButton();
    }

    public ArrayList<Road> getWholePath() {
        ArrayList<Road> path = new ArrayList<>();
        for (BoardBlock block : this.blocks) {
            path.addAll(block.getPathOnBlock());
        }
        return path;
    }

    public void discardGenNewButton() {
        this.genButt.makeInvisible();
    }

    public void setUpGenNewButton() {
        int x = this.blocks.get(this.blocks.size() - 1).getX();
        int y = this.blocks.get(this.blocks.size() - 1).getY();
        this.genButt = new GenerateNewBlockButton(y + 64 * 4, x + 64 * 10);
    }

    //TODO poriadne vytestovat obcas to spavne daky bs, nechapem preco, ale si to bude ten block L smerom dolu
    public void addBoardBlock() {
        this.discardGenNewButton();
        BoardBlock last = this.blocks.get(this.blocks.size() - 1);
        int y = last.getY() + last.getOrientation().getX() * 9 * 64;
        int x = last.getX() + last.getOrientation().getY() * 9 * 64;
        this.blocks.add(new BoardBlock(y, x, last.getTemplate(), last.getOrientation(), this.random));
    }

    public void move(int y, int x) {
        this.genButt.move(y, x);
        for (BoardBlock block : this.blocks) {
            block.move(y, x);
        }
    }

    public ClickOnBoardResult click(int y, int x) {



        for (BoardBlock block : this.blocks) {
            if (block.click(y, x)) {
                System.out.println(" block clicked " + this.blocks.indexOf(block));
                return new PlaceTowerResult();
            }
        }

        if (this.genButt.click(y, x)) {
//            System.out.println(y);
//            System.out.println(x);
//            System.out.println();
//            System.out.println(this.genButt.getY());
//            System.out.println(this.genButt.getX());
            this.addBoardBlock();
            this.discardGenNewButton();
            return new StartWaveResult(0, 0);
        }

        return new MissClick();
    }
}
