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

import com.mp1.node.Node;
import com.mp1.node.State;

public class Maze {

	public char[][] maze;
	
	public Maze(String filename) {
		this.maze = this.readMazeInput(filename);
	}
	
	public void set(int x, int y, char c) {
		this.maze[x][y] = c;
	}
	
	public char get(int x, int y) {
		return this.maze[x][y];
	}
	
	public boolean isGoal(Node child) {
		State childState = child.getState();
		return this.maze[childState.x][childState.y] == '.';
	}

	public boolean isNotAWall(Node child) {
		State childState = child.getState();
		return this.maze[childState.x][childState.y] != '%';
	}

	public boolean isInMaze(Node child) {
		State childState = child.getState();
		return childState.y >= 0 && childState.y < this.maze[0].length
				&& childState.x >= 0 && childState.x < this.maze.length;
	}
	
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

	public Coordinate findNode(char characterToFind) {
		int x = 0;
		int y = 0;

		for (char[] row : this.maze) {
			for (char content : row) {
				if (content == characterToFind) {
					return new Coordinate(x, y);
				}
				y++;
			}
			x++;
			y = 0;
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(char[] x : this.maze) {
			for(char y : x) {
				str.append(y);
			}
			str.append('\n');
		}
		return str.toString();
	}
	
}
