package board;


import controls.clickResults.ClickOnBoardResult;

public interface Node {
    int getX();
    int getY();
    ClickOnBoardResult clicked();
    void move(int y, int x);
    void makeInvisible();
}
