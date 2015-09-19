package com.mp1.node;

public class AStarNode extends Node implements Comparable<AStarNode> {

	public int expectedDistanceToGo;
		
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
		
		if(myExpectedTotalDistance < theirExpectedTotalDistance) {
			return -1;
		}
		else if(myExpectedTotalDistance > theirExpectedTotalDistance) {
			return 1;
		}
		else { // if tied, use just expected distance to go to resolve (greedy)
			
			if(this.expectedDistanceToGo < o.expectedDistanceToGo) {
				return -1;
			}
			else if(this.expectedDistanceToGo > o.expectedDistanceToGo) {
				return 1;
			}
			
			else { // if STILL tied, just some conventions
				// always go UP over DOWN
				if(this.state.x < o.state.x) {
					return -1;
				}
				else if(this.state.x > o.state.x) {
					return 1;
				}
				
				// if still tied, then LEFT over RIGHT
				if(this.state.y < o.state.y) {
					return -1;
				}
				else if(this.state.y > o.state.y) {
					return 1;
				}
				
				// if still tied, go with equal
				return 0;
			}
			
		}
	}

	public void setExpectedDistanceToGo(int expectedDistanceToGo) {
		this.expectedDistanceToGo = expectedDistanceToGo;
	}
}
