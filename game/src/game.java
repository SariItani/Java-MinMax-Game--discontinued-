public class game {
    public static void main(String[] args) {
        int size = 8;
        State state = new State(size);
        state.initializeGame();
        state.printBoard();
        for (Moves move : state.getLegalMoves()) {
            move.printMove();
        }
    }
}
