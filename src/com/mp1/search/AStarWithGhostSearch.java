package com.mp1.search;

import com.mp1.movement.DIRECTION;
import com.mp1.node.AStarNode;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.search.base.Ghost;

public class AStarWithGhostSearch extends AStarSearch {

	private Ghost ghost; 
	
	public AStarWithGhostSearch(String filename) {
		super(filename);
		
		Node ghostInitialLocation = this.findNode('G');
		System.out.println("INITIAL LOC " + ghostInitialLocation.getState().x + " " + ghostInitialLocation.getState().y);
		int ghostStartX = ghostInitialLocation.getState().x;
		int ghostStartY = ghostInitialLocation.getState().y;
		
		int ghostLeftY = ghostStartY;
		while(this.maze[ghostStartX][ghostLeftY] == 'g' || this.maze[ghostStartX][ghostLeftY] == 'G') {
			ghostLeftY--;
		}
		int ghostRightY = ghostStartY;
		while(this.maze[ghostStartX][ghostRightY] == 'g' || this.maze[ghostStartX][ghostRightY] == 'G') {
			ghostRightY++;
		}
		
		this.ghost = new Ghost(ghostStartX, ghostStartY, ghostLeftY, ghostRightY);
	}

	@Override
	protected Node makeNode(int x, int y, DIRECTION directionFacing, Node parent) {
		int distanceSoFar = 0;
		if(parent != null) {
			// add 1 to the parent's distance since all steps are equal cost (1)
			distanceSoFar = parent.getDistanceSoFar() + 1;
		}
		
		AStarNode newNode;
		if(this.ghost != null) {
			int ghostX = this.ghost.getGhostX();
			int ghostY = this.ghost.getGhostY(distanceSoFar);
			newNode = new AStarNode(new State(x, y, ghostX, ghostY), parent, distanceSoFar);
		}
		else { // need this check just for the first time
			newNode = new AStarNode(new State(x, y), parent, distanceSoFar);
		}

		if(this.heuristicValues != null) { // check for end node (before heuristics)
			newNode.setExpectedDistanceToGo(this.heuristicValues[x][y]);
		}
		return newNode;
	}

}
