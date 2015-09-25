package com.mp1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mp1.search.AStarWithGhostSearch;
import com.mp1.search.base.Search;
import com.mp1.solution.MazeSolution;

public class GhostSearchTest {

	private void assertMazesAreEqual(MazeSolution actual, char[][] expectedPath) {
		assertEquals(actual.path.maze.length, expectedPath.length);
		for(int i=0; i<actual.path.maze.length; i++) {
			assertArrayEquals(actual.path.maze[i], expectedPath[i]);
		}
	}
	
	@Test
	public void ghost1() {
		System.out.println("Ghost 1 - should go long way");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', '.', '.', '.', '%' },
				{ '%', ' ', ' ', ' ', '.', '%' },
				{ '%', 'g', 'G', 'g', '.', '%' },
				{ '%', '%', ' ', '%', '.', '%' },
				{ '%', '%', 'P', '.', '.', '%' },
				{ '%', '%', '%', '%', '%', '%' }};

		Search x = new AStarWithGhostSearch("test-files/ghost1.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 8);
		assertEquals(actual.numNodesExpanded, 13);
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

		Search x = new AStarWithGhostSearch("test-files/ghost2.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 4);
		assertEquals(actual.numNodesExpanded, 5);
		assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void ghost3() {
		System.out.println("Ghost 3 - should go all the way around");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', '.', '.', '.', '%' },
				{ '%', 'G', 'g', 'g', '.', '%' },
				{ '%', '%', ' ', '%', '.', '%' },
				{ '%', '%', '.', '.', '.', '%' },
				{ '%', '%', 'P', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%' }};

		Search x = new AStarWithGhostSearch("test-files/ghost3.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 8);
		assertEquals(actual.numNodesExpanded, 16);
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

		Search x = new AStarWithGhostSearch("test-files/ghost4.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 8);
		assertEquals(actual.numNodesExpanded, 11);
		assertMazesAreEqual(actual, expectedPath);
	}

	@Test
	public void ghost5() {
		System.out.println("Ghost 5 - stalls 2x then up");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', '.', ' ', ' ', ' ', '%' },
				{ '%', 'g', '.', 'G', 'g', ' ', '%' },
				{ '%', '%', '.', '%', '%', ' ', '%' },
				{ '%', '%', '.', ' ', ' ', ' ', '%' },
				{ '%', '%', 'P', ' ', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%', '%' }};

		Search x = new AStarWithGhostSearch("test-files/ghost5.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 8);
		assertEquals(actual.numNodesExpanded, 12);
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

		Search x = new AStarWithGhostSearch("test-files/ghost6.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		  
		assertEquals(actual.pathCost, 4);
		assertEquals(actual.numNodesExpanded, 5);
		assertMazesAreEqual(actual, expectedPath);
	}

	@Test
	public void ghost7() {
		System.out.println("Ghost 7 - should stall");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', ' ', '.', ' ', ' ', ' ', ' ', '%' },
				{ '%', 'g', 'G', '.', '%', '%', '%', ' ', '%' },
				{ '%', '%', '%', '.', '.', ' ', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%', '%', '%', '%'}};

		Search x = new AStarWithGhostSearch("test-files/ghost7.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 4);
		assertEquals(actual.numNodesExpanded, 5);
		assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void ghost8() {
		System.out.println("Ghost 8 - always collide, so long way");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', ' ', '.', '.', '.', '%' },
				{ '%', '%', '%', ' ', ' ', '.', '%' },
				{ '%', 'G', 'g', 'g', 'g', '.', '%' },
				{ '%', ' ', '%', '%', '%', '.', '%' },
				{ '%', 'P', '.', '.', '.', '.', '%' },
				{ '%', '%', '%', '%', '%', '%', '%'}};

		Search x = new AStarWithGhostSearch("test-files/ghost8.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 10);
		assertEquals(actual.numNodesExpanded, 24);
		assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void ghost9() {
		System.out.println("Ghost 9 - stall x2 for pass in the nights... then go through");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', ' ', '.', ' ', ' ', '%' },
				{ '%', '%', '%', '.', ' ', ' ', '%' },
				{ '%', '.', '.', '.', 'g', ' ', '%' },
				{ '%', '.', '%', '%', '%', ' ', '%' },
				{ '%', 'P', ' ', ' ', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%', '%'}};

		Search x = new AStarWithGhostSearch("test-files/ghost9.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 10);
		assertEquals(actual.numNodesExpanded, 18);
		assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void ghost10() {
		System.out.println("Ghost 10 - easy, straight up");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', ' ', '.', ' ', ' ', ' ', '%' },
				{ '%', '%', '%', '.', ' ', ' ', ' ', '%' },
				{ '%', '.', '.', '.', 'g', 'g', ' ', '%' },
				{ '%', '.', '%', '%', '%', '%', ' ', '%' },
				{ '%', 'P', ' ', ' ', ' ', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%', '%', '%'}};

		Search x = new AStarWithGhostSearch("test-files/ghost10.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 6);
		assertEquals(actual.numNodesExpanded, 7);
		assertMazesAreEqual(actual, expectedPath);
	}
	
	@Test
	public void ghost11() {
		System.out.println("Ghost 11 (starting on right) should make it straight thru");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%' },
				{ '%', ' ', '.', ' ', ' ', '%' },
				{ '%', ' ', '.', ' ', ' ', '%' },
				{ '%', 'g', '.', 'G', ' ', '%' },
				{ '%', '%', '.', '%', ' ', '%' },
				{ '%', '%', 'P', ' ', ' ', '%' },
				{ '%', '%', '%', '%', '%', '%' }};

		Search x = new AStarWithGhostSearch("test-files/ghost11.txt");
		MazeSolution actual = x.solve();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 4);
		assertEquals(actual.numNodesExpanded, 5);
		assertMazesAreEqual(actual, expectedPath);
	}
}
