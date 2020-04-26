package assignments.assignment3;

import java.io.*;
import java.util.*;

// Uncomment it to use junit tests.
/*
import org.junit.Test;
import static org.junit.Assert.*;
*/

/**
 * @author Roman Soldatov BS19-02
 * <p>
 * 2.1 Connected graph.
 * Submission number: 78216138
 * https://codeforces.com/group/3ZU2JJw8vQ/contest/276900/submission/78216138
 * <p>
 * 2.2 Components dictionary.
 * Submission number: 78216837
 * https://codeforces.com/group/3ZU2JJw8vQ/contest/276900/submission/78216837
 * <p>
 * 2.3 Minimum spanning forest.
 * Submission number: 78217012
 * https://codeforces.com/group/3ZU2JJw8vQ/contest/276900/submission/78217012
 * <p>
 * This is the main class with main method which launch Codeforces tasks.
 * Also, this class contains junit methods for testing the Graph class.
 * It requires to import the junit library.
 * Maybe your IDE does not import this library automatically.
 * So, then this class won't compile because of errors.
 * So, just in case I commented it out.
 * Well, you can uncomment the code inside this class and import junit library to check tests.
 * Also, comment the main() method to run tests.
 * Tests are at the very bottom of this class.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Launch any task for the Codeforces.

        // A. Connectivity analysis.
        // task1();

        // B. Components dictionary.
        // task2();

        // C. Minimum spanning forest.
        task3();
    }

    /**
     * A. Connectivity analysis.
     */
    public static void task1() throws IOException {
        // Input Graph.
        Graph<Integer, Integer> graph = inputGraph();
        // Start vertex. Relative to it find the vertex which does not have a path with it.
        Integer start = 1;
        // The vertex which does not have a path with the start vertex.
        Integer counterexample = graph.analyzeConnectivity(start);

        // Output.
        OutputStream out = new BufferedOutputStream(System.out);
        // In case we found the counterexample vertex.
        if (counterexample != null) {
            out.write(("VERTICES " + start + " AND " + counterexample + " ARE NOT CONNECTED BY A PATH").getBytes());
        } // In case the graph is connected and we couldn't find the counterexample.
        else {
            out.write(("GRAPH IS CONNECTED").getBytes());
        }
        out.flush();
    }

    /**
     * B. Components dictionary.
     */
    public static void task2() throws IOException {
        // Input Graph.
        Graph<Integer, Integer> graph = inputGraph();

        // Calculate the dictionary.
        // Each vertex refer to some connectivity component.
        HashMap<Integer, Integer> dictionary = graph.vertexComponents();

        // Output
        OutputStream out = new BufferedOutputStream(System.out);
        // For each vertex output the component.
        for (int i = 1; i <= graph.getNumberVertices(); i++) {
            out.write((dictionary.get(i) + " ").getBytes());
        }
        out.flush();
    }

    /**
     * C. Minimum spanning forest.
     */
    public static void task3() throws IOException {
        // Input.
        int n = nextInt(); // number of vertices.
        int m = nextInt(); // number of edges.

        // Insert vertices from 1 to n inclusive.
        Graph<Integer, Integer> graph = new Graph<>();
        for (int i = 1; i <= n; i++) {
            graph.insertVertex(i);
        }

        // Insert edges from the input.
        for (int i = 0; i < m; i++) {
            int from = nextInt(); // start vertex.
            int to = nextInt(); // end vertex.
            int weight = nextInt(); // edge's weight.
            graph.insertEdge(from, to, weight); // insert an edge.
        }

        // Calculate the minimum spanning forest.
        ArrayList<Graph<Integer, Integer>> msf = graph.minimumSpanningForest();

        // Output.
        OutputStream out = new BufferedOutputStream(System.out);
        // Output the number of trees.
        out.write((msf.size() + "\n").getBytes());
        // Output each tree.
        for (Graph<Integer, Integer> tree : msf) {
            // Output the number of vertices and any vertex from the current tree.
            out.write((tree.getNumberVertices() + " " + tree.getAnyVertex() + "\n").getBytes());
            // Output edges from the current tree.
            for (Graph<Integer, Integer>.Edge edge : tree.getEdges()) {
                out.write((edge.toString() + "\n").getBytes());
            }
        }
        out.flush();
    }

    /**
     * The helper method for input an unweighted graph.
     * In this case all edges have the weight = 1.
     *
     * @return the graph.
     */
    private static Graph<Integer, Integer> inputGraph() throws IOException {
        int n = nextInt(); // number of vertices.
        int m = nextInt(); // number of edges.

        // Input vertices from 1 to n inclusive.
        Graph<Integer, Integer> graph = new Graph<>();
        for (int i = 1; i <= n; i++) {
            graph.insertVertex(i);
        }

        // Input edges.
        for (int i = 0; i < m; i++) {
            int from = nextInt();
            int to = nextInt();
            graph.insertEdge(from, to, 1);
        }

        return graph;
    }

    /**
     * Some methods for fast input reading.
     * I took it from here https://pastebin.com/2y4kFUzp
     */
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer stringTokenizer = new StringTokenizer("");

    private static String nextToken() throws IOException {
        while (!stringTokenizer.hasMoreTokens()) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        }
        return stringTokenizer.nextToken();
    }

    private static int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    /***** Tests *****/
    // Uncomment it to use junit tests.
    /*
    @Test
    public void testInsertion() {
        Graph<String, Integer> graph = initializeGraph1();

        String expected = "a:{[a-(2)-d]}, b:{[b-(1)-c], [b-(3)-d]}, c:{[c-(1)-b]}, d:{[d-(2)-a], [d-(3)-b]}, e:{}";
        assertEquals(expected, graph.toString());
    }

    @Test
    public void testAdjacentVertices() {
        Graph<String, Integer> graph = initializeGraph1();

        String expected = "{c=1, d=3}";
        assertEquals(expected, graph.getAdjacentVertices("b").toString());
    }

    @Test
    public void testGetNumberVertices() {
        Graph<String, Integer> graph = initializeGraph1();

        int expected = 5;
        assertEquals(expected, graph.getNumberVertices());
    }

    @Test
    public void testGetEdges() {
        Graph<String, Integer> graph = initializeGraph1();
        ArrayList<Graph<String, Integer>.Edge> edges = graph.getEdges();

        String expected = "[b c 1, a d 2, b d 3]";
        assertEquals(expected, edges.toString());
    }
     */

    /**
     * Helper method for Tests.
     */
    private Graph<String, Integer> initializeGraph1() {
        Graph<String, Integer> graph = new Graph<>();
        graph.insertVertex("a");
        graph.insertEdge("b", "c", 1);
        graph.insertEdge("a", "d", 2);
        graph.insertVertex("e");
        graph.insertEdge("b", "d", 3);

        return graph;
    }

    /**
     * Helper method for Tests.
     */
    private Graph<Integer, Integer> initializeGraph2() {
        Graph<Integer, Integer> graph = new Graph<>();
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 5, 1);
        graph.insertEdge(2, 1, 1);
        graph.insertEdge(2, 3, 1);
        graph.insertEdge(2, 4, 1);
        graph.insertEdge(3, 4, 1);
        graph.insertEdge(3, 5, 1);

        return graph;
    }
}

