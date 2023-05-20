package board;



/**
 * interface that represents part of board block
 */
public interface Node {
    int getX();
    int getY();
    void move(int y, int x);
    void makeInvisible();
}
