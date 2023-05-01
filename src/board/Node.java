package board;


import controls.ClickResults.ClickOnBoardResult;

public interface Node {
    int getX();
    int getY();
    ClickOnBoardResult clicked();
    void move(int y, int x);
    void makeInvisible();
}
