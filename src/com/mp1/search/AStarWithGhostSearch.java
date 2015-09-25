package com.mp1.search;

import com.mp1.ghost.FastGhost;
import com.mp1.ghost.Ghost;
import com.mp1.ghost.HorizontalGhost;
import com.mp1.movement.DIRECTION;
import com.mp1.node.AStarNode;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.search.base.Coordinate;

public class AStarWithGhostSearch extends AStarSearch {

	private Ghost ghost;
    private FastGhost fGhost;
    private Ghost AGhost;
	
	public AStarWithGhostSearch(String filename) {
		super(filename);
		
		Coordinate ghostXY = this.maze.findNode('G');
		Node ghostInitialLocation = this.makeNode(ghostXY.x, ghostXY.y, DIRECTION.RIGHT, null, "no-action");
		
		int ghostStartX = ghostInitialLocation.getState().x;
		int ghostStartY = ghostInitialLocation.getState().y;
		
		int wallToLeftOfGhost = ghostStartY;
		while(this.maze.get(ghostStartX, wallToLeftOfGhost) == 'g' || this.maze.get(ghostStartX, wallToLeftOfGhost) == 'G') {
			wallToLeftOfGhost--;
		}
		int wallToRightOfGhost = ghostStartY;
		while(this.maze.get(ghostStartX, wallToRightOfGhost) == 'g' || this.maze.get(ghostStartX, wallToRightOfGhost) == 'G') {
            wallToRightOfGhost++;
        }
		this.ghost = new HorizontalGhost(ghostStartX, ghostStartY, wallToLeftOfGhost, wallToRightOfGhost);


//        Node fGhostInitialLocation = this.findNode('F');
//
//        if(fGhostInitialLocation != null) {
//            int fGhostStartX = fGhostInitialLocation.getState().x;
//            int fGhostStartY = fGhostInitialLocation.getState().y;
//
//            int fWallToLeftOfGhost = fGhostStartY;
//            while (this.maze[fGhostStartX][fWallToLeftOfGhost] == 'f' || this.maze[fGhostStartX][fWallToLeftOfGhost] == 'F') {
//                fWallToLeftOfGhost--;
//            }
//            int fWallToRightOfGhost = fGhostStartY;
//            while (this.maze[fGhostStartX][fWallToRightOfGhost] == 'f' || this.maze[fGhostStartX][fWallToRightOfGhost] == 'F') {
//                fWallToRightOfGhost++;
//            }
//            this.fGhost = new FastGhost(fGhostStartX, fGhostStartY, fWallToLeftOfGhost, fWallToRightOfGhost);
//        }
//        Node AGhostInitialLocation = this.findNode('A');
//
//        if(AGhostInitialLocation != null) {
//            int AGhostStartX = AGhostInitialLocation.getState().x;
//            int AGhostStartY = AGhostInitialLocation.getState().y;
//
//            int AWallToLeftOfGhost = AGhostStartY;
//            while (this.maze[AGhostStartX][AWallToLeftOfGhost] == 'g' || this.maze[AGhostStartX][AWallToLeftOfGhost] == 'G') {
//                AWallToLeftOfGhost--;
//            }
//            int AWallToRightOfGhost = AGhostStartY;
//            while (this.maze[AGhostStartX][AWallToRightOfGhost] == 'g' || this.maze[AGhostStartX][AWallToRightOfGhost] == 'G') {
//                AWallToRightOfGhost++;
//            }
//
//            this.AGhost = new HorizontalGhost(AGhostStartX, AGhostStartY, AWallToLeftOfGhost, AWallToRightOfGhost);
//        }

	}

	@Override
	protected Node makeNode(int x, int y, DIRECTION directionFacing, Node parent, String action) {
		int distanceSoFar = 0;
		if(parent != null) {
			// add 1 to the parent's distance since all steps are equal cost (1)
			distanceSoFar = parent.getDistanceSoFar() + 1;
		}
		
		AStarNode newNode;
        if(this.ghost != null && this.fGhost != null && this.AGhost != null) {
            int ghostX = this.ghost.getX(parent);
            int ghostY = this.ghost.getY(parent);
            DIRECTION ghostDirection = this.ghost.getDirection(parent);

            int fGhostX = this.fGhost.getX(parent);
            int fGhostY = this.fGhost.getY(parent);
            DIRECTION fGhostDirection = this.fGhost.getDirection(parent);

            int AGhostX = this.AGhost.getX(parent);
            int AGhostY = this.AGhost.getY(parent);
            DIRECTION AGhostDirection = this.AGhost.getDirection(parent);

            newNode = new AStarNode(new State(x, y, ghostX, ghostY, ghostDirection, fGhostX, fGhostY, fGhostDirection,
                    AGhostX, AGhostY, AGhostDirection), parent, distanceSoFar);
        }
		else if(this.ghost != null && this.fGhost != null && this.AGhost == null) {
			int ghostX = this.ghost.getX(parent);
			int ghostY = this.ghost.getY(parent);
			DIRECTION ghostDirection = this.ghost.getDirection(parent);

            int fGhostX = this.fGhost.getX(parent);
            int fGhostY = this.fGhost.getY(parent);
            DIRECTION fGhostDirection = this.fGhost.getDirection(parent);

			newNode = new AStarNode(new State(x, y, ghostX, ghostY, ghostDirection, fGhostX, fGhostY, fGhostDirection), parent, distanceSoFar);
		}
        else if (this.ghost != null && this.fGhost == null) {
            int ghostX = this.ghost.getX(parent);
            int ghostY = this.ghost.getY(parent);
            DIRECTION ghostDirection = this.ghost.getDirection(parent);

            newNode = new AStarNode(new State(x, y, ghostX, ghostY, ghostDirection), parent, distanceSoFar);
        }
		else { // need this check just for the first time (when finding the ghost node)
			newNode = new AStarNode(new State(x, y), parent, distanceSoFar);
		}

        if(this.goalNode != null) { // if the goalNode is set (when we are making the goalNode at the beginning, we need this check)
            newNode.setExpectedDistanceToGo(this.heuristic.computeHeuristic(newNode.getState(), this.goalNode.getState()));
        }
        
		return newNode;
	}

}
