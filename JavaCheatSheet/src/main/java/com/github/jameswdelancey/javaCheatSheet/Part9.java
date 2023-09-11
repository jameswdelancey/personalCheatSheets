package com.github.jameswdelancey.javaCheatSheet;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Part9 {
    public static void main(String[] args) {
        Integer[] nums = { 1, 2, 3 };
        {
            Iterator<Integer> iter = new ArrayIterator<>(nums);
            while (iter.hasNext()) {
                System.out.println(iter.next());
            }
        }
    }
}

class Part9_2 {
    public static void main(String[] args) {
        int[] nums2 = { 1, 2, 3 };
        int[] nums3 = { 1, 2, 3 };
        {
            Iterator<Integer> iter2 = new OurIterator(nums2, 2);
            Iterator<Integer> iter3 = new OurIterator(nums3, 3);
            while (iter2.hasNext()) {
                System.out.println(iter2.next());
            }
            while (iter3.hasNext()) {
                System.out.println(iter3.next());
            }
        }

        System.out.println("Pushing 4");
        nums2 = push(nums2, 4);
    }

    public static int[] push(int[] array, int value) {
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;
        return newArray;
    }
}

class ArrayIterator<T> implements Iterator<T> {
    private final T[] array;
    private int index;

    public ArrayIterator(T[] array) {
        this.array = array;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }
}

class OurIterator implements Iterator<Integer> {
    private final int[] nums;
    private int index;
    private int x;

    public OurIterator(int[] nums, int x) {
        this.nums = nums;
        this.x = x;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < nums.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        x = nums[index] * x;
        index++;
        return x;
    }

    @Override
    protected void finalize() throws Throwable {
        // Custom cleanup code goes here
        // This method is called before the object is garbage collected
        System.out.println("Dropping OurIterator");
        super.finalize();
    }
}
