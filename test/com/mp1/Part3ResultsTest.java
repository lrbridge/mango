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
		this.runMaze(new AStarSearch("smallGhost.txt"), "smallGhost.txt", 19, 54);
		this.runMaze(new AStarWithGhostSearch("smallGhost.txt"), "smallGhost.txt", 21, 122);
	}
	
	@Test
	public void medium() {
		this.runMaze(new AStarSearch("mediumGhost.txt"), "mediumGhost.txt", 24, 27);
		this.runMaze(new AStarWithGhostSearch("mediumGhost.txt"), "mediumGhost.txt", 26, 30);
	}
	
	@Test
	public void large() {
		this.runMaze(new AStarSearch("bigGhost.txt"), "bigGhost.txt", 68, 110);
		this.runMaze(new AStarWithGhostSearch("bigGhost.txt"), "bigGhost.txt", 70, 131);
	}

    @Test
    public void shortGhostMove() {
        this.runMaze(new AStarSearch("shortGhost.txt"), "shortGhost.txt", 32, 58);
        this.runMaze(new AStarWithGhostSearch("shortGhost.txt"), "shortGhost.txt", 32, 59);
    }

    @Test
    public void longGhostMove() {
        this.runMaze(new AStarSearch("longGhost.txt"), "longGhost.txt", 76, 469);
        this.runMaze(new AStarWithGhostSearch("longGhost.txt"), "longGhost.txt", 76, 1304);
    }

    /* part 3-2: advanced ghosts added */
    @Test
    public void smallGhostWithFastGhost() {
        this.runMaze(new AStarSearch("smallGhost2.txt"), "smallGhost2.txt", 19, 50);
        this.runMaze(new AStarWithGhostSearch("smallGhost2.txt"), "smallGhost2.txt", 19, 68);
    }

    @Test
    public void mediumGhostWithFastGhost() {
        this.runMaze(new AStarSearch("mediumGhost2.txt"), "mediumGhost2.txt", 24, 27);
        this.runMaze(new AStarWithGhostSearch("mediumGhost2.txt"), "mediumGhost2.txt", 26, 33);
    }

    @Test
    public void bigGhostWithFastGhost() {
        this.runMaze(new AStarSearch("bigGhost2.txt"), "bigGhost2.txt", 68, 110);
        this.runMaze(new AStarWithGhostSearch("bigGhost2.txt"), "bigGhost2.txt", 70, 126);
    }

    /* part 3-2-2 : even more ghosts */
    @Test
    public void ghostWorld1() {
        this.runMaze(new AStarSearch("ghostWorld1.txt"), "ghostWorld1.txt", 26, 41);
        this.runMaze(new AStarWithGhostSearch("ghostWorld1.txt"), "ghostWorld1.txt", 32, 88);
    }

    @Test
    public void ghostWorld2() {
        this.runMaze(new AStarSearch("ghostWorld2.txt"), "ghostWorld2.txt", 24, 27);
        this.runMaze(new AStarWithGhostSearch("ghostWorld2.txt"), "ghostWorld2.txt", 34, 226);
    }


}
