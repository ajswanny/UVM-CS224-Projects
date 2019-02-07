package Practice1;

import java.util.*;

/**
 * @author 200_success (https://codereview.stackexchange.com/users/9357/200-success)
 */
public class Graph {

    private Map<String, List<String>> edges = new HashMap<String, List<String>>();

    public void addEdge(String src, String dest) {

        List<String> srcNeighbors = this.edges.computeIfAbsent(src, k -> new ArrayList<>());

        srcNeighbors.add(dest);

    }

    public Iterable<String> getNeighbors(String vertex) {

        List<String> neighbors = this.edges.get(vertex);

        if (neighbors == null) {

            return Collections.emptyList();

        } else {

            return Collections.unmodifiableList(neighbors);

        }

    }

}
