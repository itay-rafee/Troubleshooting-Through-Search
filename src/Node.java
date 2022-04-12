import java.util.Arrays;
import java.util.Comparator;

public class Node implements Comparable<Node> {

    static final int firstPlace = 0, nextPlace = 1;
    private final int _sizeBoard;
    private final int _sizeSpace;
    private final int _cost;
    private int i, j, newI, newJ, indexSpace = 0, circleMove = 0;
    private final int[] _space, _move;
    private final int[][] _board;
    private boolean has_child = false;
    private final String _id;
    private final Node _father;


    public int get_cost() {
        return _cost;
    }

    public Node get_father() {
        return _father;
    }

    public int[][] get_board() {
        return _board;
    }

    public int[] get_space() {
        return _space;
    }

    public String get_id() {
        return _id;
    }

    public Node get_child() {
        int newPlace = getPlace(newI, newJ);
        // set new board
        int [][] newBoard = new int[_sizeBoard][_sizeBoard];
        for(int i = 0; i < _sizeBoard; i++)
        {
            System.arraycopy(_board[i], 0, newBoard[i], 0, _sizeBoard);
        }
        newBoard[i][j] = _board[newI][newJ];
        newBoard[newI][newJ] = _board[i][j];

        // set space
        int[] newSpace = new int[_sizeSpace];
        System.arraycopy(_space, 0, newSpace, 0, _sizeSpace);
        newSpace[indexSpace] = newPlace;

        // set move
        int[] newMove = {newPlace, getPlace(i, j)};

        // set Id
        String newId = Arrays.deepToString(newBoard);

        // set cost
        int cost = _cost;
        switch (_board[newI][newJ]){
            case Ex1.redCell:
            case Ex1.yellowCell: cost += 1;break;
            case Ex1.blueCell: cost += 2;break;
            case Ex1.greenCell: cost += 10;break;
        }
        return new Node(this, newBoard, newSpace, newMove, newId, cost);
    }

    public boolean has_next_child() {
        boolean has_not_child = true;
        while (has_not_child && indexSpace < _sizeSpace)
        {
            if (circleMove == 4){
                ++indexSpace;
                setI();setJ();
                circleMove = 0;
            }
            else{
                switch (circleMove) {
                    case 0: has_not_child = check(i-1, j);break;
                    case 1: has_not_child = check(i, j+1);break;
                    case 2: has_not_child = check(i+1, j);break;
                    case 3: has_not_child = check(i, j-1);break;
                }
            }
            ++circleMove;
        }
        has_child = !has_not_child;
        return has_child;
    }

    private boolean check(int i, int j) {
        boolean ans = true;
        if ((i < _sizeBoard && i >-1 && j < _sizeBoard && j > -1 && _board[i][j] != 0 && checkMove(i,j))){
            newI = i;
            newJ = j;
            ans = false;
        }
        return ans;
    }

    private boolean checkMove(int i, int j) {
        return !(_move[firstPlace] == _space[indexSpace] && _move[nextPlace] == getPlace(i,j));
    }

    private int getPlace (int i, int j) {
        return i * 10 + j;
    }

    public Node(Node father, int[][] board, int[] space, int[] move, String id, int cost){
        _father = father;
        _board = board;
        _sizeBoard = board.length;
        _space = space;
        _sizeSpace = space.length;
        _move = move;
        _id = id;
        _cost = cost;
        setI();setJ();
    }

    public String getPath(){
        int i1 = _move[firstPlace] / 10 + 1;
        int j1 = _move[firstPlace] % 10 + 1;
        int i2 = _move[nextPlace] / 10 + 1;
        int j2 = _move[nextPlace] % 10 + 1;
        return "("+i1+","+j1+"):"+ Ex1.arrChar[_board[i2-1][j2-1]]+":("+i2+','+j2+')';
    }

    private void setJ() {
        if (_sizeSpace > indexSpace){
            j = _space[indexSpace] % 10;
        }
    }

    private void setI() {
        if (_sizeSpace > indexSpace){
            i = _space[indexSpace] / 10;
        }
    }


    @Override
    public int compareTo(Node o) {
        return Integer.compare(_cost, o.get_cost());
    }
}
