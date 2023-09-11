package com.github.jameswdelancey.javaCheatSheet;

public class Part4 {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        int x = 17;
        int y = 9;
        findAnswer();
        int answer = findAnswer2(x, y);
        System.out.println("The answer is: " + answer);
    }

    public static void findAnswer() {
        int answer = 42;
        System.out.println("The answer is " + answer);
    }

    public static int findAnswer2(int x, int y) {
        return x + y + 5;
    }
}
