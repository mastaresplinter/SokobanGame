import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * Class that setup and start everything.
 *
 *
 *
 * @author Zorlolz, Mastaresplinter
 * @version 2.0
 * @since May 10, 2021
 */

public class Sokoban extends GameFrame implements MoveObserver{
    
    private SokobanMap[] maps;
    private int currentMapIndex;

    private PlayerTile player;
    private boolean winFlag = false;

    private Heatmap hmap;
    private Winchecker victory;

    private MovableTile[] movableTiles;

    /**
     * Inherited from GameFrame, will be called when a key is pressed.
     * If key pressed is one of the arrow keys, corresponding move method is called.
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {

        if(!winFlag) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    player.moveLeft();
                    break;

                case KeyEvent.VK_RIGHT:
                    player.moveRight();
                    break;

                case KeyEvent.VK_UP:

                    player.moveUp();
                    break;

                case KeyEvent.VK_DOWN:
                    player.moveDown();
                    break;

                case KeyEvent.VK_SPACE: // Spacebar resets amp
                    loadSokobanMap(maps[currentMapIndex]);
                    break;
            }

        }
    }

    /**
     * This function is called when a Movable tile is moved.
     * It updates the positions of the tiles in the GameFrame.
     * @param tile The tile that was moved.
     * @param direction Integer representation of direction, 1 = up, 2 = down, 3 = left and 4 = right.
     */
    @Override
    public void moveTile(MovableTile tile, int direction) {
        
        switch (direction){
            case 1:
                addComponent(tile.getOccupying(),tile.getRow()+1,tile.getColumn());
                tile.setOccupying((Tile)addComponent(tile,tile.getRow(),tile.getColumn()));
                break;
            case 2:
                addComponent(tile.getOccupying(),tile.getRow()-1,tile.getColumn());
                tile.setOccupying((Tile)addComponent(tile,tile.getRow(),tile.getColumn()));
                break;
            case 3:
                addComponent(tile.getOccupying(),tile.getRow(),tile.getColumn()+1);
                tile.setOccupying((Tile)addComponent(tile,tile.getRow(),tile.getColumn()));
                break;
            case 4:
                addComponent(tile.getOccupying(),tile.getRow(),tile.getColumn()-1);
                tile.setOccupying((Tile)addComponent(tile,tile.getRow(),tile.getColumn()));
                break;

                
        }
        update();
        if (victory.checkWinCondition()) // Check if win occured after move
        {
            if (currentMapIndex + 1 == maps.length)
                generateWinScreen();
            else {
                loadSokobanMap(maps[++currentMapIndex]);
            }
        }
    }

    /**
     * Function that updates adjacent tiles for all movable tiles after a move has been made.
     */
    public void update()
    {
        for (int i = 0; i < movableTiles.length; i++)
        {
            movableTiles[i].setAdjacentTiles((Tile) getComponent(movableTiles[i].getRow()-1, movableTiles[i].getColumn()),(Tile) getComponent(movableTiles[i].getRow()+1, movableTiles[i].getColumn()),
                    (Tile) getComponent(movableTiles[i].getRow(), movableTiles[i].getColumn()-1),(Tile) getComponent(movableTiles[i].getRow(), movableTiles[i].getColumn()+1));
        }
    }


    /**
     * Constructor.
     * @param maps SokobanMaps array containing all the maps.
     */
    public Sokoban(SokobanMap[] maps)
    {
        super(maps[0].mapNrOfRows(),maps[0].mapNrOfColumns()); // Start GameFrame with grid size of first map
        this.maps = maps;
        setTitle("Sokoban");
        hmap = new Heatmap(0,0);
        loadSokobanMap(maps[0]); // Load first map
        currentMapIndex = 0;
        showFrame();



    }

    /**
     * Generates and shows the whole map. Setups all the tiles so they are ready for player to start moving.
     * @param map SokobanMap that contains the layout of the whole map.
     */
    public void loadSokobanMap(SokobanMap map)
    {
        generateFreshWindow(map.mapNrOfRows(),map.mapNrOfColumns());
        setupBackground(map.generateBackgroundArray()); // First setup background


        hmap.resetWindow(map.mapNrOfRows(),map.mapNrOfColumns());
        CrateTile[] temp = map.generateCrateArray();
        victory = new Winchecker(temp);
        movableTiles = new MovableTile[temp.length+1]; // Crates + player

        for (int i = 0; i < temp.length; i++)
        {
            temp[i].setOccupying((Tile)addComponent(temp[i],temp[i].getRow(),temp[i].getColumn()));
            temp[i].addMoveObservers(this);
            temp[i].addMoveObservers(victory);
            movableTiles[i] = temp[i];
        }

        player = map.getPlayer();
        player.setOccupying((Tile)addComponent(player,player.getRow(),player.getColumn()));
        player.addMoveObservers(this);
        player.addMoveObservers(hmap);
        movableTiles[temp.length] = player;
        update();
    }

    /**
     * Generates and shows a simple victory screen
     */
    public void generateWinScreen()
    {
        generateFreshWindow(1,1);
        Tile win = new Tile(new File("src/winner.png"));
        addComponent(win,0,0);
        winFlag = true;

    }


    /**
     * Main, arrays are representation of maps and crate start positions.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {

        int [][] map1 = {
                {1,1,1,1,1,1,1,1},
                {1,1,1,0,0,0,1,1},
                {1,2,0,0,0,0,1,1},
                {1,1,1,0,0,2,1,1},
                {1,2,1,1,0,0,1,1},
                {1,0,1,0,2,0,1,1},
                {1,0,0,2,0,0,2,1},
                {1,0,0,0,2,0,0,1},
                {1,1,1,1,1,1,1,1}};

        int[][] cratesPos1 = {{2,3},{3,4},{4,4},{6,1},{6,3},{6,4},{6,5}};



        int [][] map2 = {
                {0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,1,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
                {1,1,1,0,1,0,1,1,1,0,1,0,0,0,0,0,1,1,1,1,1,1},
                {1,0,0,0,1,0,1,1,1,0,1,1,1,1,1,1,1,0,0,2,2,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,1},
                {1,1,1,1,1,0,1,1,1,1,0,1,0,1,1,1,1,0,0,2,2,1},
                {0,0,0,0,1,0,0,0,0,0,0,1,1,1,0,0,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0}};

        int[][] cratesPos2 = {{2,5},{3,7},{4,5},{4,8},{7,2},{7,5}};

        SokobanMap [] maps = {new SokobanMap(map1,cratesPos1,2,2), new SokobanMap(map2,cratesPos2,8,12)};
        Sokoban test = new Sokoban(maps);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
