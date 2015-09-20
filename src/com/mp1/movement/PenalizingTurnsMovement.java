package com.mp1.movement;

public class PenalizingTurnsMovement implements PenalizeMovement {

	public String[] actions = {"TURN LEFT", "TURN RIGHT", "FORWARD"};
	
	public String[] getActions() {
		return actions;
	}
	
	public int getPChildX(int x, DIRECTION directionFacing) {

        if (directionFacing == DIRECTION.UP)
            x -= 2;
        else if (directionFacing == DIRECTION.DOWN)
            x += 2;

		return x;
	}
	
	public int getPChildY(int y, DIRECTION directionFacing) {

        if (directionFacing == DIRECTION.LEFT)
            y -= 2;
        else if (directionFacing == DIRECTION.RIGHT)
            y += 2;
		
		return y;
	}
	

    /* this method is to update the direction of node after change */
    public DIRECTION getNewChildDirectionFacing(DIRECTION directionFacing, String action) {
        switch (action) {
            case "TURN LEFT":
            {
                if(directionFacing == DIRECTION.LEFT)
                    directionFacing = DIRECTION.DOWN;
                else if (directionFacing == DIRECTION.UP)
                    directionFacing = DIRECTION.LEFT;
                else if (directionFacing == DIRECTION.RIGHT)
                    directionFacing = DIRECTION.UP;
                else
                    directionFacing = DIRECTION.RIGHT;
                break;
            }
            case "TURN RIGHT":
            {
                if(directionFacing == DIRECTION.LEFT)
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

    /* this method is to find the direction of node before change */
    public DIRECTION getChildDirectionFacing(DIRECTION directionFacing, String action) {
        return directionFacing;
    }
}
