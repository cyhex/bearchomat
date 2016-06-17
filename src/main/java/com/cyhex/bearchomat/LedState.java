package com.cyhex.bearchomat;

public class LedState {

    enum State {OK, ERR}

    private State state = State.OK;

    public synchronized State getState() {
        return state;
    }

    public synchronized void setState(State state) {
        this.state = state;
    }
}
