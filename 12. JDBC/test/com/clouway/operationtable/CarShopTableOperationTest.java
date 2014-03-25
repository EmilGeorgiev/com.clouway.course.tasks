package com.clouway.operationtable;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;


public class CarShopTableOperationTest {

  private BasicDataSource basicDataSource = new BasicDataSource();
  private Car car = new Car(1234, "Opel", "Germany", "2002");
  CarShopTableOperation carShopTableOperation;

  @Before
  public void setUp() throws SQLException {
    basicDataSource.setPassword("root");
    basicDataSource.setUsername("root");
    basicDataSource.setUrl("jdbc:mysql://localhost/car_shop");
    basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");

    carShopTableOperation = new CarShopTableOperation(basicDataSource.getConnection());

    carShopTableOperation.insert(car);
  }

  @Test
  public void selectCar() throws Exception {
    String actualModel = carShopTableOperation.findCarByRegistrationNumber(car.getRegistrationNumber()).getModel();

    assertThat(actualModel, is("Opel"));

  }

  @Test
  public void updateCarInfo() throws Exception {

    Car newCar = new Car(4321, "BMW", "Germany", "2006");
    carShopTableOperation.update(newCar, car);
    String actual = carShopTableOperation.findCarByRegistrationNumber(newCar.getRegistrationNumber()).getModel();

    assertThat(actual, is("BMW"));

  }

  @Test
  public void deletedRecord() throws Exception {
    carShopTableOperation.deleted(car);

    Car actual = carShopTableOperation.findCarByRegistrationNumber(car.getRegistrationNumber());

    assertThat(actual, nullValue());

  }



  @After
  public void cleanerTable() {
    carShopTableOperation.cleaner();
  }
}
