package com.mp1.search;

import com.mp1.node.AStarNode;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.search.base.InformedSearch;


public class AStarSearch extends InformedSearch {
	
	public AStarSearch(String filename) {
		super(filename);
	}
	
	protected Node makeNode(int x, int y, Node parent) {
		int distanceSoFar = 0;
		if(parent != null) {
			// add 1 to the parent's distance since all steps are equal cost (1)
			distanceSoFar = parent.getDistanceSoFar() + 1;
		}
		
		AStarNode newNode = new AStarNode(new State(x, y), parent, distanceSoFar);
		if(this.heuristicValues != null) { // check for end node (before heuristics)
			newNode.setExpectedDistanceToGo(this.heuristicValues[x][y]);
		}
		return newNode;
	}

	@Override
	protected void replaceNodeOnFrontierIfBetter(Node c) {
		AStarNode child = (AStarNode) c;
		System.out.println(" A*FRONTIER:  for " + c.getState().x + " " + c.getState().y);
		
		Node nodeToSwap = null;
		
		for(Node plainnode : this.frontier) {
			AStarNode node = (AStarNode) plainnode;
//			System.out.println(node.getState().x + ", " + node.getState().y + " - " + node.getDistanceSoFar() + " " + node.expectedDistanceToGo);
			
			if(node.equals(child)) {  // nodes = if states = by our definition
//				System.out.println("EQUAL vs " + child.getDistanceSoFar() + " " + child.expectedDistanceToGo);
				
				int nodeVsChild = node.compareTo(child);
				if(nodeVsChild > 0) { // if this new child node has a lower cost than the frontier node
					System.out.println("SWAP IT "+ child.getDistanceSoFar() + " " + child.expectedDistanceToGo + " vs " + node.getDistanceSoFar() + " " + node.expectedDistanceToGo);
					nodeToSwap = node;
					break;
				}
				
			}
		}
		
		if(nodeToSwap != null) { // need to put this down here to avoid add/removing in loop (concurrent modification exception)
			
//			System.out.println("FRONT B4");
//			for(Node x : this.frontier) {
//				AStarNode node = (AStarNode) x;
//				System.out.println(node.getState().x + ", " + node.getState().y + " - " + node.getDistanceSoFar() + " " + node.expectedDistanceToGo);
//			}
			this.frontier.remove(nodeToSwap);
			this.frontier.add(child);
			
//			System.out.println("FRONT AFT");
//			for(Node x : this.frontier) {
//				AStarNode node = (AStarNode) x;
//				System.out.println(node.getState().x + ", " + node.getState().y + " - " + node.getDistanceSoFar() + " " + node.expectedDistanceToGo);
//			}
		}
	}
	
}
