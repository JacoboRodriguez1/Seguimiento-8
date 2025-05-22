package structures;

public class GraphNode {

    private String id;
    private MyList<Edge> edges;

    public GraphNode(String id) {
        this.id = id;
        this.edges = new MyList<>();
    }

    public String getId() {
        return id;
    }

    public MyList<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GraphNode)) return false;
        GraphNode other = (GraphNode) obj;
        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
