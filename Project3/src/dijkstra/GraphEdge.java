package dijkstra;

class GraphEdge {

    private TraceableGraphVertex origin;
    private TraceableGraphVertex target;

    private double weight;

    GraphEdge(TraceableGraphVertex target, TraceableGraphVertex origin, double weight) {

        this.origin = origin; this.target = target; this.weight = weight;

    }

}
