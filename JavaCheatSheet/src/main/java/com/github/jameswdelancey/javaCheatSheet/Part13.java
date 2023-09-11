package com.github.jameswdelancey.javaCheatSheet;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Part13 {
    static class Node {
        String value;
        List<Node> edges;

        Node(String value) {
            this.value = value;
            this.edges = new ArrayList<>();
        }

        void display() {
            System.out.println("Value: " + value);
            for (Node edge : edges) {
                edge.display();
            }
            System.out.println("UP");
        }
    }

    public static void main(String[] args) {
        Node e = new Node("E");
        Node d = new Node("D");
        d.edges.add(e);
        Node b = new Node("B");
        Node c = new Node("C");
        c.edges.add(d);
        c.edges.add(e);
        Node a = new Node("A");
        a.edges.add(b);
        a.edges.add(c);
        a.edges.add(d);
        a.edges.add(e);
        WeakReference<Node> dWeak = new WeakReference<>(d);
        a.display();
        a = null; // Drop a
        d = null; // Drop d
        System.gc(); // Explicitly trigger garbage collection
        Node dStrong = dWeak.get();
        if (dStrong != null) {
            System.out.println("D is still alive: " + dStrong.value);
            dStrong.display();
        } else {
            System.out.println("D is dead");
        }
        System.out.println("Done");
    }
}

class Part13_2 {

    static class Cat {
        private int timesSpoken = 0;

        void report() {
            System.out.println("Spoken " + timesSpoken + " times");
        }

        void speak() {
            timesSpoken++;
            System.out.println("Meow");
        }
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.speak();
        cat.speak();
        cat.speak();
        cat.report();
    }
}
