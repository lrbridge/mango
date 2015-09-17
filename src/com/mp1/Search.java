package com.mp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Search {

	protected char[][] maze;
	protected List<Node> explored;
	protected int numNodesExpanded;

	protected enum ACTIONS {
		LEFT, UP, RIGHT, DOWN
	}

	public Search(String filename) {		
		this.maze = this.readMazeInput(filename);
		this.explored = new ArrayList<Node>();
		this.numNodesExpanded = 0;
	}

	/**
	 * Solve the maze
	 * @return
	 */
	public abstract MazeSolution solve();
	
	protected abstract boolean doesFrontierContain(Node child);

	protected abstract Node popNodeOffFrontier();

	protected abstract boolean isFrontierEmpty();

	protected abstract void addNodeToFrontier(Node firstNode);

	protected boolean isGoal(Node child) {
		return this.maze[child.state.x][child.state.y] == '.';
	}

	protected boolean isNotAWall(Node child) {
		return this.maze[child.state.x][child.state.y] != '%';
	}

	protected boolean isInMaze(Node child) {
		return child.state.y >= 0 && child.state.y < this.maze[0].length
				&& child.state.x >= 0 && child.state.x < this.maze.length;
	}

	/**
	 * Returns the child node from the given node with the action
	 * @param node
	 * @param action
	 * @return
	 */
	protected Node getChildNode(Node node, ACTIONS action) {
		Node child = new Node();
		child.parent = node;
		child.state = new State(node.state.x, node.state.y);

		switch (action) {
		case LEFT:
			child.state.y--;
			break;
		case UP:
			child.state.x--;
			break;
		case RIGHT:
			child.state.y++;
			break;
		case DOWN:
		default:
			child.state.x++;
			break;
		}
		return child;
	}

	/**
	 * Summarize the solution - path cost, number of nodes expanded, pretty maze
	 * path output
	 * 
	 * @param maze
	 * @param node
	 * @param numNodesExpanded
	 * @return solution
	 */
	protected MazeSolution makeSolution(Node node) {

		int pathCost = 0;

		while (this.maze[node.state.x][node.state.y] != 'P') {
			this.maze[node.state.x][node.state.y] = '.';
			node = node.parent;
			pathCost++;
		}

		return new MazeSolution(this.maze, pathCost, this.numNodesExpanded);
	}

	/**
	 * Returns the first node in the maze ('P' character), or null if it can't
	 * find a 'P'
	 * 
	 * @param maze
	 * @return node or null
	 */
	protected Node findFirstNode(char[][] maze) {
		int x = 0;
		int y = 0;

		for (char[] row : maze) {
			for (char content : row) {
				if (content == 'P') {
					Node node = new Node();
					node.state = new State(x, y);
					node.parent = null;
					return node;
				}
				y++;
			}
			x++;
			y = 0;
		}
		return null;
	}

	/**
	 * Reads the input file and returns a char[][] of the maze
	 * 
	 * @param filename
	 * @return char[][] maze
	 */
	private char[][] readMazeInput(String filename) {
		List<char[]> maze = new ArrayList<char[]>();

		Path file = Paths.get(filename);
		try (InputStream in = Files.newInputStream(file);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				maze.add(line.toCharArray());
			}
		} catch (IOException e) {
			System.err.println(e);
		}

		char[][] plainMaze = {};
		return maze.toArray(plainMaze);
	}

}