package com.github.jameswdelancey.javaCheatSheet;

import java.util.ArrayList;
import java.util.List;

class Part12 {
    static class SomeHugeType {
        int[] data = new int[10_000_000];
    }

    static SomeHugeType allocateNumbers() {
        SomeHugeType numbers = new SomeHugeType();
        return numbers;
    }

    static SomeHugeType generateNumbersOnHeap() {
        SomeHugeType numbers = allocateNumbers();
        for (int i = 0; i < numbers.data.length; i++) {
            numbers.data[i] = i * 2;
        }
        return numbers;
    }

    static final int ANSWER = 42;
    static String QUESTION = "";

    static SomeHugeType generateNumbers() {
        SomeHugeType numbers = new SomeHugeType();
        for (int i = 0; i < numbers.data.length; i++) {
            numbers.data[i] = i * 2;
        }
        return numbers;
    }

    public static void main(String[] args) {
        generateNumbers();
    }
}

class Part12_2 {
    public static void main(String[] args) {
        Part12.generateNumbersOnHeap();
    }
}

class Part12_3 {
    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }

        void display() {
            System.out.println(value);
            if (left != null) {
                left.display();
            }
            if (right != null) {
                right.display();
            }
            System.out.println("UP");
        }
    }

    public static void main(String[] args) {
        Node root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(3);
        root.display();
    }
}

class Part12_4 {
    interface Animal {
        void speak();
    }

    static class Cat implements Animal {
        @Override
        public void speak() {
            System.out.println("Meow!");
        }
    }

    static class Dog implements Animal {
        @Override
        public void speak() {
            System.out.println("Woof!");
        }
    }

    static void workWithAnimals(List<Animal> animals) {
        for (Animal animal : animals) {
            animal.speak();
        }
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        List<Animal> animals = new ArrayList<>();
        animals.add(cat);
        animals.add(dog);
        workWithAnimals(animals);
    }
}