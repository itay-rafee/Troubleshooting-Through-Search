public class HeuristicFunctions {
    public static int heuristicFunctions(int[][] board) {
        int[][][] currentStatus = getCurentStatus(board);
        int ans = 0;
        ans += finedCost(currentStatus[Ex1.redCell - 1], Ex1.goalBoard[Ex1.redCell - 1], Ex1.redCost);
        ans += finedCost(currentStatus[Ex1.blueCell - 1], Ex1.goalBoard[Ex1.blueCell - 1], Ex1.blueCost);
        ans += finedCost(currentStatus[Ex1.greenCell - 1], Ex1.goalBoard[Ex1.greenCell - 1], Ex1.greenCost);
        if (!Ex1.small)
            ans += finedCost(currentStatus[Ex1.yellowCell - 1], Ex1.goalBoard[Ex1.yellowCell - 1], Ex1.yellowCost);
        return ans;
    }

    private static int[][][] getCurentStatus(int[][] board) {
        int[][][] currentStatus;
        int[] indColor = {0,0,0,0};//Red = 0, indBlue = 0, indGreen = 0, indYellow = 0;
        if (Ex1.small) {
            currentStatus = new int[Ex1.smallGameSize][Ex1.numOfMarblesSmall][2];
        }
        else {
            currentStatus = new int[Ex1.bigGameSize - 1][Ex1.numOfMarblesBig][2];
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] - 1 != -1){
                    currentStatus[board[i][j] - 1][indColor[board[i][j] - 1]][0] = i;
                    currentStatus[board[i][j] - 1][indColor[board[i][j] - 1]++][1] = j;
                }
            }
        }
        return currentStatus;
    }

    private static int finedCost(int[][] currentStatus, int[][] goal, int cost) {
        int ans = 1000;
        if (Ex1.small) {
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{0,1}));
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{1,0}));
        }
        else {
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{0,1,2,3})); //1
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{0,1,3,2})); //2
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{0,2,3,1})); //3
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{0,2,1,3})); //4
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{0,3,1,2})); //5
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{0,3,2,1})); //6

            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{1,0,2,3})); //1
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{1,0,3,2})); //2
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{1,2,3,0})); //3
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{1,2,0,3})); //4
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{1,3,0,2})); //5
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{1,3,2,0})); //6

            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{2,1,0,3})); //1
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{2,1,3,0})); //2
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{2,0,3,1})); //3
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{2,0,1,3})); //4
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{2,3,1,0})); //5
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{2,3,0,1})); //6

            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{3,1,2,0})); //1
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{3,1,0,2})); //2
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{3,2,0,1})); //3
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{3,2,1,0})); //4
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{3,0,1,2})); //5
            ans = Math.min(ans, costFoStat(currentStatus, goal, new int[]{3,0,2,1})); //6
        }
        return ans * cost;
    }

    private static int costFoStat(int[][] currentStatus, int[][] goal, int[] stat) {
        int ans = 0;
        for (int i = 0; i < goal.length; i++) {
            ans += Math.abs(currentStatus[stat[i]][0] - goal[i][0]) + Math.abs(currentStatus[stat[i]][1] - goal[i][1]);
        }
        return ans;
    }
}
