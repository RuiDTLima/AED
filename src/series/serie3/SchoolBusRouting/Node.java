package series.serie3.SchoolBusRouting;


public class Node {
    public int weight,adjVertex,vertex;
    public Node next;
    public boolean visited;

    public Node(int w,int v,int vertex){
        weight = w;
        adjVertex = v;
        this.vertex = vertex;
    }

}
