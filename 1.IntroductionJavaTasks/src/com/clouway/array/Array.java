package com.clouway.array;

public class Array {

    private int[] array;

    public Array(int[] array) {
      if (array == null) {
        throw new IllegalArgumentException("Array is empty.");
      }
        this.array = array;
    }

    /**
     * Returns the smallest element of the array
     * is the array which takes the smallest item
     *
     * @return the smallest number
     */
    public int getMinElement() {
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            if (result > array[i]) {
                result = array[i];
            }
        }
        return result;
    }

    /**
     * Returns the sum of the elements of the array
     * is the array which sum all the elements.
     *
     * @return sum of all elements
     */
    public int getSum() {
        int sumOfElements = 0;
        for (int i = 0; i < array.length; i++) {
            sumOfElements += array[i];
        }
        return sumOfElements;
    }

    /**
     * shows the elements of the array
     * is the array which print all elements.
     */
    public void printArray() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    /**
     * Reversing array.
     *
     * @return
     */
    public int[] reversingArray() {
        int exchange;
        for (int j = array.length - 1, i = 0; i < (array.length / 2); i++, j--) {
            exchange = array[i];
            array[i] = array[j];
            array[j] = exchange;
        }
        return array;
    }
}
