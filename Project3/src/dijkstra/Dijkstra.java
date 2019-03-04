package dijkstra;

import java.util.HashSet;
import java.util.PriorityQueue;

public class Dijkstra {

    private static TraceableGraphVertex ExtractMin(PriorityQueue<TraceableGraphVertex> Q) {

        return Q.poll();

    }

    private static void ChangeKey(PriorityQueue<TraceableGraphVertex> Q, TraceableGraphVertex w, int newDistanceOfW) {

        Q.remove(w);
        w.setDistanceToGraphSource(newDistanceOfW);
        Q.add(w);

    }

    public static void computeForGraph(Graph graph, TraceableGraphVertex sourceVertex) {

        // Set a(vertex) = infinity for every vertex in the graph.
        for (TraceableGraphVertex vertex : graph.getVertices()) { vertex.setDistanceToGraphSource(Integer.MAX_VALUE); }

        PriorityQueue<TraceableGraphVertex> Q = new PriorityQueue<>(graph.getVertices());
        HashSet<TraceableGraphVertex> S = new HashSet<>(8);
        sourceVertex.setDistanceToGraphSource(0);
        TraceableGraphVertex v;
        TraceableGraphVertex w;
        int newDistanceOfW;

        while (!Q.isEmpty()) {

            v = ExtractMin(Q);

            S.add(v);

            for (GraphEdge edge : v.getEdges()) {

                w = edge.getDestination();

                if (!S.contains(w)) {

                    if (v.getDistanceToGraphSource() + edge.getWeight() < w.getDistanceToGraphSource()) {

                        w.setPreviousVertexInPath(v);
                        newDistanceOfW = v.getDistanceToGraphSource() + edge.getWeight();
                        ChangeKey(Q, w, newDistanceOfW);

                    }

                }

            }

        }

    }

    public static void main(String[] args) {


        // Construct a graph.e
        Graph graph = new Graph(8);
        TraceableGraphVertex vS, v2, v3, v4, v5, v6, v7, vT;
        vS = new TraceableGraphVertex("s"); graph.addVertex(vS);
        v2 = new TraceableGraphVertex("2"); graph.addVertex(v2);
        v3 = new TraceableGraphVertex("3"); graph.addVertex(v3);
        v4 = new TraceableGraphVertex("4"); graph.addVertex(v4);
        v5 = new TraceableGraphVertex("5"); graph.addVertex(v5);
        v6 = new TraceableGraphVertex("6"); graph.addVertex(v6);
        v7 = new TraceableGraphVertex("7"); graph.addVertex(v7);
        vT = new TraceableGraphVertex("t"); graph.addVertex(vT);

        vS.addEdge(v2, 9); vS.addEdge(v6, 14); vS.addEdge(v7, 15);
        v2.addEdge(v3, 23);
        v3.addEdge(v5, 2); v3.addEdge(vT, 19);
        v4.addEdge(v3, 6); v4.addEdge(vT, 6);
        v5.addEdge(v4, 11); v5.addEdge(vT, 16);
        v6.addEdge(v3, 18); v6.addEdge(v5, 30); v6.addEdge(v7, 5);
        v7.addEdge(v5, 20); v7.addEdge(vT, 44);

        graph.print();

        System.out.println("----------------------------------");

        computeForGraph(graph, vS);
        for (TraceableGraphVertex vertex : graph.getVertices()) {

            System.out.printf(
                    "Vertex: %s \t Distance: %d \t Previous: %s \n",
                    vertex, vertex.getDistanceToGraphSource(), vertex.getPreviousVertexInPath()
            );

        }


    }


}

