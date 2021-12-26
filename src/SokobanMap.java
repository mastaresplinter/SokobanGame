import javax.swing.*;
import java.io.File;

/**
 *
 * @author Zorlolz, Mastaresplinter
 * @version 1.0
 * @since May 8, 2021
 */
public class SokobanMap {

    private int [][] mapBackground;
    private int [][] cratePositions;
    private int playerStartRow;
    private int playerStartColumn;

    private PlayerTile player;
    private WallTile wall = new WallTile(new File("src/sokoban_icons/wall.png"));
    private BlankTile blank = new BlankTile(new File("src/sokoban_icons/blank.png"));
    private BlankMarkedTile blankmarked = new BlankMarkedTile(new File("src/sokoban_icons/blankmarked.png"));


    /**
     * Constructor.
     * @param mapBackground Array representation of background were values and position in array determines what tile is placed where
     * @param cratePositions Array containing the start position of all crates
     * @param playerStartRow Row where player starts
     * @param playerStartColumn Column where player starts
     */
    public SokobanMap(int[][] mapBackground,int [][] cratePositions, int playerStartRow, int playerStartColumn)
    {
        this.mapBackground = mapBackground;
        this.cratePositions = cratePositions;
        this.playerStartRow = playerStartRow;
        this.playerStartColumn = playerStartColumn;

    }

    /**
     * This will generate a JComponent array that is the representation of the background to the game with the help of numbers
     * from the mapBackground array. 1 = Wall tile, 2 = blank marked tile and 0/any int but 1,2 = blank tile.
     * @return JComponent array representation of background
     */
    public JComponent[][] generateBackgroundArray()
    {
        JComponent[][] temp = new JComponent[mapBackground.length][mapBackground[0].length];

            for (int i = 0; i < mapBackground.length; i++)
                for (int j = 0; j < mapBackground[0].length; j++) {
                    if (mapBackground[i][j] == 1) {
                        temp[i][j] = wall.clone();
                    } else if (mapBackground[i][j] == 2) {
                        temp[i][j] = blankmarked.clone();
                    } else {
                        temp[i][j] = blank.clone();
                    }
                }

        return temp;
    }

    /**
     * Generates an array of crates and set the crates start position according to the positions given
     * to the constructor.
     * @return An array of crates with start positions set
     */
    public CrateTile[] generateCrateArray()
    {
        CrateTile [] crates = new CrateTile[cratePositions.length];
        for (int i = 0; i < cratePositions.length; i++)
        {
                crates[i] = new CrateTile(new File("src/sokoban_icons/crate.png"),new File("src/sokoban_icons/cratemarked.png"));
                crates[i].setRow(cratePositions[i][0]);
                crates[i].setColumn(cratePositions[i][1]);
        }
        return crates;
    }

    /**
     * Returns a fresh MovableSokobanTile (Player) with start positions set.
     * @return PlayerTile representation of player.
     */
    public PlayerTile getPlayer()
    {
        player = new PlayerTile(new File("src/sokoban_icons/player.png"));
        player.setColumn(playerStartColumn); // set the start values for the player on this map
        player.setRow(playerStartRow);
        return player;
    }

    /**
     * Gets map height in rows
     * @return Map height in rows
     */
    public int mapNrOfRows() {return mapBackground.length;}

    /**
     * Gets map width in columns
     * @return Map width in columns
     */
    public int mapNrOfColumns() {return mapBackground[0].length;}

}
