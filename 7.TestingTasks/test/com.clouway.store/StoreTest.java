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
    store.add(apple, 15);
  }

  @Test(expected = FullStoreException.class)
  public void addMoreProductsThanCanFitStore() {
    store.add(apple, 45);
  }

  @Test
  public void sellProducts() throws Exception {
    int actual = store.sell(apple, 10);

    assertThat(actual, is(5));
  }

  @Test(expected = NotEnoughProductsException.class)
  public void sellMoreProductThanThereIsInTheStore() throws Exception {
    store.sell(apple, 20);
  }

  @Test
  public void sortProductsByPrice() throws Exception {
    Product pear = new Product(30, 1.2);
    Product orange = new Product(35, 3.2);
    store.add(pear, 5);
    store.add(orange, 23);

    List<Product> actual = store.sortProductsByPrice();
    List<Product> expected = new ArrayList<Product>();
    expected.add(pear);
    expected.add(apple);
    expected.add(new Product(35, 3.2));

    assertThat(actual, is(expected));
  }
}
