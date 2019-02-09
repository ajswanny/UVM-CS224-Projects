/*
Property of Alexander Joseph Swanson Villares
alexanderjswanson@icloud.com | https://github.com/ajswanny
*/


/* Imports */
import java.util.*;


/**
 * An implementation of an AdjacencyList that allows for basic customization of its values and a Breadth-First
 * searching algorithm.
 */
public class AdjacencyList {

    /* Class Fields */
    /**
     * The AdjacencyList data structure.
     */
    private LinkedList<Integer>[] adjList;

    /**
     * The default constructor.
     * @param numOfVertices The number of vertices to the graph this AdjacencyList represents.
     */
    private AdjacencyList(int numOfVertices) {

        // Create object adjList
        adjList = new LinkedList[numOfVertices];

        // New LinkedList for every vertex
        for (int i = 0; i < numOfVertices; i++) {

            // Initialize a LinkedList for every vertex.
            adjList[i] = new LinkedList<>();

        }

    }

    /**
     * Adds a single graph edge.
     * @param u The vertex.
     * @param v The neighbor.
     */
    private void addEdge(int u, int v) {

        adjList[u-1].add(v);

    }

    /**
     * Adds multiple neighbors to a single vertex.
     * @param u The vertex.
     * @param v The array of neighbors.
     */
    private void addEdges(int u, int[] v) {

        for (int i : v) { addEdge(u, i); }

    }

    /**
     * Performs a Breadth-First search for the graph starting at the provided vertex.
     * @param startingVertex The starting vertex for the search.
     * @param verbose Whether or not the method should output statuses.
     */
    private void breadthFirstSearch(int startingVertex, boolean verbose) {

        // Initialize a Queue to contain all nodes to be traversed.
        Queue<Integer> nodesQueue = new ArrayDeque<>();

        // Initialize tracker for discovered nodes.
        HashSet<Integer> discovered = new HashSet<>(8);

        // Output status.
        if (verbose) { System.out.print("  Breadth-First search: "); }

        // Initialize head of queue.
        nodesQueue.add(startingVertex);

        // Perform a BFS.
        while (!nodesQueue.isEmpty()) {

            // Retrieve the head of the queue.
            int v = nodesQueue.poll();

            // If the node has not been visited, do so.
            if (!discovered.contains(v)) {

                // Output status.
                if (verbose) { System.out.print(v + " "); }

                // Add neighbors of v to queue for searching
                nodesQueue.addAll(adjList[v-1]);

                // Flag "v" as discovered
                discovered.add(v);

            }

        }

        // Output status.
        if (verbose) { System.out.println(); }

    }


    /* Use Example */
    public static void main(String[] args) {

        String errorMessage = "Please enter an integer from 1 to 8.";
        int response = -1;

        // Initialize an AdjacencyList.
        AdjacencyList adjacencyList = new AdjacencyList(8);

        // Define the graph "graph1".
        adjacencyList.addEdges(1, new int[]{2, 3});
        adjacencyList.addEdges(2, new int[]{1, 3, 4, 5});
        adjacencyList.addEdges(3, new int[]{1, 2, 5, 7, 8});
        adjacencyList.addEdges(4, new int[]{2, 5});
        adjacencyList.addEdges(5, new int[]{2, 3, 4, 6});
        adjacencyList.addEdge(6, 5);
        adjacencyList.addEdges(7, new int[]{3, 8});
        adjacencyList.addEdges(8, new int[]{3, 7});

        while (true) {

            // Initialize input scanner.
            Scanner input = new Scanner(System.in);

            // Output status.
            System.out.print("BFS Starting vertex (0 to quit): ");

            try {

                // Attempt to parse integer input.
                response = input.nextInt();

            } catch (InputMismatchException e) {

                // Output status.
                System.out.println(errorMessage);
                continue;

            }

            if (response == 0) {

                break;

            } else {

                // Run BFS using the provided starting vertex value if it is within the legal values.
                if (0 < response && response < 9) {
                    adjacencyList.breadthFirstSearch(response, true);
                } else {
                    // Output status.
                    System.out.println(errorMessage);
                }

            }

        }

    }


}
