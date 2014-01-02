package com.clouway.store;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by clouway on 12/20/13.
 */
public class StoreTest {

  private Store store;
  private Product apple;

  @Before
  public void setUp() {
    store = new Store();
    apple = new Product(20, 2.4);
  }

  @Test
  public void testAddProducts() throws Exception {
    int actual = addApple();
    int expected = 15;

    assertThat(expected, is(actual));
  }

  @Test(expected = FullStoreException.class)
  public void testOverfullStore() {
    store.add(apple, 45);
  }

  @Test
  public void testSellProducts() throws Exception {
    addApple();
    int actual = store.sell(apple, 10);
    int expected = 5;

    assertThat(expected, is(actual));
  }

  @Test(expected = NotSoAvailableException.class)
  public void testSellMoreProductThanThereAreAvailable() throws Exception {
    addApple();
    store.sell(apple, 20);
  }

  @Test
  public void testSortProductsByPrace() throws Exception {
    Product pear = new Product(30, 1.2);
    Product orange = new Product(35, 3.2);
    addApple();
    store.add(pear, 5);
    store.add(orange, 23);

    List<Product> actual = store.sortProductsByPrace();
    List<Product> expected = new ArrayList<Product>();
    expected.add(pear);
    expected.add(apple);
    expected.add(orange);

    assertThat(expected, is(actual));
  }

  private int addApple() {
    return store.add(apple, 15);
  }
}
