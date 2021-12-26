/**
 * Interface for non player tiles. The functions specified will be called to determined
 * if a specific move is allowed or not.
 *
 * @author Zorlolz, Mastaresplinter
 * @version 1.0
 * @since May 8, 2021
 */
public interface NonPlayerSokobanTile {

    /**
     * Decides if a tile can be occupied or not.
     * @return True if implementing tile can be occupied, else false.
     */
    boolean canOccupy();

    /**
     * Decides if a tile can be moved or not.
     * @return True if implementing tile can be moved, else false.
     */
    boolean isMovableTile();
}
