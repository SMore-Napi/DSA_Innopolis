package week14;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph<String, Double> roads = new Graph<>();
        roads.addEdge("Innopolis", "Kazan", 50.0);
        roads.addEdge("Moscow", "Kazan", 1000.0);
        roads.addEdge("St. Petersburg", "Kazan", 1100.0);
        roads.addEdge("St. Petersburg", "Moscow", 900.0);
        roads.addEdge("St. Petersburg", "New York", 10000.0);
        roads.addEdge("Moscow", "New York", 9000.0);

        String start = "Innopolis";
        HashMap<String, Double> distances = roads.shortestPaths(start);
        for (String city : distances.keySet()) {
            System.out.println("Distance from " + start + " to " + city + " is " + distances.get(city));
        }
    }
}

class SimplePriorityQueue<K extends Comparable, V> {
    ArrayList<KeyValuePair> pairs;

    class KeyValuePair {
        K key;
        V value;

        public KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public SimplePriorityQueue() {
        this.pairs = new ArrayList<>();
    }

    // O(1)
    public void add(K key, V value) {
        this.pairs.add(new KeyValuePair(key, value));
    }

    // O(n)
    public KeyValuePair extractMinimum() {
        KeyValuePair minPair = Collections.min(this.pairs, Comparator.comparing(KeyValuePair::getKey));
        this.pairs.remove(minPair);
        return minPair;
    }

    // O(n)
    public void decreaseKey(V value, K newKey) {
        for (KeyValuePair pair : this.pairs) {
            if (pair.value.equals(value)) {
                if (pair.key.compareTo(newKey) > 0) {
                    pair.key = newKey; // only update key if newKey is better
                }
                return;
            }
        }

        add(newKey, value);
    }

    public boolean isEmpty() {
        return this.pairs.isEmpty();
    }
}

class Graph<V, E extends Number & Comparable> {
    HashMap<V, ArrayList<Edge>> adjacencyMap;

    class Edge {
        V from, to;
        E weight;

        public Edge(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public Graph() {
        this.adjacencyMap = new HashMap<>();
    }

    public void addEdge(V from, V to, E weight) {
        if (!adjacencyMap.containsKey(from)) {
            adjacencyMap.put(from, new ArrayList<>());
        }
        adjacencyMap.get(from).add(new Edge(from, to, weight));

        if (!adjacencyMap.containsKey(to)) {
            adjacencyMap.put(to, new ArrayList<>());
        }
        adjacencyMap.get(to).add(new Edge(to, from, weight));
    }

    public HashMap<V, E> shortestPaths(V from) {
        HashMap<V, E> visited = new HashMap<>();

        SimplePriorityQueue<E, V> notVisited = new SimplePriorityQueue<>();
        notVisited.add((E) (Integer) 0, from);

        while (!notVisited.isEmpty()) {
            SimplePriorityQueue<E, V>.KeyValuePair currentPair = notVisited.extractMinimum();

            V current = currentPair.value;
            E distanceToCurrent = currentPair.key;
            visited.put(current, distanceToCurrent);

            for (Edge edge : this.adjacencyMap.getOrDefault(current, new ArrayList<>())) {
                if (!visited.containsKey(edge.to)) {
                    E distanceViaCurrent = (E) (Double) (distanceToCurrent.doubleValue() + edge.weight.doubleValue());
                    notVisited.decreaseKey(edge.to, distanceViaCurrent);
                }
            }
        }

        return visited;
    }
}