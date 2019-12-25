
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Graph {
    private HashMap<Node, ArrayList<Node>> nodes;
    private int n;
    private BufferedWriter writer;
   public Graph(int n){
       this.n = n;
       nodes = new HashMap<>();
       try{
            writer = new BufferedWriter(new FileWriter("C:\\Users\\Niyazi\\Desktop\\results.txt"));
       }
       catch (Exception ex){
           ex.printStackTrace();
       }
   }

   public void addEdge(Node node, Node neighbour){
        ArrayList<Node> neighbours = nodes.get(node);
        if(neighbours == null)
            neighbours = new ArrayList<Node>();
        neighbours.add(neighbour);
        nodes.put(node,neighbours);


   }

   public HashMap<Node, Boolean> initVisitStates(){
       HashMap<Node,Boolean> visited = new HashMap<>();
       for (Map.Entry item: nodes.entrySet()){
           Node node = (Node) item.getKey();
           visited.put(node, new Boolean(false));
       }
       return visited;
   }

   public void visitedYazdir(HashMap<Node, Boolean> visited){
       String x = "";
       for (Map.Entry item: visited.entrySet()){
            boolean b = (Boolean) item.getValue();
            if(b){
                Node node = (Node) item.getKey();
                x+=node.printNode()+"->";
            }
       }

       System.out.println(x);
   }
    String x = "";

   public void yazdir(Stack<Node> st){
       Stack<Node> tmp = (Stack<Node>) st.clone();
       x="";
       tmp.forEach(y->{
           x+=y.printNode()+"->";
       });
       System.out.println(x);
       try {
           writer.write(x);
           writer.newLine();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    int size = 0;
    public boolean checkSolution(Stack<Node> st){
        Stack<Node> tmp = (Stack<Node>) st.clone();
        x="";
        size = 0;
        tmp.forEach(y->{
            size = y.getColor() != Color.EMPTY? size+1: size;
        });
        if (size == 9){
            Node start = tmp.firstElement();
            Node end = tmp.lastElement();
            Node beforeEnd = tmp.get(tmp.size() - 1 - 1);
            if((end.getX() == start.getX() - 1 || end.getX() == start.getX() + 1) && end.getY() == start.getY() ){
                if (beforeEnd.getY() == end.getY()){
                    tmp.forEach(y->{
                        x+= y.printNode();
                    });
                }
            }
            else if((end.getY() == start.getY() -1 || end.getY() == start.getY() +1 ) && end.getX() == start.getX()){
               if(beforeEnd.getX() == end.getX()){
                   tmp.forEach(y->{
                       x+= y.printNode();
                   });
               }
            }
        }
        if(x == ""){
        }
        else{
            System.out.println(x);
        }

        return false;
    }


    public void check(Stack<Node> st){
        Stack<Node> tmp = (Stack<Node>) st.clone();

        for (int i = 0; i < tmp.size(); i++){
            Node node = tmp.get(i);
        }

    }

   public void DFSUtil(Node startNode,HashMap<Node, Boolean> visited, Stack<Node> nodes1){
       ArrayList<Node> neighbours = (ArrayList<Node>) nodes.get(startNode);
       visited.put(startNode, true);
       //visitedYazdir(visited);
       nodes1.push(startNode);
       Stack<Node> tmp = (Stack<Node>) nodes1.clone();
       //yazdir(nodes1);
        checkSolution(nodes1);
       for (Node item: neighbours ){
           if(!visited.get(item)) {

                    if(startNode.getColor() == Color.BLACK){
                        tmp = (Stack<Node>) nodes1.clone();
                        tmp.pop();
                        //Bir onceki
                        Node nodex;
                        if(!tmp.empty()){
                            nodex = tmp.pop();
                            if(nodex.getX() == startNode.getX()){
                                if(item.getX() == startNode.getX())
                                    continue;
                                else
                                    DFSUtil(item,visited,nodes1);
                            }
                            else if(nodex.getY() == startNode.getY()){
                                if(item.getY() == startNode.getY())
                                    continue;
                                else
                                    DFSUtil(item,visited,nodes1);
                            }
                        }
                        else{
                            DFSUtil(item, visited, nodes1);
                        }

                    }
                    else if(startNode.getColor() == Color.WHITE){
                        tmp = (Stack<Node>) nodes1.clone();
                        tmp.pop();
                        Node nodex;
                        if(!tmp.empty()) {
                            nodex = tmp.pop();
                            if (nodex.getX() == startNode.getX()) {
                                if (item.getX() == startNode.getX())
                                    DFSUtil(item, visited, nodes1);
                                else {
                                    continue;

                                }
                            } else if (nodex.getY() == startNode.getY()) {
                                if (item.getY() == startNode.getY())
                                    DFSUtil(item, visited, nodes1);
                                else {
                                    continue;

                                }
                            } else
                                System.out.println("ws");
                        }
                        else{
                            DFSUtil(item, visited, nodes1);
                        }
                    }
                    else{
                        Node nodex;
                        tmp = (Stack<Node>) nodes1.clone();
                        tmp.pop();
                        if(!tmp.empty()){
                            nodex = tmp.pop();
                            if(nodex.getColor() == Color.BLACK){
                                if (nodex.getX() == startNode.getX()){
                                    if(item.getX() == startNode.getX()){ // Bu ifler düzeltilecek.
                                        DFSUtil(item, visited, nodes1);
                                    }
                                }
                                else if (nodex.getY() == startNode.getY()){
                                    if(item.getY() == startNode.getY()){
                                        DFSUtil(item, visited, nodes1);
                                    }
                                }
                            }
                            else if(nodex.getColor() == Color.WHITE){
                                if(!tmp.empty()){
                                    Node nodey = tmp.pop();
                                    if(!tmp.empty()) {
                                        Node nodea = tmp.pop();
                                        if (nodea.getY() == nodey.getY()) {
                                            if (nodex.getY() != nodey.getY())
                                                continue;
                                            if (item.getY() == nodex.getY())
                                                continue;
                                            else {
                                                if(item.getColor() == Color.BLACK){
                                                    if(nodex.getX() == startNode.getX()){
                                                        if(item.getY() == startNode.getY())
                                                            continue;
                                                        else
                                                            DFSUtil(item,visited, nodes1);
                                                    }
                                                }
                                                else
                                                    DFSUtil(item, visited, nodes1);
                                            }
                                        }
                                        else if (nodea.getX() == nodey.getX()) {
                                            if(nodex.getX() != nodey.getX())
                                                continue;
                                            if (item.getX() == nodex.getX())
                                                continue;
                                            else {
                                                if(item.getColor() == Color.BLACK){
                                                    
                                                }
                                                else
                                                    DFSUtil(item, visited, nodes1);
                                            }
                                        } else {
                                            DFSUtil(item, visited, nodes1);
                                        }
                                    }
                                    else
                                        DFSUtil(item, visited, nodes1);
                                }
                                else
                                    DFSUtil(item, visited, nodes1);
                            }

                            else {

                                if(item.getColor() == Color.BLACK){
                                    if(item.getY() == startNode.getY()){
                                        if(nodex.getY() == startNode.getY()){
                                            DFSUtil(item,visited,nodes1);
                                        }
                                        else
                                            continue;
                                    }
                                    else if(item.getX() == startNode.getX()){
                                        if(nodex.getX() == startNode.getX()){
                                            DFSUtil(item,visited,nodes1);
                                        }
                                        else
                                            continue;
                                    }
                                    else
                                        DFSUtil(item,visited,nodes1);
                                }
                                else
                                    DFSUtil(item, visited, nodes1);
                            }
                        }
                        else{
                            DFSUtil(item,visited,nodes1);
                        }


                    }

           }

       }
       nodes1.pop();
       visited.put(startNode, false);

   }
   public void DFS(Node startNode){
        HashMap<Node, Boolean> visited = initVisitStates();
        Stack<Node> nodes = new Stack<>();

        DFSUtil(startNode, visited,nodes);
   }

   public int count(Node node){
       return nodes.get(node).size();
   }



}
