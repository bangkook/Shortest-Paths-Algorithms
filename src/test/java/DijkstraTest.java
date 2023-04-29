import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DijkstraTest {

        @Test
        public void DijkstraInput() {
            Graph G = new Graph("testcases\\Dijkstra\\dijkstrainput.txt");
            int [] cost = new int[5];
            int [] p = new int[5];
            G.Dijkstra(0,cost,p);
            int [] correctCost ={0, 8, 5, 9, 7};
            int []correctP= {-1, 2, 0, 1, 2};
            Assert.assertArrayEquals(cost,correctCost);
            Assert.assertArrayEquals(p,correctP);
        }
        @Test
        public void NegativeWeightsTest() {
            Graph G = new Graph("testcases\\Dijkstra\\input.txt");
            int[] cost = new int[7];
            int[] p = new int[7];

            try {
                G.Dijkstra(0, cost, p);
            } catch (IllegalArgumentException e) {
                assertEquals("Dijkstra method cannot cope with negative weights.", e.getMessage());
            }
        }

        @Test
        public void ParallelEdgesTest() {
            Graph G = new Graph("testcases\\Dijkstra\\parallelEdges.txt");
            int[] cost = new int[5];
            int[] p = new int[5];
            G.Dijkstra(0,cost,p);
            int [] correctCost ={0, 5, 8, 9, 10};
            int []correctP= {-1, 0, 1, 1, 2};
            Assert.assertArrayEquals(cost,correctCost);
            Assert.assertArrayEquals(p,correctP);
        }
        @Test
        public void DisconnectedGraphTest() {
            Graph G = new Graph("testcases\\Dijkstra\\disconnectedGraph.txt");
            int[] cost = new int[6];
            int[] p = new int[6];
            G.Dijkstra(0,cost,p);
            int [] correctCost ={0, 2, 5, 2147483647, 2147483647, 2147483647};
            int []correctP= {-1, 0, 1, -1, -1, -1};
            Assert.assertArrayEquals(cost,correctCost);
            Assert.assertArrayEquals(p,correctP);
        }
        @Test
        public void OnlyOneVertexTest() {
            Graph G = new Graph("testcases\\Dijkstra\\OnlyOneVertex.txt");
            int[] cost = new int[1];
            int[] p = new int[1];
            G.Dijkstra(0,cost,p);
            int [] correctCost ={0};
            int []correctP= {-1};
            Assert.assertArrayEquals(cost,correctCost);
            Assert.assertArrayEquals(p,correctP);
        }
        @Test
        public void multipleShortestPathsTest() {
            Graph G = new Graph("testcases\\Dijkstra\\multipleShortestPaths.txt");
            int[] cost = new int[6];
            int[] p = new int[6];
            G.Dijkstra(0, cost, p);
            int[] correctCost1 = {0, 1, 4, 4, 6, 5};
            int[] correctP1 = {-1, 0, 0, 1, 2, 3};
            int[] correctCost2 = {0, 1, 4, 4, 6, 5};
            int[] correctP2 = {-1, 0, 0, 1, 1, 3};

            boolean isValid = false;
            if (Arrays.equals(cost, correctCost1) && Arrays.equals(p, correctP1)) {
                isValid = true;
            } else if (Arrays.equals(cost, correctCost2) && Arrays.equals(p, correctP2)) {
                isValid = true;
            }
            assertTrue(isValid);
        }
}

