import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
                int [][] costFW = new int[G.V][G.V];
                int [][] next = new int[G.V][G.V];
                int [] parents = new int[G.V];
                boolean FW = false;
                List<Integer> path;
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
                        G.Dijkstra(srcNode,cost,parents);
                        break;
                    } else if (choice == 2){
                        G.BellmanFord(srcNode,cost,parents);
                        break;
                    } else if (choice == 3){
                        FW = true;
                        G.FloydWarshall(costFW, next);
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
                        if(!FW){
                            path = G.getPath(srcNode, disNode, cost,parents);
                        }else {
                            path = G.getFWPath(srcNode, disNode, next,costFW);
                        }
                        if(path.size() != 0){
                            for (int i = 0; i < path.size() ; i++) {
                                System.out.print(path.get(i) );
                                if(i != path.size() - 1){
                                    System.out.print("->");
                                }else {
                                    System.out.print("\n");
                                }
                            }
                        }else{
                            System.out.println("There is not a path between both nodes.");
                        }


                    } else if(choice == 2){
                        while(true) {
                            System.out.println("Enter the destination node: ");
                            disNode = scanner.nextInt();
                            if (disNode >= 0 || disNode < G.V - 1) break;
                            else System.out.println("Invalid destination node!");
                        }
                        if(!FW){
                           System.out.println(cost[disNode]);
                        }else {
                            System.out.println(costFW[srcNode][disNode]);
                        }

                    } else if(choice == 3){
                        while(true) {
                            System.out.println("1. Bellman-Ford algorithm.");
                            System.out.println("2. Floyd-Warshall algorithm.");
                            System.out.println("3. Return.");
                            System.out.println("Chose the Algorithm: ");
                            choice = scanner.nextInt();
                            if (choice == 1) {
                                System.out.println(G.BellmanFord(srcNode,cost,parents));
                                break;
                            } else if (choice == 2) {
                                System.out.println(G.FloydWarshall(costFW, next));
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






