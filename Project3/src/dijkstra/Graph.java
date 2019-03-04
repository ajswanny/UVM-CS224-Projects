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

    void addVertices(ArrayList<TraceableGraphVertex> vertices) {

        this.vertices.addAll(vertices);

    }

    void addNeighborToVertexByTargetIndex(int targetVertexIndex, TraceableGraphVertex neighborVertex) {

        vertices.get(targetVertexIndex).addNeighbor(neighborVertex);

    }

    void print() {

        for (TraceableGraphVertex traceableGraphVertex : vertices) {

            System.out.printf("Vertex (ID): (%s) | Neighbors (ID): ", traceableGraphVertex.getId());

            if (traceableGraphVertex.getEdges().isEmpty()) { System.out.print("None"); System.out.println(); } else {

                for (GraphEdge edge : traceableGraphVertex.getEdges()) {
                    System.out.printf("(%s) ", edge.getDestination().getId());
                }

                System.out.println();

            }

        }

    }

    TraceableGraphVertex getVertex(int targetVertexIndex) {

        return vertices.get(targetVertexIndex);

    }

    TraceableGraphVertex getVertexById(String targetVertexId) {

        for (TraceableGraphVertex vertex : vertices) { if (vertex.getId().equals(targetVertexId)) return vertex; }

        return null;

    }

    public ArrayList<TraceableGraphVertex> getVertices() {
        return vertices;
    }

}
