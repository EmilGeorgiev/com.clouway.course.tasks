package com.clouway.array;

import static org.junit.Assert.*;

import com.clouway.array.Array;
import org.junit.Test;

public class TestProcessArray {

	@Test
	public void testGetMinElement() {
		int[] array = new int[] {1, 2, 3, 4};
		Array process = new Array(array);
		int actual = process.getMinElement();
		int expected = 1;
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testGetMinElementOfMixed() {
		int[] array = new int[] {3, -4, 1, 2};
		Array process = new Array(array);
		int actual = process.getMinElement();
		int expected = -4;
		assertEquals(expected, actual);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPassedNullParams() {
		int[] array = null;
		Array process = new Array(array);
		process.getMinElement();
	}
	
	@Test
	public void testGetSumOfPositivNumbers() {
		int[] array = new int[] {1, 2, 3, 4};
		Array process = new Array(array);
		int actual = process.getSum();
		int expected = 10;
		assertEquals(expected, actual);		
	}
	
	@Test
	public void testGetSumOfNegativNumbers() {
		int[] array = new int[] {-1, -2, -3, -4};
		Array process = new Array(array);
		int actual = process.getSum();
		int expected = -10;
		assertEquals(expected, actual);		
	}

    @Test
    public void testReverse() {
        int[] array = new int[] {10, 14, 1, 3};
        Array process = new Array(array);
        int[] actual =process.reversingArray();
        int[] expected = new int[] {3, 1, 14, 10};
        assertArrayEquals(actual, expected);
    }
}
