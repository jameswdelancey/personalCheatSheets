package com.github.jameswdelancey.javaCheatSheet;

class Part5 {
    public static void main(String[] args) {
        int n = 42;
        System.out.println("F(" + n + ") = " + Part5Lib.fib(n));
    }
}

class Part5Lib {
    public static void main(String[] args) {
        int n = 42;
        System.out.println("F(" + n + ") = " + fib2(n));
    }

    public static int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static int fib2(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int x = 1;
            int a = 0;
            int b = 1;
            while (true) {
                int next = a + b;
                a = b;
                b = next;
                x++;
                if (x < n) {
                    continue;
                }
                break;
            }
            return b;
        }
    }
}
