package com.github.jameswdelancey.javaCheatSheet;

import com.github.jameswdelancey.javaCheatSheet.part1n2.printing.TimeStuff;

public class Part1n2 {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Printing.announceTime();
        Printing.sayGoodbye();
    }
}

class Printing {
    static void announceTime() {
        TimeStuff.giveUsTheTime();
    }

    static void sayGoodbye() {
        System.out.println("Goodbye!");
    }
}
