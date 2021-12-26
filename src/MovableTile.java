import java.io.File;


/**
 * Class for movable tiles. It has expanded capabilities compared to the super class.
 * By keeping track of its position and surroundings move functions can easily be implemented.
 *
 * @author Zorlolz, Mastaresplinter
 * @version 2.0
 * @since May 13, 2021
 */
public abstract class MovableTile extends Tile {


    protected Tile occupying; // Variables that keep tack what tiles are around and under the movable tile.
    protected Tile up;
    protected Tile down;
    protected Tile left;
    protected Tile right;
    protected int tileRow;
    protected int tileColumn;


    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRight();

    /**
     * Constructor.
     * @param file File that will be shown on tile.
     */
    public MovableTile(File file) { super(file); }

    /**
     * Method that saves the tile currently being occupied.
     * @param tile Tile crate is occupying.
     */
    public void setOccupying(Tile tile) {
        this.occupying = tile;
    }

    /**
     * Method that returns the tile currently being occupied.
     * @return Currently occupied tile.
     */
    public Tile getOccupying() {
        return occupying;
    }

    /**
     * Method that sets the adjacent tiles.
     * @param up Tile above.
     * @param down Tile under.
     * @param left Tile to the left.
     * @param right Tile to the right.
     */
    public void setAdjacentTiles(Tile up, Tile down, Tile left, Tile right)
    {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    /**
     * Method to set the row position.
     * @param row Row position.
     */
    public void setRow(int row){tileRow = row;}

    /**
     * Method to set the column position.
     * @param column Column position.
     */
    public void setColumn(int column){tileColumn = column;}

    /**
     * Method to return the row position.
     * @return Row position.
     */
    public int getRow(){return tileRow;}

    /**
     * Method to return the column position.
     * @return Column position.
     */
    public int getColumn(){return tileColumn;}

}
