

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * <h1>Framework for games!</h1>
 * This framework builds a JFrame with application given JComponents.
 * It is based on a grid system with row and column as coordinates.
 * The frame has both MouseListener and KeyListener added to it and the methods to
 * handle these event will need to be implemented by the application (The ones your game won't react to can be left empty).
 * <p>
 * <b>Note:</b> Since the coordinates are row and column, grid (0,0) would be the top left corner.
 *
 *
 * @author Zorlolz, Mastaresplinter
 * @version 1.0
 * @since May 7, 2021
 */

public abstract class GameFrame implements MouseListener, KeyListener {

    private JFrame frame;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JComponent [][] componentsArray;
    private JPanel window;

    /**
     * Constructor. 
     * @param rows Amount of rows the grid should have.
     * @param columns Amount of columns the grid should have.
     */
    public GameFrame(int rows, int columns)
    {
        frame = new JFrame();
        window = new JPanel();
        gbl = new GridBagLayout();
        //frame.setLayout(as);
        window.setLayout(gbl);
        frame.setAlwaysOnTop(true);
        gbc = new GridBagConstraints();
        componentsArray = new JComponent[rows][columns];

        window.addMouseListener(this);
        window.addKeyListener(this);

        frame.add(window);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    /**
     * Method to add a JComponent to the frame at the specified row and column.
     * You have to fill the frame entirely for the row and column index to work correctly, you cant leave certain indexes empty.
     * JFrames pack function will move them together if you do that.
     * All componentsArray should have the same dimensions.
     * Index starts from 0.
     * @param component JComponent to be added
     * @param row Index of row
     * @param column Index of column
     * @return JComponent that was removed if you add at an index that already had a component.
     * @precondition You can only add to a row,column index that exists.
     */
    public JComponent addComponent(JComponent component, int row, int column)
    {
        if (row < 0 || row >= componentsArray.length || column < 0 || column >= componentsArray[0].length)
            throw new IndexOutOfBoundsException
                    ("Index ["+row+"]["+column+"] is out of bounds for GameFrame grid with size ["+componentsArray.length +"]["+componentsArray[0].length+"]");

        gbc.gridx = column;
        gbc.gridy = row;
        JComponent ret = componentsArray[row][column];

        if (componentsArray[row][column] != null)
        {
            window.remove(ret);
        }

        componentsArray[row][column] = component;
        window.add(component,gbc);
        window.revalidate();
        componentsArray[row][column].repaint();
        frame.pack();
        //window.repaint();
        window.requestFocusInWindow();
        return ret;

    }

    /**
     * Method that adds every component from the array to the matching grid position
     * in the frame.
     * @param background JComponent array with a component for every grid in the window
     * @precondition size of background == size of frame grid
     */
    public void setupBackground(JComponent[][] background)
    {
        
        if (background.length != componentsArray.length  || background[1].length != componentsArray[0].length)
            throw new IllegalArgumentException
                    ("Background array with size ["+background.length+"]["+background[1].length+"] needs to match the GameFrame grid size of["+componentsArray.length +"]["+componentsArray[0].length+"]");
        for (int i = 0; i < background.length; i++)
        {
            gbc.gridy = i;
            for (int j = 0; j < background[0].length; j++)
            {
                gbc.gridx = j;
                window.add(background[i][j],gbc);
                componentsArray[i][j] = background[i][j];
            }
        }

        window.revalidate();
        window.repaint();
        //frame.revalidate();
        frame.pack();
        window.requestFocusInWindow();
        //frame.repaint();

    }

    /**
     * Returns the component from the frame at the given position.
     * @param row Row index of component
     * @param column Column index of component
     * @return Component at that position.
     */
    public JComponent getComponent(int row, int column)
    {
        if (row < 0 || row >= componentsArray.length  || column < 0 || column >= componentsArray[0].length)
            throw new IndexOutOfBoundsException
                    ("Index ["+row+"]["+column+"] is out of bounds for grid with size ["+componentsArray.length +"]["+componentsArray[0].length+"]");
        return componentsArray[row][column];
    }


    /**
     * Removed and returns the component at given index
     * @param row Row index of component
     * @param column Column index of component
     * @return Component that was at that position.
     */
    public JComponent removeComponent(int row, int column)
    {
        if (row < 0 || row >= componentsArray.length  || column < 0 || column >= componentsArray[0].length)
            throw new IndexOutOfBoundsException
                    ("Index ["+row+"]["+column+"] is out of bounds for grid with size ["+componentsArray.length +"]["+componentsArray[0].length+"]");

        if (componentsArray[row][column] == null)
            throw new NullPointerException("Component at ["+row+"]["+column+"] is null");
        JComponent ret = componentsArray[row][column];
        componentsArray[row][column] = null;
        window.remove(ret);
        window.revalidate();
        window.repaint();
        frame.pack();
        return ret;
    }

    /**
     * Sets the title of the frame.
     * @param title Title of frame.
     */
    public void setTitle(String title)
    {
        frame.setTitle(title);
    }

    /**
     *  Packs and makes the frame visible.
     */
    public void showFrame()
    {
        frame.pack();
        frame.setVisible(true);
        window.requestFocusInWindow();
    }

    /**
     * Removes all componentsArray from the frame and generates a new grid with
     * the specified size.
     * @param rows Number of rows wanted in new grid.
     * @param columns Number of columns wanted in new grid.
     */
    public void generateFreshWindow(int rows, int columns)
    {
        window.removeAll();
        componentsArray = new JComponent[rows][columns];
    }

    /**
     * Function that close the window.
     */
    public void close()
    {
        frame.setVisible(false);
        frame.dispose();

    }

}
