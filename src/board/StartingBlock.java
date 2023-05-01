package board;

import towers.Tower;

import java.util.Random;

public class StartingBlock extends BoardBlock implements java.io.Serializable {
    private Tower mainTower;
    public StartingBlock(int y, int x, Random random) {

        super(y, x, random);
    }
}
