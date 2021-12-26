import java.io.File;

/**
 * Class for the blank tiles. It can be occupied (player/crate can stand on it),
 * but it can't be moved.
 *
 * @author Zorlolz, Mastaresplinter
 * @version 1.0
 * @since May 7, 2021
 */

public class BlankTile extends Tile implements NonPlayerSokobanTile, Cloneable {

    /** Constructor.
     *
     * @param file File that will be shown on tile.
     */
    public BlankTile(File file) {
        super(file);
    }

    /** Method that all non player tiles have that tells the application if this tile can be occupied or not.
     *
     * @return True, blank tiles can be occupied.
     */
    public boolean canOccupy() {
        return true;
    }

    /** Method that all non player tiles have that tells the application if this tile can be moved or not.
     *
     * @return False, blank tiles can't be moved.
     */
    public boolean isMovableTile() {
        return false;
    }

    /** Returns a clone of the blank tile.
     *
     * @return Clone of blank tile.
     */
    public Tile clone() {

        try {
            return (BlankTile ) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
