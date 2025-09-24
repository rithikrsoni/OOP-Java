package jungle;

import java.util.ArrayList;
import java.util.List;
import jungle.pieces.Lion;
import jungle.pieces.Piece;
import jungle.pieces.Rat;
import jungle.pieces.Tiger;
import jungle.squares.Den;
import jungle.squares.PlainSquare;
import jungle.squares.Square;
import jungle.squares.Trap;
import jungle.squares.WaterSquare;

/**
 * The class initalises the jungle game
 * 
 * @param initialize          Board is a function that intialises the board.
 * @param initialize          Trap is to place the traps onto the board.
 * @param setupSpecialSquares is the function where the water is placed onto the
 *                            board.
 * @param intialize           Pieces is for the pieces to be placed. Most pieces
 *                            behave the same with the exception of the rat,
 *                            lion and tiger which behave differently.
 * 
 */
public class Game {

    public static final int HEIGHT = 9;
    public static final int WIDTH = 7;
    public static final int[] WATER_ROWS = { 3, 4, 5 };
    public static final int[] WATER_COLS = { 1, 2, 4, 5 };
    public static final int DEN_COL = 3;

    private Player player0;
    private Player player1;
    private Square[][] board;
    private Piece[][] pieceBoard;
    private Player currentPlayer;

    public Game(Player p0, Player p1) {
        this.player0 = p0;
        this.player1 = p1;
        this.board = new Square[HEIGHT][WIDTH];
        this.pieceBoard = new Piece[HEIGHT][WIDTH]; // Initialise pieceBoard
        this.currentPlayer = player0;

        initializeBoard();
        initializeDen();
        initializeTrap();
        initializePieces();
        setupSpecialSquares();
    }

    private void initializeBoard() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                board[row][col] = new PlainSquare();
            }
        }
    }

    private void initializeTrap() {
        board[0][2] = new Trap(player0);
        board[1][3] = new Trap(player0);
        board[0][4] = new Trap(player0);
        board[8][2] = new Trap(player1);
        board[7][3] = new Trap(player1);
        board[8][4] = new Trap(player1);
    }

    private void initializeDen() {
        board[0][DEN_COL] = new Den(player0);
        board[8][DEN_COL] = new Den(player1);
        player0.setDenCoords(0, DEN_COL);
        player1.setDenCoords(8, DEN_COL);
    }

    private void setupSpecialSquares() {
        for (int waterX : WATER_ROWS) {
            for (int waterY : WATER_COLS) {
                board[waterX][waterY] = new WaterSquare();
            }
        }
    }

    private void initializePieces() {
        pieceBoard[0][2] = new Rat(player0, board[0][2]);
        pieceBoard[0][0] = new Lion(player0, board[0][0]);
        pieceBoard[0][6] = new Tiger(player0, board[0][6]);
        pieceBoard[1][1] = new Piece(player0, board[1][1], 3); // Dog
        pieceBoard[2][2] = new Piece(player0, board[2][2], 5); // Leopard
        pieceBoard[1][5] = new Piece(player0, board[1][5], 2); // Cat
        pieceBoard[2][4] = new Piece(player0, board[2][4], 4); // Wolf
        pieceBoard[2][6] = new Piece(player0, board[2][6], 8); // Elephant

        pieceBoard[8][1] = new Tiger(player1, board[8][1]);
        pieceBoard[8][6] = new Lion(player1, board[8][6]);
        pieceBoard[6][6] = new Rat(player1, board[6][6]);
        pieceBoard[6][0] = new Piece(player1, board[6][0], 8); // Elephant
        pieceBoard[7][1] = new Piece(player1, board[7][1], 2); // Cat
        pieceBoard[6][2] = new Piece(player1, board[6][2], 4); // Wolf
        pieceBoard[6][4] = new Piece(player1, board[6][4], 5); // Leopard
        pieceBoard[7][5] = new Piece(player1, board[7][5], 3); // Dog
    }

    public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalMoveException {
        Piece piece = pieceBoard[fromRow][fromCol];
        Square destination = board[toRow][toCol];

        if (piece == null || piece.getOwner() != currentPlayer) {
            throw new IllegalMoveException("It is not your turn");
        }

        List<Coordinate> legalMoves = getLegalMoves(fromRow, fromCol);
        if (!legalMoves.contains(new Coordinate(toRow, toCol))) {
            throw new IllegalMoveException("Invalid move.");
        }

        if (destination.hasEnemyPiece(piece.getOwner())) {
            Piece enemy = destination.getPiece();
            if (piece.canDefeat(enemy)) {
                capturePiece(enemy);
            } else {
                throw new IllegalMoveException("Cannot move to a stronger piece's square");
            }
        }

        piece.move(board, toRow, toCol);
        isDenCaptured(currentPlayer);
        switchPlayer();
    }

    public List<Coordinate> getLegalMoves(int row, int col) {
        List<Coordinate> legalMoves = new ArrayList<>();
        Piece piece = pieceBoard[row][col];

        if (piece == null) {
            return legalMoves;
        }

        // Define possible directions for movement: up, down, left, right
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (isInBounds(newRow, newCol)) {
                Square targetSquare = board[newRow][newCol];

                if (piece.canMoveTo(targetSquare)) {
                    legalMoves.add(new Coordinate(newRow, newCol));
                }
            }
        }

        return legalMoves;
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < HEIGHT && col >= 0 && col < WIDTH;
    }

    private void capturePiece(Piece piece) {
        removePiece(piece);
    }

    public void removePiece(Piece piece) {
        piece = null;
    }

    private boolean isDenCaptured(Player player) {
        if (pieceBoard[player.denCoords.row][player.denCoords.col] != null
                && pieceBoard[player.denCoords.row][player.denCoords.col].getOwner() != currentPlayer) {
            return true;
        }
        return false;
    }

    private void switchPlayer() {
        if (currentPlayer == player0) {
            currentPlayer = player1;
        } else {
            currentPlayer = player0;
        }
    }

}
