import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for the crate tiles. It has implemented the abstract move methods from the parent class and
 * have moveObservers that will be notified when the tile moves. This class also changes what image is
 * display depending on what tile it is occupying.
 *
 * @author Zorlolz, Mastaresplinter
 * @version 1.0
 * @since May 13, 2021
 */
public class CrateTile extends MovableTile implements NonPlayerSokobanTile {

    private BufferedImage crate;
    private BufferedImage markedCrate;
    private ArrayList<MoveObserver> observers;

    /**
     * Constructor.
     * @param file File that will be shown on tile.
     * @param file2 File that will be shown on tile when it is occupying a marked tile.
     */
    public CrateTile(File file, File file2) {
        super(file); // Give start file to super class
        try {
            crate = super.image; // And save it here
            markedCrate = ImageIO.read(file2); // Load in second file
            observers = new ArrayList<MoveObserver>();
        }
        catch (IOException e) {
        }
    }

    /**
     * Function to add MoveObservers.
     * These observers will be notified everytime the tile moves.
     * @param m MoveObserver to add.
     */
    public void addMoveObservers(MoveObserver m)
    {
        observers.add(m);
    }

    /**
     * Method that moves the tile up if it can be moved there.
     */
    @Override
    public void moveUp() {

        if (((NonPlayerSokobanTile)up).canOccupy()) // Check if tile can be occupied
        {
            tileRow--;
            for (MoveObserver m: observers)
            {
                m.moveTile(this,1);
            }
        }
    }

    /**
     * Method that moves the tile down if it can be moved there.
     */
    @Override
    public void moveDown() {

        if (((NonPlayerSokobanTile)down).canOccupy()) // Check if tile can be occupied
        {
            tileRow++;
            for (MoveObserver m: observers)
            {
                m.moveTile(this,2);
            }
        }
    }

    /**
     * Method that moves the tile to the left if it can be moved there.
     */
    @Override
    public void moveLeft() {
        if (((NonPlayerSokobanTile)left).canOccupy()) // Check if tile can be occupied
        {
            tileColumn--;
            for (MoveObserver m: observers)
            {
                m.moveTile(this,3);
            }
        }
    }

    /**
     * Method that moves the tile to the right if it can be moved there.
     */
    @Override
    public void moveRight() {
        if (((NonPlayerSokobanTile)right).canOccupy()) // Check if tile can be occupied
        {
            tileColumn++;
            for (MoveObserver m: observers)
            {
                m.moveTile(this,4);
            }
        }
    }

    /**
     * Method all MovableSokobanTiles have that saves the tile currently being occupied.
     * Overrides since crates also changes what file is displayed depending on what tile it occupies.
     * @param tile Tile crate is occupying.
     */
    @Override
    public void setOccupying(Tile tile) {
        if (tile instanceof BlankMarkedTile) // If occupying marked tile, display marked crate
        {
            super.image = markedCrate;
        } else {
            super.image = crate;
        }
        super.occupying = tile;
    }

    /**
     * Method that all non player tiles have that tells the application if this tile can be occupied or not.
     * @return False, crate tiles can't be occupied.
     */
    public boolean canOccupy() {
        return false;
    }

    /**
     * Method that all non player tiles have that tells the application if this tile can be moved or not.
     * @return True, crate tiles can be moved..
     */
    public boolean isMovableTile() {
        return true;
    }
}
