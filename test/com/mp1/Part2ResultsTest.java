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
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/openMaze.txt", 1, 2, new ManhattanDistanceHeuristic()), "openMaze.txt", "ManhattanDistance", 1, 2, 70, 501);
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/openMaze.txt", 2, 1, new ManhattanDistanceHeuristic()), "openMaze.txt", "ManhattanDistance", 2, 1, 116, 906);
    }

    @Test
    public void turnsHeuristicOpenMaze() {
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/openMaze.txt", 1, 2, new TurnsHeuristic(1, 2)), "openMaze.txt", "TurnsHeuristic", 1, 2, 70, 391);
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/openMaze.txt", 2, 1, new TurnsHeuristic(2, 1)), "openMaze.txt", "TurnsHeuristic", 2, 1, 116, 391);
    }

	@Test
	public void manhattanDistanceHeuristicSmallTurns() {
		this.runMaze(new AStarPenalizingTurnsSearch("test-files/smallTurns.txt", 1, 2, new ManhattanDistanceHeuristic()), "smallTurns.txt", "ManhattanDistance", 1, 2, 74, 438);
		this.runMaze(new AStarPenalizingTurnsSearch("test-files/smallTurns.txt", 2, 1, new ManhattanDistanceHeuristic()), "smallTurns.txt", "ManhattanDistance", 2, 1, 120, 633);
	}

	@Test
	public void turnsHeuristicSmallTurns() {
		this.runMaze(new AStarPenalizingTurnsSearch("test-files/smallTurns.txt", 1, 2, new TurnsHeuristic(1, 2)), "smallTurns.txt", "TurnsHeuristic", 1, 2, 74, 376);
		this.runMaze(new AStarPenalizingTurnsSearch("test-files/smallTurns.txt", 2, 1, new TurnsHeuristic(2, 1)), "smallTurns.txt", "TurnsHeuristic", 2, 1, 120, 265);
	}

    @Test
    public void manhattanDistanceHeuristicMediumTurns() {
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/mediumTurns.txt", 1, 2, new ManhattanDistanceHeuristic()), "mediumTurns.txt", "ManhattanDistance", 1, 2, 58, 439);
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/mediumTurns.txt", 2, 1, new ManhattanDistanceHeuristic()), "mediumTurns.txt", "ManhattanDistance", 2, 1, 93, 789);
    }

    @Test
    public void turnsHeuristicMediumTurns() {
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/mediumTurns.txt", 1, 2, new TurnsHeuristic(1, 2)), "mediumTurns.txt", "TurnsHeuristic", 1, 2, 58, 397);
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/mediumTurns.txt", 2, 1, new TurnsHeuristic(2, 1)), "mediumTurns.txt", "TurnsHeuristic", 2, 1, 93, 356);
    }

    @Test
    public void manhattanDistanceHeuristicBigTurns() {
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/bigTurns.txt", 1, 2, new ManhattanDistanceHeuristic()), "bigTurns.txt", "ManhattanDistance", 1, 2, 90, 1583);
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/bigTurns.txt", 2, 1, new ManhattanDistanceHeuristic()), "bigTurns.txt", "ManhattanDistance", 2, 1, 133, 2044);
    }

    @Test
    public void turnsHeuristicBigTurns() {
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/bigTurns.txt", 1, 2, new TurnsHeuristic(1, 2)), "bigTurns.txt", "TurnsHeuristic", 1, 2, 90, 1513);
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/bigTurns.txt", 2, 1, new TurnsHeuristic(2, 1)), "bigTurns.txt", "TurnsHeuristic", 2, 1, 133, 971);
    }

    /* this test generates different paths based on the forward code and path cost
       and to prove that no matter what kind of path is created, our new heuristic is always better.
     */
    @Test
    public void manhattanDistanceHeuristicHugeTurns() {
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/hugeTurns.txt", 1, 2, new ManhattanDistanceHeuristic()), "hugeTurns.txt", "ManhattanDistance", 1, 2, 114, 2137);
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/hugeTurns.txt", 2, 1, new ManhattanDistanceHeuristic()), "hugeTurns.txt", "ManhattanDistance", 2, 1, 194, 3118);
    }

    @Test
    public void turnsHeuristicHugeTurns() {
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/hugeTurns.txt", 1, 2, new TurnsHeuristic(1, 2)), "hugeTurns.txt", "TurnsHeuristic", 1, 2, 114, 1924);
        this.runMaze(new AStarPenalizingTurnsSearch("test-files/hugeTurns.txt", 2, 1, new TurnsHeuristic(2, 1)), "hugeTurns.txt", "TurnsHeuristic", 2, 1, 194, 2049);
    }

}
