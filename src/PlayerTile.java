import java.io.File;
import java.util.ArrayList;

/**
 * Class for player tiles. It has implemented the abstract move methods from the parent class and
 * have moveObservers that will be notified when the tile moves.
 *
 * @author Zorlolz, Mastaresplinter
 * @version 1.0
 * @since May 13, 2021
 */

public class PlayerTile extends MovableTile {

    private ArrayList<MoveObserver> observers;

    public PlayerTile(File file)
    {
        super(file);
        observers = new ArrayList<MoveObserver>();
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
     * Method that moves the tile up if there is space free or if the
     * tile above is movable and also can move up.
     */
    @Override
    public void moveUp()
    {

        if (((NonPlayerSokobanTile)up).canOccupy()) // Check if tile can be occupied
        {
            tileRow--;
            for (MoveObserver m: observers)
            {
                m.moveTile(this,1);
            }
        }
        else if (((NonPlayerSokobanTile)up).isMovableTile()) // Else check if adjacent tile also can be moved
        {
            NonPlayerSokobanTile nextNextTile = (NonPlayerSokobanTile)((MovableTile)up).up;
            if (nextNextTile.canOccupy())
            {
                ((MovableTile) up).moveUp();
                tileRow--;
                for (MoveObserver m : observers)
                {
                    m.moveTile(this, 1);
                }
            }
        }
    }

    /**
     * Method that moves the tile down if there is space free or if the
     * tile under is movable and also can move down.
     */
    @Override
    public void moveDown()
    {
        if (((NonPlayerSokobanTile)down).canOccupy()) // Check if tile can be occupied
        {
            tileRow++;
            for (MoveObserver m: observers)
            {
                m.moveTile(this,2);
            }

        }
        else if (((NonPlayerSokobanTile)down).isMovableTile()) // Else check if adjacent tile also can be moved
        {
            NonPlayerSokobanTile nextNextTile = (NonPlayerSokobanTile)((MovableTile)down).down;
            if (nextNextTile.canOccupy())
            {
                ((MovableTile)down).moveDown();
                tileRow++;
                for (MoveObserver m : observers)
                {
                    m.moveTile(this, 2);
                }
            }
        }

    }
    /**
     * Method that moves the tile to the left if there is space free or if the
     * tile to the left is movable and also can move to the left.
     */
    @Override
    public void moveLeft()
    {
        if (((NonPlayerSokobanTile)left).canOccupy()) // Check if tile can be occupied
        {
            tileColumn--;
            for (MoveObserver m: observers)
            {
                m.moveTile(this,3);
            }

        }
        else if (((NonPlayerSokobanTile)left).isMovableTile()) // Else check if adjacent tile also can be moved
        {
            NonPlayerSokobanTile nextNextTile = (NonPlayerSokobanTile)((MovableTile)left).left;
            if (nextNextTile.canOccupy())
            {
                ((MovableTile) left).moveLeft();
                tileColumn--;
                for (MoveObserver m : observers)
                {
                    m.moveTile(this, 3);
                }
            }
        }

    }

    /**
     * Method that moves the tile to the right if there is space free or if the
     * tile to the right is movable and also can move to the right.
     */
    @Override
    public void moveRight()
    {

        if (((NonPlayerSokobanTile)right).canOccupy()) // Check if tile can be occupied
        {
            tileColumn++;
            for (MoveObserver m: observers)
            {
                m.moveTile(this,4);
            }
        }
        else if (((NonPlayerSokobanTile)right).isMovableTile()) // Else check if adjacent tile also can be moved
        {
            NonPlayerSokobanTile nextNextTile = (NonPlayerSokobanTile)((MovableTile)right).right;
            if (nextNextTile.canOccupy())
            {
                ((MovableTile)right).moveRight();
                tileColumn++;
                for (MoveObserver m : observers)
                {
                    m.moveTile(this, 4);
                }
            }
        }
    }
}
