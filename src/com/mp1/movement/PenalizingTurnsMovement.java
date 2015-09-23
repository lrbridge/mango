package com.mp1.movement;

import com.mp1.node.State;

public class PenalizingTurnsMovement implements Movement {

	public String[] actions = { "TURN LEFT", "TURN RIGHT", "FORWARD" };

	public String[] getActions() {
		return actions;
	}

	public DIRECTION getChildDirectionFacing(State state, String action) {
		DIRECTION directionFacing = state.directionFacing;

		switch (action) {
		case "TURN LEFT": {
			if (directionFacing == DIRECTION.LEFT)
				directionFacing = DIRECTION.DOWN;
			else if (directionFacing == DIRECTION.UP)
				directionFacing = DIRECTION.LEFT;
			else if (directionFacing == DIRECTION.RIGHT)
				directionFacing = DIRECTION.UP;
			else
				directionFacing = DIRECTION.RIGHT;
			break;
		}
		case "TURN RIGHT": {
			if (directionFacing == DIRECTION.LEFT)
				directionFacing = DIRECTION.UP;
			else if (directionFacing == DIRECTION.UP)
				directionFacing = DIRECTION.RIGHT;
			else if (directionFacing == DIRECTION.RIGHT)
				directionFacing = DIRECTION.DOWN;
			else
				directionFacing = DIRECTION.LEFT;
			break;
		}
		default:
			break;
		}
		return directionFacing;
	}

	@Override
	public int getChildX(State state, String action) {
		int x = state.x;

		if (action == "FORWARD") {
			if (state.directionFacing == DIRECTION.UP)
				x -= 1;
			else if (state.directionFacing == DIRECTION.DOWN)
				x += 1;
		}

		return x;
	}

	@Override
	public int getChildY(State state, String action) {
		int y = state.y;

		if (action == "FORWARD") {
			if (state.directionFacing == DIRECTION.LEFT)
				y -= 1;
			else if (state.directionFacing == DIRECTION.RIGHT)
				y += 1;
		}

		return y;
	}
}
