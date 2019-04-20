/*
Created by Alexander Swanson on 03/04/19.
*/

/**
 * An implementation of the Ford-Fulkerson algorithm, using a BFS methodology to find paths that can be augmented.
 *
 * This implementation is based on Princeton's Sedgewick and Wayne's program:
 *      https://algs4.cs.princeton.edu/64maxflow/FordFulkerson.java.html.
 */
public class FordFulkerson {

    /** Array of vertices discovered using BFS */
    private boolean[] discovered;

    /** Array of Edges that lead to a vertex */
    private GraphEdge[] edgeTo;

    private int maximumFlow;

    private FordFulkerson() {
    }

    public double MaxFlow(Graph G, int s, int t) {
        maximumFlow = 0;
        while (hasAugmentingPath(G, s, t)) {
            maximumFlow += Augment(s, t);
        }
        return maximumFlow;
    }

    private double Augment(int s, int t) {

        // Compute the bottleneck.
        double b = Double.POSITIVE_INFINITY;
        for (int v = t; v!= s; v = edgeTo[v].getOppositeVertexAlongEdgeOf(v)) {
            b = Math.min(b, edgeTo[v].getResidualCapacityTo(v));
        }

        // Augment the flow.
        for (int v = t; v != s; v = edgeTo[v].getOppositeVertexAlongEdgeOf(v)) {
            GraphEdge e = edgeTo[v];

            if (e.getOrigin() == v) {
                // 'e' is a backward edge
                e.setFlow(e.getFlow() - b);

            } else if (v == e.getDestination()) {
                // 'e' is a forward edge
                e.setFlow(e.getFlow() + b);
            }
        }

        return b;

    }

    private boolean hasAugmentingPath(Graph G, int s, int t) {
        edgeTo = new GraphEdge[G.numOfVertices];
        discovered = new boolean[G.numOfVertices];
        Queue<Integer> queue = new Queue<>();

        queue.enqueue(s);
        discovered[s] = true;

        int tail; int head;
        while (!queue.isEmpty() && !discovered[t]) {

            tail = queue.dequeue();
            for (GraphEdge e : G.adj(tail)) {

                head = e.getOppositeVertexAlongEdgeOf(tail);
                // If there is a residual capacity from tail to head
                if (e.getResidualCapacityTo(head) > 0) {
                    if (!discovered[head]) {
                        edgeTo[head] = e;
                        discovered[head] = true;
                        queue.enqueue(head);
                    }
                }
            }
        }

        return discovered[t];
    }

    /** Determines if 'vertex' is in the S partition of the minimum cut */
    private boolean vertexIsInSCut(int vertex) {
        return discovered[vertex];
    }

    public static void main(String[] args) {

        // Construct the given graph
        Graph g = new Graph(8);

        // Create the Graph edges using a format for creation to match the id's of vertices on the graph provided for
        // the assignment. Furthermore, s := 0 and t:= 7 (number of vertices in the graph).
        int s = 0, t = g.numOfVertices -1;
        g.addEdge(s, 2-1, 10); g.addEdge(s, 3-1, 5); g.addEdge(s, 4-1, 15);
        g.addEdge(2-1, 3-1, 4); g.addEdge(2-1, 5-1, 9); g.addEdge(2-1, 6-1, 15);
        g.addEdge(3-1, 4-1, 4); g.addEdge(3-1, 6-1, 8);
        g.addEdge(4-1, 7-1, 30);
        g.addEdge(5-1, 6-1, 15); g.addEdge(5-1, t, 10);
        g.addEdge(6-1, 7-1, 15); g.addEdge(6-1, t, 10);
        g.addEdge(7-1, 3-1, 6); g.addEdge(7-1, t, 10);

        // Some output
        FordFulkerson fordFulkerson = new FordFulkerson();
        int maxFlow = (int) fordFulkerson.MaxFlow(g, s, t);
        System.out.println("Graph:");
        System.out.println(g);
        System.out.println("Maximum flow: " + maxFlow + "\n");
        System.out.print("Minimum cut: ");
        for (int v = 0; v < g.numOfVertices; v++) {
            if (fordFulkerson.vertexIsInSCut(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println("\n");

    }

}
