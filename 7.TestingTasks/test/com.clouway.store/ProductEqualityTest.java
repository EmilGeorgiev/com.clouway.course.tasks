package com.clouway.store;

import junit.framework.TestCase;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 1/8/14.
 */
public class ProductEqualityTest {

  @Test
  public void maxQuantityIsEqual() throws Exception {
    Product product1 = new Product(20, 2.3);
    Product product2 = new Product(20, 2.3);

    assertThat(product1, is(equalTo(product2)));
  }

  @Test
  public void maxQuantityIsNotEqual() throws Exception {
    Product product1 = new Product(10, 2.3);
    Product product2 = new Product(20, 2.3);

    assertThat(product1, is(not(equalTo(product2))));
  }

  @Test
  public void priceIsEqual() throws Exception {
    Product product1 = new Product(20, 2.3);
    Product product2 = new Product(20, 2.3);

    assertThat(product1, is(equalTo(product2)));
  }

  @Test
  public void priceIsNotEqual() throws Exception {
    Product product1 = new Product(20, 2.6);
    Product product2 = new Product(20, 2.3);

    assertThat(product1, is(not(equalTo(product2))));
  }
}
