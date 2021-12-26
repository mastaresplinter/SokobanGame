/**
 *
 *
 * @author Zorlolz, Mastaresplinter
 * @version 1.0
 * @since May 11, 2021
 */
public class Winchecker implements MoveObserver {

    private MovableTile[] crates;
    private boolean win;

    public Winchecker(MovableTile[] crates)
    {
        this.crates = crates;
        win = false;
    }
    @Override
    public void moveTile(MovableTile tile, int direction) {

        if (tile.getOccupying() instanceof BlankMarkedTile) // First check if move resulted in this tile being marked
        {

            for (MovableTile tiles : crates) {
                if (!(tiles.getOccupying() instanceof BlankMarkedTile)) // Then check all crates if they are marked
                {
                    return; // Stop if one is not marked
                }
            }
            win = true; // If all are marked, you have won
        }

    }

    public boolean checkWinCondition()
    {
        return win;
    }
}
