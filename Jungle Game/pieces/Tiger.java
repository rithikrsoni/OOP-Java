
package jungle.pieces;

import jungle.Player;
import jungle.squares.Square;

/**
 * @param boolean canLeapHorrizontally is overriden because the tiger can function differently to other pieces. Also the rank specified is 6 as a tiger will only ever be a rank 6
 * 
 */
public class Tiger extends Piece  {
    public Player owner;
    public Square square;

    public Tiger(Player owner, Square square){
        super(owner,square,6); // tiger is ranked six
    }
    @Override
    public boolean canLeapHorizontally(){
        return true;
    }
    

}
