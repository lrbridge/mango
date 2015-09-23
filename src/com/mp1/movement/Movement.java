package com.mp1.movement;

import com.mp1.node.State;

public interface Movement {

    public String[] getActions();

    public int getChildX(State state, String action);

    public int getChildY(State state, String action);

    public DIRECTION getChildDirectionFacing(State state, String action);
}
