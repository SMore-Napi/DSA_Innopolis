package week13;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Graph<String, Integer> roads = new Graph<>();
        roads.addEdge("Kazan", "Innopolis", 50);
        roads.addEdge("Kazan", "Moscow", 1000);
        roads.addEdge("St. Petersburg", "Moscow", 800);
        roads.addEdge("St. Petersburg", "Kazan", 700);
        roads.addEdge("Kazan", "Rome", 5000);
        roads.addEdge("Moscow", "Rome", 4500);
        roads.addEdge("Moscow", "New York", 15000);

        for (String city : roads.vertices) {
            System.out.println(city);
        }

        for (Graph.Edge edge : roads.kruskalMST().edges) {
            System.out.println(edge);
        }
    }
}

class DisjointIntSets {
    int[] parents;

    public DisjointIntSets(int size) {
        this.parents = new int[size];
        for (int i = 0; i < size; i++) {
            this.parents[i] = i;
        }
    }

    public int find(int i) {
        if (parents[i] == i) {
            return i;
        } else {
            return find(parents[i]);
        }
    }

    public void union(int i, int j) {
        this.parents[this.find(i)] = this.find(j);
    }

    // { 0, 1, 2, 3, 4, 5 }
    // { {0 <- 2}, {1 -> 5 -> 4}, {3} }

    // { {    2 -> 0
    //             |
    //             v
    //   1 -> 5 -> 4}

    // { 1, 2, 1, 3, 2, 2 }


}

class Graph<V, E extends Comparable> {
    ArrayList<V> vertices;
    ArrayList<Edge> edges;

    class Edge {
        int from, to;
        E weight;

        public Edge(int from, int to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public E getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return
                    vertices.get(this.from).toString()
                            + " -(" + this.weight.toString() + ")- "
                            + vertices.get(this.to).toString();
        }
    }

    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public Graph(ArrayList<V> vertices) {
        this.vertices = new ArrayList<>();
        this.vertices.addAll(vertices);
        this.edges = new ArrayList<>();
    }

    public int addVertexIfNotExists(V vertex) {
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).equals(vertex)) {
                return i;
            }
        }
        this.vertices.add(vertex);
        return (this.vertices.size() - 1);
    }

    public void addEdge(V from, V to, E weight) {
        int fromIndex = this.addVertexIfNotExists(from);
        int toIndex = this.addVertexIfNotExists(to);
        this.addEdge(new Edge(fromIndex, toIndex, weight));
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    public Graph<V, E> kruskalMST() {
        Graph<V, E> mst = new Graph<>(this.vertices);  // O(V)

        DisjointIntSets forest = new DisjointIntSets(this.vertices.size()); // O(V)

        this.edges.sort(Comparator.comparing(Edge::getWeight)); // O(E log E)

        // E

        // O(E log V)
        for (Edge edge : this.edges) {
            if (forest.find(edge.from) != forest.find(edge.to)) {
                forest.union(edge.from, edge.to);
                mst.addEdge(edge);
            }
        }

        return mst;
    }
}
