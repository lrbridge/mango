package com.mp1;

public class AStarNode extends Node implements Comparable<AStarNode> {

	private int expectedDistanceToGo;
		
	public AStarNode(State state, Node parent, int distanceSoFar) {
		super(state, parent, distanceSoFar);
	}

	/*
	 * When comparing two nodes (used in the priority queue), 
	 */
	@Override
	public int compareTo(AStarNode o) {
		
		int myExpectedTotalDistance = this.getDistanceSoFar() + this.expectedDistanceToGo;
		int theirExpectedTotalDistance = o.getDistanceSoFar() + o.expectedDistanceToGo;
		
//		System.out.println("comparing " + this.state.x + "," + this.state.y + "(" + this.expectedDistanceToGo + ") && " + o.state.x + "," + o.state.y + "(" + o.expectedDistanceToGo + ")");
		if(myExpectedTotalDistance < theirExpectedTotalDistance) { //System.out.println("distless");
			return -1;
		}
		else if(myExpectedTotalDistance > theirExpectedTotalDistance) { //System.out.println("distmore");
			return 1;
		}
		else { // if tied
			// always go UP over DOWN
			if(this.state.x < o.state.x) { //System.out.println("up");
				return -1;
			}
			else if(this.state.x > o.state.x) { //System.out.println("down");
				return 1;
			}
			
			// if still tied, then LEFT over RIGHT
			if(this.state.y < o.state.y) { //System.out.println("left");
				return -1;
			}
			else if(this.state.y > o.state.y) { //System.out.println("right");
				return 1;
			}
			
			// if still tied, go with equal
			return 0;
		}
	}

	public void setExpectedDistanceToGo(int expectedDistanceToGo) {
		this.expectedDistanceToGo = expectedDistanceToGo;
	}
}
