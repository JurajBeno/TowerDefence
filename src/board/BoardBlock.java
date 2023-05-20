package board;

import towers.DefenceTower;
import towers.MainTower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class that represents one block of board that is base on block template
 */
public class BoardBlock {
    private final Node[][] nodes;
    private BlockTemplates template;
    private Orientarion orientarion;
    private final Random random;
    private int y;
    private int x;
    private final int nodeSize = 64;
    private final ArrayList<Road> path;

    /**
     * @param y position
     * @param x position
     * @param template of previous board block
     * @param orientarion of previous board block
     * @param random
     */
    public BoardBlock(int y, int x, BlockTemplates template, Orientarion orientarion, Random random) {
        this.random = random;
        this.y = y;
        this.x = x;
        this.chooseTemplate(template, orientarion);
        this.nodes = new Node[9][9];
        this.path = new ArrayList<>();
        this.createNodes();
        this.buildPath();
        Collections.reverse(this.path);
    }

    /**
     * first block will use this board block
     * @param y position
     * @param x position
     * @param random
     * @param base main tower
     */
    public BoardBlock(int y, int x, Random random, MainTower base) {
        this.random = random;
        this.y = y;
        this.x = x;
        this.template = BlockTemplates.BASE;
        this.orientarion = Orientarion.EAST;
        this.nodes = new Node[9][9];
        this.path = new ArrayList<>();
        this.createNodes();
        this.buildPath();
        Collections.reverse(this.path);
        Land midLand = (Land)this.nodes[4][4];
        midLand.setTower(base);
    }

    /**
     * will make all nodes invisible even if they hold towers
     */
    public void makeInvisible() {
        for (int i = 0; i < this.nodes.length; i++) {
            for (int j = 0; j < this.nodes[i].length; j++) {
                this.nodes[i][j].makeInvisible();
            }
        }
    }

    /**
     * @return path for enemy to walk on
     */
    public ArrayList<Road> getPathOnBlock() {
        return new ArrayList<>(this.path);
    }

    private void chooseTemplate(BlockTemplates prewTemplate, Orientarion prewOrientarion) {
        var temp =  prewTemplate.getNextPossibleTemplates(prewOrientarion);
        this.template = temp.get(this.random.nextInt(temp.size() - 1));
        this.orientarion = this.template.getOtherOrientation(prewOrientarion);
    }


    /**
     * @return orientation of block
     */
    public Orientarion getOrientation() {
        return this.orientarion;
    }

    /**
     * @return template of block
     */
    public BlockTemplates getTemplate() {
        return this.template;
    }

    /**
     * moves whole block useful for camera movement
     * @param y
     * @param x
     */
    public void move(int y, int x) {
        this.y += y;
        this.x += x;
        for (Node[] nodeLine : this.nodes) {
            for (Node node : nodeLine) {
                node.move(y, x);
            }
        }
    }

    private void buildPath() {
        Node start = null;
        int xPos = 0;
        int yPos = 0;
        switch (this.orientarion) {
            case NORTH -> {
                start = this.nodes[0][4];
                xPos = 4;
                yPos = 0;
            }
            case EAST -> {
                start = this.nodes[4][8];
                xPos = 8;
                yPos = 4;
            }
            case WEST -> {
                start = this.nodes[4][0];
                xPos = 0;
                yPos = 4;
            }
            case SOUTH -> {
                start = this.nodes[8][4];
                xPos = 4;
                yPos = 8;
            }
        }
        this.buildPathRecurs(start, xPos, yPos);
    }

    private void buildPathRecurs(Node start, int x, int y) {
        this.path.add((Road)start);
        if (y + 1 < 9 && y + 1 >= 0 &&
                this.nodes[y + 1][x] instanceof Road &&
                !this.path.contains(this.nodes[y + 1][x])) {
            this.buildPathRecurs(this.nodes[y + 1][x], x, y + 1);
        } else if (y - 1 < 9 && y - 1 >= 0 &&
                this.nodes[y - 1][x] instanceof Road &&
                !this.path.contains(this.nodes[y - 1][x])) {
            this.buildPathRecurs(this.nodes[y - 1][x], x, y - 1);
        } else if (x - 1 < 9 && x - 1 >= 0 &&
                this.nodes[y][x - 1] instanceof Road &&
                !this.path.contains(this.nodes[y][x - 1])) {
            this.buildPathRecurs(this.nodes[y][x - 1], x - 1, y);
        } else if (x + 1 < 9 && x + 1 >= 0 &&
                this.nodes[y][x + 1] instanceof Road &&
                !this.path.contains(this.nodes[y][x + 1])) {
            this.buildPathRecurs(this.nodes[y][x + 1], x + 1, y);
        }
    }

    private void createNodes() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.template.getTemplateNode(i, j) == 0) {
                    this.nodes[i][j] = new Land(this.nodeSize * i + this.y, this.nodeSize * j + this.x);
                } else {
                    this.nodes[i][j] = new Road(this.nodeSize * i + this.y, this.nodeSize * j + this.x);
                }
            }
        }
    }

    /**
     * @return x position
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return y position
     */
    public int getY() {
        return this.y;
    }

    /**
     * checks if tower can be placed on node that is on given position
     * @param tower defence tower
     * @param y
     * @param x
     * @return boolean value true if tower has been placed
     */
    public boolean placeTower(DefenceTower tower, int y, int x) {
        if (y >= this.y && y <= this.y + 9 * this.nodeSize &&
                x >= this.x && x <= this.x + 9 * this.nodeSize) {
            int posInBlockX = Math.abs(x - this.x) / 64;
            int posInBlockY = Math.abs(y - this.y) / 64;
            Node node = this.nodes[posInBlockY][posInBlockX];
            if (node instanceof Land land) {
                return land.setTower(tower);
            }
        }
        return false;
    }
}
