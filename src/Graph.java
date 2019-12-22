import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Graph {
    private HashMap<Node, ArrayList<Node>> nodes;
    private int n;

   public Graph(int n){
       this.n = n;
       nodes = new HashMap<>();
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
   }
   public void DFSUtil(Node startNode,HashMap<Node, Boolean> visited, Stack<Node> nodes1){
       ArrayList<Node> neighbours = (ArrayList<Node>) nodes.get(startNode);
       visited.put(startNode, true);
       //visitedYazdir(visited);
       nodes1.push(startNode);
       Stack<Node> tmp = (Stack<Node>) nodes1.clone();
       yazdir(nodes1);
       for (Node item: neighbours ){
           if(!visited.get(item)) {


               if(startNode.getY() == 0 || startNode.getY() == n-1){

                   if(startNode.getColor() == Color.WHITE){
                        if(startNode.getX() == 0 && startNode.getX() == n-1){
                            continue;
                        }
                        else{
                            if(item.getY() == startNode.getY() && (item.getX() == startNode.getX() - 1 || item.getX() == startNode.getX() +1 )){
                                DFSUtil(item, visited,nodes1);
                            }
                        }
                   }
                   else { DFSUtil(item,visited,nodes1);}
               }

               //Yatayda köşeler kontrol
               else if(startNode.getX() == 0 || startNode.getX() == n-1){

                  if(startNode.getColor() == Color.WHITE){
                      if(startNode.getY() ==0 || startNode.getY() == n-1 )
                          continue;
                      if (startNode.getX() == item.getX() && (startNode.getY() == item.getY() -1 || startNode.getY() == item.getY() + 1) ){
                            DFSUtil(item,visited,nodes1);
                      }
                  }
                  else
                     DFSUtil(item,visited,nodes1);
               }

               else {

                    if(startNode.getColor() == Color.BLACK){
                        tmp.pop();
                        Node nodex = tmp.pop();
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
                    else if(startNode.getColor() == Color.WHITE){
                        tmp.pop();
                        Node nodex = tmp.pop();
                        if(nodex.getX() == startNode.getX()){
                            if(item.getX() == startNode.getX())
                                DFSUtil(item,visited,nodes1);
                            else
                                continue;
                        }
                        if(nodex.getY() == startNode.getY()){
                            if(item.getY() == startNode.getY())
                                DFSUtil(item,visited,nodes1);
                            else
                                continue;
                        }
                    }
                    else
                        DFSUtil(item,visited,nodes1);
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