/**
 * A representation for undirected weighted generic graph.
 *
 * @param <V> - type of vertices.
 * @param <E> - type of edges (should be comparable).
 */
class Graph<V, E extends Comparable<E>> {

    // Adjacency list of vertices.
    // Each vertex stores the adjacent vertex and the edge's weight which connects them.
    private HashMap<V, HashMap<V, E>> adjacencyList;
    // Pairs (vertex <-> id)
    // id is the integer unique value.
    private Pairs pairs;
    // The list of graph edges.
    private ArrayList<Edge> edges;

    // Each vertex has the status (true or false).
    private HashMap<V, Boolean> vertexStatus;
    // Each vertex refer to some connectivity component.
    private HashMap<V, Integer> dictionary;
    // The number of components.
    private int componentNumber;

    /**
     * Default constructor.
     */
    public Graph() {
        this.adjacencyList = new HashMap<>();
        edges = new ArrayList<>();
        pairs = new Pairs();
    }

    /**
     * Constructor which copies vertices from another graph.
     *
     * @param vertices - vertices to insert.
     */
    private Graph(Set<V> vertices) {
        this();
        for (V vertex : vertices) {
            this.insertVertex(vertex);
        }
    }

    /**
     * Vertex insertion.
     *
     * @param vertex - vertex value to insert.
     */
    public void insertVertex(V vertex) {
        int size = this.getNumberVertices();
        pairs.insertPair(vertex, size);
        adjacencyList.put(vertex, new HashMap<>());
    }

