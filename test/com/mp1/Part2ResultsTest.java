package com.mp1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mp1.heuristic.ManhattanDistanceHeuristic;
import com.mp1.heuristic.TurnsHeuristic;
import com.mp1.search.AStarPenalizingTurnsSearch;
import com.mp1.solution.MazeSolution;

public class Part2ResultsTest {

	private void runMaze(AStarPenalizingTurnsSearch algo, String filename, String heuristicName, int forwardCost, int turnsCost, int pathCost, int numExpanded) {
		System.out.println(algo.getClass().getSimpleName() + " " + heuristicName + " " + filename + " forwardcost:" + forwardCost + " turnscost:" + turnsCost);
		MazeSolution solution = algo.solve();
		System.out.println(solution);
		assertEquals(solution.pathCost, pathCost);
		assertEquals(solution.numNodesExpanded, numExpanded);
	}
		
	@Test
	public void manhattanDistanceHeuristicSmallTurns() {
		this.runMaze(new AStarPenalizingTurnsSearch("smallTurns.txt", 1, 2, new ManhattanDistanceHeuristic()), "smallTurns.txt", "ManhattanDistance", 1, 2, 74, 438);
		this.runMaze(new AStarPenalizingTurnsSearch("smallTurns.txt", 2, 1, new ManhattanDistanceHeuristic()), "smallTurns.txt", "ManhattanDistance", 2, 1, 120, 633);
	}
	
	@Test
	public void manhattanDistanceHeuristicBigTurns() {
		this.runMaze(new AStarPenalizingTurnsSearch("bigTurns.txt", 1, 2, new ManhattanDistanceHeuristic()), "bigTurns.txt", "ManhattanDistance", 1, 2, 90, 1584);
		this.runMaze(new AStarPenalizingTurnsSearch("bigTurns.txt", 2, 1, new ManhattanDistanceHeuristic()), "bigTurns.txt", "ManhattanDistance", 2, 1, 133, 2044);
	}
	
	@Test
	public void turnsHeuristicSmallTurns() {
		this.runMaze(new AStarPenalizingTurnsSearch("smallTurns.txt", 1, 2, new TurnsHeuristic()), "smallTurns.txt", "TurnsHeuristic", 1, 2, 74, 405);
		this.runMaze(new AStarPenalizingTurnsSearch("smallTurns.txt", 2, 1, new TurnsHeuristic()), "smallTurns.txt", "TurnsHeuristic", 2, 1, 120, 267);
	}
	
	@Test
	public void turnsHeuristicBigTurns() {
		this.runMaze(new AStarPenalizingTurnsSearch("bigTurns.txt", 1, 2, new TurnsHeuristic()), "bigTurns.txt", "TurnsHeuristic", 1, 2, 90, 1575);
		this.runMaze(new AStarPenalizingTurnsSearch("bigTurns.txt", 2, 1, new TurnsHeuristic()), "bigTurns.txt", "TurnsHeuristic", 2, 1, 133, 999);
	}
	
}
