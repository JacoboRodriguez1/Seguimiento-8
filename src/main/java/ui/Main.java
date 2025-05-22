package ui;

import structures.*;

public class Main {

    public static void main(String[] args) {

        // Crear nodos
        GraphNode a = new GraphNode("A");
        GraphNode b = new GraphNode("B");
        GraphNode c = new GraphNode("C");
        GraphNode d = new GraphNode("D");
        GraphNode e = new GraphNode("E");

        // Crear grafo y añadir nodos
        GraphAlgorithms graph = new GraphAlgorithms();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);

        // Añadir aristas (edges) con peso (para algoritmos como Dijkstra y Prim)
        a.addEdge(new Edge(a, b, 2));
        a.addEdge(new Edge(a, c, 5));

        b.addEdge(new Edge(b, c, 1));
        b.addEdge(new Edge(b, d, 2));

        c.addEdge(new Edge(c, d, 3));
        c.addEdge(new Edge(c, e, 1));

        d.addEdge(new Edge(d, e, 1));

        // Probamos DFS desde A
        System.out.println("DFS starting from A:");
        graph.dfsRecursive("A");
        System.out.println();

        // Probamos BFS desde A
        System.out.println("BFS starting from A:");
        graph.bfs("A");
        System.out.println();

        // Probamos Dijkstra desde A
        System.out.println("Dijkstra starting from A:");
        graph.dijkstra("A");
        System.out.println();

        // Probamos Floyd-Warshall (matriz de distancias)
        System.out.println("Floyd-Warshall shortest paths matrix:");
        graph.floydWarshall();
        System.out.println();

        // Probamos Prim para árbol de expansión mínima empezando en A
        System.out.println("Prim MST starting from A:");
        graph.prim("A");
    }
}
