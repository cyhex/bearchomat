package com.cyhex.bearchomat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpPingThread extends AbstractThread {

    private final String  url ;
    private static Logger LOG = LoggerFactory.getLogger(HttpPingThread.class);
    private final LedState ledState;
    private final LcdDisplay lcdDisplay;

    public HttpPingThread(int waitDelay, String url, LedState ledState, LcdDisplay lcdDisplay) {
        super(waitDelay);
        this.url = url;
        this.ledState = ledState;
        this.lcdDisplay = lcdDisplay;
    }


    protected void runForever() throws InterruptedException {
        Result result = fetchUrl();

        if (result.getResponseCode() != 200) {
            ledState.setState(LedState.State.ERR);
        } else {
            ledState.setState(LedState.State.OK);
        }

        lcdDisplay.print(result);

    }

    private Result fetchUrl() {
        try {
            long startTime = System.currentTimeMillis();
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();
            connection.disconnect();
            long elapsedTime = System.currentTimeMillis() - startTime;
            return new Result(elapsedTime, responseCode, responseMessage);
        } catch (IOException e) {
            return new Result(0, 500, e.getMessage());
        }

    }


    static class Result {
        long time;
        int responseCode;
        String responseMessage;

        public Result(long time, int responseCode, String responseMessage) {
            this.time = time;
            this.responseCode = responseCode;
            this.responseMessage = responseMessage;
        }

        public long getTime() {
            return time;
        }

        public int getResponseCode() {
            return responseCode;
        }

        public String getResponseMessage() {
            return responseMessage;
        }
    }
}
