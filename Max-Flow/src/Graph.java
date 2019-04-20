/*
Created by Alexander Swanson on 03/04/19.
*/


/* Package */


/* Imports */
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * An implementation of a Graph.
 */
class Graph {

    int numOfVertices;

    int numOfEdges;

    LinkedList<GraphEdge>[] adj;

    Graph(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        numOfEdges = 0;
        adj = (LinkedList<GraphEdge>[]) new LinkedList[numOfVertices];
        for (int v = 0; v < numOfVertices; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    void addEdge(int tail, int head, double capacity) {
        GraphEdge e = new GraphEdge(tail, head, capacity);
        int v = e.origin();
        int w = e.destination();
        adj[v].add(e);
        adj[w].add(e);
        numOfEdges++;
    }

    Iterable<GraphEdge> adj(int vertex) {
        return adj[vertex];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Vertex:\tv -(Capacity|Flow)-> w\n");
        for (int v = 0; v < numOfVertices; v++) {
            stringBuilder.append(v).append(": ");
            for (GraphEdge e : adj[v]) {
                if (e.destination() != v) {
                    stringBuilder.append("\t").append(e.toString(numOfVertices)).append("\t");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
