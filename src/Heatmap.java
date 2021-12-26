import javax.swing.*;
import java.awt.*;
/**
 *
 *
 * @author Zorlolz, Mastaresplinter
 * @version 1.0
 * @since May 11, 2021
 */
public class Heatmap implements MoveObserver {

    JFrame frame;
    GridBagConstraints gbc;
    JPanel [][] heatmap;
    int[][] values;

    public Heatmap(int rows, int columns)
    {
        frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        heatmap = new JPanel[rows][columns];
        values = new int[rows][columns];

        for (int i = 0; i < rows; i++)
        {
            gbc.gridy = i;
            for (int j = 0; j < columns; j++)
            {
                gbc.gridx = j;
                JPanel temp = new JPanel();
                temp.setPreferredSize(new Dimension(32, 32));
                Color start = new Color(0,0,255);
                temp.setBackground(start);
                heatmap[i][j]=temp;
                frame.add(temp, gbc);
            }
        }

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void moveTile(MovableTile tile, int direction) {
        int column = tile.getColumn();
        int row = tile.getRow();
        values[row][column]++;

        float prop = (float) values[row][column]/10;
        int[] rgbval = rgbCalc(prop);
        heatmap[row][column].setBackground(new Color(rgbval[0],0,rgbval[1]));
        heatmap[row][column].repaint();

    }

    /**
     * Function that returns red and blue values for a value. Low value = blue, high = red.
     * @param value Value that has been normalized (between 0 and 1).
     * @return int array with color values
     */
    public int[] rgbCalc(float value)
    {
        // Goes blue to red
        int[] temp = new int[2];

        temp[0] = (int)(255*value); // Red
        temp[1] = (int)(-255*value+255); // Blue

        if (temp[0]>255)
        {
            temp[0] = 255;
            temp[1] = 0;
        }
        return temp;
    }

    public void resetWindow(int rows, int columns)
    {
        heatmap = new JPanel[rows][columns];
        values = new int[rows][columns];
        frame.getContentPane().removeAll();

        for (int i = 0; i < rows; i++)
        {
            gbc.gridy = i;
            for (int j = 0; j < columns; j++)
            {
                gbc.gridx = j;
                JPanel temp = new JPanel();
                temp.setPreferredSize(new Dimension(32, 32));
                Color start = new Color(0,0,255);
                temp.setBackground(start);
                heatmap[i][j]=temp;
                frame.add(temp, gbc);
            }
        }
        frame.revalidate();
        frame.pack();
    }

    public void close()
    {
        frame.setVisible(false);
        frame.dispose();
    }
}
