package com.mp1.solution;

import com.mp1.node.Node;
import com.mp1.search.base.Maze;

public class MazeSolution {

	public Maze path;

	public int pathCost;

	public int numNodesExpanded;
	
	public MazeSolution(Maze maze, Node finalNode, int numNodesExpanded) {	
		this.pathCost = finalNode.getDistanceSoFar();
//System.out.println("walking backwards...");
		while (finalNode.getParent() != null) {
//			System.out.println(finalNode.getState().x + " " + finalNode.getState().y + " -- ghost" + finalNode.getState().ghostCoordinates.get(4).x + " " + finalNode.getState().ghostCoordinates.get(4).y);
			maze.set(finalNode.getState().x, finalNode.getState().y, '.');
			finalNode = finalNode.getParent();
		}
		
		this.path = maze;
		this.numNodesExpanded = numNodesExpanded;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();				
		str.append("Solution:" + '\n');
		str.append("Maze:\n" + this.path);
		str.append("Path cost: " + pathCost + '\n');
		str.append("Number of nodes expanded: " + numNodesExpanded + '\n');
		str.append("----------------------------\n");
		return str.toString();
	}

}
