/*
Created by Alexander Swanson on 03/04/19.
*/

/* Package */
package dijkstra;


/* Imports */
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * An implementation of a Vertex (or Node) for a Graph that allows for tracking of neighboring Vertices and a path to
 * a specified Graph's source Vertex.
 */
@SuppressWarnings("NullableProblems")
class TraceableGraphVertex implements Comparable<TraceableGraphVertex> {

    /* Class Fields */
    /**
     * Common identifier for this Vertex.
     */
    private String id;

    /**
     * Minimum distance to the Vertex's Graph's source.
     */
    double minDistanceToGraphSource;

    /**
     * List of Vertices leading to a Graph source.
     */
    LinkedList<TraceableGraphVertex> pathToSource;

    /**
     * List of edges leading to neighboring Vertices.
     */
    ArrayList<GraphEdge> edges;


    /* Constructors */
    /**
     * Default constructor.
     */
    TraceableGraphVertex(String id) {

        this.id = id;
        edges = new ArrayList<>();
        pathToSource = new LinkedList<>();

    }


    /* Methods */
    /**
     * Adds an edge with a specified edge-weight to a neighboring Vertex.
     */
    void addEdge(TraceableGraphVertex destinationVertex, int edgeWeight) {
        edges.add(new GraphEdge(this, destinationVertex, edgeWeight));
    }

    /**
     * Adds a new Vertex to the list of path-to-source Vertices.
     */
    void appendPathToSource(TraceableGraphVertex newVertex) {
        pathToSource.add(newVertex);
    }

    /**
     * Returns a formatted version of the list of vertices leading to a Graph source.
     */
    String getPath() {

        StringBuilder x = new StringBuilder();
        for (TraceableGraphVertex vertex : pathToSource) { x.append(vertex).append(" - "); }
        x.append(this);
        return x.toString();
    }

    /**
     * Overridden String output to return the Vertex's ID.
     */
    public String toString() {
        return id;
    }

    /**
     * Overridden comparator to force comparison of Vertex distances to Graph sources.
     */
    @Override
    public int compareTo(TraceableGraphVertex otherVertex) {
        return Double.compare(minDistanceToGraphSource, otherVertex.minDistanceToGraphSource);
    }

}
