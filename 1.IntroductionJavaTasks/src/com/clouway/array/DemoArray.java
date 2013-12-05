package com.clouway.array;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 11/28/13
 * Time: 10:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoArray {
    public static void main(String[] args) {
        int[] arr = new int[] {3, 6, 8, 2, 9};
        Array array = new Array(arr);
        System.out.println(array.getMinElement());
        System.out.println(array.getSum());
        array.reversingArray();
        array.printArray();
    }
}
