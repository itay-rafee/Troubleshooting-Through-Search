import java.util.Arrays;

public class Node {
    static final int firstPlace = 0, nextPlace = 1;
    int _sizeBoard, _sizeSpace, i, j, newI, newJ, indexSpace = 0, circleMove = 0;
    int[] _space, _move;
    int[][] _board;
    boolean has_child = false;
    String _id;
    Node _father;


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

        //set Id
        String newId = Arrays.deepToString(newBoard);

        return new Node(this, newBoard, newSpace, newMove, newId);
    }

    public boolean has_next_child() {
        boolean has_not_child = true;
        while (has_not_child && indexSpace < _sizeSpace)
        {
            switch (circleMove) {
                case 0: has_not_child = check(i-1, j);break;
                case 1: has_not_child = check(i, j+1);break;
                case 2: has_not_child = check(i+1, j);break;
                case 3: has_not_child = check(i, j-1);break;
            }
            ++circleMove;
            if (circleMove == 4){
                ++indexSpace;
                setI();setJ();
                circleMove = 0;
            }
        }
        has_child = !has_not_child;
        return has_child;
    }

    private boolean check(int i, int j) {
        boolean ans = false;
        if ((i < _sizeBoard && i >-1 && j < _sizeBoard && j > -1 && _board[i][j] != 0 && checkMove(i,j))){
            newI = i;
            newJ = j;
            ans = true;
        }
        return ans;
    }

    private boolean checkMove(int i, int j) {
        return !(_move[firstPlace] == _space[indexSpace]) && _move[nextPlace] == getPlace(i,j);
    }

    private int getPlace (int i, int j) {
        return i * 10 + j;
    }

    public Node(Node father, int[][] board, int[] space, int[] move, String id){
        _father = father;
        _board = board;
        _sizeBoard = board.length;
        _space = space;
        _sizeSpace = space.length;
        _move = move;
        _id = id;
        setI();
        setJ();
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
}
