package dijkstra;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class TraceableGraphVertex implements Comparable<TraceableGraphVertex> {

    private String id;
    private int distanceToGraphSource;
    private TraceableGraphVertex previousVertexInPath;
    private ArrayList<TraceableGraphVertex> neighbors;
    private ArrayList<GraphEdge> edges;

    TraceableGraphVertex(String id) {

        this.id = id;
        neighbors = new ArrayList<>();
        edges = new ArrayList<>();

    }

    void addNeighbor(TraceableGraphVertex vertex) {
        neighbors.add(vertex);
    }

    void addNeighbors(List<TraceableGraphVertex> neighbors) {
        this.neighbors.addAll(neighbors);
    }

    void addEdge(TraceableGraphVertex destinationVertex, int edgeWeight) {
        edges.add(new GraphEdge(this, destinationVertex, edgeWeight));
    }

    String getId() {
        return id;
    }

    ArrayList<TraceableGraphVertex> getNeighbors() {
        return neighbors;
    }

    public int getDistanceToGraphSource() {
        return distanceToGraphSource;
    }

    public ArrayList<GraphEdge> getEdges() {
        return edges;
    }

    public TraceableGraphVertex getPreviousVertexInPath() {
        return previousVertexInPath;
    }

    public void setDistanceToGraphSource(int distanceToGraphSource) {
        this.distanceToGraphSource = distanceToGraphSource;
    }

    public void setPreviousVertexInPath(TraceableGraphVertex previousVertexInPath) {
        this.previousVertexInPath = previousVertexInPath;
    }

    public String toString() {
        return id;
    }

    @Override
    public int compareTo(TraceableGraphVertex other) {
        return Integer.compare(distanceToGraphSource, other.getDistanceToGraphSource());
    }

}
