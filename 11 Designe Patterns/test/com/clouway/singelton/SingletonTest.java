package com.clouway.singelton;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 2/27/14.
 */
public class SingletonTest {

  @Test
  public void testName() throws Exception {
    Singleton ref1 = Singleton.getInstance();
    Singleton ref2 = Singleton.getInstance();

    assertThat(ref1, is(ref2));
  }
}
