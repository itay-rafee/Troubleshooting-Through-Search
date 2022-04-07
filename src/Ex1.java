import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Ex1 {
    private final static int algorithmType = 0, openListCheck = 1, bigOrSmall = 2;
    private final static int BFS = 0, DFID = 1, AStar = 2, IDAStar = 3, DFBnB = 4;
    private final static int emptyCell = 0, redCell = 1, blueCell = 2, greenCell = 3, yellowCell = 4;
    private final static int smallGameSize = 3, bigGameSize = 5;
    private static boolean small = false;
    private static boolean withoutOpen = false;
    private static int algoToUse = 0;
    private static int[][] board, goalBoard;
    public static void main(String[] args) {
        String filename = "input.txt";
        setup(filename);

    }

    private static void setup(String filename) {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            for (int i = 0; i < 3 && myReader.hasNextLine(); i++) {
                String data = myReader.nextLine();
                switch (i) {
                    case algorithmType -> setAlgoToUse(data);
                    case openListCheck -> setOpenList(data);
                    case bigOrSmall -> setBigSmall(data);
                }
            }

            // set start & goal board
            if (small){
                board = setBoard(smallGameSize, myReader);
                myReader.nextLine();
                goalBoard = setBoard(smallGameSize, myReader);
            }
            else { // is big
                board = setBoard(bigGameSize, myReader);
                myReader.nextLine();
                goalBoard = setBoard(bigGameSize, myReader);
            }

            // set the goal board

//            if (withoutOpen){}
//            else {} // is with open list
            System.out.println("the board is"+Arrays.deepToString(board));
            System.out.println("the goal board is"+Arrays.deepToString(goalBoard));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int[][] setBoard(int boardSize, Scanner myReader) {
        int[][] newBoard = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize && myReader.hasNextLine(); i++) {
            String data = myReader.nextLine();
            String[] colorOfMarbles = data.split(",");
            for (int j = 0; j < boardSize; j++){
                String marble = colorOfMarbles[j];
                switch (marble) {
                    case "_" -> newBoard[i][j] = emptyCell;
                    case "R" -> newBoard[i][j] = redCell;
                    case "B" -> newBoard[i][j] = blueCell;
                    case "G" -> newBoard[i][j] = greenCell;
                    case "Y" -> newBoard[i][j] = yellowCell;
                }
            }
        }
        return newBoard;
    }

    private static void setBigSmall(String isBigSmall) {
        if (isBigSmall.equals("small")){small = true;}
    }

    private static void setOpenList(String isOpenList) {
        if (isOpenList.equals("no open")){withoutOpen = true;}
    }

    private static void setAlgoToUse(String algoName) {
        switch (algoName) {
            case "BFS" -> algoToUse = BFS;
            case "DFID" -> algoToUse = DFID;
            case "A*" -> algoToUse = AStar;
            case "IDA*" -> algoToUse = IDAStar;
            case "DFBnB" -> algoToUse = DFBnB;
        }
    }

//        switch (algoName){
//            case "BFS":
//                vectorCommand[algorithmType] = 0;
//                break;
//            case "DFID":
//                vectorCommand[algorithmType] = 1;
//                break;
//            case "A*":
//                vectorCommand[algorithmType] = 2;
//                break;
//            case "IDA*":
//                vectorCommand[algorithmType] = 3;
//                break;
//            case "DFBnB":
//                vectorCommand[algorithmType] = 4;
//                break;
//        }


//    private static void BFS(){
//
//    }
//
//    private static void DFID(){
//
//    }
//
//    private static void AStar(){
//
//    }
//
//    private static void IDAStar(){
//
//    }
//
//    private static void DFBnB(){
//
//    }
}
