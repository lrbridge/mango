package com.mp1;

import org.junit.Test;

import com.mp1.search.AStarSearch;
import com.mp1.search.BreadthFirstSearch;
import com.mp1.search.DepthFirstSearch;
import com.mp1.search.GreedyBestFirstSearch;
import com.mp1.search.base.Search;

/** Easy way to run just this Test class to get all results for part 1 **/
public class Part1ResultsTest {

	private void runMaze(Search algo, String filename) {
		System.out.println(algo.getClass().getSimpleName() + " " + filename);
		System.out.println(algo.solve());
	}
		
	@Test
	public void bfs() {
		this.runMaze(new BreadthFirstSearch("mediumMaze.txt"), "mediumMaze.txt");
		this.runMaze(new BreadthFirstSearch("bigMaze.txt"), "bigMaze.txt");
		this.runMaze(new BreadthFirstSearch("openMaze.txt"), "openMaze.txt");
	}
	
	@Test
	public void dfs() {
		this.runMaze(new DepthFirstSearch("mediumMaze.txt"), "mediumMaze.txt");
		this.runMaze(new DepthFirstSearch("bigMaze.txt"), "bigMaze.txt");
		this.runMaze(new DepthFirstSearch("openMaze.txt"), "openMaze.txt");
	}
	
	@Test
	public void greedyBestFirstSearch() {
		this.runMaze(new GreedyBestFirstSearch("mediumMaze.txt"), "mediumMaze.txt");
		this.runMaze(new GreedyBestFirstSearch("bigMaze.txt"), "bigMaze.txt");
		this.runMaze(new GreedyBestFirstSearch("openMaze.txt"), "openMaze.txt");
	}
	
	@Test
	public void aStar() {
		this.runMaze(new AStarSearch("mediumMaze.txt"), "mediumMaze.txt");
		this.runMaze(new AStarSearch("bigMaze.txt"), "bigMaze.txt");
		this.runMaze(new AStarSearch("openMaze.txt"), "openMaze.txt");
	}
	
}
