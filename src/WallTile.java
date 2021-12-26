import java.io.File;

/**
 * Class for the wall tiles. It can't be occupied (player/crate can't stand on it),
 * and it can't be moved.
 *
 * @author Zorlolz, Mastaresplinter
 * @version 1.0
 * @since May 7, 2021
 */
public class WallTile extends Tile implements NonPlayerSokobanTile, Cloneable {

    /**
     * Constructor.
     *
     * @param file File that will be shown on tile.
     */
    public WallTile(File file) {
        super(file);
    }

    /**
     * Method that all non player tiles have that tells the application if this tile can be occupied or not.
     *
     * @return False, wall tiles can't be occupied.
     */
    public boolean canOccupy() {
        return false;
    }

    /**
     * Method that all non player tiles have that tells the application if this tile can be moved or not.
     *
     * @return False, wall tiles can't be moved.
     */
    public boolean isMovableTile() {
        return false;
    }

    /** Returns a clone of the wall tile.
     *
     * @return Clone of wall tile.
     */
    public Tile clone() {

        try {
            return (WallTile) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
