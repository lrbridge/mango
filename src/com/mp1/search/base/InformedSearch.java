package com.mp1.search.base;

import java.util.Iterator;
import java.util.PriorityQueue;

import com.mp1.heuristic.Heuristic;
import com.mp1.heuristic.ManhattanDistanceHeuristic;
import com.mp1.movement.DIRECTION;
import com.mp1.movement.Movement;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.solution.MazeSolution;

public abstract class InformedSearch extends Search {

	protected Movement movement;
	
	protected int[][] heuristicValues;
	
	protected PriorityQueue<Node> frontier;
	
    private Heuristic heuristic;
    
    private Ghost ghost;
	
    private class Pair {
    	public int x;
    	public int y;
    }
    
	public InformedSearch(String filename, Movement movement) {
		super(filename);
		
		this.frontier = new PriorityQueue<Node>();
		this.movement = movement;
		
        this.heuristic = new ManhattanDistanceHeuristic();
                
        Node ghostStart = this.findNode('G');
        if(ghostStart == null) {
        	this.ghost = null;
        }
        else {
            int ghostStartX = ghostStart.getState().x;
            int ghostStartY = ghostStart.getState().y;
            
            int ghostLeftY = ghostStartY;
            while(this.maze[ghostStartX][ghostLeftY] == 'g' || this.maze[ghostStartX][ghostLeftY] == 'G') {
            	ghostLeftY--; // find the farthest left point
            }
            int ghostRightY = ghostStartY;
            while(this.maze[ghostStartX][ghostRightY] == 'g' || this.maze[ghostStartX][ghostRightY] == 'G') {
            	ghostRightY++; // find the farthest right point
            }
            
            this.ghost = new Ghost(ghostStartX, ghostStartY, ghostLeftY, ghostRightY);
        }
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

				if(this.collidesWithGhost(child)) { //|| this.crossesGhost()) {
					// don't put it anywhere
				}
				else if (this.isInMaze(child) && this.isNotAWall(child)
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
	
	private boolean collidesWithGhost(Node child) {
		if(this.ghost == null) {
			return false; // no ghost
		}
		
		if(child.getState().x == this.ghost.getGhostX() && 
				child.getState().y == this.ghost.getGhostY(1)) {
			return true;
		}
		return false;
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
				
                int value = heuristic.computeHeuristic(goalNode.getState(), i, j, 0, 0);
				heuristicValues[i][j] = value;
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
