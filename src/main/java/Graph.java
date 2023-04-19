import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        try {
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            boolean VE = true;
            while ((line = br.readLine()) != null) {
                String[] input = line.split(" ");
                if(VE){
                    V = Integer.parseInt(input[0]);
                    E = Integer.parseInt(input[1]);
                    adj = new ArrayList<>(V);
                    for (int i = 0; i < V; i++) { //add list for each vertex
                        adj.add(new ArrayList<>());
                    }
                    VE = false;
                }else{
                    int from = Integer.parseInt(input[0]);
                    int to = Integer.parseInt(input[1]);
                    int weight = Integer.parseInt(input[2]) ;
                    adj.get(from).add(new Edge(to, weight));
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void printG(){
        for (int i = 0; i < V; i++) {
            for (Edge edge : adj.get(i)) {
                System.out.print("from: " + i +" to: ");
                System.out.print(edge.dest +" weight: " +"(" + edge.weight + ") ");
            }
            System.out.println();
        }

    }
    public void Dijkstra(int src, int [] cost, int [] parents) {
            Arrays.fill(cost, INF);
            cost[src] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {//comparing the weights in ascending order
            public int compare(Edge a, Edge b) {
                return Integer.compare(a.weight, b.weight);
            }
        });
            pq.offer(new Edge(src, 0));

            while (!pq.isEmpty()) {
                 Edge u = pq.poll();
                for (Edge e : adj.get(u.dest)) {
                    int v = e.dest;
                    int alt = cost[u.dest] + e.weight;
                    if (alt < cost[v]) {
                        cost[v] = alt;
                        parents[v] = u.dest;
                        pq.offer(new Edge(v, cost[v]));
                    }
                }
            }

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

    public static void main(String[] args) {
        Graph G = new Graph("D:\\CSE25\\Second year\\Second term\\Data structure 2\\Projects\\Shortest path\\Shortest-Paths-Algorithms\\input.txt");
        G.printG();
        int [] cost = new int[G.V];
        int [] p = new int[G.V];
        G.Dijkstra(0,cost,p);
        for (int i = 0; i < G.V; i++) {
            System.out.println("node "+ i +" with coast " + cost[i] + " whose parent is "+p[i]+" ");
        }

    }


}
