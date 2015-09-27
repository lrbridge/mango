package com.mp1.search;

import java.util.ArrayList;
import java.util.List;

import com.mp1.ghost.Ghost;
import com.mp1.ghost.HorizontalGhost;
import com.mp1.movement.DIRECTION;
import com.mp1.node.AStarNode;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.search.base.Coordinate;

public class AStarWithGhostSearch extends AStarSearch {
	
    private List<Ghost> ghosts;
    
	public AStarWithGhostSearch(String filename) {
		super(filename);
		
		ghosts = new ArrayList<Ghost>();
		
		Ghost ghost = new HorizontalGhost('G', this.maze);
		if(ghost.exists()) {
			ghosts.add(ghost);
		}		
//		
//		Ghost fGhost = new FastGhost('F', this.maze);
//		if(fGhost != null) {
//			ghosts.add(fGhost);
//		}		
		
		Ghost aGhost = new HorizontalGhost('A', this.maze);
		if(aGhost.exists()) {
			ghosts.add(aGhost);
		}
//
//        Ghost vGhost = new VerticalGhost('V', this.maze);
//        if(vGhost != null) {
//			ghosts.add(vGhost);
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

		List<Coordinate> ghostCoordinates = new ArrayList<Coordinate>();
		
		int ghostIndex = 0;
		while(ghostIndex < this.ghosts.size()) {
			
			Ghost ghost = this.ghosts.get(ghostIndex);
			if(parent == null) {
				ghostCoordinates.add(ghost.getCoordinate(null));
			}
			else {
				Coordinate lastGhostCoordinates = parent.getState().ghostCoordinates.get(ghostIndex);
				ghostCoordinates.add(ghost.getCoordinate(lastGhostCoordinates));
			}
			
			ghostIndex++;
		}
		
		newNode = new AStarNode(new State(x, y, ghostCoordinates), parent, distanceSoFar);

        if(this.goalNode != null) { // if the goalNode is set (when we are making the goalNode at the beginning, we need this check)
            newNode.setExpectedDistanceToGo(this.heuristic.computeHeuristic(newNode.getState(), this.goalNode.getState()));
        }
        
		return newNode;
	}

}
