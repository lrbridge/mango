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

		int ghostStartX = ghostInitialLocation.getState().x;
		int ghostStartY = ghostInitialLocation.getState().y;
		
		int wallToLeftOfGhost = ghostStartY;
		while(this.maze[ghostStartX][wallToLeftOfGhost] == 'g' || this.maze[ghostStartX][wallToLeftOfGhost] == 'G') {
			wallToLeftOfGhost--;
		}
		int wallToRightOfGhost = ghostStartY;
		while(this.maze[ghostStartX][wallToRightOfGhost] == 'g' || this.maze[ghostStartX][wallToRightOfGhost] == 'G') {
			wallToRightOfGhost++;
		}
		
		this.ghost = new Ghost(ghostStartX, ghostStartY, wallToLeftOfGhost, wallToRightOfGhost);
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
			int ghostY = this.ghost.getGhostY(parent);
			DIRECTION ghostDirection = this.ghost.getGhostDirection(parent);
			newNode = new AStarNode(new State(x, y, ghostX, ghostY, ghostDirection), parent, distanceSoFar);
		}
		else { // need this check just for the first time (when finding the ghost node)
			newNode = new AStarNode(new State(x, y), parent, distanceSoFar);
		}

		if(this.heuristicValues != null) { // check for end node (before heuristics)
			newNode.setExpectedDistanceToGo(this.heuristicValues[x][y]);
		}
		return newNode;
	}

}
