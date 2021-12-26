/**
 * Interface for any class that want to be notified when a Player och Crate tile moves.
 * Implementing class needs to add itself to observer list for the movable tile it wants to observe.
 *
 * @author Zorlolz, Mastaresplinter
 * @version 1.0
 * @since May 10, 2021
 */
public interface MoveObserver {

    /**
     * Will be called when Player och Crate tile moves.
     * @param tile The tile that was moved.
     * @param direction Integer representation of direction, 1 = up, 2 = down, 3 = left and 4 = right.
     */
    void moveTile(MovableTile tile, int direction);

}
