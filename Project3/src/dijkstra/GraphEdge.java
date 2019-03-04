package dijkstra;

class GraphEdge {

    private TraceableGraphVertex origin;
    private TraceableGraphVertex destination;

    private int weight;

    GraphEdge(TraceableGraphVertex origin, TraceableGraphVertex destination, int weight) {

        this.origin = origin; this.destination = destination; this.weight = weight;

    }

    public TraceableGraphVertex getOrigin() {
        return origin;
    }

    public TraceableGraphVertex getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
