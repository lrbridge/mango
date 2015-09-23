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
	
}
