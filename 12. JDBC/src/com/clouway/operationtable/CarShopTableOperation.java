package com.clouway.operationtable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class perform operations on database to store cars.
 * Constructor of the class is fed "Connection" which communicates with database.
 * Class have methods to perform fowling queries: insert, findCarByRegistrationNumber, update, delete, drop, create, alter.
 */
public class CarShopTableOperation {

  private final Connection connection;

  public CarShopTableOperation(Connection connection) {
    this.connection = connection;
  }

  /**
   * Insert new car in database.
   * @param car which added in database.
   */
  public void insert(Car car) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("insert into cars (registration_number, model, country, year_manufacture)" +
              " values (?, ?, ?, ?)");
      preparedStatement.setInt(1, car.getRegistrationNumber());
      preparedStatement.setString(2, car.getModel());
      preparedStatement.setString(3, car.getCountry());
      preparedStatement.setString(4, car.getYearManufacture());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Select car from database than passed specify registration number.
   * @return Car object with specific registration number.
   */
  public Car findCarByRegistrationNumber(int registrationNumber) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT registration_number, model, country, year_manufacture " +
              "FROM cars" +
              " WHERE registration_number = ?");
      preparedStatement.setInt(1, registrationNumber);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (!resultSet.next()) {
        return null;
      }

      String model = resultSet.getString("model");
      String country = resultSet.getString("country");
      String yearManufacture = resultSet.getString("year_manufacture");

      return new Car(registrationNumber, model, country, yearManufacture);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;

  }

  /**
   * Change data of the car.
   * @param newCar which change data.
   */
  public void update(Car newCar, Car oldCar) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cars" +
              " SET registration_number = ?, model = ?, country = ?, year_manufacture = ?" +
              " WHERE registration_number= ?, model = ?, country = ?, year_manufacture = ?");

      preparedStatement.setInt(1, newCar.getRegistrationNumber());
      preparedStatement.setString(2, newCar.getModel());
      preparedStatement.setString(3, newCar.getCountry());
      preparedStatement.setString(4, newCar.getYearManufacture());

      preparedStatement.setInt(5, oldCar.getRegistrationNumber());
      preparedStatement.setString(6, oldCar.getModel());
      preparedStatement.setString(7, oldCar.getCountry());
      preparedStatement.setString(8, oldCar.getYearManufacture());

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void cleaner() {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE cars");
      preparedStatement.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Delete car from databases.
   * @param car which deleted.
   */
  public void deleted(Car car) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cars" +
              " WHERE registration_number = ?");

      preparedStatement.setInt(1, car.getRegistrationNumber());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void deleteTable() {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE cars");
      preparedStatement.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
