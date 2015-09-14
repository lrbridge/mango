package com.mp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {

	private char[][] maze;
	private Stack<Node> frontier;
	private HashMap<String, Boolean> isExplored;
	private HashMap<String, Boolean> wasOnFrontier;
	
	private enum ACTIONS {
		L, U, R, D
	};
	
	public MazeSolution findSolution() {
		
		this.maze = this.readMazeInput("simpleMaze.txt");				
		this.frontier = new Stack<Node>();
		this.isExplored = new HashMap<String, Boolean>();
		this.wasOnFrontier = new HashMap<String, Boolean>();
		
		int numNodesExpanded = 0;
		
		Node firstNode = this.findFirstNode(this.maze);
		if(firstNode != null) {
			this.frontier.push(firstNode);
			this.wasOnFrontier.put(firstNode.state.x + "," + firstNode.state.y, true);
		}
		
		// doesn't really make sense, but including b/c in algorithm
		if(this.maze[firstNode.state.x][firstNode.state.y] == '.') {
			System.out.println("FOUnd solution!!!");
			return this.makeSolution(this.maze, firstNode, numNodesExpanded);					
		}
				
		while(!this.frontier.empty()) {
			System.out.println("tpo " + this.frontier.empty());
			Node node = this.frontier.pop();
			numNodesExpanded++;
			System.out.println("looking at " + node.state.x + " " + node.state.y);
			this.isExplored.put(node.state.x + "," + node.state.y, true);
			
			int x = node.state.x;
			int y = node.state.y;
			
			for(ACTIONS action : ACTIONS.values()) {
				
				Node child = new Node();
				child.parent = node;
				child.state = new State(x, y);
				
				switch(action) {
				case L: 
					child.state.y--;
					break;
				case U:
					child.state.x--;
					break;
				case R:
					child.state.y++;
					break;
				default: // case D
					child.state.x++;
					break;
				}
				
				// if is in the maze
				if(child.state.y >= 0 && child.state.y < this.maze[0].length && child.state.x >=0 && child.state.x < this.maze.length) {
					
					// if is not a % (wall)
					if(this.maze[child.state.x][child.state.y] != '%') {
					
		 				// if child state ! in frontier or explored TODO
						String rep = child.state.x + "," + child.state.y;
						if(!this.isExplored.containsKey(rep) && !this.wasOnFrontier.containsKey(rep)) {
							
							// if is goal, return solution
							if(this.maze[child.state.x][child.state.y] == '.') {
								System.out.println("FOUnd solution!!!");
								return this.makeSolution(this.maze, child, numNodesExpanded);					
							}
							
							System.out.println("push on frontier " + child.state.x + " " + child.state.y);
							this.frontier.push(child);
							this.wasOnFrontier.put(node.state.x + "," + node.state.y, true);
						}
						else{
							System.out.println("was already in frontier or explored");
						}
						
					}
					else {
						System.out.println("wall");
					}
					
				}
				else {
					System.out.println("not in maze");
				}
			
			}			
			
		}
		
		return null; // fail if no solution is found
	}
	
	private MazeSolution makeSolution(char[][] maze, Node node, int numNodesExpanded) {
		
		int pathCost = 0;
		
		while(maze[node.state.x][node.state.y] != 'P') {
			maze[node.state.x][node.state.y] = '.';
			node = node.parent;
			pathCost++;
		}
		
		return new MazeSolution(maze, pathCost, numNodesExpanded);
	}

	private Node findFirstNode(char[][] maze) {
        int x = 0;
        int y = 0;
		
        for(char[] row : maze) {
	        for(char content : row) {
	        	if(content == 'P') {
	        		System.out.println("found it!" + content + x + " " +  y);
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

	private char[][] readMazeInput(String filename) {
		List<char[]> maze = new ArrayList<char[]>();
		
		Path file = Paths.get(filename);
		try (InputStream in = Files.newInputStream(file);
		    BufferedReader reader =
		      new BufferedReader(new InputStreamReader(in))) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	System.out.println(line);
		        maze.add(line.toCharArray());
		    }
		} catch (IOException e) {
		    System.err.println(e);
		}
		
		char[][] plainMaze = {};
		return maze.toArray(plainMaze);
	}
	
}
