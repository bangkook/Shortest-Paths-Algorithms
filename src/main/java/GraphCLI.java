import java.io.File;
import java.util.Scanner;
public class GraphCLI {
    public static void main(String[] args) {

        int srcNode = 0, disNode = 0;
        String filePath;

        while(true){
            System.out.println("Main Menu:");
            System.out.println("1. Finds the shortest paths from source node to all other nodes.");
            System.out.println("2. Finds the shortest paths between all the pairs of nodes.");
            System.out.println("3. Exit.");

            System.out.println("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1 || choice ==2 ){
                System.out.println("Enter the input file path:");
                filePath = scanner.nextLine();
                File file = new File(filePath);
                Graph G = new Graph(filePath);
                int [] cost = new int[G.V];
                int [] p = new int[G.V];
                if (!file.exists()) {
                    while(true){
                        System.out.println("File not found!");
                        System.out.println("Enter the input file path:");
                        filePath = scanner.nextLine();
                        file = new File(filePath);
                        if(file.exists()) break;
                    }
                }

                if(choice == 1){

                    while(true) {
                        System.out.println("Enter the source node: ");
                        srcNode = scanner.nextInt();
                        if (srcNode >= 0 || srcNode < G.V - 1) break;
                        else System.out.println("Invalid source node!");
                    }

                }

                while(true) {
                    System.out.println("1. Dijkstra Algorithm.");
                    System.out.println("2. Bellman-Ford Algorithm.");
                    System.out.println("3. Floyd-Warshall Algorithm.");
                    System.out.println("chose Algorithm: ");
                    choice = scanner.nextInt();

                    if(choice == 1){
                        G.Dijkstra(srcNode,cost,p);
                        break;
                    } else if (choice == 2){
                        //
                        break;
                    } else if (choice == 3){
                        //
                        break;
                    } else {
                        System.out.println("Invalid!");
                    }
                }
                while(true){
                    System.out.println("1. The shortest path to specific node.");
                    System.out.println("2. The cost of the path to a specific node.");
                    System.out.println("3. Check if the graph contains a negative cycle.");
                    System.out.println("4. Return to Main Menu.");
                    System.out.println("Enter: ");
                    choice = scanner.nextInt();
                    if(choice == 1){

                        while(true) {
                            System.out.println("Enter the destination node: ");
                             disNode = scanner.nextInt();
                            if (disNode >= 0 || disNode < G.V - 1) break;
                            else System.out.println("Invalid destination node!");
                        }
                        //waiting for function

                    } else if(choice == 2){
                        while(true) {
                            System.out.println("Enter the destination node: ");
                            disNode = scanner.nextInt();
                            if (disNode >= 0 || disNode < G.V - 1) break;
                            else System.out.println("Invalid destination node!");
                        }
                        System.out.println(cost[disNode]);

                    } else if(choice == 3){
                        //
                        while(true) {
                            System.out.println("1. Bellman-Ford algorithm.");
                            System.out.println("2. Floyd-Warshall algorithm.");
                            System.out.println("3. Return.");
                            System.out.println("Chose the Algorithm: ");
                            choice = scanner.nextInt();
                            if (choice == 1) {
                                System.out.println(G.BellmanFord(srcNode,cost,p));
                                break;
                            } else if (choice == 2) {
                                //G.FloydWarshall();
                                break;
                            } else if(choice == 3){
                                break;
                            } else{
                                System.out.println("Invalid!");
                            }
                        }

                    } else if(choice == 4){
                        break;
                        //do nothing
                    } else {
                        System.out.println("Invalid!");
                    }

                }
                if(choice != 4) break;
            }

            else if (choice == 3)
                System.exit(0);
              else {
                System.out.println("Invalid!");
            }
        }

    }

}






