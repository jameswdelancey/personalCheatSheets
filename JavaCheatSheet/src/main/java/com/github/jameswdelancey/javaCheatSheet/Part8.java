package com.github.jameswdelancey.javaCheatSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Part8 {
    public static void main(String[] args) {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        primes.add(3);
        primes.add(5);
        System.out.println(primes);
    }
}

class Part8_2 {
    public static void main(String[] args) {
        List<Integer> primes = new ArrayList<>(List.of(2, 3, 5));
        System.out.println(primes);
        primes.remove(1);
        System.out.println(primes);
    }
}

class Part8_3 {
    public static void main(String[] args) {
        List<Integer> primes = List.of(2, 3, 5);
        int i = 0;
        while (i < primes.size()) {
            System.out.println(primes.get(i));
            i++;
        }

        for (Integer prime : primes) {
            System.out.println(prime);
        }
    }
}

class Part8_4 {
    public static void main(String[] args) {
        List<Integer> primes = List.of(2, 3, 5);
        for (int i = 0; i < primes.size(); i++) {
            System.out.println(i + ": " + primes.get(i));
        }
    }
}

class Part8_5 {
    public static void main(String[] args) {
        Deque<Integer> primes = new ArrayDeque<>();
        primes.addLast(2);
        primes.addLast(3);
        primes.addLast(5);
        primes.addFirst(1);

        for (Integer prime : primes) {
            System.out.println(prime);
        }
    }
}

class Part_6 {
    public static void main(String[] args) {
        Map<Pair, String> grid = new HashMap<>();
        grid.put(new Pair(2, 3), "tree");
        grid.put(new Pair(4, 7), "rock");
        grid.computeIfAbsent(new Pair(-3, 1), key -> "bird");
        grid.remove(new Pair(4, 7));

        Pair coords = new Pair(2, 2);
        String thing = grid.get(coords);
        if (thing != null) {
            System.out.println("There's a " + thing + " at " + coords);
        } else {
            System.out.println("There's nothing at " + coords);
        }
    }

}

class Pair {
    private final int x;
    private final int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Pair pair = (Pair) obj;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
