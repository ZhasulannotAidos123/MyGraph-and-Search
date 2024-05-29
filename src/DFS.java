import java.util.*;

public class DFS<Vertex> extends Search<Vertex> {
    public DFS(MyGraph<Vertex> graph, Vertex source) {
        super(source);
        dfs(graph, source, new HashSet<>());
    }

    private void dfs(MyGraph<Vertex> graph, Vertex current, Set<Vertex> visited) {
        visited.add(current);
        marked.add(current);

        for (Vertex v : graph.adjacencyList(current)) {
            if (!visited.contains(v)) {
                edgeTo.put(v, current);
                dfs(graph, v, visited);
            }
        }
    }
}