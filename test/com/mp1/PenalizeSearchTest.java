package com.mp1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.mp1.search.AStarPenalizingTurnsSearch;
import com.mp1.search.base.PSearch;
import org.junit.Test;

import com.mp1.solution.MazeSolution;

public class PenalizeSearchTest {

    private void assertMazesAreEqual(MazeSolution actual, char[][] expectedPath) {
        assertEquals(actual.path.length, expectedPath.length);
        for(int i=0; i<actual.path.length; i++) {
            assertArrayEquals(actual.path[i], expectedPath[i]);
        }
    }

    @Test
    public void penalizeSimple() {
        System.out.println("A* Penalizing Search - simple");

        char[][] expectedPath = {
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
                { '%', '.', '.', '.', '.', '.', '%', '.', '.', '.', '.', '%' },
                { '%', '%', '%', '%', '%', '.', '.', '.', '%', '%', 'P', '%' },
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' } };

        PSearch x = new AStarPenalizingTurnsSearch("simpleMaze.txt", 2, 1);
        MazeSolution actual = x.solve();

//        System.out.println(actual.toString());

//        assertEquals(actual.pathCost, 12);
//        assertEquals(actual.numNodesExpanded, 13);
//        assertMazesAreEqual(actual, expectedPath);
    }



}
