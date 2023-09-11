package com.github.jameswdelancey.javaCheatSheet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// import java.nio.file.FileSystems;
// import java.nio.file.Path;

public class Part11 {
    public static void main(String[] args) {
        String path = System.getenv("PATH");
        System.out.println("PATH: " + path);

        // Split paths by the system path separator into paths
        String[] pathArray = path.split(";");
        for (String folder : pathArray) {
            System.out.println("Folder in PATH: " + folder);
        }
    }
}

class Part11_2 {
    public static void main(String[] args) {
        // Print args to the console
        for (String arg : args) {
            System.out.println("Arg: " + arg);
        }
    }
}

class Part11_3 {
    public static void main(String[] args) {
        // Print args to the console using stdout.write and a string formatter
        for (String arg : args) {
            System.out.write(String.format("Arg: %s\n", arg).getBytes(), 0, String.format("Arg: %s\n", arg).length());
        }
    }
}

class Part11_4 {
    public static void main(String[] args) {
        // Read one line from stdin and print it back to stdout
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        try {
            bytesRead = System.in.read(buffer, 0, 1024);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.printf("You typed: %s (%d bytes)\n", new String(buffer, 0, bytesRead), bytesRead);
    }
}

class Part11_5 {
    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader(
                "src/main/java/com/github/jameswdelancey/javaCheatSheet/Part11.java");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                FileWriter fileWriter = new FileWriter(
                        "src/main/java/com/github/jameswdelancey/javaCheatSheet/Part11.java.bak");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            System.out.println("File copied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Part11_6 {
    public static void main(String[] args) {
        try (DatagramSocket receiver = new DatagramSocket(0, InetAddress.getByName("127.0.0.1"));
                DatagramSocket sender = new DatagramSocket();) {
            System.out.println("We bound port " + receiver.getLocalPort() + " for receiving");
            byte[] message = "Hello, World!".getBytes();

            DatagramPacket packet = new DatagramPacket(message, message.length, InetAddress.getByName("127.0.0.1"),
                    receiver.getLocalPort());
            sender.send(packet);

            receiver.close();
            sender.close();

            System.out.println("Message sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Part11_7 {
    public static class FileOpenError extends Exception {
        private final String message;

        public FileOpenError(String message) {
            this.message = message;
        }
    }

    public static class FileReadError extends Exception {
        private final String message;

        public FileReadError(String message) {
            this.message = message;
        }
    }

    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream(
                    "src/main/java/com/github/jameswdelancey/javaCheatSheet/Part11.java");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            FileOpenError error = new FileOpenError("Error opening file: " + e.getMessage());
            handleError(error);
        } catch (IOException e) {
            FileReadError error = new FileReadError("FileReadError reading file: " + e.getMessage());
            handleError(error);
        }
    }

    private static void handleError(Exception error) {
        System.err.println("Error: " + error.getMessage());
    }
}