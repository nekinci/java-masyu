import javax.swing.*;
import java.util.Stack;

public class Game {

    Node nodes[][];
    Graph graph;
    private int n;
    public Game(int n){
        this.n = n;
        nodes = new Node[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nodes[i][j] = new Node(i,j,Color.EMPTY);
            }
        }

        nodes[0][2].setColor(Color.BLACK);
        nodes[1][5].setColor(Color.WHITE);
        nodes[2][0].setColor(Color.WHITE);
        nodes[3][3].setColor(Color.BLACK);
        nodes[3][5].setColor(Color.WHITE);
        nodes[4][0].setColor(Color.WHITE);
        nodes[4][2].setColor(Color.WHITE);
       // nodes[0][0].setColor(Color.BLACK);
       // nodes[1][2].setColor(Color.WHITE);
        /*nodes[0][1].setColor(Color.BLACK);
        nodes[0][4].setColor(Color.WHITE);
        nodes[2][3].setColor(Color.WHITE);
        nodes[2][4].setColor(Color.WHITE);
        nodes[3][1].setColor(Color.WHITE);
        nodes[3][2].setColor(Color.BLACK);
        nodes[4][1].setColor(Color.WHITE);
        nodes[5][1].setColor(Color.WHITE);
        nodes[4][5].setColor(Color.WHITE);*/

        /*nodes[0][0].setColor(Color.BLACK);
        nodes[0][1].setColor(Color.WHITE);
        nodes[0][2].setColor(Color.BLACK);
        nodes[0][4].setColor(Color.WHITE);
        nodes[1][3].setColor(Color.WHITE);
        nodes[3][5].setColor(Color.BLACK);
        nodes[4][2].setColor(Color.WHITE);
        nodes[5][0].setColor(Color.BLACK);
        nodes[5][3].setColor(Color.WHITE);*/

        /*nodes[0][5].setColor(Color.BLACK);
        nodes[1][3].setColor(Color.WHITE);
        nodes[1][2].setColor(Color.BLACK);
        nodes[4][0].setColor(Color.WHITE);
        nodes[4][2].setColor(Color.WHITE);
        nodes[4][4].setColor(Color.BLACK);
        nodes[4][5].setColor(Color.WHITE);*/

        graph = new Graph(n);
        int k = 0;
        for(int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                if(nodes[i][j].getColor() != Color.EMPTY)
                    k++;
            }
        }
        toGraphImplementation();
        Stack<Node> solutionWays = graph.DFS(nodes[0][2], k);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Screen scr = new Screen(nodes,n,"Çözülmemiş Hali");
                Screen scr1 = new Screen(nodes,n,"Çözülmüş Hali");
                scr1.solve(solutionWays);
                scr1.setLocation(scr.getX()+scr.getWidth(),scr.getY());
            }
        });

    }

    public Graph getGraph() {
        return graph;
    }

    public void toGraphImplementation(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                toGraphImplementationHelper(i,j);
            }
        }
    }

    public void toGraphImplementationHelper(int i, int j){

        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                if(isNeighbour(nodes[i][j], nodes[k][l]))
                    graph.addEdge(nodes[i][j], nodes[k][l]);
            }

        }

    }

    public boolean isNeighbour(Node node, Node node1){
        int x1 = node.getX();
        int y1 = node.getY();

        int x2 = node1.getX();
        int y2 = node1.getY();

        if (x1-1>=0 && x1-1 == x2 && y1 == y2)
            return true;
        if(x1+1<n && x1+1 == x2 && y1 == y2)
            return true;
        if(x1 == x2 && y1-1 >=0 && y1-1 == y2)
            return true;
        if(x1 == x2 && y1+1 < n && y1+1 == y2)
            return true;
        return false;
    }
}
