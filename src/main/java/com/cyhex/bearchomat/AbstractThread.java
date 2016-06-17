package com.cyhex.bearchomat;

public abstract class AbstractThread extends Thread {

    protected final int waitDelay;

    public AbstractThread(int waitDelay) {
        this.waitDelay = waitDelay;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                runForever();
                synchronized (this){
                    wait(waitDelay);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    protected abstract void runForever() throws InterruptedException;
}
