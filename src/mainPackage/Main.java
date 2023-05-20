package mainPackage;

import controls.GameControl;

import java.io.IOException;
import java.util.Random;

/**
 * Start point of application
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String currentPath = new java.io.File(".").getCanonicalPath();
        System.out.println("Current dir:" + currentPath);
        /* for testing purposes args[0] is seed for random */
        if (args.length > 0) {
            new GameControl(new Random(Integer.parseInt(args[0])));
        } else {
            new GameControl(new Random());
        }
    }
}
