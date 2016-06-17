package com.cyhex.bearchomat;

public class App {

    public static void main(String[] args) throws InterruptedException {
        App app = new App();
        app.run();
    }
    private LedState ledState = new LedState();
    private LedThread ledThread = new LedThread(10, ledState);
    private LcdDisplay lcdDisplay = new LcdDisplay();
    private HttpPingThread httpPingThread = new HttpPingThread(2000, "https://www.bearch.de/api/ping", ledState, lcdDisplay);

    private void run() throws InterruptedException {
        httpPingThread.start();
        ledThread.start();

        while (!Thread.currentThread().isInterrupted()) {
           Thread.sleep(100);
        }
    }

}
