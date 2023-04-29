import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FloydWarshallTest {
    static final int INF = Integer.MAX_VALUE;

    @Test
    // Test small graph with positive edges
    public void testAllPositive(){
        Graph G = new Graph("testcases\\FW\\5.txt");
        int n = G.size();
        int[][] cost = new int[n][n];
        int[][] next = new int[n][n];
        // Assert there is negative cycles
        Assert.assertTrue(G.FloydWarshall(cost, next));
        int [][] correctCost = {{0, 1, 2, 3},
                {INF, 0, 1, 2},
                {INF, 2, 0, 1},
                {INF, 1, 2, 0}};
        Assert.assertArrayEquals(cost, correctCost);
        // All diagonal elements are positive
        for(int i = 0; i < n; i++)
          Assert.assertTrue(cost[i][i] >= 0);
    }

    @Test
    // Test Dense graph with all weights positive
    public void testAllPositiveDense(){
        Graph G = new Graph("testcases\\FW\\1.txt");
        int n = G.size();
        int[][] cost = new int[n][n];
        int[][] next = new int[n][n];
        // Assert there is no negative cycles
        Assert.assertTrue(G.FloydWarshall(cost, next));
        // correct cost from 0 to all other vertices
        int [] correctCost0 = {0, 5, 7, 7, 7, 6, 3, 4, 5};
        Assert.assertArrayEquals(cost[0], correctCost0);
        // Correct path from 0 to 8
        Assert.assertEquals(G.getFWPath(0, 8, next, cost), Arrays.asList(0, 6, 7, 8));
        Assert.assertEquals(G.getFWPath(6, 8, next, cost), Arrays.asList(6, 7, 8));
        Assert.assertEquals(Arrays.toString(cost[3]), "[7, 6, 4, 0, 5, 4, 4, 3, 2]");
        Assert.assertEquals(G.getFWPath(3, 6, next, cost), Arrays.asList(3, 8, 7, 6));
    }

    @Test
    public void testUnreachable(){
        Graph G = new Graph("testcases\\FW\\2.txt");
        int n = G.size();
        int[][] cost = new int[n][n];
        int[][] next = new int[n][n];
        // Assert there is no negative cycles
        Assert.assertTrue(G.FloydWarshall(cost, next));
        // correct cost from 0 to all other vertices
        int [] correctCost = {0, 2, 4, 1, INF};
        Assert.assertArrayEquals(cost[0], correctCost);
        // Infinite cost from 0 to unreachable node 4
        Assert.assertEquals(cost[0][4], INF);
        // no path from 0 to unreachable node 4
        Assert.assertEquals(G.getFWPath(0, 4, next, cost), Collections.emptyList());
    }

    @Test
    // Test graph with negative weights but no negative cycles
    public void testNegativeWeight(){
        Graph G = new Graph("testcases\\FW\\4.txt");
        int n = G.size();
        int[][] cost = new int[n][n];
        int[][] next = new int[n][n];
        // Assert there is no negative cycles
        Assert.assertTrue(G.FloydWarshall(cost, next));
        int[][] correctCost =
                {{0, -1, -2, 0},
                {4, 0, 2, 4},
                {5, 1, 0, 2},
                {3, -1, 1, 0}};
        Assert.assertArrayEquals(cost, correctCost);
        Assert.assertEquals(G.getFWPath(0, 1, next, cost), Arrays.asList(0, 2, 3, 1));
        Assert.assertEquals(G.getFWPath(1, 2, next, cost), Arrays.asList(1, 0, 2));
        Assert.assertEquals(G.getFWPath(2, 0, next, cost), Arrays.asList(2, 3, 1, 0));
        Assert.assertEquals(G.getFWPath(3, 2, next, cost), Arrays.asList(3, 1, 0, 2));
    }

    @Test
    // Test graph with negative cycles
    public void testNegativeCycles(){
        Graph G = new Graph("testcases\\FW\\3_negative.txt");
        int n = G.size();
        int[][] cost = new int[n][n];
        int[][] next = new int[n][n];
        // Assert there is negative cycles
        Assert.assertFalse(G.FloydWarshall(cost, next));
        // All diagonal elements are negative
        for(int i = 0; i < n; i++)
            Assert.assertTrue(cost[i][i] < 0);
    }


}
