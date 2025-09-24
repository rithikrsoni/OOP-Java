package jungle.pieces;
import jungle.Player;
import jungle.squares.Square;
/**
 * @param booleans are overriden because the lion can behave differenly with regards to its movement. Rank is also specified because it is a constant 
 */
public class Lion extends Piece{

    public Lion(Player owner, Square square){
       super(owner,square,7); 
    }

    @Override
    public boolean canLeapHorizontally(){
        return true;
    }
    @Override
    public boolean canLeapVertically(){
        return true;
    }

}
