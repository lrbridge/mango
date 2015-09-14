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
import java.util.Stack;

public class DepthFirstSearch {

	private String filename;
	private char[][] maze;
	private Stack<Node> frontier;
	private List<Node> explored;
	
	private enum ACTIONS {
		LEFT, UP, RIGHT, DOWN
	};
	
	public DepthFirstSearch(String filename) {
		this.filename = filename;
	}
	
	public MazeSolution findSolution() {
		
		this.maze = this.readMazeInput(this.filename);				
		this.frontier = new Stack<Node>();
		this.explored = new ArrayList<Node>();
		
		int numNodesExpanded = 0;
		
		Node firstNode = this.findFirstNode(this.maze);
		if(firstNode != null) {
			this.frontier.push(firstNode);
		}
		
		// doesn't really make sense (since can't have character P and . in same space), but including b/c in algorithm
		if(isGoal(firstNode)) {
			return makeSolution(this.maze, firstNode, numNodesExpanded);					
		}
				
		while(!this.frontier.empty()) {
			Node node = this.frontier.pop();
			numNodesExpanded++;
			this.explored.add(node);
	
			for(ACTIONS action : ACTIONS.values()) {
				
				Node child = new Node();
				child.parent = node;
				child.state = new State(node.state.x, node.state.y);
				
				switch(action) {
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
				
				if(isInMaze(child) && isNotAWall(child) && !this.explored.contains(child) && !this.frontier.contains(child)) {
					
					// if is goal, return solution
					if(isGoal(child)) {
						return makeSolution(this.maze, child, numNodesExpanded);					
					}
					
//					System.out.println("push on frontier " + child.state.x + " " + child.state.y);
					this.frontier.push(child);
				}
			
			}			
			
		}
		
		return null; // fail if no solution is found
	}
	
	private boolean isGoal(Node child) {
		return this.maze[child.state.x][child.state.y] == '.';
	}

	private boolean isNotAWall(Node child) {
		return this.maze[child.state.x][child.state.y] != '%';
	}

	private boolean isInMaze(Node child) {
		return child.state.y >= 0 && child.state.y < this.maze[0].length && child.state.x >=0 && child.state.x < this.maze.length;
	}

	/**
	 * Summarize the solution - path cost, number of nodes expanded, pretty maze path output
	 * @param maze
	 * @param node
	 * @param numNodesExpanded
	 * @return solution
	 */
	private MazeSolution makeSolution(char[][] maze, Node node, int numNodesExpanded) {
		
		int pathCost = 0;
		
		while(maze[node.state.x][node.state.y] != 'P') {
			maze[node.state.x][node.state.y] = '.';
			node = node.parent;
			pathCost++;
		}
		
		return new MazeSolution(maze, pathCost, numNodesExpanded);
	}

	/**
	 * Returns the first node in the maze ('P' character), or null if it can't find a 'P'
	 * @param maze
	 * @return node or null
	 */
	private Node findFirstNode(char[][] maze) {
        int x = 0;
        int y = 0;
		
        for(char[] row : maze) {
	        for(char content : row) {
	        	if(content == 'P') {
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
	 * @param filename
	 * @return char[][] maze
	 */
	private char[][] readMazeInput(String filename) {
		List<char[]> maze = new ArrayList<char[]>();
		
		Path file = Paths.get(filename);
		try (InputStream in = Files.newInputStream(file);
		    BufferedReader reader =
		      new BufferedReader(new InputStreamReader(in))) {
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
