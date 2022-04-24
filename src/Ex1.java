import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ex1 {
    ////////////////// Magic Number ///////////////////
    private final static int algorithmType = 0, openListCheck = 1, bigOrSmall = 2;
    private final static int numBFS = 0, numDFID = 1, numAStar = 2, numIDAStar = 3, numDFBnB = 4;
    public final static int smallGameSize = 3, bigGameSize = 5, numOfMarblesSmall = 2, numOfMarblesBig = 4;
    public final static int emptyCell = 0, redCell = 1, blueCell = 2, greenCell = 3, yellowCell = 4;
    public final static int redCost = 1, blueCost = 2, greenCost = 10, yellowCost = 1;
    public final static char[] arrChar = {'_', 'R', 'B', 'G', 'Y'};
    public static boolean small = false;
    public static boolean withoutOpen = false, needHeuristicFun = true;
    private static int algoToUse = 0;
    private static Node start;
    private static String goalId;
    public static int[][][] goalBoard;

    public static void main(String[] args) {
        String filename = "input.txt";
        setup(filename);
        run();
    }

    /**
     * In this method we run the algorithm of the game.
     */
    private static void run() {
        switch (algoToUse) {
            case numBFS:
                BFS.bfs(start, goalId);
                break;
            case numDFID:
                DFID.DFID(start, goalId);
                break;
            case numAStar:
                AStar.aStar(start, goalId);
                break;
            case numIDAStar:
                IDAStar.IDAStar(start, goalId);
                break;
            case numDFBnB:
                DFBnB.DFBnB(start, goalId);
                break;
        }
    }

    /**
     * Setup the data of the game
     */
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
                goalBoard = new int[smallGameSize][numOfMarblesSmall][2];
                SetGoal(smallGameSize, myReader);
            }
            else { // is big
                start = getNode(bigGameSize, myReader);
                myReader.nextLine();
                goalBoard = new int[bigGameSize - 1][numOfMarblesBig][2];
                SetGoal(bigGameSize, myReader);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void SetGoal(int boardSize, Scanner myReader) {
        int[][] newBoard = new int[boardSize][boardSize];
        int indRed = 0, indBlue = 0, indGreen = 0, indYellow = 0;
        for (int i = 0; i < boardSize && myReader.hasNextLine(); i++) {
            String data = myReader.nextLine();
            String[] colorOfMarbles = data.split(",");
            for (int j = 0; j < boardSize; j++){
                String marble = colorOfMarbles[j];
                if (marble.equals("_")){
                    newBoard[i][j] = emptyCell;
                }
                else{
                    switch (marble) {
                        case "R":
                            newBoard[i][j] = redCell;
                            goalBoard[redCell - 1][indRed][0] = i;
                            goalBoard[redCell - 1][indRed++][1] = j;
                            break;
                        case "B":
                            newBoard[i][j] = blueCell;
                            goalBoard[blueCell - 1][indBlue][0] = i;
                            goalBoard[blueCell - 1][indBlue++][1] = j;
                            break;
                        case "G":
                            newBoard[i][j] = greenCell;
                            goalBoard[greenCell - 1][indGreen][0] = i;
                            goalBoard[greenCell - 1][indGreen++][1] = j;
                            break;
                        case "Y":
                            newBoard[i][j] = yellowCell;
                            goalBoard[yellowCell - 1][indYellow][0] = i;
                            goalBoard[yellowCell - 1][indYellow++][1] = j;
                            break;
                    }
                }
            }
        }
        goalId = Arrays.deepToString(newBoard);
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

//    private static void setColor(int[][] board, int i, int j, String marble) {
//        switch (marble) {
//            case "R": board[i][j] = redCell;break;
//            case "B": board[i][j] = blueCell;break;
//            case "G": board[i][j] = greenCell;break;
//            case "Y": board[i][j] = yellowCell;break;
//        }
//    }


    private static void setBigSmall(String isBigSmall) {
        if (isBigSmall.equals("small")){small = true;}
    }

    private static void setOpenList(String isOpenList) {
        if (isOpenList.equals("no open")){withoutOpen = true;}
    }

    private static void setAlgoToUse(String algoName) {
        switch (algoName) {
            case "BFS": algoToUse = numBFS;needHeuristicFun = false;break;
            case "DFID": algoToUse = numDFID;needHeuristicFun = false;break;
            case "A*": algoToUse = numAStar;break;
            case "IDA*": algoToUse = numIDAStar;break;
            case "DFBnB": algoToUse = numDFBnB;break;
        }
    }
}
