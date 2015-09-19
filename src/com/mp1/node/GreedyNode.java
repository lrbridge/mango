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
//		System.out.println("comparing " + this.state.x + "," + this.state.y + "(" + this.expectedDistanceToGo + ") && " + o.state.x + "," + o.state.y + "(" + o.expectedDistanceToGo + ")");
		if(this.expectedDistanceToGo < o.expectedDistanceToGo) { //System.out.println("distless");
			return -1;
		}
		else if(this.expectedDistanceToGo > o.expectedDistanceToGo) { //System.out.println("distmore");
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
