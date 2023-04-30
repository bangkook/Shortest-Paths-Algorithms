
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateGraph {
    public static void generateGraph(int numNodes, int numEdges, String filePath) throws IOException {
        Random random = new Random();
        BufferedWriter w = new BufferedWriter(new FileWriter(filePath));
        // Write number of nodes and edges
        w.write(numNodes + " " + numEdges);
        w.newLine();
        // Generate random edges with random weights
        for (int i = 0; i < numEdges; i++) {
            int u = random.nextInt(numNodes);
            int v = random.nextInt(numNodes);
            int weight = random.nextInt(100) + 1; // Random weight between 1 and 100
            w.write(u + " " + v + " " + weight);
            w.newLine();
        }
        w.close();
    }

    public static void main(String[] args) {
        int numNodes = 500;
        int numEdges = 250000;
        String filePath = "D:\\Second Year Computer\\Term 2\\Data Structures and Algorithms\\Labs\\Lab 2\\Shortest-Paths-Algorithms\\testcases\\compare500dense.txt";

        try {
            generateGraph(numNodes, numEdges, filePath);
            System.out.println("Graph written to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}