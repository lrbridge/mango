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

    /* open maze has only one solution. This test case is to prove that our new heuristic is at least as good as manhattan distance heuristic. */
    @Test
    public void manhattanDistanceHeuristicOpenMaze() {
        this.runMaze(new AStarPenalizingTurnsSearch("openMaze.txt", 1, 2, new TurnsHeuristic()), "openMaze.txt", "ManhattanDistance", 1, 2, 70, 397);
        this.runMaze(new AStarPenalizingTurnsSearch("openMaze.txt", 2, 1, new TurnsHeuristic()), "openMaze.txt", "ManhattanDistance", 2, 1, 116, 395);
    }

    @Test
    public void turnsHeuristicOpenMaze() {
        this.runMaze(new AStarPenalizingTurnsSearch("openMaze.txt", 1, 2, new TurnsHeuristic()), "openMaze.txt", "TurnsHeuristic", 1, 2, 70, 397);
        this.runMaze(new AStarPenalizingTurnsSearch("openMaze.txt", 2, 1, new TurnsHeuristic()), "openMaze.txt", "TurnsHeuristic", 2, 1, 116, 395);
    }

	@Test
	public void manhattanDistanceHeuristicSmallTurns() {
		this.runMaze(new AStarPenalizingTurnsSearch("smallTurns.txt", 1, 2, new ManhattanDistanceHeuristic()), "smallTurns.txt", "ManhattanDistance", 1, 2, 74, 438);
		this.runMaze(new AStarPenalizingTurnsSearch("smallTurns.txt", 2, 1, new ManhattanDistanceHeuristic()), "smallTurns.txt", "ManhattanDistance", 2, 1, 120, 633);
	}

	@Test
	public void turnsHeuristicSmallTurns() {
		this.runMaze(new AStarPenalizingTurnsSearch("smallTurns.txt", 1, 2, new TurnsHeuristic()), "smallTurns.txt", "TurnsHeuristic", 1, 2, 74, 404);
		this.runMaze(new AStarPenalizingTurnsSearch("smallTurns.txt", 2, 1, new TurnsHeuristic()), "smallTurns.txt", "TurnsHeuristic", 2, 1, 120, 266);
	}

    @Test
    public void manhattanDistanceHeuristicMediumTurns() {
        this.runMaze(new AStarPenalizingTurnsSearch("mediumTurns.txt", 1, 2, new ManhattanDistanceHeuristic()), "mediumTurns.txt", "ManhattanDistance", 1, 2, 58, 439);
        this.runMaze(new AStarPenalizingTurnsSearch("mediumTurns.txt", 2, 1, new ManhattanDistanceHeuristic()), "mediumTurns.txt", "ManhattanDistance", 2, 1, 93, 789);
    }

    @Test
    public void turnsHeuristicMediumTurns() {
        this.runMaze(new AStarPenalizingTurnsSearch("mediumTurns.txt", 1, 2, new TurnsHeuristic()), "mediumTurns.txt", "TurnsHeuristic", 1, 2, 58, 411);
        this.runMaze(new AStarPenalizingTurnsSearch("mediumTurns.txt", 2, 1, new TurnsHeuristic()), "mediumTurns.txt", "TurnsHeuristic", 2, 1, 93, 359);
    }

    @Test
    public void manhattanDistanceHeuristicBigTurns() {
        this.runMaze(new AStarPenalizingTurnsSearch("bigTurns.txt", 1, 2, new ManhattanDistanceHeuristic()), "bigTurns.txt", "ManhattanDistance", 1, 2, 90, 1583);
        this.runMaze(new AStarPenalizingTurnsSearch("bigTurns.txt", 2, 1, new ManhattanDistanceHeuristic()), "bigTurns.txt", "ManhattanDistance", 2, 1, 133, 2044);
    }

    @Test
    public void turnsHeuristicBigTurns() {
        this.runMaze(new AStarPenalizingTurnsSearch("bigTurns.txt", 1, 2, new TurnsHeuristic()), "bigTurns.txt", "TurnsHeuristic", 1, 2, 90, 1574);
        this.runMaze(new AStarPenalizingTurnsSearch("bigTurns.txt", 2, 1, new TurnsHeuristic()), "bigTurns.txt", "TurnsHeuristic", 2, 1, 133, 1001);
    }

    /* this test generates different paths based on the forward code and path cost
       and to prove that no matter what kind of path is created, our new heuristic is always better.
     */
    @Test
    public void manhattanDistanceHeuristicHugeTurns() {
        this.runMaze(new AStarPenalizingTurnsSearch("hugeTurns.txt", 1, 2, new ManhattanDistanceHeuristic()), "hugeTurns.txt", "ManhattanDistance", 1, 2, 114, 2137);
        this.runMaze(new AStarPenalizingTurnsSearch("hugeTurns.txt", 2, 1, new ManhattanDistanceHeuristic()), "hugeTurns.txt", "ManhattanDistance", 2, 1, 194, 3118);
    }

    @Test
    public void turnsHeuristicHugeTurns() {
        this.runMaze(new AStarPenalizingTurnsSearch("hugeTurns.txt", 1, 2, new TurnsHeuristic()), "hugeTurns.txt", "TurnsHeuristic", 1, 2, 114, 2004);
        this.runMaze(new AStarPenalizingTurnsSearch("hugeTurns.txt", 2, 1, new TurnsHeuristic()), "hugeTurns.txt", "TurnsHeuristic", 2, 1, 194, 2065);
    }

}
