package com.github.jameswdelancey.javaCheatSheet;

public class Part6 {
    public static void main(String[] args) {
        Hands hands = new Hands();
        hands.report();
        hands = hands.juggle();
        hands.report();
    }
}

class Hands {
    private Item left;
    private Item right;

    public Hands() {
        this.left = new Item("an apple", true);
        this.right = new Item("a banana", true);
    }

    public Hands juggle() {
        System.out.println("Let's juggle!");
        Item air = this.left;
        this.left = this.right;
        this.right = air;
        return this;
    }

    public void report() {
        left.reportItem("Left");
        right.reportItem("Right");
    }
}

class Item {
    private String what;
    private boolean present;

    public Item(String what, boolean present) {
        this.what = what;
        this.present = present;
    }

    public void reportItem(String which) {
        if (present) {
            System.out.println(which + " hand is holding " + what);
        } else {
            System.out.println(which + " hand is not holding anything");
        }
    }
}
