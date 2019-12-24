import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedWriter;
import java.io.File;
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


    public boolean checkSolution(Stack<Node> st){
        Stack<Node> tmp = (Stack<Node>) st.clone();

        tmp.forEach(y->{

        });
        return false;
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


               //Eksiklik var

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
                        Node nodex = tmp.pop();
                        if(nodex.getX() == startNode.getX()){
                            if(item.getX() == startNode.getX())
                                DFSUtil(item,visited,nodes1);
                            else{
                                System.out.println("girdiilk");
                                continue;

                            }
                        }
                        else if(nodex.getY() == startNode.getY()){
                            if(item.getY() == startNode.getY())
                                DFSUtil(item,visited,nodes1);
                            else{
                                System.out.println("girdi");
                                continue;

                            }
                        }
                        else
                            System.out.println("ws");
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
                                    Node nodea = tmp.pop();
                                    if(nodea.getX() != nodey.getX()){
                                        if(startNode.getY() == item.getY() )
                                            DFSUtil(item, visited, nodes1);
                                    }
                                    else if(nodea.getY() != nodey.getY()){
                                        if(startNode.getX() == item.getX())
                                            DFSUtil(item, visited, nodes1);
                                    }
                                    else{
                                        //Kırılmıştır istediği gibi gitsin
                                        DFSUtil(item, visited, nodes1);
                                    }
                                }
                                else
                                    DFSUtil(startNode, visited, nodes1);
                            }
                            else
                                DFSUtil(item, visited, nodes1);
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
