import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
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
    public int V, E;
    // Adjacency list
    private List<List<Edge>> adj;
    private ArrayList<int[]> edges = new ArrayList<>();

    public Graph(String filename) {
        String absolutePath = Paths.get("").toAbsolutePath() + File.separator + filename;
        this.initialize(absolutePath);
    }

    public int size(){
        return this.V;
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
    PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {//comparing the weights in ascending order
        public int compare(Edge a, Edge b) {
            return Integer.compare(a.weight, b.weight);
        }
    });
    public void Dijkstra(int src, int [] cost, int [] parents) {

        Arrays.fill(cost, INF);
        Arrays.fill(parents, -1);
        cost[src] = 0;


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
//        for(int i = 0 ;i <cost.length ;i++){
//            if(cost[i]<0){
//                throw new IllegalArgumentException("Dijkstra method cannot cope with negative weights.");
//            }
//        }

    }
    public  List<Integer> getPath(int src, int dst,int [] cost ,int [] parents){
        List<Integer> path = new ArrayList<>();
        if(cost[dst] != INF ){
            path.add(dst);
            int temp = parents[dst];
            while (temp != src && temp != -1){
                path.add(temp);
                temp = parents[temp];
            }
            path.add(src);
            Collections.reverse(path);
            return path;
        }
        return path;
    }
    private void initiateBellman(){
//        ArrayList<int[]> edges = new ArrayList<>();
        for(int u = 0; u < V; u++) {
            // u --> src, v --> dest
            for (Edge e : adj.get(u)) {
                int v = e.dest;
                int weight = e.weight;
                edges.add(new int[]{u, v, weight});
            }
        }

    }
    public boolean BellmanFord(int src, int [] cost, int [] parents) {
        Arrays.fill(cost, INF);
        Arrays.fill(parents, -1);
        cost[src] = 0;
        // create array of edges
        initiateBellman();

        for (int i = 0; i < V-1; i++) {
            for(int j = 0; j < edges.size(); j++){
                // u --> src, v --> dest
                int u = edges.get(j)[0];
                int v = edges.get(j)[1];
                int weight = edges.get(j)[2];
//                System.out.println("u = " + u + ", v = "+v+", weight = "+ weight);
                if(cost[u] != INF && cost[v] > cost[u] + weight) {
                    cost[v] = cost[u] + weight;
                    parents[v] = u;
                }
            }
        }

        boolean hasNegativeCycle = false;
        // run Bellman Ford once more to detect negative cycles
        for(int i = 0; i < edges.size(); i++) {
            int u = edges.get(i)[0];
            int v = edges.get(i)[1];
            int weight = edges.get(i)[2];
            // if cost changes, then there is a negative weight cycle
            if (cost[u] != INF && cost[v] > cost[u] + weight) {
                cost[v] = cost[u] + weight;
                hasNegativeCycle = true;
            }
        }
        return !hasNegativeCycle;
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

        boolean hasNegativeCycle = false;
        // Execute FW for all pairs of the graph
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (cost[i][k] != INF && cost[k][j] != INF && cost[i][k] + cost[k][j] < cost[i][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                        next[i][j] = next[i][k];
                    }
                }
                if(cost[k][k] < 0)
                    hasNegativeCycle = true;
            }
        }

        /*boolean hasNegativeCycle = false;
        // Run FW one more time to detect negative cycles
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // If the cost can still be improved, a negative cycle is detected
                    // or a node has negative cost to itself
                    if(cost[i][k] != INF && cost[k][j] != INF && cost[k][k] < 0){
                        cost[i][j] = -INF;
                        next[i][j] = -1;
                        hasNegativeCycle = true;
                    }
                }
            }
        }*/
        return !hasNegativeCycle;
    }

    public List<Integer> getFWPath(int src, int dest, int[][] next, int[][] cost){
        List<Integer> path = new ArrayList<>();
        // There is no path between the 2 nodes
        if(cost[src][dest] == INF)
            return path;
        int cur = src;
        for(; cur != dest; cur = next[cur][dest]){
            // Path passes through a negative cycle
            if(cur == -1) return null;
            path.add(cur);
        }
        // If the destination node has negative cycle to itself
        if(next[cur][dest] == -1) return null;
        path.add(cur);
        return path;
    }


    public static void main(String[] args) {
        int numV = 500;
//        Graph G = new Graph("testcases\\Dijkstra\\dijkstrainput.txt");

//        Graph G = new Graph("testcases\\compare20.txt");
//        Graph G = new Graph("testcases\\compare40.txt");
//        Graph G = new Graph("testcases\\compare100.txt");
//        Graph G = new Graph("testcases\\compare500dense.txt");
        Graph G = new Graph("testcases\\compare500sparse.txt");

        int [] p = new int[G.V];
        int [] cost = new int[G.V];
        long sumD = 0;
        for(int i = 0 ; i < 6; i++){
            for(int j = 0; j < numV;j++){
                long startD = System.nanoTime();
                G.Dijkstra(j,cost,p);
                long endD= System.nanoTime();
                long elapsedTime = endD - startD;
                sumD += elapsedTime;
            }
        }
        long averageD = sumD / 6;
        System.out.println("average D= " + averageD + " ns");

//        G.initiateBellman();
        long sumB = 0;
        for(int i = 0 ; i < 6; i++){
            for(int j = 0; j< numV; j++){
                long startB = System.nanoTime();
                G.BellmanFord(0,cost,p);
                long endB= System.nanoTime();
                long elapsedTime = endB - startB;
                sumB += elapsedTime;
            }
//            System.out.println("elapsed = "+elapsedTime);
//            System.out.println("sumB = "+sumB);
        }
        long averageB = sumB / 6;
        System.out.println("average B= " + averageB + " ns");

//        int [][] costF = new int[G.V][G.V];
//        int nextF[][] = new int[G.V][G.V];
//        long sumF = 0;
//        for(int i = 0;i <6 ; i++){
//            long startTime = System.nanoTime();
//            G.FloydWarshall(costF,nextF);
//            long endTime = System.nanoTime();
//            long elapsedTime = endTime - startTime;
//            sumF += elapsedTime;
//        }
//        long averageF = sumF / 6;
//        System.out.println("average F= " + averageF + " ns");
    }


}
