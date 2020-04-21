package week14.leetcode;

import java.util.*;

/**
 * Roman Soldatov BS19-02
 */
public class NetworkDelayTime {
    public static void main(String[] args) {
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4;
        int K = 2;

        Solution solution = new Solution();
        int x = solution.networkDelayTime(times, N, K);
        System.out.println(x);
    }
}

class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Graph<Integer, Integer> network = new Graph<>();

        for (int i = 1; i <= N; i++) {
            network.addVertex(i);
        }

        for (int[] time : times) {
            network.addEdge(time[0], time[1], time[2]);
        }

        int maxDistance = -1;

        HashMap<Integer, Integer> distances = network.shortestPaths(K);
        for (int i = 1; i <= N; i++) {
            Integer distance = distances.get(i);
            if (distance == null) {
                return -1;
            }
            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }

        return maxDistance;
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

    public void addVertex(V vertex) {
        adjacencyMap.put(vertex, new ArrayList<>());
    }

    public void addEdge(V from, V to, E weight) {
        if (!adjacencyMap.containsKey(from)) {
            adjacencyMap.put(from, new ArrayList<>());
        }
        adjacencyMap.get(from).add(new Edge(from, to, weight));
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
                    E distanceViaCurrent = (E) (Integer) (distanceToCurrent.intValue() + edge.weight.intValue());
                    notVisited.decreaseKey(edge.to, distanceViaCurrent);
                }
            }
        }

        return visited;
    }
}
