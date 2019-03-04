package dijkstra;

import java.util.Arrays;

public class Dijkstra {


    public static void main(String[] args) {


        // Construct a graph.
        Graph graph = new Graph(8);
        TraceableGraphVertex vS = new TraceableGraphVertex("s");
        TraceableGraphVertex v2 = new TraceableGraphVertex("2");
        TraceableGraphVertex v3 = new TraceableGraphVertex("3");
        TraceableGraphVertex v4 = new TraceableGraphVertex("4");
        TraceableGraphVertex v5 = new TraceableGraphVertex("5");
        TraceableGraphVertex v6 = new TraceableGraphVertex("6");
        TraceableGraphVertex v7 = new TraceableGraphVertex("7");
        TraceableGraphVertex vT = new TraceableGraphVertex("t");
        vS.addNeighbors(Arrays.asList(v2, v6, v7));
        v2.addNeighbor(v3);
        v3.addNeighbors(Arrays.asList(v5, vT));
        v4.addNeighbors(Arrays.asList(v3, vT));
        v5.addNeighbors(Arrays.asList(v4, vT));
        v6.addNeighbors(Arrays.asList(v3, v5, v7));
        v7.addNeighbors(Arrays.asList(v5, vT));



        graph.print();


    }


}

