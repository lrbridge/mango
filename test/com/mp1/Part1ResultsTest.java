package com.mp1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mp1.search.AStarSearch;
import com.mp1.search.BreadthFirstSearch;
import com.mp1.search.DepthFirstSearch;
import com.mp1.search.GreedyBestFirstSearch;
import com.mp1.search.base.Search;
import com.mp1.solution.MazeSolution;

/** Easy way to run just this Test class to get all results for part 1 **/
public class Part1ResultsTest {

	private void runMaze(Search algo, String filename, int pathCost, int numExpanded) {
		System.out.println(algo.getClass().getSimpleName() + " " + filename);
		MazeSolution solution = algo.solve();
		System.out.println(solution);
		assertEquals(solution.pathCost, pathCost);
		assertEquals(solution.numNodesExpanded, numExpanded);
	}
		
	@Test
	public void bfs() {
		this.runMaze(new BreadthFirstSearch("mediumMaze.txt"), "mediumMaze.txt", 42, 224);
		this.runMaze(new BreadthFirstSearch("bigMaze.txt"), "bigMaze.txt", 62, 720);
		this.runMaze(new BreadthFirstSearch("openMaze.txt"), "openMaze.txt", 54, 275);
	}
	
	@Test
	public void dfs() {
		this.runMaze(new DepthFirstSearch("mediumMaze.txt"), "mediumMaze.txt", 88, 201);
		this.runMaze(new DepthFirstSearch("bigMaze.txt"), "bigMaze.txt", 232, 603);
		this.runMaze(new DepthFirstSearch("openMaze.txt"), "openMaze.txt", 162, 274);
	}
	
	@Test
	public void greedyBestFirstSearch() {
		this.runMaze(new GreedyBestFirstSearch("mediumMaze.txt"), "mediumMaze.txt", 56, 80);
		this.runMaze(new GreedyBestFirstSearch("bigMaze.txt"), "bigMaze.txt", 70, 117);
		this.runMaze(new GreedyBestFirstSearch("openMaze.txt"), "openMaze.txt", 60, 123);
	}
	
	@Test
	public void aStar() {
		this.runMaze(new AStarSearch("mediumMaze.txt"), "mediumMaze.txt", 42, 88);
		this.runMaze(new AStarSearch("bigMaze.txt"), "bigMaze.txt", 62, 273);
		this.runMaze(new AStarSearch("openMaze.txt"), "openMaze.txt", 54, 123);
	}
	
}
