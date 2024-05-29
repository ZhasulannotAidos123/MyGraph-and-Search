import java.util.*;

public class DijkstraSearch<Vertex> extends Search<Vertex> {
    private final Set<Vertex> unsettledNodes;
    private final Map<Vertex, Double> distances;
    private final WeightedGraph<Vertex> graph;

    public DijkstraSearch(WeightedGraph<Vertex> graph, Vertex source) {
        super(source);
        this.graph = graph;
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Vertex currentNode = getVertexWithMinimumWeight(unsettledNodes);

            marked.add(currentNode);
            unsettledNodes.remove(currentNode);

            for (WeightedGraph.AdjacentVertex<Vertex> neighbor : graph.getEdges(currentNode)) {
                double newDistance = getShortestDistance(currentNode) + neighbor.getWeight();

                if (getShortestDistance(neighbor.getVertex()) > newDistance) {
                    distances.put(neighbor.getVertex(), newDistance);
                    edgeTo.put(neighbor.getVertex(), currentNode); // inverted adding
                    unsettledNodes.add(neighbor.getVertex());
                }
            }
        }
    }

    private Vertex getVertexWithMinimumWeight(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null || getShortestDistance(vertex) < getShortestDistance(minimum)) {
                minimum = vertex;
            }
        }
        return minimum;
    }

    private double getShortestDistance(Vertex direction) {
        return distances.getOrDefault(direction, Double.MAX_VALUE);
    }
}

