package com.mp1.search.base;

import java.util.Iterator;
import java.util.PriorityQueue;

import com.mp1.ghost.FastGhost;
import com.mp1.ghost.Ghost;
import com.mp1.ghost.HorizontalGhost;
import com.mp1.movement.DIRECTION;
import com.mp1.movement.Movement;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.solution.MazeSolution;

public abstract class InformedSearch extends Search {

	protected Movement movement;
	
	protected PriorityQueue<Node> frontier;
	    
	protected Node goalNode;
		
    private Ghost ghost;
    private FastGhost fGhost;
    private Ghost AGhost;
    
	public InformedSearch(String filename, Movement movement) {
		super(filename);
		this.frontier = new PriorityQueue<Node>();
		this.movement = movement;
	}

	public MazeSolution solve() {

		this.goalNode = this.findNode('.');
		this.findGhosts();
		
		Node firstNode = this.findNode('P');
		this.addNodeToFrontier(firstNode);
		
		while (!this.isFrontierEmpty()) {

			Node node = this.popNodeOffFrontier();
			System.out.println("EXPANDING " + node.getState().x + " " + node.getState().y + " " + node.getState().directionFacing);
			this.numNodesExpanded++;
			this.explored.add(node);
			
			if(this.isGoal(node)) {
				return this.makeSolution(node);
			}
						
			for (String action : this.movement.getActions()) {

				Node child = this.getChildNode(node, action);
//System.out.println(action+ "    CHILD: "+ child.getState().x + " " + child.getState().y + " " + child.getState().directionFacing);
				if(this.collidesWithGhost(child)) { 
					// don't put it anywhere
//					System.out.println("GHOST");
				}
				else if (this.isInMaze(child) && this.isNotAWall(child)
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

	private void findGhosts() {
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

            this.ghost = new HorizontalGhost(ghostStartX, ghostStartY, ghostLeftY, ghostRightY);
        }

        Node fGhostStart = this.findNode('F');
        if(fGhostStart == null) {
            this.fGhost = null;
        }

        else {
            int fGhostStartX = fGhostStart.getState().x;
            int fGhostStartY = fGhostStart.getState().y;

            int fGhostLeftY = fGhostStartY;
            while(this.maze[fGhostStartX][fGhostLeftY] == 'f' || this.maze[fGhostStartX][fGhostLeftY] == 'F') {
                fGhostLeftY--; // find the farthest left point
            }
            int fGhostRightY = fGhostStartY;
            while(this.maze[fGhostStartX][fGhostRightY] == 'f' || this.maze[fGhostStartX][fGhostRightY] == 'F') {
                fGhostRightY++; // find the farthest right point
            }

            this.ghost = new HorizontalGhost(fGhostStartX, fGhostStartY, fGhostLeftY, fGhostRightY);
        }

        Node AGhostStart = this.findNode('A');
        if(AGhostStart == null) {
            this.fGhost = null;
        }

        else {
            int AGhostStartX = AGhostStart.getState().x;
            int AGhostStartY = AGhostStart.getState().y;

            int AGhostLeftY = AGhostStartY;
            while(this.maze[AGhostStartX][AGhostLeftY] == 'f' || this.maze[AGhostStartX][AGhostLeftY] == 'F') {
                AGhostLeftY--; // find the farthest left point
            }
            int AGhostRightY = AGhostStartY;
            while(this.maze[AGhostStartX][AGhostRightY] == 'f' || this.maze[AGhostStartX][AGhostRightY] == 'F') {
                AGhostRightY++; // find the farthest right point
            }

            this.ghost = new HorizontalGhost(AGhostStartX, AGhostStartY, AGhostLeftY, AGhostRightY);
        }
	}

	private boolean collidesWithGhost(Node child) {
		if(this.ghost == null && this.fGhost == null) {
			return false; // no ghost
		}
		
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
