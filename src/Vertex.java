import java.util.*;

    public class Vertex<T> {
        private T data;
        private Map<Vertex<T>, Double> adjacentVertices;

        public Vertex(T data) {
            this.data = data;
            this.adjacentVertices = new HashMap<>();
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Map<Vertex<T>, Double> getAdjacentVertices() {
            return adjacentVertices;
        }

        public void addAdjacentVertex(Vertex<T> vertex, double weight) {
            adjacentVertices.put(vertex, weight);
        }

        public void removeAdjacentVertex(Vertex<T> vertex) {
            adjacentVertices.remove(vertex);
        }


    }