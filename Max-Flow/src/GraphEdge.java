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
    double capacity;

    double flow;

    double residualFlow;


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

    double residualCapacityTo(int vertex) {
        if (vertex == v) {
            return flow;
        } else {
            return capacity - flow;
        }
    }

    void addResidualFlowToVertex(int vertex, double residualFlow) {

        // Backward edge
        if (vertex == v) {
            flow -= residualFlow;
            this.residualFlow = flow;

        // Forward Edge
        } else if (vertex == w) {
            flow += residualFlow;
            this.residualFlow = flow;
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

}
