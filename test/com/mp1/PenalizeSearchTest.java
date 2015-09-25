package com.mp1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mp1.heuristic.ManhattanDistanceHeuristic;
import com.mp1.heuristic.TurnsHeuristic;
import com.mp1.search.AStarPenalizingTurnsSearch;
import com.mp1.search.base.Search;
import com.mp1.solution.MazeSolution;

public class PenalizeSearchTest {

	private void assertMazesAreEqual(MazeSolution actual, char[][] expectedPath) {
		assertEquals(actual.path.maze.length, expectedPath.length);
		for(int i=0; i<actual.path.maze.length; i++) {
			assertArrayEquals(actual.path.maze[i], expectedPath[i]);
		}
	}
    
    @Test
    public void penalizeTurns1ForwardWeighted2Manhattan() {        
        System.out.println("A* Penalizing Search - turns1 with forward weighted 2 - manhattan distance");

        char[][] expectedPath = {
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
                { '%', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' }, // . instead of P is ok here because we turn first in place
                { '%', '.', '%', '%', '%', '%', '%', '%', ' ', '%' },
                { '%', '.', '.', '%', '%', '%', '%', '%', ' ', '%' },
                { '%', '%', '.', '.', '%', '%', '%', '%', ' ', '%' },
                { '%', '%', '%', '.', '.', '%', '%', '%', ' ', '%' },
                { '%', '%', '%', '%', '.', '.', '.', ' ', ' ', '%' },
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' }};

        Search x = new AStarPenalizingTurnsSearch("turns1.txt", 2, 1, new ManhattanDistanceHeuristic());
        MazeSolution actual = x.solve();

        System.out.println(actual.toString());

        assertEquals(actual.pathCost, 28);
        assertEquals(actual.numNodesExpanded, 82);
        assertMazesAreEqual(actual, expectedPath);
    }
    
    @Test
    public void penalizeTurns1TurnsWeighted2Manhattan() {
    	        
        System.out.println("A* Penalizing Search - turns1 with turns weighted 2 - manhattan distance");

        char[][] expectedPath = {
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
                { '%', 'P', '.', '.', '.', '.', '.', '.', '.', '%' },
                { '%', ' ', '%', '%', '%', '%', '%', '%', '.', '%' },
                { '%', ' ', ' ', '%', '%', '%', '%', '%', '.', '%' },
                { '%', '%', ' ', ' ', '%', '%', '%', '%', '.', '%' },
                { '%', '%', '%', ' ', ' ', '%', '%', '%', '.', '%' },
                { '%', '%', '%', '%', ' ', ' ', '.', '.', '.', '%' },
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' }};

        Search x = new AStarPenalizingTurnsSearch("turns1.txt", 1, 2, new ManhattanDistanceHeuristic());
        MazeSolution actual = x.solve();

        System.out.println(actual.toString());

        assertEquals(actual.pathCost, 18);
        assertEquals(actual.numNodesExpanded, 51); // explores U/L/R/D for every node
        assertMazesAreEqual(actual, expectedPath);
    }

    @Test
    public void penalizeTurns1ForwardWeighted2TurnsHeuristic() {        
        System.out.println("A* Penalizing Search - turns1 with forward weighted 2 - turns heuristic");

        char[][] expectedPath = {
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
                { '%', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' }, // . is instead of P here because we go down first, so we do a turn in place
                { '%', '.', '%', '%', '%', '%', '%', '%', ' ', '%' },
                { '%', '.', '.', '%', '%', '%', '%', '%', ' ', '%' },
                { '%', '%', '.', '.', '%', '%', '%', '%', ' ', '%' },
                { '%', '%', '%', '.', '.', '%', '%', '%', ' ', '%' },
                { '%', '%', '%', '%', '.', '.', '.', ' ', ' ', '%' },
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' }};

        Search x = new AStarPenalizingTurnsSearch("turns1.txt", 2, 1, new TurnsHeuristic(2, 1));
        MazeSolution actual = x.solve();

        System.out.println(actual.toString());

        assertEquals(actual.pathCost, 28);
        assertEquals(actual.numNodesExpanded, 55);
        assertMazesAreEqual(actual, expectedPath);
    }
    
    @Test
    public void penalizeTurns1TurnsWeighted2TurnsHeuristic() {
    	        
        System.out.println("A* Penalizing Search - turns1 with turns weighted 2 - turns heuristic");

        char[][] expectedPath = {
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
                { '%', 'P', '.', '.', '.', '.', '.', '.', '.', '%' },
                { '%', ' ', '%', '%', '%', '%', '%', '%', '.', '%' },
                { '%', ' ', ' ', '%', '%', '%', '%', '%', '.', '%' },
                { '%', '%', ' ', ' ', '%', '%', '%', '%', '.', '%' },
                { '%', '%', '%', ' ', ' ', '%', '%', '%', '.', '%' },
                { '%', '%', '%', '%', ' ', ' ', '.', '.', '.', '%' },
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' }};

        Search x = new AStarPenalizingTurnsSearch("turns1.txt", 1, 2, new TurnsHeuristic(1, 2));
        MazeSolution actual = x.solve();

        System.out.println(actual.toString());

        assertEquals(actual.pathCost, 18);
        assertEquals(actual.numNodesExpanded, 42); // explores U/L/R/D for every node... less UPs now because now we know those are at least 2 turns away
        assertMazesAreEqual(actual, expectedPath);
    }

}
