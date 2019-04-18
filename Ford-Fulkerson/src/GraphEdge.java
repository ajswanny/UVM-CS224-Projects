/*
Created by Alexander Swanson on 03/03/19.
*/


/* Package */


/**
 * An implementation of a Graph Edge connecting two Vertices.
 */
class GraphEdge {

    /* Class Fields */
    /**
     * The origin Vertex.
     */
    private TraceableGraphVertex origin;

    /**
     * The destination Vertex.
     */
    private TraceableGraphVertex destination;

    /**
     * The weight of the Edge.
     */
    private int weight;


    /* Constructors */
    /**
     * The default constructor.
     */
    GraphEdge(TraceableGraphVertex origin, TraceableGraphVertex destination, int weight) {
        this.origin = origin; this.destination = destination; this.weight = weight;
    }

    /* Methods */
    /**
     * Returns the destination-Vertex of this Edge.
     */
    TraceableGraphVertex getDestination() {
        return destination;
    }

    /**
     * Returns the weight of this Edge.
     */
    int getWeight() {
        return weight;
    }

}
