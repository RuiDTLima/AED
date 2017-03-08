package series.serie3;


public class Edge implements Comparable<Edge>{
    private Vertex adjacent;
    private double weight;
    private Edge next;

    public Edge(Vertex adjacent, double weight) {
        this.adjacent = adjacent;
        this.weight = weight;
    }

    public Vertex getAdjacent() {
        return adjacent;
    }

    public double getWeight() {
        return weight;
    }

    public Edge getNext() {
        return next;
    }

    @Override
    public int compareTo(Edge edge) {
        return Double.compare(this.weight, edge.weight);
    }

    public void setNext(Edge next) {
        this.next = next;
    }

    public void setAdjacent(Vertex adjacent) {
        this.adjacent = adjacent;
    }
}
