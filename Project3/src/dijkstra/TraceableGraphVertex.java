package dijkstra;

import java.util.ArrayList;
import java.util.List;

class TraceableGraphVertex {

    private String id;
    private int distanceToGraphSource;
    private TraceableGraphVertex previousVertexInPath;
    private ArrayList<TraceableGraphVertex> neighbors;
    private ArrayList<GraphEdge> edges;

    TraceableGraphVertex(String id) {

        this.id = id;
        neighbors = new ArrayList<>();

    }

    void addNeighbor(TraceableGraphVertex vertex) {

        neighbors.add(vertex);

    }

    void addNeighbors(List<TraceableGraphVertex> neighbors) {

        this.neighbors.addAll(neighbors);

    }

    String getId() {

        return id;

    }

    ArrayList<TraceableGraphVertex> getNeighbors() {

        return neighbors;

    }

}
