package com.mp1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mp1.search.AStarSearch;
import com.mp1.search.AStarWithGhostSearch;
import com.mp1.search.base.Search;
import com.mp1.solution.MazeSolution;

public class Part3ResultsTest {

	private void runMaze(Search algo, String filename, int pathCost, int numExpanded) {
		System.out.println(algo.getClass().getSimpleName() + " " + filename);
		MazeSolution solution = algo.solve();
		System.out.println(solution);
		assertEquals(solution.pathCost, pathCost);
		assertEquals(solution.numNodesExpanded, numExpanded);
	}

    /* part 3-1 */
	@Test
	public void small() {
		this.runMaze(new AStarSearch("test-files/smallGhost.txt"), "smallGhost.txt", 19, 54);
		this.runMaze(new AStarWithGhostSearch("test-files/smallGhost.txt"), "smallGhost.txt", 21, 122);
	}
	
	@Test
	public void medium() {
		this.runMaze(new AStarSearch("test-files/mediumGhost.txt"), "mediumGhost.txt", 24, 27);
		this.runMaze(new AStarWithGhostSearch("test-files/mediumGhost.txt"), "mediumGhost.txt", 26, 30);
	}
	
	@Test
	public void large() {
		this.runMaze(new AStarSearch("test-files/bigGhost.txt"), "bigGhost.txt", 68, 110);
		this.runMaze(new AStarWithGhostSearch("test-files/bigGhost.txt"), "bigGhost.txt", 70, 131);
	}

    @Test
    public void shortGhostMove() {
        this.runMaze(new AStarSearch("test-files/shortGhost.txt"), "shortGhost.txt", 32, 58);
        this.runMaze(new AStarWithGhostSearch("test-files/shortGhost.txt"), "shortGhost.txt", 32, 59);
    }

    @Test
    public void longGhostMove() {
        this.runMaze(new AStarSearch("test-files/longGhost.txt"), "longGhost.txt", 76, 469);
        this.runMaze(new AStarWithGhostSearch("test-files/longGhost.txt"), "longGhost.txt", 76, 1304);
    }

    /* part 3-2: advanced ghosts added */
    @Test
    public void smallGhostWithFastGhost() {
//        this.runMaze(new AStarSearch("test-files/smallGhost2.txt"), "smallGhost2.txt", 19, 50);
        this.runMaze(new AStarWithGhostSearch("test-files/smallGhost2.txt"), "smallGhost2.txt", 23, 105); // has to stall 2x to get through fast ghost
    }

    @Test
    public void mediumGhostWithFastGhost() {
        this.runMaze(new AStarSearch("test-files/mediumGhost2.txt"), "mediumGhost2.txt", 24, 27);
        this.runMaze(new AStarWithGhostSearch("test-files/mediumGhost2.txt"), "mediumGhost2.txt", 26, 31);
    }

    @Test
    public void bigGhostWithFastGhost() {
        this.runMaze(new AStarSearch("test-files/bigGhost2.txt"), "bigGhost2.txt", 68, 110);
        this.runMaze(new AStarWithGhostSearch("test-files/bigGhost2.txt"), "bigGhost2.txt", 70, 125);
    }
    
    @Test
    public void fastghost() {
        this.runMaze(new AStarSearch("test-files/fastghost.txt"), "fastghost.txt", 7, 8);
        this.runMaze(new AStarWithGhostSearch("test-files/fastghost.txt"), "fastghost.txt", 9, 10);
    }

    @Test
    public void verticalghost() {
        this.runMaze(new AStarSearch("test-files/verticalghost.txt"), "verticalghost.txt", 7, 8);
        this.runMaze(new AStarWithGhostSearch("test-files/verticalghost.txt"), "verticalghost.txt", 11, 15);
    }
    
    /* part 3-2-2 : even more ghosts */
    @Test
    public void ghostWorld1() {
        this.runMaze(new AStarSearch("test-files/ghostWorld1.txt"), "ghostWorld1.txt", 26, 41);
        try {
        	this.runMaze(new AStarWithGhostSearch("test-files/ghostWorld1.txt"), "ghostWorld1.txt", 32, 88);
        	// passes A no problem @ 6
        	// goes through g area @ 8-12 and ghost is 1 step ahead luckily
        	// f 14-16 PASS IN NIGHT @ 16, ghost all way on right, and no way to backtrack... NO SOLUTION
        	assertEquals(true, false);
        } catch(NullPointerException e) {
        	// no solution, so expect it!
        }
    }

    @Test
    public void ghostWorld2() {
        this.runMaze(new AStarSearch("test-files/ghostWorld2.txt"), "ghostWorld2.txt", 24, 27);
        this.runMaze(new AStarWithGhostSearch("test-files/ghostWorld2.txt"), "ghostWorld2.txt", 34, 222);
        	// passes F no problem @ 4
        	// gs at 10-12 (hits @ 10, stall 1x)
        	// 14 should go up or down? G behind
        		// As in way if up, can't go all the way through A
        		// forward can't b/c collide with Gs
        		// down - 24 G again (all way at other end), 27 A (other end)
    }

    @Test
    public void ghostWorld3() {
        this.runMaze(new AStarSearch("test-files/ghostWorld3.txt"), "ghostWorld3.txt", 50, 115);
        try {
        	this.runMaze(new AStarWithGhostSearch("test-files/ghostWorld3.txt"), "ghostWorld3.txt", 60, 281);
        	// NO SOLUTION!
        	assertEquals(true, false);
        } catch(NullPointerException e) {
        	// no solution, so ok
        }
    }

    @Test
    public void ghostWorld4() {
        this.runMaze(new AStarSearch("test-files/ghostWorld4.txt"), "ghostWorld4.txt", 48, 116);
        this.runMaze(new AStarWithGhostSearch("test-files/ghostWorld4.txt"), "ghostWorld4.txt", 62, 1069);
    }
}
