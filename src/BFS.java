import java.util.LinkedList;
import java.util.Queue;

public class BFS<Vertex> extends Search<Vertex>{
    public BFS(MyGraph<Vertex> graph, Vertex source) {
        super(source);

        bfs(graph, source);
    }

    private void bfs(MyGraph<Vertex> graph, Vertex curr) {
        marked.add(curr);
        Queue<Vertex> queu = new LinkedList<>();
        queu.add(curr);


        while (!queu.isEmpty()) {
            Vertex v = queu.remove();
            for (Vertex vertex : graph.adjacencyList(v)) {
                if (!marked.contains(vertex)) {
                    marked.add(vertex);
                    edgeTo.put(vertex, v);
                    queu.add(vertex);
                }
            }
        }
    }
}
