package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ConnPool {

    private List<Conn> connList = new ArrayList<>();

    private Semaphore semaphore = new Semaphore(3);

    public ConnPool() {
        connList.add(new Conn());
        connList.add(new Conn());
        connList.add(new Conn());
    }

    public Conn getConn() throws InterruptedException {
        semaphore.acquire();
        Conn c = null;
        synchronized (connList) {
            c = connList.remove(0);
        }
        System.out.println(Thread.currentThread().getName() + " get conn " + c);
        return c;
    }

    public void release(Conn c) {
        connList.add(c);
        System.out.println(Thread.currentThread().getName() + " realse a conn " + c);
        semaphore.release();
    }

    public static void main(String[] args) {

        final ConnPool connPool = new ConnPool();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Conn c = connPool.getConn();
                    Thread.sleep(3000);
                    connPool.release(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for(int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        connPool.getConn();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private class Conn {

    }
}
