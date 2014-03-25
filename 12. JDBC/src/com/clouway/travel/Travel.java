package com.clouway.travel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.clouway.travel.PersonBuilder.newPerson;

/**
 * This class menages database by adding, retrieve and update data from two tables, people and trip.
 * In constructor of class passing Connection that create PrepareStatement which create different SQL queries.
 *
 * <pre>
 *   public class MyClass {
 *     public Person getPersonFromDB(int egn) {
 *       Travel travel = new Travel(DataSource.getConnection);
 *       return travel.findPersonByEgn(egn);
 *     }
 *   }
 * </pre>
 *
 * @author georgiev_882@abv.bg (Emil GEoregiev)
 */
public class Travel {

  private final Connection connection;

  public Travel(Connection connection) {

    this.connection = connection;
  }

  /**
   * Adding a new person and information about it.
   * Adding a new trip and information about it.
   * То insert person and trip in tables using transaction.
   * @param person who added in table people.
   * @param trip who added in table trip.
   */
  public void addNewTravel(Person person, Trip trip) {
    boolean autoCommit = true;
    PreparedStatement insertPeople = null;
    PreparedStatement insertTrip = null;

    try {
      //If person whit specify EGN not contains in table we insert new person.
      if (findPersonByEGN(person.getEgn()) == null) {
        autoCommit = false;
        connection.setAutoCommit(false);
        insertPeople = connection.prepareStatement("INSERT INTO people (EGN, name, age, e_mail)" +
                " values (?, ?, ?, ?)");

        insertPeople.setString(1, person.getEgn());
        insertPeople.setString(2, person.getName());
        insertPeople.setInt(3, person.getAge());
        insertPeople.setString(4, person.getEmail());
        insertPeople.executeUpdate();
      }

      insertTrip = connection.prepareStatement("INSERT INTO trip (city, date_arrival, date_departure, EGN) " +
              "VALUES (?, ?, ?, ?)");

      insertTrip.setString(1, trip.getCity());
      insertTrip.setDate(2, trip.getDateArrival());
      insertTrip.setDate(3, trip.getDateDeparture());
      insertTrip.setString(4, trip.getEgn());
      insertTrip.executeUpdate();

      if (!autoCommit) {
        connection.commit();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.setAutoCommit(true);
        if (insertPeople != null) {
          insertPeople.close();
        }
        if (insertTrip != null) {
          insertTrip.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Find specify person by EGN.
   * @param egn  through which person find.
   * @return Person with a EGN.
   */
  public Person findPersonByEGN(String egn) {
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement  = connection.prepareStatement("SELECT EGN, name, age, e_mail" +
              " FROM people" +
              " WHERE EGN = ?");

      preparedStatement.setString(1, egn);

      ResultSet resultSet = preparedStatement.executeQuery();

       if (!resultSet.next()) {
         return null;
       }

      String name = resultSet.getString("name");
      int age = resultSet.getInt("age");
      String eMail = resultSet.getString("e_mail");

      return new Person(egn, name, age, eMail);

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  /**
   * Cleaner tables people and trip, but not delete tables.
   * Use transaction and DELETE statement.
   */
  public void cleanerTables() {
    PreparedStatement cleanedPeople = null;
    PreparedStatement cleanedTrip = null;
    try {
      connection.setAutoCommit(false);

      cleanedPeople = connection.prepareStatement("DELETE FROM people");
      cleanedTrip = connection.prepareStatement("DELETE FROM trip");

      cleanedTrip.executeUpdate();

      cleanedPeople.executeUpdate();

      connection.commit();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.setAutoCommit(true);
        if (cleanedPeople != null) {
          cleanedPeople.close();
        }
        if (cleanedTrip != null) {
          cleanedTrip.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Find list of trip by specify city.
   * @param city which trip is visited
   * @return List of trips.
   */
  public List<Trip> findTripByCity(String city) {

    PreparedStatement preparedStatement = null;
    try {
       preparedStatement = connection.prepareStatement("SELECT city, date_arrival, date_departure, EGN " +
              "FROM trip " +
               "WHERE city = ?");

      preparedStatement.setString(1, city);

      ResultSet resultSet = preparedStatement.executeQuery();

      return fullTripsInList(resultSet);

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   * Change existing info for Person
   * @param newPerson who replace old Person.
   */
  public void updatePerson(Person newPerson) {
    PreparedStatement updatePeople = null;

    try {

      updatePeople = connection.prepareStatement("UPDATE people " +
              "SET name = ?, age = ?, e_mail = ? " +
              "WHERE EGN = ?");

      updatePeople.setString(1, newPerson.getName());
      updatePeople.setInt(2, newPerson.getAge());
      updatePeople.setString(3, newPerson.getEmail());
      updatePeople.setString(4, newPerson.getEgn());

      updatePeople.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (updatePeople != null) {
          updatePeople.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   *
   * @return
   */
  public List<Person> findPersons() {
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = connection.prepareStatement("SELECT EGN, name, age, e_mail FROM people");
      ResultSet resultSet = preparedStatement.executeQuery();

      return fullPersonInList(resultSet);

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  public List<Trip> findTrips() {
    List<Trip> tripList = new ArrayList<Trip>();

    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = connection.prepareStatement("SELECT city, date_arrival, date_departure, EGN FROM trip");
      ResultSet resultSet = preparedStatement.executeQuery();

      return fullTripsInList(resultSet);

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return tripList;

  }

  public List<Person> findPersonsByCharacters(String characters) {
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("SELECT EGN, name, age, e_mail FROM people " +
              "WHERE name LIKE '" + characters + "%'");


      ResultSet resultSet = preparedStatement.executeQuery();

      return fullPersonInList(resultSet);

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  public void deleteTable(String tableName) {
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = connection.prepareStatement(String.format("DROP table %s", tableName));
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public List<String> getCityOrderByVisited() {
    List<String> listCity = new ArrayList<String>();

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("SELECT city FROM trip " +
              "GROUP BY city " +
              "ORDER BY COUNT(city) desc");
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        String city = resultSet.getString("city");
        listCity.add(city);
      }
      return listCity;

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  /**
   * Find all people who is in some city at the some time.
   * @param date a date
   * @return list of persons.
   */
  public List<Person> findAllPersonInSomeCityByTime(Date date, Date date2, Trip trip) {

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("SELECT people.EGN, name, age, e_mail FROM people " +
              "INNER JOIN trip " +
              "ON people.EGN=trip.EGN " +
              "WHERE not trip.date_arrival > ? and not trip.date_departure < ? and trip.city = ? " +
              "ORDER BY name");

      preparedStatement.setDate(1, date2);
      preparedStatement.setDate(2, date);
      preparedStatement.setString(3, trip.getCity());

      ResultSet resultSet = preparedStatement.executeQuery();

      return fullPersonInList(resultSet);

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public void closeConnection() {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private List<Trip> fullTripsInList(ResultSet resultSet) {
    List<Trip> tripList = new ArrayList<Trip>();
    try {
      while (resultSet.next()) {
        String city = resultSet.getString("city");
        Date dateArrival = resultSet.getDate("date_arrival");
        Date dateDeparture = resultSet.getDate("date_departure");
        String egn = resultSet.getString("EGN");

        tripList.add(new Trip(city, dateArrival.toString(), dateDeparture.toString(), egn));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tripList;
  }

  private List<Person> fullPersonInList(ResultSet resultSet) {
    List<Person> personList = new ArrayList<Person>();
    try {
      while(resultSet.next()) {
        String egn = resultSet.getString("EGN");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        String eMail = resultSet.getString("e_mail");
        personList.add(newPerson().egn(egn).name(name).age(age).eMail(eMail).build());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return personList;

  }
}
