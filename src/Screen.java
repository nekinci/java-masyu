import Constants.ImageConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Screen extends JFrame {

    public Node nodes[][];
    Cell cells[][] = new Cell[6][6];
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
                cells[i][j] = new Cell();
                if(nodes[i][j].getColor() == Color.WHITE)
                    cells[i][j].setImage(ImageConstants.WHITE_CIRCLE);
                if(nodes[i][j].getColor() == Color.BLACK)
                    cells[i][j].setImage(ImageConstants.BLACK_CIRCLE);
                add(cells[i][j]);
                cells[i][j].repaint();

            }
        }
    }

    public void solve(Stack<Node> nodes){
        for(int i = 0; i < nodes.size(); i++){
            cells[nodes.get(i).getX()][nodes.get(i).getY()].setLineImage(null,String.valueOf(i));
        }
    }

}
