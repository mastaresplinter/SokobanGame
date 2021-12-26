import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Class for tiles. The parent class to all sorts of tiles in the game.
 *
 * @author Zorlolz, Mastaresplinter
 * @version 1.0
 * @since May 7, 2021
 */
public class Tile extends JComponent{

    protected BufferedImage image;

    /**
     * Constructor.
     * @param file File that will be shown on tile.
     */
    public Tile(File file) {
        try {
            image = ImageIO.read(file); // Read the file
            Dimension dem = new Dimension(image.getWidth(),image.getHeight());
            this.setPreferredSize(dem); // Set tile size to picture size
        }
        catch (IOException e) {
        }
    }

    /**
     * Method that paints the image read from the file.
     * @param g Graphics.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, null, 0, 0); // Draw the image

    }
}

