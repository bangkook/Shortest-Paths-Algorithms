import org.junit.Assert;

import static org.junit.Assert.*;

public class BellmanFordTest {

    @org.junit.Test
    public void bellmanFordInput() {
        Graph G = new Graph("testcases\\Bellman\\input.txt");
//        Graph g = new Graph( );
        int [] cost = new int[7];
        int [] p = new int[7];
        boolean hasNoNegativeCycle = G.BellmanFord(0,cost,p);
        int [] correctCost ={0,2,7,3,9,5,2};
        int []correctP= {-1,0,0,6,2,3,4};
        // Assert there is no negative cycles
        assertTrue(hasNoNegativeCycle);
        Assert.assertArrayEquals(cost,correctCost);
        Assert.assertArrayEquals(p,correctP);
    }

    @org.junit.Test
    public void negativeCycle() {
        Graph G = new Graph("testcases\\Bellman\\cycle.txt");
        int [] cost = new int[4];
        int [] p = new int[4];
        boolean hasNoNegativeCycle = G.BellmanFord(0,cost,p);
        Assert.assertFalse(hasNoNegativeCycle);
    }
    @org.junit.Test
    public void test1() {
        Graph G = new Graph("testcases\\Bellman\\test1.txt");
        int [] cost = new int[9];
        int [] p = new int[9];
        boolean hasNoNegativeCycle = G.BellmanFord(0,cost,p);
        int [] correctCost ={0,5,7,7,7,6,3,4,5};
        int []correctP= { -1, 0, 1, 8, 5, 7, 0, 6, 7};
        assertTrue(hasNoNegativeCycle);
        Assert.assertArrayEquals(cost,correctCost);
        Assert.assertArrayEquals(p,correctP);
    }

    @org.junit.Test
    public void killer() {
        Graph G = new Graph("testcases\\Bellman\\bellmanKiller.txt");
        int [] cost = new int[7];
        int [] p = new int[7];
        boolean hasNoNegativeCycle = G.BellmanFord(0,cost,p);
        int [] correctCost ={0,6,11,15,18,20,21};
        int []correctP= {-1, 0, 1,2,3,4,5};
        assertTrue(hasNoNegativeCycle);
        Assert.assertArrayEquals(cost,correctCost);
        Assert.assertArrayEquals(p,correctP);
    }
    @org.junit.Test
    public void unReachable() {
        Graph G = new Graph("testcases\\Bellman\\unReachable.txt");
        int [] cost = new int[7];
        int [] p = new int[7];
        boolean hasNoNegativeCycle = G.BellmanFord(0,cost,p);
        int [] correctCost ={0,2,3,2147483647,2147483647,2147483647,2147483647};
        int []correctP= {-1,0,1,-1,-1,-1,-1};
        assertTrue(hasNoNegativeCycle);
        Assert.assertArrayEquals(cost,correctCost);
        Assert.assertArrayEquals(p,correctP);
    }

    @org.junit.Test
    public void positiveCycle() {
        Graph G = new Graph("testcases\\Bellman\\positiveCycle.txt");
        int [] cost = new int[4];
        int [] p = new int[4];
        boolean hasNoNegativeCycle = G.BellmanFord(0,cost,p);
        int [] correctCost ={0,1,2,3};
        int []correctP= {-1,0,1,2};
        assertTrue(hasNoNegativeCycle);
        Assert.assertArrayEquals(cost,correctCost);
        Assert.assertArrayEquals(p,correctP);
    }

}
