import java.util.*;

abstract class Search<Vertex> {
    protected Set<Vertex> marked;
    protected Map<Vertex, Vertex> edgeTo;
    protected final Vertex source;

    public Search(Vertex source) {
        this.source = source;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(Vertex vertex) {
        return marked.contains(vertex);
    }

    public Iterable<Vertex> pathTo(Vertex vertex) {
        if (!hasPathTo(vertex)) return null;

        LinkedList<Vertex> path = new LinkedList<>();
        for (Vertex i = vertex; i != source; i = edgeTo.get(i)) {
            path.push(i);
        }
        path.push(source);

        return path;
    }
}