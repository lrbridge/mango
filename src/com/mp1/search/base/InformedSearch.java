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
	
	protected PriorityQueue<Node> frontier;
	    
	protected Node goalNode;
    
	public InformedSearch(String filename, Movement movement) {
		super(filename);
		this.frontier = new PriorityQueue<Node>();
		this.movement = movement;
	}

	public MazeSolution solve() {

		Coordinate goalXY = this.maze.findNode('.');
		this.goalNode = this.makeNode(goalXY.x, goalXY.y, DIRECTION.RIGHT, null, "no-action");
				
		Coordinate startXY = this.maze.findNode('P');
		Node firstNode = this.makeNode(startXY.x, startXY.y, DIRECTION.RIGHT, null, "no-action");
		this.addNodeToFrontier(firstNode);
		
		while (!this.isFrontierEmpty()) {

			Node node = this.popNodeOffFrontier();
//			System.out.println("EXPANDING " + node.getState().x + " " + node.getState().y + " " + node.getState().directionFacing);
			this.numNodesExpanded++;
			this.explored.add(node);
			
			if(this.maze.isGoal(node)) {
				return new MazeSolution(this.maze, node, this.numNodesExpanded);
			}
						
			for (String action : this.movement.getActions()) {

				Node child = this.getChildNode(node, action);
//System.out.println(action+ "    CHILD: "+ child.getState().x + " " + child.getState().y + " " + child.getState().directionFacing);
				if(this.collidesWithGhost(child)) { 
					// don't put it anywhere
//					System.out.println("GHOST");
				}
				else if (this.maze.isInMaze(child) && this.maze.isNotAWall(child)
						&& !this.explored.contains(child)
						&& !this.doesFrontierContain(child)) {
					// explored & frontier 'contains' checks look for when states are equal
					// because Node is equal when State is equal and State is equal when x & y are same
//System.out.println("ADD");
					this.addNodeToFrontier(child);
				}
				else if(this.doesFrontierContain(child)) {//System.out.println("IF BETT");
					this.replaceNodeOnFrontierIfBetter(child);
				}

			}

		}

		return null; // fail if no solution is found
	}

	private boolean collidesWithGhost(Node child) {

		State childState = child.getState();
		State parentState = child.getParent().getState();

		if(childState.x == childState.ghostX) {
			if(childState.y == childState.ghostY) {
				return true;
			}
			else if(parentState.y == childState.ghostY && parentState.ghostY == childState.y) {
                return true;
            }
		}

        if(childState.x == childState.fGhostX) {
            if(childState.y == childState.fGhostY) {
                return true;
            }
            else if(parentState.y == childState.fGhostY && parentState.fGhostY == childState.y) {
                return true;
            }
        }

        if(childState.x == childState.AGhostX) {
            if(childState.y == childState.AGhostY) {
                return true;
            }
            else if(parentState.y == childState.AGhostY && parentState.AGhostY == childState.y) {
                return true;
            }
        }

        return false;
	}

	private Node getChildNode(Node node, String action) {
		State state = node.getState();
		int x = this.movement.getChildX(state, action);
		int y = this.movement.getChildY(state, action);
		DIRECTION directionFacing = this.movement.getChildDirectionFacing(state, action);
		return this.makeNode(x, y, directionFacing, node, action);
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
