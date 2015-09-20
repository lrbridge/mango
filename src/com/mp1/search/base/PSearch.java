package com.mp1.search.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.mp1.movement.DIRECTION;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.solution.MazeSolution;

public abstract class PSearch {

    protected char[][] maze;
    protected List<Node> explored;
    protected int numNodesExpanded;
    protected int numOfTurns;

    public PSearch(String filename) {
        this.maze = this.readMazeInput(filename);
        this.explored = new ArrayList<Node>();
        this.numNodesExpanded = 0;
        this.numOfTurns = 0;
    }

    /**
     * Solve the maze
     * @return
     */
    public abstract MazeSolution solve();

    protected abstract boolean doesFrontierContain(Node child);

    protected abstract Node popNodeOffFrontier();

    protected abstract boolean isFrontierEmpty();

    protected abstract void addNodeToFrontier(Node firstNode);


    protected abstract Node makePNode(int x, int y, DIRECTION directionFacing, Node node, int new_x, int new_y, int forwardCode, int turnCost);

    protected boolean isGoal(Node child) {
        State childState = child.getState();
        return this.maze[childState.x][childState.y] == '.';
    }

    protected boolean isNotAWall(Node child) {
        State childState = child.getState();
        return this.maze[childState.x][childState.y] != '%';
    }

    protected boolean isInMaze(Node child) {
        State childState = child.getState();
        return childState.y >= 0 && childState.y < this.maze[0].length
                && childState.x >= 0 && childState.x < this.maze.length;
    }

    /**
     * Summarize the solution - path cost, number of nodes expanded, pretty maze
     * path output
     *
     * @param maze
     * @param node
     * @param numNodesExpanded
     * @return solution
     */
    protected MazeSolution makeSolution(Node node) {
        int pathCost = node.getDistanceSoFar();

        while (this.maze[node.getState().x][node.getState().y] != 'P') {
            this.maze[node.getState().x][node.getState().y] = '.';
            node = node.getParent();
        }

        return new MazeSolution(this.maze, pathCost, this.numNodesExpanded);
    }



    /**
     * Returns the first node in the maze with the given character, or null
     * if it can't be found.
     * Specialized for penalizing search
     *
     * @return node or null
     */
    protected Node findPNode(char characterToFind, int forwardCode, int turnCost) {
        int x = 0;
        int y = 0;

        for (char[] row : this.maze) {
            for (char content : row) {
                if (content == characterToFind) {
                    return this.makePNode(x, y, DIRECTION.RIGHT, null, 0, 0, forwardCode, turnCost); // pacman initially facing right (and doesn't matter direction for part 1/part 3)
                }
                y++;
            }
            x++;
            y = 0;
        }
        return null;
    }


    /**
     * Reads the input file and returns a char[][] of the maze
     *
     * @param filename
     * @return char[][] maze
     */
    private char[][] readMazeInput(String filename) {
        List<char[]> maze = new ArrayList<char[]>();

        Path file = Paths.get(filename);
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                maze.add(line.toCharArray());
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        char[][] plainMaze = {};
        return maze.toArray(plainMaze);
    }

}