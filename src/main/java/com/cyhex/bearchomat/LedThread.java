package com.cyhex.bearchomat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LedThread extends AbstractThread {

    private final LedState ledState ;
    private static Logger LOG = LoggerFactory.getLogger(LedThread.class);

    public LedThread(int waitDelay, LedState ledState) {
        super(waitDelay);
        this.ledState = ledState;
    }

    protected void runForever() throws InterruptedException {
        if(ledState.getState().equals(LedState.State.ERR)){
            LOG.info("ON");
            sleep(100);
            LOG.info("OFF");
        }

    }
}
