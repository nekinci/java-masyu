public class Node {
    private Color color;
    private int x, y;

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Node(int x,int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public String printNode(){
        return "node: {"+x+", "+y+", "+color+" } ";
    }
}
