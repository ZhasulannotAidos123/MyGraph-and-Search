import java.util.*;
public class WeightedGraph<Vertex> {
    private boolean undirected;
    private Map<Vertex, List<AdjacentVertex<Vertex>>> adjacencyMap;

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
        this.adjacencyMap = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        if (!adjacencyMap.containsKey(vertex)) {
            adjacencyMap.put(vertex, new LinkedList<>());
        }
    }

    public void addEdge(Vertex source, Vertex destination, double weight) {
        if (!adjacencyMap.containsKey(source)) {
            addVertex(source);
        }
        if (!adjacencyMap.containsKey(destination)) {
            addVertex(destination);
        }
        if (source.equals(destination) || hasEdge(source, destination)) {
            return;
        }
        adjacencyMap.get(source).add(new AdjacentVertex<>(destination, weight));
        if (undirected) {
            adjacencyMap.get(destination).add(new AdjacentVertex<>(source, weight));
        }
    }

    public int getVertexCount() {
        return adjacencyMap.size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (Vertex vertex : adjacencyMap.keySet()) {
            count += adjacencyMap.get(vertex).size();
        }
        if (undirected) {
            count /= 2;
        }
        return count;
    }

    public boolean hasVertex(Vertex vertex) {
        return adjacencyMap.containsKey(vertex);
    }

    public boolean hasEdge(Vertex source, Vertex destination) {
        if (!hasVertex(source)) {
            return false;
        }
        for (AdjacentVertex<Vertex> adj : adjacencyMap.get(source)) {
            if (adj.getVertex().equals(destination)) {
                return true;
            }
        }
        return false;
    }

    public List<Vertex> getAdjacentVertices(Vertex vertex) {
        if (!hasVertex(vertex)) {
            return Collections.emptyList();
        }
        List<Vertex> adjacentVertices = new LinkedList<>();
        for (AdjacentVertex<Vertex> adj : adjacencyMap.get(vertex)) {
            adjacentVertices.add(adj.getVertex());
        }
        return adjacentVertices;
    }

    public Iterable<AdjacentVertex<Vertex>> getEdges(Vertex vertex) {
        if (!hasVertex(vertex)) {
            return Collections.emptyList();
        }
        return new LinkedList<>(adjacencyMap.get(vertex));
    }

    public static class AdjacentVertex<Vertex> {
        private final Vertex vertex;
        private final double weight;

        public AdjacentVertex(Vertex vertex, double weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public Vertex getVertex() {
            return vertex;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            AdjacentVertex<?> that = (AdjacentVertex<?>) obj;
            return Double.compare(that.weight, weight) == 0 && Objects.equals(vertex, that.vertex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex, weight);
        }
    }
}