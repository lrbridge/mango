package com.mp1.search;

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

}
