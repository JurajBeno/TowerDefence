package emenies;

import board.Road;

import java.util.ArrayList;


//TODO fix so enemies walk on path
public class Wave {
    private int numberOfLight;
    private final int y;
    private final int x;
    private int numberOfHeavy;
    private final ArrayList<Attacker> attackers;
    private final ArrayList<Road> path;
    public Wave(double roundMultiplier, ArrayList<Road> path) {
        this.y = path.get(path.size() - 1).getY();
        this.x = path.get(path.size() - 1).getX();
        this.numberOfHeavy = (int)(roundMultiplier * 1);
        this.numberOfLight = (int)(roundMultiplier * 2);
        this.attackers = new ArrayList<>();
        for (Road road : path) {
            System.out.println(road.getY() + " " + road.getX());
        }
        this.path = path;
    }

    public int getNumberOfAttackers() {
        return this.attackers.size();
    }

    //TODO FIX THAT ENDING OF THE PATH
    public void moveWave() {
        ArrayList<Integer> deleteIndexes = new ArrayList<>();
        int index = 0;
        for (Attacker attacker : this.attackers) {
            if (attacker.getIndexOfPath() < 0) {
                attacker.giveDamage();
            } else if (attacker.getHp() == 0) {
                attacker.die();
                deleteIndexes.add(index);
            } else {
                System.out.println(attacker);
                System.out.println((this.path.get(attacker.getIndexOfPath()).getY() + 16) + " " + (this.path.get(attacker.getIndexOfPath()).getX() + 16));
                attacker.walk(
                        this.path.get(attacker.getIndexOfPath()).getY() + 16,
                        this.path.get(attacker.getIndexOfPath()).getX() + 16);
            }
            index++;
        }
        for (Integer deleteIndex : deleteIndexes) {
            this.attackers.remove((int)deleteIndex);
        }
    }

    public void move(int y, int x) {
        for (Attacker attacker : this.attackers) {
            attacker.move(y, x);
        }
    }


    public boolean spawnEnemy() {
        if (this.numberOfHeavy > 0) {
            this.attackers.add(new Heavy(this.x + 16, this.y + 16, 100, this.path.size() - 1));
            this.numberOfHeavy--;
            return true;
        } else if (this.numberOfLight > 0) {
            this.attackers.add(new Light(this.x + 16, this.y + 16, 35, this.path.size() - 1));
            this.numberOfLight--;
            return true;
        }
        return false;
    }

}