    /**
     * Edge insertion
     *
     * @param from   - first vertex.
     * @param to     - second vertex.
     * @param weight - edge's weight.
     */
    public void insertEdge(V from, V to, E weight) {
        // If vertex 'from' hasn't been inserted before.
        if (!adjacencyList.containsKey(from)) {
            insertVertex(from);
        }

        // If vertex 'to' hasn't been inserted before.
        if (!adjacencyList.containsKey(to)) {
            insertVertex(to);
        }

        // Insert the edge.
        // For the 'from' vertex put the opposite vertex 'to' and the edge's weight.
        adjacencyList.get(from).put(to, weight);

        // Insert the edge.
        // For the 'to' vertex put the opposite vertex 'from' and the edge's weight.
        adjacencyList.get(to).put(from, weight);

        // Also, insert the edge in special list.
        edges.add(new Edge(from, to, weight));
    }

    /**
     * Get the number of vertices in a graph.
     *
     * @return the number of vertices.
     */
    public int getNumberVertices() {
        return adjacencyList.size();
    }

    /**
     * Get the list of edges from the graph.
     *
     * @return all edges.
     */
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     * Return the list of all vertices which are adjacent to the vertex.
     *
     * @param vertex - vertex which adjacent vertices is required to get.
     * @return the HashMap of adjacent vertices. (adjVertex <-> edge's weight).
     */
    public HashMap<V, E> getAdjacentVertices(V vertex) {
        return adjacencyList.get(vertex);
    }

    /**
     * Return a random vertex from the graph.
     *
     * @return vertex value from the graph.
     */
    public V getAnyVertex() {
        for (V vertex : adjacencyList.keySet()) {
            return vertex;
        }
        return edges.get(0).from;
    }

    /**
     * The Depth-First Search algorithm.
     *
     * @param start - start the DFS from this vertex.
     * @return - the list of vertices which are connected with 'start' vertex in DFS order.
     */
    public LinkedList<V> DFS(V start) {
        LinkedList<V> visitedVertices = new LinkedList<>();
        DFSVisit(start, visitedVertices);

        return visitedVertices;
    }

    /**
     * Recursive DFS method for visiting vertices.
     *
     * @param vertex          - start vertex.
     * @param visitedVertices - list of visited vertices.
     */
    private void DFSVisit(V vertex, LinkedList<V> visitedVertices) {
        // Put the status for the current vertex as 'visited'
        vertexStatus.put(vertex, true);
        // Add visited vertex to the list.
        visitedVertices.add(vertex);
        // Put for this vertex the component in which DFS works.
        dictionary.put(vertex, componentNumber);

        // Launch the DFS for adjacent vertices if they hasn't been visited yet.
        for (V adjacentVertex : this.getAdjacentVertices(vertex).keySet()) {
            if (vertexStatus.get(adjacentVertex).equals(false)) {
                DFSVisit(adjacentVertex, visitedVertices);
            }
        }
    }

    /**
     * This method indicates if the graph is connected or not.
     * In case it is not connected it returns a counterexample:
     * a vertex which doesn't have a path with the start vertex.
     * If there is no any counterexample then it return null.
     * <p>
     * This method launch the DFS method w.r.t. 'start' vertex.
     * 1) If it returns the list of all vertices then it means that all vertices are connected.
     * 2) If the list doesn't contain all vertices,
     * then there is at least one counterexample vertex in a graph
     * which is not connected with vertices from the DFS-list.
     * In this case we linearly scan all vertices to find the counterexample.
     * <p>
     * DFS: O(|V|+|E|). Linear scan: O(V).
     * Therefore, the total time complexity: O(|V|+|E|).
     *
     * @param start - vertex w.r.t. which a counterexample is searched.
     * @return counterexample vertex or null.
     */
    public V analyzeConnectivity(V start) {

        // Put for all vertices non visited status to launch the DFS.
        initializeVariables();

        // Get connected vertices w.r.t. 'start' vertex.
        LinkedList<V> vertices = this.DFS(start);

        // In case DFS-list contains not every vertex from the graph.
        if (vertices.size() != this.getNumberVertices()) {
            // Find the vertex which is not belong to DFS-list.
            for (V vertex : adjacencyList.keySet()) {
                if (!vertices.contains(vertex)) {
                    return vertex;
                }
            }
        }

        return null;
    }

    /**
     * This method returns a dictionary that maps every vertex of a graph
     * into a connected component it belongs to.
     * The dictionary is a HashMap. Each vertex assigns to the component.
     * The connected component is an integer number.
     * If vertices belong to connected path, then they belong to the same component.
     * <p>
     * To find components we can launch the DFS.
     * During the DFS all visited vertices put in a 'dictionary' with current 'componentNumber'.
     * Then we check the 'vertexStatus'. If some vertex has the 'false' status, i.e. it hasn't been visited,
     * then it means that this vertex belongs to another connected component.
     * So, we launch the DFS again starting from this vertex.
     * Do this procedure for all non visited vertices.
     * <p>
     * DFS: O(|V|+|E|).
     * Linear scan for non visited vertices: O(V).
     * As we linearly scan all vertices, but lunch DFS only for non visited vertices,
     * then we can say that |V| = |V1| + ... + |Vn|, where Vi - number of vertices in i component.
     * So, we will launch the DFS n times. and get
     * O(|V1|+|E|) + ... + O(|Vn|+|E|) = O(|V|+|E|).
     * In other words, we do the DFS for all vertices (imagine if they are all connected),
     * but just launch it several times for Vi....Vj vertices (not for all).
     * Therefore, the time complexity: O(|V|+|E|).
     *
     * @return HashMap of vertices and their component number.
     */
    public HashMap<V, Integer> vertexComponents() {

        // Put for all vertices non visited status to launch the DFS.
        // Initialize the 'dictionary' and the 'componentNumber'.
        initializeVariables();

        // For each non visited vertex launch the DFS
        // and put the current 'componentNumber' for vertices, which will be visited after DFS, in the dictionary.
        for (V vertex : adjacencyList.keySet()) {
            if (vertexStatus.get(vertex).equals(false)) {
                DFS(vertex);
                componentNumber++;
            }
        }

        return dictionary;
    }

    /**
     * This method returns a minimum spanning forest of a weighted graph.
     * It returns the list of graphs, where each graph is a minimum spanning tree.
     * <p>
     * We can launch the Kruskal's algorithm.
     * It uses the additional DS DisjointIntSets which represents some connected component for each vertex.
     * It helps us to determine if two any vertices belong to the same connected component.
     * 1) To fill DisjointIntSet and MSF we sort edges firstly.
     * 2) Then we linearly scan sorted edges from initial graph and insert them in MSF
     * if such edge connects two components.
     * 3) After that we can recalculate components in a DisjointIntSet.
     * 4) Then we linearly scan edges from MSF.
     * For each edge we determine the component of vertices, which it connects,
     * and insert such edge in a map, where key - component and value - graph (tree).
     * 5) Then it rewrites the map into the list of graph.
     * 6) Finally, it adds vertices, which doesn't have any adjacent vertex, to the list.
     * Such vertices haven't been added before because we scanned only edges
     * and that's why lonely vertices hasn't been observed.
     * <p>
     * MSF initialization: O(|V|).
     * DisjointIntSet initialization: O(|V|).
     * Sort edges: O(|E| log|E|).
     * Fill the minimum spanning forest: O(|E|).
     * Rewrite MSF into the map and the list of graphs: O(|E|).
     * Insert non adjacent vertices: O(V).
     * Time complexity: O(|E| log|E|)
     * or we can write O(|E| log|V|) as E = V(V-1)/2 for undirected graph in a worst case.
     *
     * @return Array List of graphs. Each graph is the minimum spanning tree.
     */
    public ArrayList<Graph<V, E>> minimumSpanningForest() {
        // Create the minimum spanning forest.
        // Initially this graph contains vertices from the main graph and doesn't have edges.
        Graph<V, E> msf = new Graph<>(adjacencyList.keySet());

        // Use the additional data structure to store connected components.
        DisjointIntSets disjointIntSets = new DisjointIntSets(this.getNumberVertices());

        // Sort edges.
        this.edges.sort(Comparator.comparing(Edge::getWeight));

        // Do the union for adjacent vertices which are in different components and add the edge in msf.
        for (Edge edge : this.edges) {
            if (disjointIntSets.find(pairs.getID(edge.from)) != disjointIntSets.find(pairs.getID(edge.to))) {
                disjointIntSets.union(pairs.getID(edge.from), pairs.getID(edge.to));
                msf.insertEdge(edge.from, edge.to, edge.weight);
            }
        }

        // Find representative (component number) for each vertex.
        disjointIntSets.recalculateRepresentatives();

        // Put for each representative (connected component) its connected graph.
        HashMap<V, Graph<V, E>> forestMap = new HashMap<>();
        for (Edge edge : msf.getEdges()) {
            V representative = pairs.getVertex(disjointIntSets.find(pairs.getID(edge.from)));

            Graph<V, E> graph = forestMap.getOrDefault(representative, new Graph<>());
            graph.insertEdge(edge.from, edge.to, edge.weight);

            forestMap.put(representative, graph);
        }

        // Temporary variable for calculating the number of vertices from added trees.
        int numberOfVertices = 0;
        ArrayList<Graph<V, E>> forest = new ArrayList<>();
        // Rewrite 'forestMap' in 'forest'.
        // Each 'forest' element is a graph (minimum spanning tree).
        for (V component : forestMap.keySet()) {
            Graph<V, E> tree = forestMap.get(component);
            forest.add(tree);
            numberOfVertices += tree.getNumberVertices();
        }

        // If not every vertex has been added to any minimum spanning tree,
        // then it means that there are vertices from 'msf' which doesn't have any adjacent vertex.
        // So, we add each such vertex as a new graph (graph with one vertex).
        if (numberOfVertices != msf.getNumberVertices()) {
            for (V vertex : msf.adjacencyList.keySet()) {
                if (msf.getAdjacentVertices(vertex).size() == 0) {
                    Graph<V, E> lonelyVertex = new Graph<>();
                    lonelyVertex.insertVertex(vertex);

                    forest.add(lonelyVertex);
                }
            }
        }

        return forest;
    }

    /**
     * Initialization and setting default values for dictionary, componentNumber and vertexStatus variables.
     */
    private void initializeVariables() {
        dictionary = new HashMap<>(adjacencyList.size());
        componentNumber = 1;

        vertexStatus = new HashMap<>();
        for (V vertex : adjacencyList.keySet()) {
            vertexStatus.put(vertex, false);
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (V vertex : adjacencyList.keySet()) {
            string.append(vertex.toString()).append(":{");
            StringBuilder edges = new StringBuilder();
            HashMap<V, E> adjacentVertices = adjacencyList.get(vertex);
            for (V oppositeVertex : adjacentVertices.keySet()) {
                edges.append('[').append(vertex.toString()).append("-(").append(adjacentVertices.get(oppositeVertex)).append(")-").append(oppositeVertex.toString()).append("], ");
            }
            if (!edges.toString().isEmpty()) {
                edges.delete(edges.length() - 2, edges.length());
                string.append(edges.toString());
            }
            string.append("}, ");
        }

        if (!string.toString().isEmpty()) {
            string.delete(string.length() - 2, string.length());
        }

        return string.toString();
    }

    /**
     * Edge representation.
     */
    class Edge {
        V from, to; // connected vertices.
        E weight; // the weight of the edge.

        /**
         * Constructor.
         *
         * @param from   - first vertex.
         * @param to     - second vertex.
         * @param weight - edge's weight.
         */
        public Edge(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        /**
         * Getter for the weight.
         * Should be used for the Comparator.
         *
         * @return weight of the edge.
         */
        public E getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return from + " " + to + " " + weight;
        }
    }

    /**
     * Helper class for storing the integer id for each vertex.
     */
    private class Pairs {
        private HashMap<V, Integer> pairV; // each vertex has the id
        private HashMap<Integer, V> pairID; // each id refers to some vertex

        /**
         * Default Constructor.
         */
        public Pairs() {
            pairV = new HashMap<>();
            pairID = new HashMap<>();
        }

        /**
         * Insertion the pair.
         *
         * @param vertex - vertex to store.
         * @param id     - assign the id (key) for this vertex.
         */
        public void insertPair(V vertex, int id) {
            pairV.put(vertex, id);
            pairID.put(id, vertex);
        }

        /**
         * Return the integer id to which the vertex refers to.
         *
         * @param vertex - vertex which id is required to get.
         * @return the id of the vertex.
         */
        public Integer getID(V vertex) {
            return pairV.get(vertex);
        }

        /**
         * Return the value of the vertex by id.
         *
         * @param id - id of this vertex.
         * @return value of the vertex.
         */
        public V getVertex(Integer id) {
            return pairID.get(id);
        }
    }
}

/**
 * The auxiliary class for storing disjoint integer sets.
 * Generic graph should have integer unique indices to work with methods.
 * After filling the disjoint set it stores the parent for each element.
 * Attention! Use the recalculateRepresentatives() method to get the final result.
 */
class DisjointIntSets {
    // Array which stores a parent for each element, i.e. parent[element] = parent
    private int[] parents;

    /**
     * The single constructor of disjoint sets.
     * Initially each element has a parent = element,
     * i.e. parent[element] = element
     *
     * @param size - the number of elements.
     */
    public DisjointIntSets(int size) {
        parents = new int[size];

        for (int i = 0; i < size; i++) {
            parents[i] = i;
        }
    }

    /**
     * Do the union of two elements.
     * The 'i' element will have a new parent of 'j' element.
     *
     * @param i - first element.
     * @param j - second element.
     */
    public void union(int i, int j) {
        parents[find(i)] = find(j);
    }

    /**
     * Find the parent of an element 'i'.
     *
     * @param i - element which parent is required to find.
     * @return the parent of the element 'i'.
     */
    public int find(int i) {
        if (parents[i] == i) {
            return i;
        }

        // Memoization of a parent (Now it may refer to another ancestor).
        parents[i] = find(parents[i]);
        return parents[i];
    }

    /**
     * Recalculates the parents for each element.
     * Now elements of the same hierarchy have the same parent (representative)
     * <p>
     * For example, if A had a parent B, B had a parent C, and C had a parent D,
     * i.e. A -> B -> C -> D,
     * then after this method all elements refers to D.
     * i.e. A -> D, B -> D, C -> D, D -> D.
     * So, all elements from union have the same parent (representative).
     */
    public void recalculateRepresentatives() {
        for (int i = 0; i < parents.length; i++) {
            parents[i] = find(parents[i]);
        }
    }
}
