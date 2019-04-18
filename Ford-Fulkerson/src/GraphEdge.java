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
     * The v Vertex.
     */
    private int v;

    /**
     * The w Vertex.
     */
    private int w;

    /**
     * The capacity of the Edge.
     */
    int capacity;

    int flow;

    int residualCapacity;


    /* Constructors */
    /**
     * The default constructor.
     */
    GraphEdge(int v, int w, int capacity, int flow) {
        this.v = v; this.w = w; this.capacity = capacity; this.flow = flow;
    }

    int residualCapacityTo(int vertex) {
        if (vertex == v) {
            return flow;
        } else {
            return capacity - flow;
        }
    }

    int other(int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw  new IllegalArgumentException();
        }
    }

    int origin() {
        return v;
    }

    int destination() {
        return w;
    }

}
