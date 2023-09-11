package com.github.jameswdelancey.javaCheatSheet;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Part14 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, world!");
        int steps = 10;
        Thread thread = new Thread(() -> {
            for (int i = 0; i < steps; i++) {
                System.out.println("Thread step: " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread done");
        });
        thread.start();
        System.out.println("Spawned thread");
        for (int i = 0; i < steps; i++) {
            System.out.println("Main step: " + i);
            TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println("Main done");
        thread.join();
        System.out.println("Joined thread: " + 42);
        System.out.println("Done");
    }
}

class Part14_2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, world!");
        AtomicInteger steps = new AtomicInteger(10);
        Thread thread = new Thread(() -> {
            for (int i = 1; i <= steps.get(); i++) {
                System.out.println("Thread step: " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread done");
        });
        thread.start();
        System.out.println("Spawned thread with a step count of " + steps + "!");
        for (int i = 0; i <= steps.get(); i++) {
            System.out.println("Main step: " + i);
            TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println("Main done");
        thread.join();
        System.out.println("Joined thread: " + 42);
        System.out.println("Done");
    }
}

class Part14_3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, world!");
        AtomicInteger steps = new AtomicInteger(10);
        Lock lock = new ReentrantLock();
        Thread thread = new Thread(() -> {
            while (steps.get() > 0) {
                lock.lock();
                try {
                    if (steps.get() > 0) {
                        System.out.println("Thread step: " + steps.getAndDecrement());
                        TimeUnit.MILLISECONDS.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
            System.out.println("Thread done");
        });
        thread.start();
        lock.lock();
        try {
            System.out.println("Spawned thread with a step count of " + steps.get() + "!");
        } finally {
            lock.unlock();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("Main step: " + i);
            TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println("Main done");
        thread.join();
        System.out.println("Joined thread: " + 42);
        System.out.println("Done");
    }
}

class Channel<T> {
    private BlockingQueue<T> queue;

    public Channel(int capacity) {
        queue = new ArrayBlockingQueue<>(capacity);
    }

    public void send(T item) throws InterruptedException {
        queue.put(item);
    }

    public T receive() throws InterruptedException {
        return queue.take();
    }
}

class Part14_4 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, world!");
        Channel<Integer> channel = new Channel<>(10); // Adjust the capacity as needed
        Thread thread = new Thread(() -> {
            try {
                int steps = channel.receive();
                for (int step = 1; step <= steps; step++) {
                    System.out.println("Thread step: " + step);
                    TimeUnit.MILLISECONDS.sleep(100);
                }
                System.out.println("Thread done");
                channel.send(42);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        channel.send(10);
        System.out.println("Spawned thread with a step count of " + 10 + "!");
        for (int i = 0; i < 10; i++) {
            System.out.println("Main step: " + i);
            TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println("Main done");
        thread.join();
        System.out.println("Joined thread: " + 42);
        System.out.println("Done");
    }
}
