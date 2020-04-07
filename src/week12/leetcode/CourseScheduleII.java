package week12.leetcode;

import java.util.*;

public class CourseScheduleII {

    public static void main(String[] args) {

        /*
        int[][] prerequisites = {{0, 1}, {0, 2}, {1, 2}};
        int n = 3;

         */



        /*
        int[][] prerequisites = {};
        int n = 2;

         */

        /*
        int[][] prerequisites = {};
        int n = 1;

         */


        /*
        int[][] prerequisites = {{0, 1}, {3, 0}, {2, 3}, {0, 2}};
        int n = 4;

         */


        /*
        int[][] prerequisites = {{0, 1}, {3, 0}, {2, 3}, {2, 0}, {3,1}, {1,2}};
        int n = 4;

         */


        /*
        int[][] prerequisites = {{1, 0}};
        int n = 2;

         */

        /*
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int n = 4;

         */


        int[][] prerequisites = {{1, 0}, {2, 6}, {1, 7}, {5, 1}, {6, 4}, {7, 0}, {0, 5}};
        int n = 8;



        System.out.println(Arrays.toString(findOrder(n, prerequisites)));

    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        // Rewrite 'prerequisites' in a graph representation.
        Graph graph = new Graph();
        for (int i = 0; i < numCourses; i++) {
            graph.insertVertex(i);
        }
        for (int[] prerequisite : prerequisites) {
            graph.insertEdge(prerequisite[1], prerequisite[0]);
        }

        // The answer will be the in the same order as the topological sort result.
        return graph.topSort();
    }
}

class Graph {
    private ArrayList<Integer> vertices;
    private ArrayList<Edge> edges;

    private int size;

    class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public Graph() {
        this.vertices = new ArrayList<>(size);
        this.edges = new ArrayList<>();
    }

    public void insertVertex(int v) {
        this.vertices.add(v);
    }

    public void insertEdge(int from, int to) {
        this.edges.add(new Edge(from, to));
    }

    private ArrayList<Integer> adjacentVertices(int start) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Edge edge : this.edges) {
            if (edge.from == start) {
                result.add(edge.to);
            }
        }
        return result;
    }

    private LinkedList<Edge> incomingEdges(int to) {
        LinkedList<Edge> result = new LinkedList<>();
        for (Edge edge : this.edges) {
            if (edge.to == to) {
                result.add(edge);
            }
        }
        return result;
    }

    enum Status {NOT_VISITED, OPENED, EXITED}

    public int[] topSort() {
        size = vertices.size();
        int[] result = new int[size];
        int index = size - 1;

        HashMap<Integer, Status> vertexStatus = new HashMap<>();

        for (int vertex : this.vertices) {
            vertexStatus.put(vertex, Status.NOT_VISITED);
        }

        Integer start = null;
        for (int vertex : this.vertices) {
            if (incomingEdges(vertex).size() == 0
                    && vertexStatus.get(vertex) == Status.NOT_VISITED) {
                start = vertex;

                Stack<Integer> dfsStack = new Stack<>();
                dfsStack.push(start);
                while (!dfsStack.isEmpty()) {
                    int current = dfsStack.peek();

                    if (vertexStatus.get(current) != Status.EXITED) {
                        vertexStatus.put(current, Status.OPENED);
                        boolean isTerminal = true;

                        for (int child : adjacentVertices(current)) {
                            switch (vertexStatus.get(child)) {
                                case NOT_VISITED:
                                    isTerminal = false;
                                    dfsStack.push(child);
                                    break;
                                case OPENED:
                                    // In case of a loop
                                    return new int[0];
                                case EXITED: {
                                }
                            }
                        }

                        if (isTerminal) {
                            vertexStatus.put(current, Status.EXITED);
                            result[index--] = current;
                            dfsStack.pop();
                        }
                    } else {
                        dfsStack.pop();
                    }
                }
            }
        }

        // In case no initial vertex or not connected part of a graph has a loop.
        if (start == null || index >= 0) {
            return new int[0];
        }

        return result;
    }
}
