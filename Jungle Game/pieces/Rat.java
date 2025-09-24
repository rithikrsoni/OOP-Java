package jungle.pieces;
import jungle.Player;
import jungle.squares.Square;
/**
 * @param canSwim is overriden as the rat can behave differently to other pieces by being able to swim and therefore the boolean must be overridden. 
 */
public class Rat extends Piece{

    public Rat(Player owner, Square square){
        super(owner,square,1); // rat is rank one

    }
    @Override
    public boolean canSwim(){
        return true;
    }

    
}