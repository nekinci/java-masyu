import Constants.ImageConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen extends JFrame {

    public Node nodes[][];
    public Screen(Node nodes[][]){

        super();
        this.nodes = nodes;
        setSize(500,500);
        setBackground(java.awt.Color.WHITE);
        setLayout(new GridLayout(6,6));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.repaint();
        init();
    }

    public void init(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Cell cell = new Cell();
                if(nodes[i][j].getColor() == Color.WHITE)
                    cell.setImage(ImageConstants.WHITE_CIRCLE);
                if(nodes[i][j].getColor() == Color.BLACK)
                    cell.setImage(ImageConstants.BLACK_CIRCLE);
                cell.setLineImage(ImageConstants.HORIZONTAL_LINE);
                add(cell);
                cell.repaint();

            }
        }
    }

}
