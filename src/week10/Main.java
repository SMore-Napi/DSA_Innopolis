package week10;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        Graph<String, Double> sampleGraph = new Graph<>();
        sampleGraph.insertVertex("Innopolis");
        sampleGraph.insertVertex("Kazan");
        sampleGraph.insertVertex("Kyzyl");
        sampleGraph.insertVertex("Coltan");
        sampleGraph.insertVertex("Vice City");
        sampleGraph.insertVertex("Shagonar");
        sampleGraph.insertVertex("Moscow");

        sampleGraph.insertEdge("Innopolis", "Kazan", 420.1);
        sampleGraph.insertEdge("Kazan", "Kyzyl", 300.0);
        sampleGraph.insertEdge("Kazan", "Moscow", 800.0);
        sampleGraph.insertEdge("Moscow", "Vice City", 100.0);
        sampleGraph.insertEdge("Kyzyl", "Shagonar", 4000.0);

        String start = "Kazan";
        for (Graph<String, Double>.Edge edge : sampleGraph.incidentEdges(start)) {
            System.out.println(edge);
        }

        for (String city : sampleGraph.getReachable(start, 1000.0)) {
            System.out.println(city);
        }
    }
}

class Graph<V, E extends Number> {
    class Edge {
        V from;
        V to;
        E weight;

        public Edge(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return (from + " =(" + weight + ")- " + to);
        }
    }

    LinkedList<V> vertices;
    LinkedList<Edge> edges;

    public Graph() {
        this.vertices = new LinkedList<>();
        this.edges = new LinkedList<>();
    }

    /**
     * O(1)
     */
    public void insertVertex(V vertex) {
        this.vertices.add(vertex);
    }

    /**
     * O(1)
     */
    public void insertEdge(V from, V to, E weight) {
        this.edges.add(new Edge(from, to, weight));

    }

    /**
     * O(# edges)
     */
    public LinkedList<Edge> incidentEdges(V vertex) {
        LinkedList<Edge> result = new LinkedList<>();
        for (Edge edge : this.edges) {
            if (edge.from.equals(vertex) || edge.to.equals(vertex)) {
                result.add(edge);
            }
        }
        return result;
    }

    /**
     * O(n^{maxDistance})
     */
    public LinkedList<V> getReachable(V start, Double maxDistance) {
        LinkedList<V> reachable = new LinkedList<>();
        reachable.add(start);
        if (maxDistance.compareTo(0.0) <= 0) {
            return reachable;
        } else {
            for (Edge edge : incidentEdges(start)) {
                V next = edge.to;
                if (next.equals(start)) {
                    next = edge.from;
                }
                LinkedList<V> nextReachable = getReachable(next, maxDistance - edge.weight.doubleValue());
                reachable.addAll(nextReachable);
            }
            return reachable;
        }
    }
}
