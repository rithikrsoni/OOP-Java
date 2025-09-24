package jungle.squares;

import jungle.Player;
import jungle.pieces.Piece;

public abstract class Square {
    private Piece piece;
    public Player owner;

    public Square(Player owner) {
        this.owner = owner;
    }

    public Square() {

    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean hasEnemyPiece(Player player) {
        return piece != null && piece.getOwner() != player;
    }

    public boolean isOwnedBy(Player player) {
        return true;
    }

    public boolean isWater() {
        return false; 
    }

    public boolean isDen() {
        return false; 
    }

    public boolean isTrap() {
        return false; 
    }



}

