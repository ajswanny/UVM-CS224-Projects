@SuppressWarnings("Duplicates")
public class CapacityScaling {

    private boolean[] marked;
    private GraphEdge[] edgeTo;
    private int delta;

    // Value of max-flow.
    private int value;

    private CapacityScaling() {
    }

    private double ScalingMaxFlow(Graph g, int s, int t) {
        // Calculate the initial value of delta
        delta = 0;
        int c = 0;
        for (GraphEdge e : g.adj(s)) {
            c += (int) e.capacity;
        }
        delta = (int) largestPowerOfTwoFor(c);

//        g.applyDeltaConstraint(delta);
        double bottleneck = Double.POSITIVE_INFINITY;
        while (delta >= 1) {

            System.out.println("Delta scaling phase: " + delta);

            while (hasAugmentingPath(g, s, t)) {

                // Compute the bottleneck.
                for (int v = t; v!= s; v = edgeTo[v].other(v)) {
                    bottleneck = Math.min(bottleneck, edgeTo[v].residualCapacityTo(v));
                }

                // Augment the flow.
                for (int v = t; v != s; v = edgeTo[v].other(v)) {
                    edgeTo[v].addResidualFlowToVertex(v, bottleneck);
                }

                value += bottleneck;
//                g.applyDeltaConstraint(delta);
            }
            System.out.println(g);
            delta = delta/2;
        }

        return value;
    }

    private boolean hasAugmentingPath(Graph G, int s, int t) {

        edgeTo = new GraphEdge[G.numOfVertices];
        marked = new boolean[G.numOfVertices];

        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        marked[s] = true;
        int v;
        int w;
        while (!q.isEmpty() && !marked[t]) {
            v = q.dequeue();

            for (GraphEdge e : G.adj(v)) {
                w = e.other(v);

                // If there is a residual capacity from v to w
                if (e.residualCapacityTo(w) > 0 && e.residualCapacityTo(w) >= delta) {
                    if (!marked[w]) {
                        edgeTo[w] = e;
                        marked[w] = true;
                        q.enqueue(w);
                    }
                }
            }
        }

        return marked[t];

    }

    private boolean vertexIsInSCut(int vertex) {
        return marked[vertex];
    }

    private double largestPowerOfTwoFor(double n) {
        double x = 2;
        while (x < n) {
            x *= 2;
        }
        return x/2;
    }

    public static void main(String[] args) {

        Graph g = new Graph(6);
        g.addEdge(0, 1, 15); g.addEdge(0, 2, 10);
        g.addEdge(1, 2, 3); g.addEdge(1, 3, 3); g.addEdge(1, 4, 9);
        g.addEdge(2, 4, 7);
        g.addEdge(3, 5, 10);
        g.addEdge(4, 3, 3); g.addEdge(4, 5, 15);

        System.out.println("Initial:");
        System.out.println(g);

        CapacityScaling capacityScaling = new CapacityScaling();

        System.out.println("Maximum flow: " + capacityScaling.ScalingMaxFlow(g, 0, 5));
        System.out.println(g);

        System.out.println("Minimum cut: ");
        for (int v = 0; v < g.numOfVertices; v++) {
            if (capacityScaling.vertexIsInSCut(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println("\n");

    }

}
