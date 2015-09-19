package com.mp1.search;

import com.mp1.node.AStarNode;
import com.mp1.node.GreedyNode;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.search.base.InformedSearch;


public class GreedyBestFirstSearch extends InformedSearch {
	
	public GreedyBestFirstSearch(String filename) {
		super(filename);
	}
	
	protected Node makeNode(int x, int y, Node parent) {
		int distanceSoFar = 0;
		if(parent != null) {
			// add 1 to the parent's distance since all steps are equal cost (1)
			distanceSoFar = parent.getDistanceSoFar() + 1;
		}
		
		GreedyNode newNode = new GreedyNode(new State(x, y), parent, distanceSoFar);
		if(this.heuristicValues != null) { // check for end node (before heuristics)
			newNode.setExpectedDistanceToGo(this.heuristicValues[x][y]);
		}
		return newNode;
	}

	@Override
	protected void replaceNodeOnFrontierIfBetter(Node c) {
		GreedyNode child = (GreedyNode) c;
		System.out.println(" GBFSFRONTIER: ");
		for(Node plainnode : this.frontier) {
			GreedyNode node = (GreedyNode) plainnode;
			System.out.println(node.getState().x + ", " + node.getState().y + " - " + node.expectedDistanceToGo);
			
			if(node.equals(child)) {  // nodes = if states = by our definition
				System.out.println("EQUAL vs "+ child.expectedDistanceToGo);
				
				int nodeVsChild = node.compareTo(child);
				if(nodeVsChild < 0) { // if my node has a lower expected distance to go
					System.out.println("SWAP IT");
					this.frontier.remove(child);
					this.frontier.add(node);
				}
				
			}
		}
	}

}
