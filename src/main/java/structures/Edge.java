package structures;

public class Edge implements Comparable<Edge> {
    private GraphNode from;
    private GraphNode to;
    private int weight;

    public Edge(GraphNode from, GraphNode to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public GraphNode getFrom() {
        return from;
    }

    public GraphNode getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return from.getId() + " -> " + to.getId() + " (weight: " + weight + ")";
    }
}
