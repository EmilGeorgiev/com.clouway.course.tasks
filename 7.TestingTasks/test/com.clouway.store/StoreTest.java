package com.clouway.store;

import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.synth.SynthRootPaneUI;
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

  @Before
  public void setUp() {
    store = new Store();
  }

  @Test
  public void testAddProductsApples() throws Exception {
    Product apple = new Product(30, 2.4);
    int actual = store.add(apple, 20);
    int expected = 20;

    assertThat(expected, is(actual));
  }

  @Test
  public void testAddTwiceProductsLimons() throws Exception {
    Product limon = new Product(50, 1.6);
    store.add(limon, 3);
    int actual = store.add(limon, 35);
    int expected = 38;

    assertThat(expected, is(actual));
  }

  @Test
  public void testAddJustEnoughProductsFillStore() throws Exception {
    Product limon = new Product(50, 1.6);
    int actual = store.add(limon, 50);
    int expected = 50;

    assertThat(expected, is(actual));
  }

  @Test(expected = FullStoreException.class)
  public void testOverfullStore() {
    Product pear = new Product(10, 1.8);
    store.add(pear, 25);
  }

  @Test
  public void testSellProducts() throws Exception {
    Product orange = new Product(30, 2.4);
    store.add(orange, 25);
    int actual = store.sell(orange, 20);
    int expected = 5;

    assertThat(expected, is(actual));
  }

  @Test(expected = NotSoAvailableException.class)
  public void testSellMoreProductThanThereAreAvailable() throws Exception {
    Product apple = new Product(30, 2.4);
    store.add(apple, 25);
    store.sell(apple, 27);
  }

  @Test
  public void testSortProductsByPrace() throws Exception {
    Product apple = new Product(20, 2.4);
    Product limon = new Product(10, 1.2);
    Product orange = new Product(30, 3.2);
    store.add(apple, 15);
    store.add(limon, 5);
    store.add(limon, 3);
    store.add(orange, 23);

    List<Product> actual = store.sortProductsByPrace();
    List<Product> expected = new ArrayList<Product>();
    expected.add(limon);
    expected.add(apple);
    expected.add(orange);
    System.out.println(expected.get(0).getTempValue());

    assertThat(expected, is(actual));
  }
}
