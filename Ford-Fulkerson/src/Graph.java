/*
Created by Alexander Swanson on 03/04/19.
*/


/* Package */


/* Imports */
import java.util.ArrayList;


/**
 * An implementation of a Graph.
 */
class Graph {

    /* Class Fields */
    /**
     * The list of Vertices in this Graph.
     */
    private ArrayList<TraceableGraphVertex> vertices;


    /* Constructors */
    /**
     * The default constructor.
     */
    Graph(int numberOfNodes) {
        vertices = new ArrayList<>(numberOfNodes);
    }


    /* Methods */
    /**
     * Adds a Vertex (or Node) to this Graph.
     */
    void addVertex(TraceableGraphVertex vertex) {
        vertices.add(vertex);
    }

    /**
     * Returns all of the Vertices belonging to this Graph.
     */
    ArrayList<TraceableGraphVertex> getVertices() {
        return vertices;
    }

}
