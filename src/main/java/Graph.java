import java.util.List;

class Graph {
    static final int INF = Integer.MAX_VALUE;

    static class Edge {
        int dest, weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    // V -> number of vertices, E -> number of edges
    private int V, E;
    // Adjacency list
    private List<List<Edge>> adj;

    public Graph(String filename) {
        this.initialize(filename);
    }

    private void initialize(String filename) {

    }

    public void Dijkstra() {

    }

    public void BellmanFord() {

    }

    public boolean FloydWarshall(int[][] cost, int[][] next) {
        // Initialize the cost matrix with large value
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                cost[i][j] = INF;
                if (i == j) // Path cost from node to itself equals 0
                    cost[i][j] = 0;
            }
        }

        // For each edge, the cost is the weight of the edge
        for (int i = 0; i < V; i++) {
            List<Edge> edges = this.adj.get(i);
            for (Edge edge : edges) {
                cost[i][edge.dest] = edge.weight;
                next[i][edge.dest] = edge.dest;
            }
        }

        // Execute FW for all pairs of the graph
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (cost[i][k] + cost[k][j] < cost[i][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        boolean hasNegativeCycle = false;
        // Run FW one more time to detect negative cycles
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // If the cost can still be improved, a negative cycle is detected
                    if (cost[i][k] + cost[k][j] < cost[i][j]) {
                        cost[i][j] = -INF;
                        next[i][j] = -1;
                        hasNegativeCycle = true;
                    }
                }
            }
        }

        return hasNegativeCycle;
    }


}
