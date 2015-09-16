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

	private String filename;
	private char[][] maze;
	private List<Node> explored;
	private boolean shouldWaitForExpandToTestGoal = false;

	protected enum ACTIONS {
		LEFT, UP, RIGHT, DOWN
	}

	public Search(String filename) {
		super();
		this.filename = filename;
	}

	public Search(String filename, Boolean shouldWaitForExpandToTestGoal) {
		this(filename);
		this.shouldWaitForExpandToTestGoal = shouldWaitForExpandToTestGoal;
	}

	public MazeSolution solve() {

		this.maze = this.readMazeInput(this.filename);
		this.explored = new ArrayList<Node>();
		int numNodesExpanded = 0;
		Node firstNode = this.findFirstNode(this.maze);
		if (firstNode != null) {
			this.addNodeToFrontier(firstNode);
		}

		// doesn't really make sense (since can't have character P and . in same
		// space), but including b/c in algorithm
		if (!this.shouldWaitForExpandToTestGoal && isGoal(firstNode)) {
			return makeSolution(this.maze, firstNode, numNodesExpanded);
		}

		while (!this.isFrontierEmpty()) {
			Node node = this.popNodeOffFrontier();
			numNodesExpanded++;
			this.explored.add(node);

			if(this.shouldWaitForExpandToTestGoal && isGoal(node)) {
				return makeSolution(this.maze, node, numNodesExpanded);
			}
			
			for (ACTIONS action : ACTIONS.values()) {

				Node child = getChildNode(node, action);

				if (isInMaze(child) && isNotAWall(child)
						&& !this.explored.contains(child)
						&& !this.doesFrontierContain(child)) {

					// if is goal, return solution
					if (!this.shouldWaitForExpandToTestGoal && isGoal(child)) {
						return makeSolution(this.maze, child, numNodesExpanded);
					}

					this.addNodeToFrontier(child);
				}

			}

		}

		return null; // fail if no solution is found
	}

	protected abstract boolean doesFrontierContain(Node child);

	protected abstract Node popNodeOffFrontier();

	protected abstract boolean isFrontierEmpty();

	protected abstract void addNodeToFrontier(Node firstNode);

	private boolean isGoal(Node child) {
		return this.maze[child.state.x][child.state.y] == '.';
	}

	private boolean isNotAWall(Node child) {
		return this.maze[child.state.x][child.state.y] != '%';
	}

	private boolean isInMaze(Node child) {
		return child.state.y >= 0 && child.state.y < this.maze[0].length
				&& child.state.x >= 0 && child.state.x < this.maze.length;
	}

	private Node getChildNode(Node node, ACTIONS action) {
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
	private MazeSolution makeSolution(char[][] maze, Node node,
			int numNodesExpanded) {

		int pathCost = 0;

		while (maze[node.state.x][node.state.y] != 'P') {
			maze[node.state.x][node.state.y] = '.';
			node = node.parent;
			pathCost++;
		}

		return new MazeSolution(maze, pathCost, numNodesExpanded);
	}

	/**
	 * Returns the first node in the maze ('P' character), or null if it can't
	 * find a 'P'
	 * 
	 * @param maze
	 * @return node or null
	 */
	private Node findFirstNode(char[][] maze) {
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