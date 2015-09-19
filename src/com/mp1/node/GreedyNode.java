package com.mp1.node;

public class GreedyNode extends Node implements Comparable<GreedyNode> {

	public int expectedDistanceToGo;
	
	public GreedyNode(State state, Node parent, int distanceSoFar) {
		super(state, parent, distanceSoFar);
	}

	/*
	 * When comparing two nodes (used in the priority queue), 
	 */
	@Override
	public int compareTo(GreedyNode o) {
		if(this.expectedDistanceToGo < o.expectedDistanceToGo) {
			return -1;
		}
		else if(this.expectedDistanceToGo > o.expectedDistanceToGo) {
			return 1;
		}
		else { // if tied
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

	public void setExpectedDistanceToGo(int expectedDistanceToGo) {
		this.expectedDistanceToGo = expectedDistanceToGo;
	}

}
