import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Ex1Test {

    @org.junit.jupiter.api.Test
    void check1() {
        String filename = "check.txt";
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("A*\n" +
                    "no open\n" +
                    "small\n" +
                    "R,R,_\n" +
                    "B,B,_\n" +
                    "G,G,_\n" +
                    "Goal state:\n" +
                    "R,R,B\n" +
                    "B,G,_\n" +
                    "G,_,_");
            myWriter.close();
            Ex1.check(filename);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    @org.junit.jupiter.api.Test
    void check2() {
        String filename = "check.txt";
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("A*\n" +
                    "no open\n" +
                    "big\n" +
                    "G,G,G,G,_\n" +
                    "Y,Y,Y,Y,_\n" +
                    "R,R,R,R,_\n" +
                    "B,B,B,B,_\n" +
                    "_,_,_,_,_\n" +
                    "Goal state:\n" +
                    "G,G,G,B,_\n" +
                    "Y,Y,Y,Y,_\n" +
                    "R,R,R,R,_\n" +
                    "B,B,B,G,_\n" +
                    "_,_,_,_,_");
            myWriter.close();
            Ex1.check(filename);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    /** not work */
    @org.junit.jupiter.api.Test
    void bad() {
        String filename = "check.txt";
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("BFS\n" +
                    "no open\n" + // on or with open list
                    "small\n" +
                    "G,R,_\n" +
                    "B,B,_\n" +
                    "G,R,_\n" +
                    "Goal state:\n" +
                    "B,G,_\n" +
                    "R,B,_\n" +
                    "R,G,_");
            myWriter.close();
            Ex1.check(filename);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /** the BFS and A* not the same */
    @org.junit.jupiter.api.Test
    void check4() {
        String filename = "check.txt";
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("A*\n" +
                    "no open\n" + // on or with open list
                    "small\n" +
                    "G,R,_\n" +
                    "B,B,_\n" +
                    "G,R,_\n" +
                    "Goal state:\n" +
                    "R,G,_\n" +
                    "B,B,_\n" +
                    "R,G,_");
            myWriter.close();
            Ex1.check(filename);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // not work in A*
    @org.junit.jupiter.api.Test
    void checkALL() {
        String filename = "check.txt";
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("BFS\n" +
                    "no open\n" +
                    "small\n" +
                    "R,R,_\n" +
                    "G,B,_\n" +
                    "G,B,_\n" +
                    "Goal state:\n" +
                    "R,R,_\n" +
                    "B,B,_\n" +
                    "G,G,_");
            myWriter.close();
            Ex1.check(filename);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}