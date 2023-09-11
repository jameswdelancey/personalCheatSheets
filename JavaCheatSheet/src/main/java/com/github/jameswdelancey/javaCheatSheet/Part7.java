package com.github.jameswdelancey.javaCheatSheet;

public class Part7 {
    public static void main(String[] args) {
        Hands hands = new Hands();
        hands.report();
        hands = hands.juggle();
        hands.report();
    }
}

class Hands {
    private Fruit left;
    private Fruit right;

    public Hands() {
        this.left = Fruit.Apple;
        this.right = Fruit.Banana;
    }

    public Hands juggle() {
        System.out.println("Let's juggle!");
        Fruit air = this.left;
        this.left = this.right;
        this.right = air;
        return this;
    }

    @Override
    public String toString() {
        return "Left hand is holding " + left + "\n" +
                "Right hand is holding " + right;
    }

    public void report() {
        System.out.println(this);
    }
}

enum Fruit {
    Apple,
    Banana;

    @Override
    public String toString() {
        return this == Apple ? "an apple" : "a banana";
    }
}
