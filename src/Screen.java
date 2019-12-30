import Constants.ImageConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Screen extends JFrame   {

    public Node nodes[][];
    Cell cells[][] = new Cell[6][6];
    Stack<Node> solutions = null;
    private int n;


    public Screen(Node nodes[][],int n,String text){

        super(text);
        this.n = n;
        this.nodes = nodes;
        setSize(600,678);
        setBackground(java.awt.Color.WHITE);
        setLayout(new GridLayout(n,n));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.repaint();

        init();
    }


    @Override
    public void paint(Graphics g1) {
        super.paint(g1);
        Graphics2D g = (Graphics2D) g1;
        g.setStroke(new BasicStroke(5.0f));
        if(solutions==null)
            return;
        for(int i = 0; i < solutions.size(); i++){
            if(i+1 == solutions.size()){
                Point p = cells[solutions.get(0).getX()][solutions.get(0).getY()].getLocation();
                Point p1 = cells[solutions.get(i).getX()][solutions.get(i).getY()].getLocation();
                int pwidth = cells[solutions.get(0).getX()][solutions.get(0).getY()].getWidth();
                int p1width = cells[solutions.get(i).getX()][solutions.get(i).getY()].getWidth();
                int pheight = cells[solutions.get(0).getX()][solutions.get(0).getY()].getHeight();
                int p1height = cells[solutions.get(i).getX()][solutions.get(i).getY()].getHeight();

                if(solutions.get(0).getX() == solutions.get(i).getX()){
                    g.drawLine(p.x+pwidth/2,p.y+pheight/2,p1.x+p1width/2,p.y+pheight/2);

                }
                if(solutions.get(0).getY() == solutions.get(i).getY()){
                    g.drawLine(p.x+pwidth/2,p.y+pheight/2,p.x+pwidth/2,p1.y+p1height/2);

                }
                break;

            }

            Point p = cells[solutions.get(i).getX()][solutions.get(i).getY()].getLocation();
            Point p1 =  cells[solutions.get(i+1).getX()][solutions.get(i+1).getY()].getLocation();
            int pheight = cells[solutions.get(i).getX()][solutions.get(i).getY()].getHeight();
            int p1height = cells[solutions.get(i+1).getX()][solutions.get(i+1).getY()].getHeight();
            int pwidth = cells[solutions.get(i).getX()][solutions.get(i).getY()].getWidth();
            int p1width = cells[solutions.get(i+1).getX()][solutions.get(i+1).getY()].getWidth();
            if(solutions.get(i).getX() == solutions.get(i+1).getX()){
                g.drawLine(p.x+pwidth/2,p.y+pheight/2,p1.x+p1width/2,p.y+pheight/2);
            }
            if(solutions.get(i).getY() == solutions.get(i+1).getY()){
                System.out.println(p1height);
                g.drawLine(p.x+pwidth/2,p.y+pheight/2,p1.x+p1width/2,p1.y+p1height/2);
            }
        }
    }

    public void init(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell();
                if(nodes[i][j].getColor() == Color.WHITE)
                    cells[i][j].setImage(ImageConstants.WHITE_CIRCLE);
                if(nodes[i][j].getColor() == Color.BLACK)
                    cells[i][j].setImage(ImageConstants.BLACK_CIRCLE);
                add(cells[i][j]);
                cells[i][j].repaint();

            }
        }
        this.repaint();
    }

    public void solve(Stack<Node> nodes){
        this.solutions = nodes;
        for(int i = 0; i < nodes.size(); i++){
           // cells[nodes.get(i).getX()][nodes.get(i).getY()].setLineImage(null,String.valueOf(i));
        }
        this.repaint();
    }

}
