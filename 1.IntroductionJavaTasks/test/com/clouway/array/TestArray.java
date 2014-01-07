package com.clouway.array;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 12/20/13.
 */
public class TestArray {

  @Test
  public void GetMinElementWhetehrWorkCorec() throws Exception {
    int[] arr = new int[]{4, 2, 1,};
    Array array = new Array(arr);
    int actual = array.getMinElement();

    assertThat(actual, is(1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void GetMinElementWhetherThrowException() throws Exception {
    int[] arr = null;
    Array array = new Array(arr);
    array.getMinElement();
  }

  @Test
  public void GetSumWhetherCorectSumArrayElements() throws Exception {
    int[] arr = new int[] {1, 2, 3, 4};
    Array array = new Array(arr);
    int actual = array.getSum();

    assertThat(actual, is(10));
  }

  @Test(expected = IllegalArgumentException.class)
  public void GetSumWhetherThrowException() throws Exception {
    int[] arr = null;
    Array array = new Array(arr);
    array.getSum();
  }
}
