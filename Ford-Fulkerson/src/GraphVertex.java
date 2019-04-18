/*
Created by Alexander Swanson on 03/04/19.
*/

/* Package */


/* Imports */
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * An implementation of a Vertex (or Node) for a Graph that allows for tracking of neighboring Vertices and a path to
 * a specified Graph's source Vertex.
 */
@SuppressWarnings("NullableProblems")
class GraphVertex implements Comparable<GraphVertex> {

    /* Class Fields */
    /**
     * Common identifier for this Vertex.
     */
    int id;

    /**
     * Minimum distance to the Vertex's Graph's source.
     */
    private double minDistanceToGraphSource;

    /**
     * List of Vertices leading to a Graph source.
     */
    private LinkedList<GraphVertex> pathToSource;

    /**
     * List of edges leading to neighboring Vertices.
     */
    ArrayList<GraphEdge> edges;

    int flow;


    /* Constructors */
    /**
     * Default constructor.
     */
    GraphVertex(int id) {

        this.id = id;
        edges = new ArrayList<>();
        pathToSource = new LinkedList<>();

    }


    /* Methods */
    /**
     * Adds an edge with a specified edge-weight to a neighboring Vertex.
     */
    void addEdge(GraphVertex destinationVertex, int edgeWeight) {
        edges.add(new GraphEdge(this, destinationVertex, edgeWeight, 0));
    }

    /**
     * Adds a new Vertex to the list of path-to-source Vertices.
     */
    void appendPathToSource(GraphVertex newVertex) {
        pathToSource.add(newVertex);
    }

    /**
     * Returns a formatted version of the list of vertices leading to a Graph source.
     */
    String getPath() {

        StringBuilder x = new StringBuilder();
        for (GraphVertex vertex : pathToSource) { x.append(vertex).append(" - "); }
        x.append(this);
        return x.toString();
    }

    /**
     * Overridden String output to return the Vertex's ID.
     */
    public String toString() {
        return String.valueOf(id);
    }

    /**
     * Overridden comparator to force comparison of Vertex distances to Graph sources.
     */
    @Override
    public int compareTo(GraphVertex otherVertex) {
        return Double.compare(minDistanceToGraphSource, otherVertex.minDistanceToGraphSource);
    }

}
