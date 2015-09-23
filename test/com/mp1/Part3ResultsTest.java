package com.mp1;

import org.junit.Test;

import com.mp1.search.AStarSearch;
import com.mp1.search.AStarWithGhostSearch;
import com.mp1.search.base.Search;

public class Part3ResultsTest {

	private void runMaze(Search algo, String filename) {
		System.out.println(algo.getClass().getSimpleName() + " " + filename);
		System.out.println(algo.solve());
	}
		
	@Test
	public void small() {
		this.runMaze(new AStarSearch("smallGhost.txt"), "smallGhost.txt");
		this.runMaze(new AStarWithGhostSearch("smallGhost.txt"), "smallGhost.txt");
	}
	
	@Test
	public void medium() {
		this.runMaze(new AStarSearch("mediumGhost.txt"), "mediumGhost.txt");
		this.runMaze(new AStarWithGhostSearch("mediumGhost.txt"), "mediumGhost.txt");
	}
	
	@Test
	public void large() {
		this.runMaze(new AStarSearch("bigGhost.txt"), "bigGhost.txt");
		this.runMaze(new AStarWithGhostSearch("bigGhost.txt"), "bigGhost.txt");
	}
	
}
