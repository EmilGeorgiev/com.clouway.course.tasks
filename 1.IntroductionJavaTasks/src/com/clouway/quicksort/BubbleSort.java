package com.clouway.quicksort;

public class BubbleSort {

	/**
	 * Sorted array in ascending order.
	 * 
	 * @param array
	 *            which will be sorted.
	 */
	public int[] sortArray(int[] array) {
		int number;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					number = array[i];
					array[i] = array[j];
					array[j] = number;
				}
			}
		}
		return array;
	}
}
