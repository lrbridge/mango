package com.mp1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mp1.search.AStarWithGhostSearch;
import com.mp1.search.base.Search;
import com.mp1.solution.MazeSolution;

public class GhostSearchTest {

	private void assertMazesAreEqual(MazeSolution actual, char[][] expectedPath) {
		assertEquals(actual.path.length, expectedPath.length);
		for(int i=0; i<actual.path.length; i++) {
			assertArrayEquals(actual.path[i], expectedPath[i]);
		}
	}
	
	@Test
	public void ghost1() {
		System.out.println("Ghost 1 - should go right/left fast to stall and then up");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', '.', ' ', ' ', '%' },
				{ '%', ' ', '.', ' ', ' ', '%' },
				{ '%', 'g', '.', 'g', ' ', '%' },
				{ '%', '%', '.', '%', ' ', '%' },
				{ '%', '%', '.', '.', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%' }};

		Search x = new AStarWithGhostSearch("ghost1.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 12);
		assertEquals(actual.numNodesExpanded, 113);
		assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void ghost2() {
		System.out.println("Ghost 2 - should go straight up");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', '.', ' ', ' ', '%' },
				{ '%', ' ', '.', ' ', ' ', '%' },
				{ '%', 'G', '.', 'g', ' ', '%' },
				{ '%', '%', '.', '%', ' ', '%' },
				{ '%', '%', 'P', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%' }};

		Search x = new AStarWithGhostSearch("ghost1.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 4);
		assertEquals(actual.numNodesExpanded, 131);
		assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void ghost3() {
		System.out.println("Ghost 3 - should stall");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', '.', ' ', ' ', '%' },
				{ '%', 'G', '.', 'g', ' ', '%' },
				{ '%', '%', '.', '%', ' ', '%' },
				{ '%', '%', '.', '.', ' ', '%' },
				{ '%', '%', 'P', '.', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%' }};

		Search x = new AStarWithGhostSearch("ghost1.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 6);
		assertEquals(actual.numNodesExpanded, 131);
		assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void ghost4() {
		System.out.println("Ghost 4 - should go long way");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', '.', '.', '.', '%' },
				{ '%', '%', 'G', '%', '.', '%' },
				{ '%', '%', ' ', '%', '.', '%' },
				{ '%', '%', '.', '.', '.', '%' },
				{ '%', '%', 'P', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%' }};

		Search x = new AStarWithGhostSearch("ghost4.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 8);
		assertEquals(actual.numNodesExpanded, 11);
		assertMazesAreEqual(actual, expectedPath);
	}

	@Test
	public void ghost5() {
		System.out.println("Ghost 5 - should go long way");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', '.', '.', '.', '.', '%' },
				{ '%', 'g', 'g', 'G', 'g', '.', '%' },
				{ '%', '%', ' ', '%', '%', '.', '%' },
				{ '%', '%', '.', '.', '.', '.', '%' },
				{ '%', '%', 'P', ' ', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%', '%' }};

		Search x = new AStarWithGhostSearch("ghost5.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 83);
		assertEquals(actual.numNodesExpanded, 11);
		assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void ghost6() {
		System.out.println("Ghost 6 - should stall");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', '.', ' ', ' ', ' ', ' ', '%' },
				{ '%', 'G', '.', 'g', 'g', 'g', ' ', '%' },
				{ '%', '%', '.', '.', ' ', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%', '%', '%'}};

		Search x = new AStarWithGhostSearch("ghost6.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 8);
		assertEquals(actual.numNodesExpanded, 11);
		assertMazesAreEqual(actual, expectedPath);
	}
	
}
