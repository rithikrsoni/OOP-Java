package jungle;

import java.util.ArrayList;
import java.util.List;
import jungle.pieces.Piece;
import jungle.squares.Den;
import jungle.squares.Square;
/**
 * This class player containts the information of the player ofd the game such as the name, id and the methods associated with the player.
 * 
 */
public class Player {

    private final int id;
    private final List<Piece> pieces;
    private final Square den;
    private boolean denCaptured;
    public Coordinate denCoords;
    private String name;
    private int pieceCount;

    public Player(String name, int id) {
        this.id = id;
        this.name = name;
        this.den = new Den(this);
        this.pieces = new ArrayList<>();
        this.denCaptured = false;
        this.denCoords = denCoords;
        this.pieceCount = 0;
    }

    public void setDenCoords(int row, int col) {
        denCoords.row = row;
        denCoords.col = col;
    }

    public String getName() {
        return name;
    }

    public int getPlayerNumber() {
        return id;
    }

    public Coordinate getDen() {
        return denCoords;
    }

    public void captureDen() {
        denCaptured = true;
    }

    public boolean hasCapturedDen() {
        return denCaptured;
    }

    public void gainOnePiece() {
        pieceCount++;
    }

    public void loseOnePiece() {
        pieceCount--;
    }

    public void addStartingPiece(Piece piece) {
        pieces.add(piece);

    }

    public List<Piece> getPieces() {
        return new ArrayList<>(pieces);
    }

    public boolean hasPieces() {
        if (pieceCount > 0) {
            return true;
        }
        return false;
    }
}
