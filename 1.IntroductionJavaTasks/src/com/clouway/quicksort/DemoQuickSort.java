package com.clouway.quicksort;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 11/26/13
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoQuickSort {

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] array = new int[] {2, 5, 1, 4, 3};
        int[] sortArray = sort.sort(array);
        for (int i = 0; i < sortArray.length; i++) {
            System.out.print(sortArray[i]);
        }


    }
}
