import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TestGameFrame extends GameFrame {

    public TestGameFrame(SokobanMap map)
    {
        super(map.mapNrOfRows(),map.mapNrOfColumns());
        setupBackground(map.generateBackgroundArray());
        showFrame();

    }

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
        SokobanMap maps = new SokobanMap(map1,cratesPos1,2,2);
        TestGameFrame test = new TestGameFrame(maps);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        removeComponent(4,0);
        removeComponent(4,1);
        removeComponent(4,2);
        removeComponent(4,3);
        removeComponent(4,4);
        removeComponent(4,5);
        System.out.println(removeComponent(4,6));
        System.out.println(removeComponent(4,6));
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
