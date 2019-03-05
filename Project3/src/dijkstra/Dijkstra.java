/*
Created by Alexander Swanson on 03/04/19.
*/


/* Package */
package dijkstra;


/* Imports */
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class Dijkstra {

    /**
     * Computes the shortest path to every node (vertex) in a given graph using Dijkstra's famous algorithm.
     */
    private static void computeForGraph(Graph graph, TraceableGraphVertex sourceVertex) {

        // Set a(vertex) = infinity for every vertex in the graph.
        for (TraceableGraphVertex vertex : graph.getVertices()) {
            vertex.minDistanceToGraphSource = Double.POSITIVE_INFINITY;
        }

        // Initialize the PriorityQueue, Q, the set of discovered nodes, S, and helper variables.
        PriorityQueue<TraceableGraphVertex> Q = new PriorityQueue<>(graph.getVertices());
        HashSet<TraceableGraphVertex> S = new HashSet<>(8);
        TraceableGraphVertex v;
        TraceableGraphVertex w;
        double newPathLenghtOfW;

        // Initialize a(sourceVertex) = 0.
        sourceVertex.minDistanceToGraphSource = 0;

        // Perform Dijkstra's algorithm.
        while (!Q.isEmpty()) {

            v = Q.poll();
            S.add(v);

            for (GraphEdge edge : v.edges) {

                w = edge.getDestination();

                if (!S.contains(w)) {

                    if (v.minDistanceToGraphSource + edge.getWeight() < w.minDistanceToGraphSource) {

                        // Remove w from Q for editing.
                        Q.remove(w);

                        // Determine the new path-length for w.
                        newPathLenghtOfW = v.minDistanceToGraphSource + edge.getWeight();
                        w.minDistanceToGraphSource = newPathLenghtOfW;

                        // Determine the new path-to-source-vertex for w.
                        w.pathToSource = new LinkedList<>(v.pathToSource);
                        w.appendPathToSource(v);

                        // Re-enter w.
                        Q.add(w);

                    }

                }

            }

        }

    }

    private static void UVMCS224Example() {

        // Construct a graph
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

        // Output result of Dijkstra's.
        System.out.println("Performing shortest-path-to-nodes search using Dijkstra's algorithm and an Adjacency List" +
                " data structure.");
        computeForGraph(graph, vS);
        System.out.format("%6s%20s%20s\n", "Vertex", "Distance", "Path");
        for (TraceableGraphVertex vertex : graph.getVertices()) {

            System.out.format(
                    "%3s%20s%30s\n", vertex, vertex.minDistanceToGraphSource, vertex.getPath()
            );

        }

    }

    public static void main(String[] args) {

        UVMCS224Example();

    }


}

