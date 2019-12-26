
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Graph {
    private HashMap<Node, ArrayList<Node>> nodes;
    private int n, k;
    private BufferedWriter writer;
    private Stack<Node> solution;
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
        if (size == k){
            Node start = tmp.firstElement();
            Node startAfter = tmp.get(1);
            Node startAfterAfter = tmp.get(2);
            Node end = tmp.lastElement();
            Node beforeEnd = tmp.get(tmp.size() - 1 - 1);
            Node beforeBeforeEnd = tmp.get(tmp.size() -1 - 1 - 1);
            if((end.getX() == start.getX() - 1 || end.getX() == start.getX() + 1) && end.getY() == start.getY() ){

                if(start.getColor() == Color.WHITE){
                    if(startAfter.getY() == start.getY()){
                       if(end.getColor() == Color.BLACK){
                            if(beforeEnd.getY() == end.getY())
                                return false;
                            else {
                                tmp.forEach(y->{
                                    x+= y.printNode();
                                });
                            }
                       }
                       else{
                           if(startAfterAfter.getY() == startAfter.getY()){
                               if(beforeEnd.getY() == end.getY())
                                   return false;
                               else{
                                   tmp.forEach(y->{
                                       x+= y.printNode();
                                   });
                               }
                           }
                           else{
                               tmp.forEach(y->{
                                   x+= y.printNode();
                               });
                           }
                       }
                    }
                }
                else if(start.getColor() == Color.BLACK){
                    if(start.getY() == startAfter.getY())
                        return false;
                    else{
                        if(start.getX() == startAfterAfter.getX() && end.getY() == beforeEnd.getY()){
                            tmp.forEach(y->{
                                x+= y.printNode();
                            });
                        }
                        else{
                            return false;
                        }
                    }
                }
                else{
                    tmp.forEach(y->{
                        x+= y.printNode();
                    });
                }
            }
            else if((end.getY() == start.getY() -1 || end.getY() == start.getY() +1 ) && end.getX() == start.getX()){

                if(start.getColor() == Color.WHITE){
                    if(startAfter.getX() == start.getX()){
                        if(end.getColor() == Color.BLACK){
                            if(beforeEnd.getX() == end.getX())
                                return false;
                            else{
                                tmp.forEach(y->{
                                    x+= y.printNode();
                                });
                            }
                        }
                        else{
                            if(startAfterAfter.getX() == startAfter.getX()){
                                if(beforeEnd.getX() == end.getX())
                                    return false;
                                else{
                                    tmp.forEach(y->{
                                        x+= y.printNode();
                                    });
                                }
                            }

                            else{
                                tmp.forEach(y->{
                                    x+= y.printNode();
                                });
                            }
                        }
                    }
                }
                else if(start.getColor() == Color.BLACK){
                    if(start.getX() == startAfter.getX())
                        return false;
                    else {
                        if(start.getY() == startAfter.getY() && end.getX() == beforeEnd.getX()){
                            tmp.forEach(y->{
                                x+= y.printNode();
                            });
                        }
                        else{
                            boolean x = false;
                            return x;
                        }
                    }
                }
                else{
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
            return true;
        }

        return false;
    }



   public void DFSUtil(Node startNode,HashMap<Node, Boolean> visited, Stack<Node> nodes1){
       ArrayList<Node> neighbours = (ArrayList<Node>) nodes.get(startNode);
       visited.put(startNode, true);
       nodes1.push(startNode);
       Stack<Node> tmp = (Stack<Node>) nodes1.clone();

       //yazdir(nodes1);
       if(checkSolution(nodes1)) solution =(Stack<Node>) nodes1.clone();
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
                                            if (item.getX() == nodex.getX())
                                                continue;
                                            else {
                                                if(item.getColor() == Color.BLACK){
                                                    if(nodex.getY() == startNode.getY()){
                                                        if(item.getX() == startNode.getX())
                                                            continue;
                                                        else
                                                            DFSUtil(item, visited, nodes1);
                                                    }
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
   public Stack<Node> DFS(Node startNode,int k){
        HashMap<Node, Boolean> visited = initVisitStates();
        Stack<Node> nodes = new Stack<>();
        this.k = k;
        DFSUtil(startNode, visited,nodes);
        return solution;
   }

   public int count(Node node){
       return nodes.get(node).size();
   }



}
