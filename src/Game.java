
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
        //nodes[0][0].setColor(Color.BLACK);
        //nodes[1][2].setColor(Color.WHITE);
        graph = new Graph(n);

        toGraphImplementation();
        graph.DFS(nodes[0][2]);

        System.out.println(graph.count(nodes[0][2]));
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
