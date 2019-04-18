public class FordFulkerson {

    public static void main(String[] args) {

        // Construct the given graph
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

        vS.addEdge(v2, 10); vS.addEdge(v3, 5); vS.addEdge(v4, 15);
        v2.addEdge(v3, 4); v2.addEdge(v5, 9); v2.addEdge(v6, 15);
        v3.addEdge(v4, 4); v3.addEdge(v6, 8);
        v4.addEdge(v7, 30);
        v5.addEdge(v6, 15); v5.addEdge(vT, 10);
        v6.addEdge(v7, 15); v6.addEdge(vT, 10);
        v7.addEdge(v3, 6); v7.addEdge(vT, 10);

    }

}
