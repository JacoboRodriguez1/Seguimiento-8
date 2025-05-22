package structures;

public class GraphAlgorithms {

    private MyList<GraphNode> nodes;

    public GraphAlgorithms() {
        nodes = new MyList<>();
    }

    public void addNode(GraphNode node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
    }

    public GraphNode getNode(String id) {
        for (int i = 0; i < nodes.size(); i++) {
            GraphNode node = nodes.get(i);
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }

    // DFS
    public void dfsRecursive(String startId) {
        GraphNode start = getNode(startId);
        if (start == null) return;

        MyList<GraphNode> visited = new MyList<>();
        dfsHelper(start, visited);
    }

    private void dfsHelper(GraphNode current, MyList<GraphNode> visited) {
        if (visited.contains(current)) return;

        visited.add(current);
        System.out.println("Visited: " + current.getId());

        MyList<Edge> edges = current.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            GraphNode neighbor = edges.get(i).getTo();
            dfsHelper(neighbor, visited);
        }
    }


    // BFS
    public void bfs(String startId) {
        GraphNode start = getNode(startId);
        if (start == null) return;

        MyQueue<GraphNode> queue = new MyQueue<>();
        MyList<GraphNode> visited = new MyList<>();

        queue.enqueue(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            GraphNode current = queue.dequeue();
            System.out.println("Visited: " + current.getId());

            MyList<Edge> edges = current.getEdges();
            for (int i = 0; i < edges.size(); i++) {
                GraphNode neighbor = edges.get(i).getTo();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.enqueue(neighbor);
                }
            }
        }
    }

    // Dijkstra
    public void dijkstra(String startId) {
        GraphNode start = getNode(startId);
        if (start == null) return;

        MyList<GraphNode> visited = new MyList<>();
        MyList<GraphNode> allNodes = nodes;
        MyPriorityQueue<DijkstraNode> queue = new MyPriorityQueue<>();

        for (int i = 0; i < allNodes.size(); i++) {
            GraphNode node = allNodes.get(i);
            int distance = node.equals(start) ? 0 : Integer.MAX_VALUE;
            queue.insert(new DijkstraNode(node, distance));
        }

        while (!queue.isEmpty()) {
            DijkstraNode current = queue.poll();
            if (visited.contains(current.getNode())) continue;

            visited.add(current.getNode());
            System.out.println("Distance to " + current.getNode().getId() + ": " + current.getDistance());

            MyList<Edge> edges = current.getNode().getEdges();
            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                GraphNode neighbor = edge.getTo();
                if (!visited.contains(neighbor)) {
                    int newDist = current.getDistance() + edge.getWeight();
                    queue.insert(new DijkstraNode(neighbor, newDist));
                }
            }
        }
    }

    private class DijkstraNode implements Comparable<DijkstraNode> {
        private GraphNode node;
        private int distance;

        public DijkstraNode(GraphNode node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(DijkstraNode other) {
            return Integer.compare(this.distance, other.distance);
        }

        private GraphNode getNode() {
            return node;
        }

        private void setNode(GraphNode node) {
            this.node = node;
        }

        private int getDistance() {
            return distance;
        }

        private void setDistance(int distance) {
            this.distance = distance;
        }
    }

    // Floyd-Warshall
    public void floydWarshall() {
        int n = nodes.size();
        MyList<MyList<Integer>> dist = new MyList<>();

        for (int i = 0; i < n; i++) {
            MyList<Integer> row = new MyList<>();
            for (int j = 0; j < n; j++) {
                if (i == j) row.add(0);
                else row.add(Integer.MAX_VALUE);
            }
            dist.add(row);
        }

        for (int i = 0; i < n; i++) {
            GraphNode node = nodes.get(i);
            MyList<Edge> edges = node.getEdges();
            for (int j = 0; j < edges.size(); j++) {
                Edge e = edges.get(j);
                int fromIndex = indexOf(e.getFrom());
                int toIndex = indexOf(e.getTo());
                dist.get(fromIndex).removeAt(toIndex);
                dist.get(fromIndex).addAt(toIndex, e.getWeight());
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int ik = dist.get(i).get(k);
                    int kj = dist.get(k).get(j);
                    if (ik != Integer.MAX_VALUE && kj != Integer.MAX_VALUE && ik + kj < dist.get(i).get(j)) {
                        dist.get(i).removeAt(j);
                        dist.get(i).addAt(j, ik + kj);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dist.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    private int indexOf(GraphNode node) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).equals(node)) {
                return i;
            }
        }
        return -1;
    }

    // Prim's Algorithm
    public void prim(String startId) {
        GraphNode start = getNode(startId);
        if (start == null) return;

        MyList<GraphNode> visited = new MyList<>();
        MyPriorityQueue<Edge> edgeQueue = new MyPriorityQueue<>();

        visited.add(start);
        for (int i = 0; i < start.getEdges().size(); i++) {
            edgeQueue.insert(start.getEdges().get(i));
        }

        while (!edgeQueue.isEmpty()) {
            Edge edge = edgeQueue.poll();
            GraphNode next = edge.getTo();

            if (!visited.contains(next)) {
                visited.add(next);
                System.out.println("Edge: " + edge);

                for (int i = 0; i < next.getEdges().size(); i++) {
                    Edge e = next.getEdges().get(i);
                    if (!visited.contains(e.getTo())) {
                        edgeQueue.insert(e);
                    }
                }
            }
        }
    }


}
