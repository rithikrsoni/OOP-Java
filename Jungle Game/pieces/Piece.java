
package jungle.pieces;

import jungle.Player;
import jungle.squares.Square;
/**
 * @param Piece class functions to set the rules and expected behaviour of the piece. 
 * Rank is assigned to a piece which determines what it can take or be taken by 
 * the method can leap horizontally  and vertically and can swim are all set to false as all pieces are not expected to be able to do these functions 
 */
public class Piece {
    private int rank;
    private Player owner;
    private Square square;

    public Piece(Player owner, Square square, int rank) {
        this.owner = owner;
        this.rank = rank;
        this.square = square;
    }

    public void move(Square[][] board, int toRow, int toCol) {
        Square targetSquare = board[toRow][toCol];
        this.square = targetSquare;
        targetSquare.setPiece(this);
    }

    public int getRank() {
        return rank;
    }

    public Square getSquare() {
        return square;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isOwnedBy(Player player) {
        if (player == owner) {
            return true;
        }
        return false;
    }

    public int getStrength() {
        return rank;
    }

    public boolean canSwim() {
        return false;
    }

    public boolean canLeapHorizontally() {
        return false;
    }

    public boolean canLeapVertically() {
        return false;
    }

    public void move(Square newSquare) {
        this.square = square;
        newSquare.setPiece(this);
        this.square = newSquare;

    }

    public boolean canDefeat(Piece target) {

        if (getStrength() == 1 && target.getStrength() == 8) {
            return true;
        }
        return target.getRank() >= getRank();
    }

    public void beCaptured() {

    }

    public boolean canMoveTo(Square targetSquare) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canMoveTo'");
    }
}
