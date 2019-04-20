/*
Created by Alexander Swanson on 03/04/19.
*/

/* Imports */
import java.util.LinkedList;


/**
 * An implementation of a Graph.
 */
class Graph {

    /* Fields */
    /** Number of nodes in Graph */
    int numOfVertices;

    /** Adjacency list representing Graph */
    private LinkedList<GraphEdge>[] adj;

    /* Constructor */
    /** Creates a Graph with n = numOfVertices nodes */
    Graph(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        adj = (LinkedList<GraphEdge>[]) new LinkedList[numOfVertices];
        for (int v = 0; v < numOfVertices; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    /* Methods */
    /** Creates a new edge for the Graph */
    void addEdge(int tail, int head, double capacity) {
        GraphEdge e = new GraphEdge(tail, head, capacity);
        int v = e.getOrigin();
        int w = e.getDestination();
        adj[v].add(e);
        adj[w].add(e);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Vertex:\tv -(Capacity|Flow)-> w\n");
        for (int v = 0; v < numOfVertices; v++) {
            stringBuilder.append(v).append(": ");
            for (GraphEdge e : adj[v]) {
                if (e.getDestination() != v) {
                    stringBuilder.append("\t").append(e.toStringWithEndVertex(numOfVertices)).append("\t");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /* Getters */
    Iterable<GraphEdge> adj(int vertex) {
        return adj[vertex];
    }

}
