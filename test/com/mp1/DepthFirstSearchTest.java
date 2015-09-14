package com.mp1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class DepthFirstSearchTest {

	@Test
	public void dfsSimple() {
		System.out.println("Depth First Search - simple");
		
		char[][] expectedPath = {
				{ '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' },
				{ '%', '.', '.', '.', '.', '.', '%', '.', '.', '.', '.', '%' },
				{ '%', '%', '%', '%', '%', '.', '.', '.', '%', '%', 'P', '%' },
				{ '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%', '%' } };

		DepthFirstSearch x = new DepthFirstSearch("simpleMaze.txt");
		MazeSolution actual = x.findSolution();

		System.out.println(actual.toString());
		
		assertEquals(actual.pathCost, 12);
		assertEquals(actual.numNodesExpanded, 12);
		assertEquals(actual.path.length, expectedPath.length);
		assertArrayEquals(actual.path[0], expectedPath[0]);
		assertArrayEquals(actual.path[1], expectedPath[1]);
		assertArrayEquals(actual.path[2], expectedPath[2]);
		assertArrayEquals(actual.path[3], expectedPath[3]);
	}
	
	@Test
	public void dfsSmall() {
		System.out.println("Depth First Search - small");
		
		DepthFirstSearch x = new DepthFirstSearch("smallMaze.txt");
		MazeSolution actual = x.findSolution();

		System.out.println(actual.toString());
	}
	
	@Test
	public void dfsMedium() {
		System.out.println("Depth First Search - medium");
		
		DepthFirstSearch x = new DepthFirstSearch("mediumMaze.txt");
		MazeSolution actual = x.findSolution();

		System.out.println(actual.toString());
	}
	
	@Test
	public void dfsBig() {
		System.out.println("Depth First Search - big");
		
		DepthFirstSearch x = new DepthFirstSearch("bigMaze.txt");
		MazeSolution actual = x.findSolution();

		System.out.println(actual.toString());
	}
}
