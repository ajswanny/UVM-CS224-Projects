import java.util.ArrayList;
import java.util.PriorityQueue;

public class FordFulkerson {

    private boolean[] marked;
    private GraphEdge[] edgeTo;

    // Value of max-flow.
    private int value;

    FordFulkerson(Graph g, int s, int t) {

        if (s == t) {
            throw new IllegalArgumentException("Source node equals sink node.");
        }


        double bottleneck;
        while (hasAugmentingPath(g, s, t)) {

            // Compute the bottleneck.
            bottleneck = Double.POSITIVE_INFINITY;
            for (int v = t.id; v != s.id; v = edgeTo[v] )

        }

    }

    private boolean hasAugmentingPath(Graph G, int s, int t) {

        edgeTo = new GraphEdge[G.vertices.size()];
        marked = new boolean[G.vertices.size()];

        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        marked[s] = true;
        int v;
        int w;
        while (!q.isEmpty() && !marked[t]) {
            v = q.dequeue();

            for (GraphEdge e : v.edges) {
                w = e.other(v);

                // If there is a residual capacity from v to w
                if (e.residualCapacityTo(w) > 0) {
                    if (!marked[w.id]) {
                        edgeTo[w.id] = e;
                        marked[w.id] = true;
                        q.enqueue(w);
                    }
                }
            }
        }

        return marked[t.id];

    }

    public static void main(String[] args) {

        // Construct the given graph
        Graph G = new Graph(8);
        GraphVertex vS, v2, v3, v4, v5, v6, v7, vT;
        vS = new GraphVertex(0); G.addVertex(vS);
        v2 = new GraphVertex(1); G.addVertex(v2);
        v3 = new GraphVertex(2); G.addVertex(v3);
        v4 = new GraphVertex(3); G.addVertex(v4);
        v5 = new GraphVertex(4); G.addVertex(v5);
        v6 = new GraphVertex(5); G.addVertex(v6);
        v7 = new GraphVertex(6); G.addVertex(v7);
        vT = new GraphVertex(7); G.addVertex(vT);

        // Define edges; f(e) is set to 0 via 'addEdge()'.
        G.addEdge(vS, v2, 10); G.addEdge(vS, v3, 5); G.addEdge(vS, v4, 15);
        G.addEdge(v2, v3, 4); G.addEdge(v2, v5, 9); G.addEdge(v2, v6, 15);
        G.addEdge(v3, v4, 4); G.addEdge(v3, v6, 8);
        G.addEdge(v4, v7, 30);
        G.addEdge(v5, v6, 15); G.addEdge(v5, vT, 10);
        G.addEdge(v6, v7, 15); G.addEdge(v6, vT, 10);
        G.addEdge(v7, v3, 6); G.addEdge(v7, vT, 10);

    }

}
