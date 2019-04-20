/*
Created by Alexander Swanson on 03/03/19.
*/

/**
 * An implementation of a Graph Edge connecting two Vertices.
 */
class GraphEdge {

    /* Fields */
    /** Tail */
    private int v;

    /** Head */
    private int w;

    double capacity;
    private double flow;

    /* Constructors */
    /**
     * The default constructor.
     */
    GraphEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0;
    }

    /* Methods */
    /**
     * Updates flow for this edge.
     * @param vertex The head of the Edge.
     */
    void updateFlowTo(int vertex, double amount) {
        if (vertex == v) {
            // vertex is a backward edge
            flow -= amount;
        } else if (vertex == w) {
            // vertex is a forward edge
            flow += amount;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (v == 0) {
            stringBuilder.append("s");
        } else {
            stringBuilder.append(v+1);
        }
        stringBuilder.append(" -(").append(capacity).append("|").append(flow).append(")-> ").append(w+1);

        return stringBuilder.toString();
    }

    /**
     * Formatted 'toString()'.
     */
    String toStringWithEndVertex(int t) {
        StringBuilder stringBuilder = new StringBuilder();
        if (v == 0) {
            stringBuilder.append("s");
        } else {
            stringBuilder.append(v);
        }
        stringBuilder.append(" -(").append(capacity).append("|").append(flow).append(")-> ");

        if (w == t) {
            stringBuilder.append("t");
        } else {
            stringBuilder.append(w);
        }

        return stringBuilder.toString();
    }

    /* Getters */
    /** Gets the tail of this Edge */
    int getOrigin() {
        return v;
    }

    /** Gets the head of this Edge */
    int getDestination() {
        return w;
    }

    double getResidualCapacityTo(int vertex) {
        if (vertex == v) {
            return flow;
        } else {
            return capacity - flow;
        }
    }

    double getFlow() {
        return flow;
    }

    /**
     * Returns the vertex opposite to 'vertex' along this Edge.
     * @param vertex Origin vertex.
     */
    int getOppositeVertexAlongEdgeOf(int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new IllegalArgumentException("That vertex is not a part of this GraphEdge.");
        }
    }

    /* Setters */
    public void setFlow(double flow) {
        this.flow = flow;
    }

}
