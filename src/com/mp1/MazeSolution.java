package com.mp1;

public class MazeSolution {

	public char[][] path;

	public int pathCost;

	public int numNodesExpanded;
	
	public MazeSolution(char[][] path, int pathCost, int numNodesExpanded) {
		this.path = path;
		this.pathCost = pathCost;
		this.numNodesExpanded = numNodesExpanded;
	}
	
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		str.append("Solution:" + '\n');
		
		for(char[] x : path) {
			for(char y : x) {
				str.append(y);
			}
			str.append('\n');
		}
		
		str.append("Path cost: " + pathCost + '\n');
		str.append("Number of nodes expanded: " + numNodesExpanded);
		
		return str.toString();
		
	}

}
