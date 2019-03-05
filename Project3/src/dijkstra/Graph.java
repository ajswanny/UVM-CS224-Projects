package dijkstra;

import java.util.ArrayList;

@SuppressWarnings("SameParameterValue")
class Graph {

    private ArrayList<TraceableGraphVertex> vertices;

    Graph(int numberOfNodes) {

        vertices = new ArrayList<>(numberOfNodes);

    }

    void addVertex(TraceableGraphVertex vertex) {

        vertices.add(vertex);

    }

    public ArrayList<TraceableGraphVertex> getVertices() {
        return vertices;
    }

}
