import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Node implements Comparable<Node> {

    static final int firstPlace = 0, nextPlace = 1;
    private final int _sizeBoard;
    private final int _sizeSpace;
    private int _heuristicCost = 0, _regularCost;
    private final int _cost;
    private int i, j, newI, newJ, indexSpace = 0, circleMove = 0;
    private final int[] _space, _move;
    private final int[][] _board;
    private final String _id;
    private final Node _father;
    private boolean out = false;

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    public void set_regularCost(int _regularCost) {
        this._regularCost = _regularCost;
    }

    public int get_regularCost() {
        return _regularCost;
    }


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
        int[][] newBoard = new int[_sizeBoard][_sizeBoard];
        for (int i = 0; i < _sizeBoard; i++) {
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
        switch (_board[newI][newJ]) {
            case Ex1.redCell:
            case Ex1.yellowCell:
                cost += Ex1.yellowCost;
                break;
            case Ex1.blueCell:
                cost += Ex1.blueCost;
                break;
            case Ex1.greenCell:
                cost += Ex1.greenCost;
                break;
        }

        if (Ex1.needHeuristicFun) {
            int heuristicCost = HeuristicFunctions.heuristicFunctions(newBoard);
            int regularCost = cost - _heuristicCost;
            int totalCost = regularCost + heuristicCost;
            return new Node(this, newBoard, newSpace, newMove, newId, totalCost, regularCost, heuristicCost);
        }
        return new Node(this, newBoard, newSpace, newMove, newId, cost);
    }

    public boolean has_next_child() {
        boolean has_not_child = true;
        while (has_not_child && indexSpace < _sizeSpace) {
            if (circleMove == 4) {
                ++indexSpace;
                setI();
                setJ();
                circleMove = 0;
            } else {
                switch (circleMove) {
                    case 0:
                        has_not_child = check(i - 1, j);
                        break;
                    case 1:
                        has_not_child = check(i, j + 1);
                        break;
                    case 2:
                        has_not_child = check(i + 1, j);
                        break;
                    case 3:
                        has_not_child = check(i, j - 1);
                        break;
                }
                ++circleMove;
            }
        }
        return !has_not_child;
    }

    private boolean check(int i, int j) {
        boolean ans = true;
        if ((i < _sizeBoard && i > -1 && j < _sizeBoard && j > -1 && _board[i][j] != 0 && checkMove(i, j))) {
            newI = i;
            newJ = j;
            ans = false;
        }
        return ans;
    }

    private boolean checkMove(int i, int j) {
        return !(_move[firstPlace] == _space[indexSpace] && _move[nextPlace] == getPlace(i, j));
    }

    private int getPlace(int i, int j) {
        return i * 10 + j;
    }

    public Node(Node father, int[][] board, int[] space, int[] move, String id, int cost) {
        _father = father;
        _board = board;
        _sizeBoard = board.length;
        _space = space;
        _sizeSpace = space.length;
        _move = move;
        _id = id;
        _cost = cost;
        _regularCost = cost;
        setI();setJ();
    }

    public Node(Node father, int[][] board, int[] space, int[] move, String id, int cost,int regularCost, int heuristiCost) {
        _father = father;
        _board = board;
        _sizeBoard = board.length;
        _space = space;
        _sizeSpace = space.length;
        _move = move;
        _id = id;
        _cost = cost;
        setI();setJ();
        _heuristicCost = heuristiCost;
        _regularCost = regularCost;
    }

    public String getPath() {
        int i1 = _move[firstPlace] / 10 + 1;
        int j1 = _move[firstPlace] % 10 + 1;
        int i2 = _move[nextPlace] / 10 + 1;
        int j2 = _move[nextPlace] % 10 + 1;
        return "(" + i1 + "," + j1 + "):" + Ex1.arrChar[_board[i2 - 1][j2 - 1]] + ":(" + i2 + ',' + j2 + ')';
    }

    private void setJ() {
        if (_sizeSpace > indexSpace) {
            j = _space[indexSpace] % 10;
        }
    }

    private void setI() {
        if (_sizeSpace > indexSpace) {
            i = _space[indexSpace] / 10;
        }
    }


    @Override
    public int compareTo(Node o) {
        if (Ex1.algoToUse == 4) // if it is DFBnB algorithm
            return Integer.compare(_regularCost, o.get_regularCost());
        return Integer.compare(_cost, o.get_cost());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(_id, node._id);
    }

    @Override
    public String toString() {
        return "Node{" +
//                "_h=" + _heuristicCost +
                ", _r=" + _id +
                ", _c=" + _cost +
//                ", _id='" + _id + '\'' +
                '}';
    }
}
