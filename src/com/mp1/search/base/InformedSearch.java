package com.mp1.search.base;

import java.util.Iterator;
import java.util.PriorityQueue;

import com.mp1.movement.DIRECTION;
import com.mp1.movement.Movement;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.solution.MazeSolution;

public abstract class InformedSearch extends Search {

	protected Movement movement;
	
	protected int[][] heuristicValues;
	
	protected PriorityQueue<Node> frontier;
	
	public InformedSearch(String filename, Movement movement) {
		super(filename);
		
		this.frontier = new PriorityQueue<Node>();
		this.movement = movement;
	}

	public MazeSolution solve() {

		this.heuristicValues = this.computeHeuristics();

		Node firstNode = this.findNode('P');
		this.addNodeToFrontier(firstNode);
		
		while (!this.isFrontierEmpty()) {

			Node node = this.popNodeOffFrontier();
			this.numNodesExpanded++;
			this.explored.add(node);

			if(this.isGoal(node)) {
				return this.makeSolution(node);
			}
			
			for (String action : this.movement.getActions()) {

				Node child = this.getChildNode(node, action);

				if (this.isInMaze(child) && this.isNotAWall(child)
						&& !this.explored.contains(child)
						&& !this.doesFrontierContain(child)) {
					// explored & frontier 'contains' checks look for when states are equal
					// because Node is equal when State is equal and State is equal when x & y are same

					this.addNodeToFrontier(child);
				}
				else if(this.doesFrontierContain(child)) {
					this.replaceNodeOnFrontierIfBetter(child);                               
                                    
				}

			}

		}

		return null; // fail if no solution is found
	}
	
	private Node getChildNode(Node node, String action) {
		State state = node.getState();
		int x = this.movement.getChildX(state.x, action);
		int y = this.movement.getChildY(state.y, action);
		DIRECTION directionFacing = this.movement.getChildDirectionFacing(state.directionFacing, action);
		return this.makeNode(x, y, directionFacing, node);
	}

	protected void replaceNodeOnFrontierIfBetter(Node child) {

      Iterator<Node> frontierIterator = this.frontier.iterator();

      boolean addChild = true;
      while (frontierIterator.hasNext()) {

          Node nodeToCheck = frontierIterator.next();

          if(nodeToCheck.equals(child)) { // nodes = if states = by our definition
        	  
        	  if (nodeToCheck.getDistanceSoFar() <= child.getDistanceSoFar()) {
        		  // already has a better one
        		  addChild = false;
        	  }
        	  
        	  else if (nodeToCheck.getDistanceSoFar() >= child.getDistanceSoFar()) {
            	  // if my node has a lower PATH COST SO FAR 

        		  addChild = true;

	              //This removes the last item from the iterator, which
	              //would be the node that is equal to the child
	              //but has a greater current distance
	              frontierIterator.remove();
        	  }
          }
      }

      if (addChild) {
          this.addNodeToFrontier(child);
      }
		
	};

	private int[][] computeHeuristics() {
		int[][] heuristicValues = new int[this.maze.length][this.maze[0].length];
		Node goalNode = this.findNode('.');

		for(int i=0; i<this.maze.length; i++) {
			for(int j=0; j<this.maze[i].length; j++) {
				
				State state = goalNode.getState();
				int xDifference = Math.abs(i - state.x);
				int yDifference = Math.abs(j - state.y);

				int manhattanDistance = xDifference + yDifference;
				
				heuristicValues[i][j] = manhattanDistance;
			}
		}
		
		return heuristicValues;
	}

	@Override
	protected boolean doesFrontierContain(Node child) {
		return this.frontier.contains(child);
	}

	@Override
	protected Node popNodeOffFrontier() {
		return this.frontier.poll();
	}

	@Override
	protected boolean isFrontierEmpty() {
		return this.frontier.isEmpty();
	}

	@Override
	protected void addNodeToFrontier(Node node) {
		if(node != null) {
			this.frontier.add(node);
		}
	}
}
