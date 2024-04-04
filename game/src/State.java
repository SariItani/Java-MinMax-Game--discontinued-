import java.util.LinkedList;

public class State {
    private int[][] board;
    private int currentPlayer;
    private int size;

    public State(int size) {
        this.size = size;
        this.board = new int[size][size];
    }

    public State(int[][] board, int currentPlayer) {
        this.size = board.length;
        this.currentPlayer = currentPlayer;
        this.board = new int[board.length][board.length];
        this.clone(board);
    }

    public void printBoard() {
        for (int i[] : this.board) {
            for (int j : i) {
                System.err.printf("%4d", j);
            }
            System.err.println("\n");
        }
    }

    public void initializeGame() {
        this.currentPlayer = -1;
        int middle = this.size / 2 - 1;
        this.board[middle][middle] = -1;
        this.board[middle][middle + 1] = 1;
        this.board[middle + 1][middle] = 1;
        this.board[middle + 1][middle + 1] = -1;
    }

    public boolean outOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= this.size || y >= this.size);
    }

    public LinkedList<Moves> getLegalMoves() {
        LinkedList<Moves> legalMoves = new LinkedList<>();
        for (int i = 0; i < this.size; i++)
            for (int j = 0; j < this.size; j++)
                if (this.board[i][j] == this.currentPlayer)
                    legalMoves.addAll(getLegalMoves(i, j));
        return legalMoves;
    }

    public LinkedList<Moves> getLegalMoves(int x, int y) {
        // init legalMoves LL
        LinkedList<Moves> legalMoves = new LinkedList<>();
        // init i and j
        int i = x, j = y - 1;
        // descend
        while (!outOfBounds(i, j) && this.board[i][j] == this.board[x][y] * -1)
            j--;
        if (j != y - 1 && !outOfBounds(x, y) && this.board[i][j] == 0)
            legalMoves.add(new Moves(i, j, x, y));
        // left
        i = x - 1;
        j = y;
        while (!outOfBounds(i, j) && this.board[i][j] == this.board[x][y] * -1)
            i--;
        if (i != x - 1 && !outOfBounds(x, y) && this.board[i][j] == 0)
            legalMoves.add(new Moves(i, j, x, y));
        // ascend
        i = x;
        j = y + 1;
        while (!outOfBounds(i, j) && this.board[i][j] == this.board[x][y] * -1)
            j++;
        if (j != y + 1 && !outOfBounds(x, y) && this.board[i][j] == 0)
            legalMoves.add(new Moves(i, j, x, y));
        // right
        i = x + 1;
        j = y;
        while (!outOfBounds(i, j) && this.board[i][j] == this.board[x][y] * -1)
            i++;
        if (i != x + 1 && !outOfBounds(x, y) && this.board[i][j] == 0)
            legalMoves.add(new Moves(i, j, x, y));
        // up right
        i = x + 1;
        j = y + 1;
        while (!outOfBounds(i, j) && this.board[i][j] == this.board[x][y] * -1) {
            i++;
            j++;
        }
        if (i != x + 1 && j != y + 1 && !outOfBounds(x, y) && this.board[i][j] == 0)
            legalMoves.add(new Moves(i, j, x, y));
        // down left
        i = x - 1;
        j = y - 1;
        while (!outOfBounds(i, j) && this.board[i][j] == this.board[x][y] * -1) {
            i--;
            j--;
        }
        if (i != x - 1 && j != y - 1 && !outOfBounds(x, y) && this.board[i][j] == 0)
            legalMoves.add(new Moves(i, j, x, y));
        // down right
        i = x - 1;
        j = y + 1;
        while (!outOfBounds(i, j) && this.board[i][j] == this.board[x][y] * -1) {
            i--;
            j++;
        }
        if (i != x - 1 && j != y + 1 && !outOfBounds(x, y) && this.board[i][j] == 0)
            legalMoves.add(new Moves(i, j, x, y));
        // up left
        i = x + 1;
        j = y - 1;
        while (!outOfBounds(i, j) && this.board[i][j] == this.board[x][y] * -1) {
            i++;
            j--;
        }
        if (i != x + 1 && j != y - 1 && !outOfBounds(x, y) && this.board[i][j] == 0)
            legalMoves.add(new Moves(i, j, x, y));

        return legalMoves;
    }

    public State play(int x, int y) {
        int[][] afterMatrix = this.cloneMatrix(this.board);
        for (Moves move : getLegalMoves()) {
            if (move.dsx == x && move.dsy == y)
                play(move, afterMatrix);
        }
        return new State(afterMatrix, this.currentPlayer * -1);
    }

    public State play(Moves move, int[][] afterMatrix) {
        int dsi = move.dsx, dsj = move.dsy, sri = move.srx, srj = move.sry;
        
    }

    public int[][] cloneMatrix(int[][] matrix) {
        int[][] clone = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                clone[i][j] = matrix[i][j];
        return clone;
    }

    public void clone(int[][] board) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
                this.board[i][j] = board[i][j];
    }
}
