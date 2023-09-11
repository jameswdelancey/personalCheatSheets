package com.github.jameswdelancey.javaCheatSheet;

// Define an enumeration called Fruit
enum Fruit {
    Apple, Banana, Strawberry
}

// Define a type alias for Fruit
class Food {
    Fruit fruit;

    Food(Fruit fruit) {
        this.fruit = fruit;
    }
}

public class Part3 {
    public static void sayHello() {
        System.out.println("Hello World.");
    }

    public static void main(String[] args) {
        int x = 42;
        int y = -69;
        int xy = x * y;
        Tuple z = new Tuple(x, (float) y); // Tuple to store values of different types
        int[] arr = new int[] { x, y };
        int underscore = arr[0];

        // Define a Secrets class with fields x (double) and y (int)
        class Secrets {
            double x;
            int y;
        }

        Secrets secrets = new Secrets();
        secrets.x = -32.0;
        secrets.y = -42;

        Fruit fruit = Fruit.Apple;

        // Assign the sayHello method to a functional interface
        Runnable fun = Part3::sayHello;
        fun.run();

        System.out.println(x + " * " + y + " = " + xy);

        // Define a type alias for Fruit
        Food food = new Food(fruit);

        // Assign an empty tuple to a variable
        Object emptyTuple = new Object();
    }

    // Define a Tuple class to store values of different types
    static class Tuple {
        int x;
        float y;

        Tuple(int x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    // Define a functional interface for the sayHello method
    @FunctionalInterface
    interface Runnable {
        void run();
    }

}
