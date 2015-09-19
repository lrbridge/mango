package com.mp1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mp1.search.AStarSearch;
import com.mp1.search.base.Search;
import com.mp1.solution.MazeSolution;

public class AStarSearchTest {

	private void assertMazesAreEqual(MazeSolution actual, char[][] expectedPath) {
		assertEquals(actual.path.length, expectedPath.length);
		for(int i=0; i<actual.path.length; i++) {
			assertArrayEquals(actual.path[i], expectedPath[i]);
		}
	}
	
	@Test
	public void astarSimple() {
		System.out.println("A* Search - simple");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
				{ '%', '.', '.', '.', '.', '.', '%', '.', '.', '.', '.', '%' },
				{ '%', '%', '%', '%', '%', '.', '.', '.', '%', '%', 'P', '%' },
				{ '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' } };

		Search x = new AStarSearch("simpleMaze.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 12);
		assertEquals(actual.numNodesExpanded, 13);
		assertMazesAreEqual(actual, expectedPath);
	}
	
//	@Test
//	public void dfsVsBfs() {
//		System.out.println("A* Search - should go up and across (tiebreaker is up)");
//		
//		char[][] expectedPath = {
//				{ '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
//				{ '%', ' ', ' ', ' ', ' ', '.', '.', '.', '.', '.', '.', '%' },
//				{ '%', ' ', ' ', ' ', ' ', 'P', ' ', ' ', ' ', ' ', ' ', '%' },
//				{ '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
//				{ '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' } };
//		
//		Search x = new AStarSearch("dfsVsBfs.txt");
//		MazeSolution actual = x.solve();
//
//		System.out.println(actual.toString());
//		
//		assertEquals(actual.pathCost, 6);
//		assertEquals(actual.numNodesExpanded, 7);
//		assertMazesAreEqual(actual, expectedPath);
//	}
//	
//	@Test
//	public void dfsVsBfs2() {
//		System.out.println("A* Search - should take fastest");
//		
//		char[][] expectedPath = {
//				{ '%', '%', '%', '%', '%', '%', '%' },
//				{ '%', ' ', '.', '.', ' ', ' ', '%' },
//				{ '%', ' ', 'P', '%', '%', ' ', '%' },
//				{ '%', ' ', ' ', ' ', ' ', ' ', '%' },
//				{ '%', '%', '%', '%', '%', '%', '%' }};
//		
//		Search x = new AStarSearch("dfsVsBfs2.txt");
//		MazeSolution actual = x.solve();
//
//		System.out.println(actual.toString());
//		
//		assertEquals(actual.pathCost, 2);
//		assertEquals(actual.numNodesExpanded, 3);
//		assertMazesAreEqual(actual, expectedPath);
//	}
	
	@Test
	public void badGreedy() {
		System.out.println("A* Search - same as greedy unfortunately because of tiebreakers");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', ' ', ' ', '%', '%' },
				{ '%', 'P', ' ', '%', '.', '%' },
				{ '%', '.', '%', '.', '.', '%' },
				{ '%', '.', '.', '.', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%' }};
		
		Search x = new AStarSearch("badGreedy.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 7);
		assertEquals(actual.numNodesExpanded, 12);
		assertMazesAreEqual(actual, expectedPath);
	}
	
	
	@Test
	public void greedyVsAStar() {
		System.out.println("AStar Search - better than Greedy, gets off bad path faster");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', ' ', ' ', ' ', ' ', '%', '%' },
				{ '%', '.', 'P', '%', '%', ' ', ' ', '%' },
				{ '%', '.', '%', ' ', ' ', '%', ' ', '%' },
				{ '%', '.', '.', '.', '%', ' ', ' ', '%' },
				{ '%', ' ', ' ', '.', '.', '%', '%', '%' },
				{ '%', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%', '%', '%' }};
		
		Search x = new AStarSearch("greedyVsAStar.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 7);
		assertEquals(actual.numNodesExpanded, 11);
		assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void greedyVsAStar2() {
		System.out.println("AStar Search - finds optimal (unlike greedy)");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
				{ '%', ' ', 'P', ' ', ' ', ' ', ' ', ' ', '%' },
				{ '%', ' ', '.', ' ', ' ', ' ', ' ', ' ', '%' },
				{ '%', ' ', '.', ' ', ' ', ' ', ' ', ' ', '%' },
				{ '%', ' ', '.', ' ', ' ', ' ', '%', ' ', '%' },
				{ '%', ' ', '.', '%', '%', '%', '.', '%', '%' },
				{ '%', ' ', '.', '%', '%', '%', '.', ' ', '%' },
				{ '%', ' ', '.', '.', '.', '.', '.', ' ', '%' },
				{ '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%', '%', '%', '%' }};
		
		Search x = new AStarSearch("greedyVsAStar2.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 12);
		assertEquals(actual.numNodesExpanded, 44);
		assertMazesAreEqual(actual, expectedPath);
	}
	
}
