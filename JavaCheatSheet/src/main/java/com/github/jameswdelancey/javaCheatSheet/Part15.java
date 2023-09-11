package com.github.jameswdelancey.javaCheatSheet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Part15 {
    public static final int NUM_MESSAGES = 1000;

    public static void main(String[] args) throws InterruptedException, ExecutionException, SocketException {
        DatagramSocket sender = new DatagramSocket();
        DatagramSocket receiver = new DatagramSocket();

        CompletableFuture<Void> senderFuture = CompletableFuture.runAsync(() -> {
            try {
                for (int i = 0; i < NUM_MESSAGES; i++) {
                    byte[] message = "Hello, World!".getBytes();
                    DatagramPacket packet = new DatagramPacket(message, message.length,
                            InetAddress.getByName("127.0.0.1"), receiver.getLocalPort());
                    sender.send(packet);
                    TimeUnit.MILLISECONDS.sleep(1);
                }
                sender.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<Void> receiverFuture = CompletableFuture.runAsync(() -> {
            try {
                byte[] buffer = new byte[256];
                int count = 0;
                for (int i = 0; i < NUM_MESSAGES; i++) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    receiver.receive(packet);
                    count += 1;
                    System.out.println("Received message " + count);
                }
                receiver.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(senderFuture, receiverFuture);
        combinedFuture.get(); // Wait for both sender and receiver to complete
    }
}
