package com.mp1.movement;

/**
 * Created by MyPrecious on 9/20/15.
 */
public interface PenalizeMovement {

    public String[] getActions();

    public DIRECTION getNewChildDirectionFacing(DIRECTION directionFacing, String action);

    public int getPChildX(int x, DIRECTION directionFacing);

    public int getPChildY(int y, DIRECTION directionFacing);

    public DIRECTION getChildDirectionFacing(DIRECTION directionFacing, String action);

}
