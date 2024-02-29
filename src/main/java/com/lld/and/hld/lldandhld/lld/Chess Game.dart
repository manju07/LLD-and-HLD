Chess Game Low Level Design

2 Players will be playing

public class Chess {
    ChessBoard chessBoard;
    List<Player> players;
    Player currentPlayer;
    List<Move> movesList;
    GameStatus gameStatus;

    public boolean playerMove(CellPosition fromPosition, CellPosition toPosition, Piece piece);
    public void resetGame();
    private void toggleMove();
}

public class Account {
    String fName;
    String lName;
    String emailId;
    String password;
    String phoneNumber;
    Address address;
    AccountStatus accountStatus; 
}

public enum AccountStatus {
    ACTIVE, INACTIVE, BLOCKED;
}

public class ChessBoard {
    List<List<Cell>> chessBoard;

    public void resetBoard();
    public void updateBoard(Move move);
}

public class Player {
    Account account;
    Color color;
    Time timeleft;
}

public enum GameStatus {
    ACTIVE, WHITE_WIN, BLACK_WIN, DRAW;
}

public class Cell {
    Color color;
    Piece piece;
    CellPosition cellPosition;
}

public enum Color {
    BLACK, WHITE;
}

public class CellPosition {
    int row;
    int column;
}

class Move {

    Player turn;
    CellPosition fromPosition;
    CellPosition toPosition;
    Piece currentPiece;
    Piece killedPiece;
}

public abstract class Piece {
    Color color;
    public void move(CellPosition fromPosition, CellPosition toPosition);
    public List<Move> possibleMoves(int fromPosition, int toPosition);
    public Boolean validateMove(CellPosition fromPosition, CellPosition toPosition);
}


public class King extends Piece {
    public void move(CellPosition fromPosition, CellPosition toPosition);
    public List<Move> possibleMoves(int fromPosition, int toPosition);
    public Boolean validateMove(CellPosition fromPosition, CellPosition toPosition);
}

public class Queen extends Piece {
    public void move(CellPosition fromPosition, CellPosition toPosition);
    public List<Move> possibleMoves(int fromPosition, int toPosition);
    public Boolean validateMove(CellPosition fromPosition, CellPosition toPosition);
}

public class Bishop extends Piece {
    public void move(CellPosition fromPosition, CellPosition toPosition);
    public List<Move> possibleMoves(int fromPosition, int toPosition);
    public Boolean validateMove(CellPosition fromPosition, CellPosition toPosition);
}

public class Rook extends Piece {
    public void move(CellPosition fromPosition, CellPosition toPosition);
    public List<Move> possibleMoves(int fromPosition, int toPosition);
    public Boolean validateMove(CellPosition fromPosition, CellPosition toPosition);
}

public class Pawn extends Piece {
    public void move(CellPosition fromPosition, CellPosition toPosition);
    public List<Move> possibleMoves(int fromPosition, int toPosition);
    public Boolean validateMove(CellPosition fromPosition, CellPosition toPosition);
}


