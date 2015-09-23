package com.mp1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mp1.search.BreadthFirstSearch;
import com.mp1.search.base.Search;
import com.mp1.solution.MazeSolution;

public class BreadthFirstSearchTest {

	private void assertMazesAreEqual(MazeSolution actual, char[][] expectedPath) {
		assertEquals(actual.path.length, expectedPath.length);
		for(int i=0; i<actual.path.length; i++) {
			assertArrayEquals(actual.path[i], expectedPath[i]);
		}
	}
	
	@Test
	public void bfsSimple() {
		System.out.println("Breadth First Search - simple");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
				{ '%', '.', '.', '.', '.', '.', '%', '.', '.', '.', '.', '%' },
				{ '%', '%', '%', '%', '%', '.', '.', '.', '%', '%', 'P', '%' },
				{ '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' } };

		Search x = new BreadthFirstSearch("simpleMaze.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 12);
		assertEquals(actual.numNodesExpanded, 12);
		assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void bfsSmall() {
		System.out.println("Breadth First Search - small");

        char[][] expectedPath = {
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
                { '%', '.', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', '%' },
                { '%', '.', '%', '%', ' ', '%', ' ', '%', '%', '%', '%', '%', ' ', '%', ' ', '.', '.', '.', '.', '.', ' ', ' ', '%' },
                { '%', '.', '%', '.', '.', '.', '.', '.', '.', '.', '%', ' ', ' ', ' ', '%', '.', '%', ' ', '%', '.', '%', ' ', '%' },
                { '%', '.', '.', '.', '%', '%', ' ', '%', '%', '.', '%', '%', '%', ' ', '%', '.', ' ', ' ', '%', '.', '%', '%', '%' },
                { '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', '.', '.', '.', '.', '.', '.', '.', '%', ' ', '%', '.', '.', 'P', '%' },
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' }
        };

		Search x = new BreadthFirstSearch("smallMaze.txt");
		MazeSolution actual = x.solve();

        System.out.println(actual.toString());

        assertEquals(actual.pathCost, 32);
        assertEquals(actual.numNodesExpanded, 64);
        assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void bfsMedium() {
		System.out.println("Breadth First Search - medium");

        char[][] expectedPath = {
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
                { '%', '.', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', '%' },
                { '%', '.', ' ', ' ', '%', ' ', ' ', ' ', '%', '%', ' ', '%', ' ', '%', '%', '%', '%', ' ', '%', '%', ' ', ' ', '%' },
                { '%', '.', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%' },
                { '%', '.', '%', '%', ' ', '%', '%', '%', ' ', ' ', ' ', ' ', ' ', '%', '%', ' ', '%', '%', ' ', '%', '%', '%', '%' },
                { '%', '.', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%' },
                { '%', '.', '%', '%', '%', ' ', '%', ' ', '%', '%', '%', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%', '%', ' ', '%', '%' },
                { '%', '.', '%', ' ', '%', '.', '.', '.', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%' },
                { '%', '.', '.', '.', '.', '.', '%', '.', '%', ' ', '%', '%', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', '%' },
                { '%', ' ', '%', ' ', '%', ' ', '%', '.', '.', '.', '.', '.', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%' },
                { '%', ' ', ' ', ' ', '%', '%', '%', ' ', '%', '%', '%', '.', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', '%', '%' },
                { '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', '.', '.', '.', '.', '.', '%', ' ', ' ', ' ', ' ', ' ', '%' },
                { '%', '%', ' ', '%', ' ', '%', '%', ' ', ' ', ' ', ' ', '%', '%', '%', '%', '.', '%', ' ', '%', '%', ' ', '%', '%' },
                { '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', '.', '.', '.', ' ', ' ', '%', ' ', '%' },
                { '%', ' ', ' ', ' ', '%', '%', ' ', '%', '%', ' ', '%', '%', ' ', '%', '%', '%', '%', '.', '%', '%', ' ', ' ', '%' },
                { '%', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', '.', '%', ' ', '%', ' ', '%' },
                { '%', '%', '%', '%', ' ', '%', '%', ' ', '%', '%', ' ', ' ', '%', '%', ' ', '%', ' ', '.', ' ', '%', ' ', '%', '%' },
                { '%', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', '.', '%', ' ', '%', ' ', '%' },
                { '%', ' ', '%', '%', ' ', '%', ' ', '%', '%', '%', '%', '%', ' ', '%', ' ', ' ', ' ', '.', '.', '.', ' ', ' ', '%' },
                { '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', '.', '%', ' ', '%' },
                { '%', ' ', ' ', ' ', '%', '%', ' ', '%', '%', ' ', '%', '%', '%', ' ', '%', ' ', ' ', ' ', '%', '.', '%', '%', '%' },
                { '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', '.', '.', 'P', '%' },
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
        };

		Search x = new BreadthFirstSearch("mediumMaze.txt");
		MazeSolution actual = x.solve();

        System.out.println(actual.toString());

        assertEquals(actual.pathCost, 42);
        assertEquals(actual.numNodesExpanded, 224);
        assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void bfsBig() {
		System.out.println("Breadth First Search - big");

        char [][] expectedPath = {
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
                { '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%' },
                { '%', '%', ' ', '%', ' ', '%', ' ', '%', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%' },
                { '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%' },
                { '%', '%', ' ', '%', ' ', '%', '%', ' ', '%', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', '%', '%', ' ', '%', '%', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%' },
                { '%', ' ', '%', ' ', '%', ' ', '%', '%', ' ', '%', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', '%', ' ', '%', ' ', ' ', '%', ' ', '%', ' ', '%', '%', ' ', ' ', '%', ' ', '%' },
                { '%', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', '%', ' ', '%', '%', ' ', '%', ' ', '%', ' ', ' ', '%', ' ', '%', ' ', '%', '%', '%', '%', ' ', '%', ' ', ' ', ' ', '%', '%', ' ', '%', ' ', ' ', '%', '%', ' ', '%', '%' },
                { '%', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%' },
                { '%', '%', ' ', '%', ' ', '%', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', '%', ' ', '%', '%', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', '%', ' ', '%' },
                { '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%' },
                { '%', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', '%', '%', ' ', ' ', '%', ' ', '%', '%', ' ', '%', '%', ' ', '%', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', '%' },
                { '%', ' ', '%', ' ', '%', '%', ' ', '%', ' ', '%', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', '%', '%', '%', ' ', '%' },
                { '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', '%', ' ', ' ', '%', '%', ' ', ' ', '%', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', '%', '%', ' ', '%', '%', '%', ' ', '%', '%' },
                { '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', '%', ' ', ' ', '%', ' ', '%', ' ', '%', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', '%', '%', ' ', '%', ' ', '%', '%', ' ', '%', ' ', '%', ' ', '%', '%', ' ', '%', ' ', '%' },
                { '%', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', '%' },
                { '%', ' ', '%', '%', ' ', '%', ' ', '%', ' ', '%', ' ', '%', '%', ' ', ' ', '%', '%', ' ', ' ', '%', '%', ' ', '%', ' ', '%', '%', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', '%' },
                { '%', '%', ' ', ' ', '%', ' ', '%', '%', '%', ' ', ' ', ' ', ' ', '%', '%', '%', ' ', '%', '%', ' ', '%', ' ', ' ', '%', '%', '%', ' ', '%', '%', '%', ' ', '%', '%', ' ', ' ', ' ', '%' },
                { '%', ' ', '%', '.', '.', '.', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%' },
                { '%', ' ', '%', '.', '%', '.', '%', ' ', '%', '%', ' ', '%', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', '%', ' ', '%', ' ', '%', ' ', '%', '%', ' ', '%', '%', '%' },
                { '%', ' ', ' ', '.', '%', '.', '.', '.', '.', '.', '.', '.', '.', '.', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%' },
                { '%', '%', '%', '.', ' ', '%', ' ', '%', ' ', '%', '%', ' ', '%', '.', '.', '.', '%', ' ', '%', ' ', '%', ' ', '%', '%', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', '%', '%' },
                { '%', ' ', '%', '.', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '.', '.', '.', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%' },
                { '%', '%', ' ', '.', ' ', ' ', ' ', '%', '%', '%', ' ', '%', ' ', '%', ' ', ' ', '%', '.', '.', '.', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', '%', ' ', '%', ' ', '%', ' ', '%', '%' },
                { '%', ' ', '%', '.', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', '.', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', '%', '.', ' ', '%', ' ', ' ', '%', ' ', '%', '%', ' ', '%', ' ', ' ', '%', ' ', '%', '.', '%', '%', '%', '%', '%', ' ', '%', ' ', '%', ' ', '%', '%', ' ', '%', '%', '%', '%' },
                { '%', ' ', '.', '.', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', '.', '.', '.', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%' },
                { '%', '%', '.', '%', ' ', '%', ' ', ' ', '%', ' ', ' ', '%', ' ', '%', ' ', ' ', '%', ' ', ' ', ' ', '%', '.', '.', '.', '.', '.', '%', ' ', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', '%' },
                { '%', ' ', '.', '.', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', ' ', '%', '.', '.', '.', '%', '.', '.', '.', '.', '.', '%', ' ', '%' },
                { '%', '%', '%', '.', ' ', ' ', ' ', '%', ' ', '%', ' ', '%', '%', ' ', '%', '%', ' ', '%', ' ', ' ', '%', ' ', '%', '%', '%', ' ', '%', '.', '.', '.', '%', ' ', '%', '.', '.', '.', '%' },
                { '%', '.', '.', '.', '%', ' ', '%', ' ', '%', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', '%', ' ', ' ', ' ', '%', ' ', '%', 'P', '%' },
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },

        };

		Search x = new BreadthFirstSearch("bigMaze.txt");
		MazeSolution actual = x.solve();

        System.out.println(actual.toString());
        assertEquals(actual.pathCost, 62);
        assertEquals(actual.numNodesExpanded, 720);

	}

    @Test
    public void bfsOpen() {
        System.out.println("Breadth First Search - Open");


        char[][] expectedPath = {
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '.', ' ', ' ', ' ', ' ', ' ', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '.', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '.', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '.', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '.', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '.', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '.', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '.', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '.', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', '.', '.', '.', '.', 'P', '%', '.', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '.', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', '.', '%', '%', '%', '%', '%', '.', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '.', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', '.', '.', '.', '.', '.', '.', '.', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '.', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', '.', '.', '.', '.', '.', '.', '.', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
                { '%', ' ', ' ', ' ', ' ', ' ', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
                { '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
        };

        Search x = new BreadthFirstSearch("openMaze.txt");
        MazeSolution actual = x.solve();

        System.out.println(actual.toString());

        assertEquals(actual.pathCost, 54);
        assertEquals(actual.numNodesExpanded, 275);
        assertMazesAreEqual(actual, expectedPath);
    }

	@Test
	public void dfsVsBfs() {
		System.out.println("Breadth First Search - should go up, across");



		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', ' ', ' ', ' ', '.', '.', '.', '.', '.', '.', '%' },
				{ '%', ' ', ' ', ' ', ' ', 'P', ' ', ' ', ' ', ' ', ' ', '%' },
				{ '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' } };
		
		Search x = new BreadthFirstSearch("dfsVsBfs.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 6);
		assertEquals(actual.numNodesExpanded, 26);
		assertMazesAreEqual(actual, expectedPath);
	}

	@Test
	public void dfsVsBfs2() {
		System.out.println("Breadth First Search - should find solution fast");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', '.', '.', ' ', ' ', '%' },
				{ '%', ' ', 'P', '%', '%', ' ', '%' },
				{ '%', ' ', ' ', ' ', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%', '%' }};
		
		Search x = new BreadthFirstSearch("dfsVsBfs2.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 2);
		assertEquals(actual.numNodesExpanded, 3);
		assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void badGreedy() {
		System.out.println("Breadth First Search - does poorly on bad greedy");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', ' ', ' ', '%', '%' },
				{ '%', 'P', ' ', '%', '.', '%' },
				{ '%', '.', '%', '.', '.', '%' },
				{ '%', '.', '.', '.', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%' }};
		
		Search x = new BreadthFirstSearch("badGreedy.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 7);
		assertEquals(actual.numNodesExpanded, 12);
		assertMazesAreEqual(actual, expectedPath);
	}
}
