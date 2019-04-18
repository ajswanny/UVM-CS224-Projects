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

    /* Class Fields */
    /**
     * The list of Vertices in this Graph.
     */
    int numOfVertices;

    int numOfEdges;

    LinkedList<GraphEdge>[] adj;


    /* Constructors */
    /**
     * The default constructor.
     */
    Graph(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        numOfEdges = 0;
        adj = (LinkedList<GraphEdge>[]) new LinkedList[numOfVertices];
        for (int v = 0; v < numOfVertices; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    void addEdge(GraphEdge e) {
        int v = e.origin();
        int w = e.destination();
        adj[v].add(e);
        adj[w].add(e);
        numOfEdges++;
    }

}
