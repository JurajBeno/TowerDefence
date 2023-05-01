package emenies;

public interface Attacker {
    int getX();
    int getY();
    int getHp();
    int giveDamage();
    void walk(int y, int x);
    void die();
    int getIndexOfPath();
    void move(int y, int x);
}
