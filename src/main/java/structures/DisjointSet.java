package structures;

public class DisjointSet<T> {

    private class Node {
        T data;
        Node parent;
        int rank;

        Node(T data) {
            this.data = data;
            this.parent = this;
            this.rank = 0;
        }
    }

    private MyList<Node> nodes = new MyList<>();

    public void makeSet(T data) {
        Node node = new Node(data);
        nodes.add(node);
    }

    private Node findNode(T data) {
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if (node.data.equals(data)) {
                return node;
            }
        }
        return null;
    }

    public T find(T data) {
        Node node = findNode(data);
        if (node == null) return null;
        return findRoot(node).data;
    }

    private Node findRoot(Node node) {
        if (node.parent != node)
            node.parent = findRoot(node.parent);
        return node.parent;
    }

    public void union(T data1, T data2) {
        Node root1 = findRoot(findNode(data1));
        Node root2 = findRoot(findNode(data2));

        if (root1 == root2) return;

        if (root1.rank < root2.rank) {
            root1.parent = root2;
        } else if (root1.rank > root2.rank) {
            root2.parent = root1;
        } else {
            root2.parent = root1;
            root1.rank++;
        }
    }
}
