package com.cyhex.bearchomat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LcdDisplay {

    private static Logger LOG = LoggerFactory.getLogger(LcdDisplay.class);

    public void print(HttpPingThread.Result result) {
        LOG.info("{} {} {}", result.getResponseCode(), result.getResponseMessage(), result.getTime());
    }
}
