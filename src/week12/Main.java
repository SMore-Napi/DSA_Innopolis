package week12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

    }
}

class Graph<V> {

    ArrayList<V> vertexList;

    public int inDegreeOf(V vertex) {
        //TODO: implement
        return 0;
    }

    public ArrayList<V> adjacentVertices(V from) {
        //TODO: implement
        return null;
    }

    enum Status {
        NOT_VISITED, OPENED, EXITED
    }

    public ArrayList<V> topsort(Graph<V> graph) {
        ArrayList<V> result = new ArrayList<>();
        HashMap<V, Status> vertexStatus = new HashMap<>();

        for (V vertex : this.vertexList) {
            vertexStatus.put(vertex, Status.NOT_VISITED);
        }

        V start = null;
        for (V vertex : this.vertexList) {
            if (this.inDegreeOf(vertex) == 0 && vertexStatus.get(vertex) == Status.NOT_VISITED) {
                start = vertex;
                break;
            }
        }

        if (start == null) {
            //TODO: no initial vertex: maybe exception?
        }

        Stack<V> dfsStack = new Stack<>();
        dfsStack.push(start);
        while (!dfsStack.isEmpty()) {
            V current = dfsStack.peek();
            vertexStatus.put(current, Status.OPENED);
            boolean isTerminal = true;

            for (V child : this.adjacentVertices(current)) {
                switch (vertexStatus.get(child)) {
                    case NOT_VISITED:
                        isTerminal = false;
                        dfsStack.push(child);
                        break;
                    case OPENED:
                        /* TODO: handle loop situation */
                    case EXITED:
                }

            }

            /* If current is terminal */
            if (isTerminal) {
                vertexStatus.put(current, Status.EXITED);
                result.add(0, current);
                dfsStack.pop();
            }
        }

        return result;
    }
}
