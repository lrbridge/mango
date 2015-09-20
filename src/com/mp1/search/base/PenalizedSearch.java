package com.mp1.search.base;

/**
 * Created by MyPrecious on 9/20/15.
 */
import java.util.Iterator;
import java.util.PriorityQueue;

import com.mp1.movement.DIRECTION;
import com.mp1.movement.PenalizeMovement;
import com.mp1.node.Node;
import com.mp1.node.PenalizeNode;
import com.mp1.node.State;
import com.mp1.solution.MazeSolution;

public abstract class PenalizedSearch extends PSearch {

    protected PenalizeMovement movement;

    protected int[][] heuristicValues;

    protected PriorityQueue<Node> frontier;

    protected int forwardCode;

    protected int turnCost;

    public PenalizedSearch(String filename, PenalizeMovement movement, int forwardCode, int turnCost) {
        super(filename);

        this.frontier = new PriorityQueue<Node>();
        this.movement = movement;
        this.forwardCode = forwardCode;
        this.turnCost = turnCost;
    }

    public MazeSolution solve() {

        this.heuristicValues = this.computeHeuristics();

        Node firstNode = this.findPNode('P', this.forwardCode, this.turnCost);
        this.addNodeToFrontier(firstNode);

        while (!this.isFrontierEmpty()) {

//        	System.out.println("FRONTIER: ");
//        	for(Node x : this.frontier) {
//        		PenalizeNode n = (PenalizeNode) x;
//        		System.out.println(n.getState().x + " " + n.getState().y + " " + n.getState().directionFacing + " -> " + n.getDistanceSoFar() + " + " + n.expectedDistanceToGo);
//        	}
        	
            Node node = this.popNodeOffFrontier();
//            System.out.println("EXPAND " + node.getState().x + " " + node.getState().y + " " + node.getState().directionFacing);
			this.numNodesExpanded++;
            this.explored.add(node);

            if (this.isGoal(node)) {
                return this.makeSolution(node);
            }

            for (String action : this.movement.getActions()) {

                Node child = this.getChildNode(node, action);

                if (this.isInMaze(child) && this.isNotAWall(child)
                        && !this.explored.contains(child)
                        && !this.doesFrontierContain(child)) {
                    // explored & frontier 'contains' checks look for when states are equal
                    // because Node is equal when State is equal and State is equal when x & y are same

                    this.addNodeToFrontier(child);
                } else if (this.doesFrontierContain(child)) {
                    this.replaceNodeOnFrontierIfBetter(child);

                }

            }

        }

        return null; // fail if no solution is found
    }

    private Node getChildNode(Node node, String action) {    	
        State state = node.getState();
        DIRECTION directionFacing = this.movement.getChildDirectionFacing(state.directionFacing, action);
        int x = state.x;
        int y = state.y;

        int new_x = state.x;
        int new_y = state.y;
        /* call different method based on the action */
        if(action == "TURN LEFT" || action == "TURN RIGHT") {
            directionFacing = this.movement.getNewChildDirectionFacing(state.directionFacing, action);
            this.numOfTurns++;
        }
        else {
            new_x = this.movement.getPChildX(state.x, directionFacing);
            new_y = this.movement.getPChildY(state.y, directionFacing);
        }
        
        
        Node newnode = this.makePNode(x, y, directionFacing, node, new_x, new_y, this.forwardCode, this.turnCost);
//        System.out.println(newnode.getState().x + " " + newnode.getState().y + " " + newnode.getState().directionFacing);
        
        return newnode;
    }

    protected void replaceNodeOnFrontierIfBetter(Node child) {

        Iterator<Node> frontierIterator = this.frontier.iterator();

        boolean addChild = true;
        while (frontierIterator.hasNext()) {

            Node nodeToCheck = frontierIterator.next();

            if (nodeToCheck.equals(child)) { // nodes = if states = by our definition

                if (nodeToCheck.getDistanceSoFar() <= child.getDistanceSoFar()) {
                    // already has a better one
                    addChild = false;
                } else if (nodeToCheck.getDistanceSoFar() >= child.getDistanceSoFar()) {
                    // if my node has a lower PATH COST SO FAR

                    addChild = true;

                    //This removes the last item from the iterator, which
                    //would be the node that is equal to the child
                    //but has a greater current distance
                    frontierIterator.remove();
                }
            }
        }

        if (addChild) {
            this.addNodeToFrontier(child);
        }

    }

    ;

    private int[][] computeHeuristics() {
        int[][] heuristicValues = new int[this.maze.length][this.maze[0].length];
        Node goalNode = this.findPNode('.', this.forwardCode, this.turnCost);

        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze[i].length; j++) {

                State state = goalNode.getState();
                int xDifference = Math.abs(i - state.x);// * this.forwardCode;
                int yDifference = Math.abs(j - state.y);// * this.forwardCode;

                int penalizeDistance = xDifference + yDifference;// + (this.numOfTurns * this.turnCost);

//                System.out.println("the distance = " + penalizeDistance);
                heuristicValues[i][j] = penalizeDistance;
            }
        }

        return heuristicValues;
    }

    @Override
    protected boolean doesFrontierContain(Node child) {
        return this.frontier.contains(child);
    }

    @Override
    protected Node popNodeOffFrontier() {
        return this.frontier.poll();
    }

    @Override
    protected boolean isFrontierEmpty() {
        return this.frontier.isEmpty();
    }

    @Override
    protected void addNodeToFrontier(Node node) {
        if (node != null) {
            this.frontier.add(node);
        }
    }
}