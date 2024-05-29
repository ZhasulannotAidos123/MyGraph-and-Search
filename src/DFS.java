import java.util.*;

public class DFS<Vertex> extends Search<Vertex> {
    public DFS(MyGraph<Vertex> graph, Vertex source) {
        super(source);
        dfs(graph, source, new HashSet<>());
    }

    private void dfs(MyGraph<Vertex> graph, Vertex curr, Set<Vertex> visited) {
        visited.add(curr);
        marked.add(curr);

        for (Vertex vertec : graph.adjacencyList(curr)) {
            if (!visited.contains(vertec)) {
                edgeTo.put(vertec, curr);
                dfs(graph, vertec, visited);
            }
        }
    }
}