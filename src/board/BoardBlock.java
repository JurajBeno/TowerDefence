package board;

import controls.TowerSelected;
import towers.DefenceTower;
import towers.Tower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BoardBlock implements java.io.Serializable {
    private final Node[][] nodes;
    private BlockTemplates template;
    private Orientarion orientarion;
    private final Random random;
    private int y;
    private int x;
    private final int nodeSize = 64;
    private final ArrayList<Road> path;

    public BoardBlock(int y, int x, BlockTemplates template, Orientarion orientarion, Random random) {
        this.random = random;
        this.y = y;
        this.x = x;
        this.chooseTemplate(template, orientarion);
        this.nodes = new Node[9][9];
        this.path = new ArrayList<>();
        this.createNodes();
        this.buildPath();
    }

    public BoardBlock(int y, int x, Random random) {
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
        Land midLand = (Land)this.nodes[5][5];
        midLand.setTower(new Tower( "Base", "assets\\towers\\base.png"));
    }

    public ArrayList<Road> getPathOnBlock() {
        return this.path;
    }


    public boolean click(int y, int x) {
        if (y >= this.y && y <= this.y + 9 * this.nodeSize &&
            x >= this.x && x <= this.x + 9 * this.nodeSize) {
            int posInBlockX = Math.abs(x - this.x) / 64;
            int posInBlockY = Math.abs(y - this.y) / 64;
            System.out.println(this.nodes[posInBlockY][posInBlockX].getX());
        }
        return false;
    }

    public void chooseTemplate(BlockTemplates prewTemplate, Orientarion prewOrientarion) {
        var temp =  prewTemplate.getNextPossibleTemplates(prewOrientarion);
        this.template = temp.get(this.random.nextInt(temp.size() - 1));
        this.orientarion = this.template.getOtherOrientation(prewOrientarion);
    }


    public Orientarion getOrientation() {
        return this.orientarion;
    }

    public BlockTemplates getTemplate() {
        return this.template;
    }

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
                    this.nodes[i][j] = new Land(this.nodeSize * i + this.y, this.nodeSize * j + this.x, this.random.nextInt(4) - 1);
                } else {
                    this.nodes[i][j] = new Road(this.nodeSize * i + this.y, this.nodeSize * j + this.x);
                }
            }
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean placeTower(DefenceTower tower, int y, int x) {
        if (y >= this.y && y <= this.y + 9 * this.nodeSize &&
                x >= this.x && x <= this.x + 9 * this.nodeSize) {
            int posInBlockX = Math.abs(x - this.x) / 64;
            int posInBlockY = Math.abs(y - this.y) / 64;
            Node node = this.nodes[posInBlockY][posInBlockX];
            if (node instanceof Land land) {
                land.setTower(tower);
                return true;
            }
        }
        return false;
    }
}
