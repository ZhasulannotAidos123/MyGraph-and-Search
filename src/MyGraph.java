import java.util.*;

public class MyGraph<Vertex> {
    private final boolean undirected;
    private final Map<Vertex, List<Vertex>> map = new HashMap<>();

    public MyGraph() {
        this(true);
    }

    public MyGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public  void addVertex(Vertex v) {
        if (v == null) {
            throw new IllegalArgumentException("Vertex cannot be null");
        }

        if (!map.containsKey(v)) {
            map.put(v, new LinkedList<>());
        }
    }

    public synchronized void addEdge(Vertex vertex1, Vertex vertex2) {
        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("Vertices cannot be null");
        }

        if (!map.containsKey(vertex1)) {
            addVertex(vertex1);
        }

        if (!map.containsKey(vertex2)) {
            addVertex(vertex2);
        }

        if (vertex1.equals(vertex2) || map.get(vertex1).contains(vertex2)) {
            return;
        }

        map.get(vertex1).add(vertex2);

        if (undirected) {
            map.get(vertex2).add(vertex1);
        }
    }

    public  int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex v : map.keySet()) {
            count += map.get(v).size();
        }

        if (undirected) {
            count /= 2;
        }

        return count;
    }

    public boolean hasVertex(Vertex v) {
        return map.containsKey(v);
    }

    public  boolean hasEdge(Vertex vertex1, Vertex vertex2) {
        if (!map.containsKey(vertex1)) return false;
        return map.get(vertex1).contains(vertex2);
    }

    public List<Vertex> adjacencyList(Vertex v) {
        if (!map.containsKey(v)) return Collections.emptyList();
        return new ArrayList<>(map.get(v));
    }
}