package com.clouway.array;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by clouway on 12/20/13.
 */
public class TestArray {

  @Test
  public void testGetMinElementWhetehrWorkCorec() throws Exception {
    int[] arr = new int[]{4, 2, 1,};
    Array array = new Array(arr);
    int actual = array.getMinElement();
    int expected = 1;

    assertEquals(expected, actual);
  }

  @Test(expected = NullPointerException.class)
  public void testGetMinElementWhetherThrowException() throws Exception {
    int[] arr = null;
    Array array = new Array(arr);
    array.getMinElement();
  }

  @Test
  public void testGetSumWhetherCorectSumArrayElements() throws Exception {
    int[] arr = new int[] {1, 2, 3, 4};
    Array array = new Array(arr);
    int actual = array.getSum();
    int expected = 10;

    assertEquals(expected, actual);
  }

  @Test(expected = NullPointerException.class)
  public void testGetSumWhetherThrowException() throws Exception {
    int[] arr = null;
    Array array = new Array(arr);
    array.getSum();
  }
}
