import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ex1 {
    private final static int algorithmType = 0, openListCheck = 1, bigOrSmall = 2;
    private final static int numBFS = 0, numDFID = 1, numAStar = 2, numIDAStar = 3, numDFBnB = 4;
    private final static int smallGameSize = 3, bigGameSize = 5;
    public final static int emptyCell = 0, redCell = 1, blueCell = 2, greenCell = 3, yellowCell = 4;
    public final static char[] arrChar = {'_', 'R', 'B', 'G', 'Y'};
    private static boolean small = false;
    private static boolean withoutOpen = false;
    private static int algoToUse = 0;
    private static Node start;
    private static String goalBoard;


    public static void main(String[] args) {
        String filename = "input.txt";
        setup(filename);
        run();
    }

    private static void run() {
        switch (algoToUse) {
            case numBFS:
                BFS.bfs(start, goalBoard);
                break;
            case numDFID:
                DFID.DFID(start, goalBoard);
                break;
            case numAStar:
                AStar.aStar(start, goalBoard);
                break;
            case numIDAStar:
                IDAStar.IDAStar(start, goalBoard);
                break;
            case numDFBnB:
                DFBnB.DFBnB(start, goalBoard);
                break;
        }
    }

    private static void setup(String filename) {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            for (int i = 0; i < 3 && myReader.hasNextLine(); i++) {
                String data = myReader.nextLine();
                switch (i) {
                    case algorithmType: setAlgoToUse(data);break;
                    case openListCheck: setOpenList(data);break;
                    case bigOrSmall: setBigSmall(data);break;
                }
            }

            // set start & goal board
            if (small){
                start = getNode(smallGameSize, myReader);
                myReader.nextLine();
                goalBoard = getNode(smallGameSize, myReader).get_id();
            }
            else { // is big
                start = getNode(bigGameSize, myReader);
                myReader.nextLine();
                goalBoard = getNode(bigGameSize, myReader).get_id();
            }

//            System.out.println("the board is"+Arrays.deepToString(board));
//            System.out.println("the goal board is"+Arrays.deepToString(goalBoard));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static Node getNode(int boardSize, Scanner myReader) {
        int[][] newBoard = new int[boardSize][boardSize];
        ArrayList<Integer> numOfSpace = new ArrayList<>();
        for (int i = 0; i < boardSize && myReader.hasNextLine(); i++) {
            String data = myReader.nextLine();
            String[] colorOfMarbles = data.split(",");
            for (int j = 0; j < boardSize; j++){
                String marble = colorOfMarbles[j];
                if (marble.equals("_")){
                    newBoard[i][j] = emptyCell;
                    numOfSpace.add(i * 10 + j);
                }
                else{
                    switch (marble) {
                        case "R": newBoard[i][j] = redCell;break;
                        case "B": newBoard[i][j] = blueCell;break;
                        case "G": newBoard[i][j] = greenCell;break;
                        case "Y": newBoard[i][j] = yellowCell;break;
                    }
                }
            }
        }
        int[] arr = numOfSpace.stream().mapToInt(i -> i).toArray();
        String newId = Arrays.deepToString(newBoard);
        return new Node(null, newBoard, arr, new int[2], newId, 0);
    }


    private static void setBigSmall(String isBigSmall) {
        if (isBigSmall.equals("small")){small = true;}
    }

    private static void setOpenList(String isOpenList) {
        if (isOpenList.equals("no open")){withoutOpen = true;}
    }

    private static void setAlgoToUse(String algoName) {
        switch (algoName) {
            case "BFS": algoToUse = numBFS;break;
            case "DFID": algoToUse = numDFID;break;
            case "A*": algoToUse = numAStar;break;
            case "IDA*": algoToUse = numIDAStar;break;
            case "DFBnB": algoToUse = numDFBnB;break;
        }
    }
}
