package com.clouway.travel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 3/17/14.
 */
public class TravelTest {

  private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  private Person petar = new Person("1111111111", "Petar", 25, "georgiev88@abv.bg");

  private Travel travel;
  private Trip trip = new Trip("Varna", "2014-03-25", "2014-03-29", "1111111111");

  @Rule
  public DataStoreRule dataStoreRule = new DataStoreRule();

  @Before
  public void initialize() throws SQLException {
    travel = new Travel(dataStoreRule.getBasicDataSource().getConnection());

    travel.cleanerTables();

    travel.addNewTravel(petar, trip);
  }


  @Test
  public void findPerson() throws Exception {

    Person actualPerson = travel.findPersonByEGN(this.petar.getEgn());

    assertThat(actualPerson, is(petar));
  }

  @Test
   public void findTrip() throws Exception {

    List<Trip> actualTrip = travel.findTripByCity(trip.getCity());

    assertThat(actualTrip, is(Arrays.asList(trip)));
  }

  @Test
  public void changeExistingInformation() throws Exception {

    Person newPerson = new Person("1111111111", "Ivan", 24, "ivan@gmail.com");

    travel.updatePerson(newPerson);

    Person actualPerson = travel.findPersonByEGN("1111111111");

    assertThat(actualPerson, is(newPerson));
  }

  @Test
  public void extractedDataFromPeopleTable() throws Exception {

    List<Person> personList = travel.findPersons();

    assertThat(personList, is(Arrays.asList(petar)));
  }

  @Test
  public void extractedDataFromTripTable() throws Exception {

    List<Trip> tripList = travel.findTrips();

    assertThat(tripList, is(Arrays.asList(trip)));
  }

  @Test
  public void extractPersonsWhereNameBeginWithSomeCharacters() throws Exception {

    List<Person> personList = travel.findPersonsByCharacters("Pe");

    assertThat(personList, is(Arrays.asList(petar)));
  }

  @Test
  public void allPeopleInТheSomeCityAtTheSomeTime() throws Exception {

    Person ivan = new Person("1020304050", "Ivan", 24, "ivan@gmail.com");
    Person georgi = new Person("1122334455", "Georgi", 22, "georgi@");
    Trip varnaTrip = new Trip("Varna", "2014-03-10", "2014-03-20", "1020304050");
    Trip varnaTrip2 = new Trip("Varna", "2014-03-15", "2014-03-22", "1122334455");

    travel.addNewTravel(ivan, varnaTrip);
    travel.addNewTravel(georgi, varnaTrip2);

    List<Person> personList = travel.findAllPersonInSomeCityByTime(createDate("2014-03-17"), createDate("2014-03-23"), varnaTrip);

    assertThat(personList, is(Arrays.asList(georgi, ivan)));

  }

  @Test
  public void extractCityOrderByNumberOfPeopleVisitedThem() throws Exception {

    Person ivan = new Person("1020304050", "Ivan", 24, "ivan@gmail.com");
    Trip varnaTrip = new Trip("varna", "2014-03-05", "2014-03-10", "1020304050");
    Trip sofiaTrip = new Trip("Sofia", "2014-05-09", "2014-05-16", "1020304050");

    travel.addNewTravel(ivan, varnaTrip);
    travel.addNewTravel(ivan, sofiaTrip);

    List<String> listCity = travel.getCityOrderByVisited();

    assertThat(listCity.get(0), is("Varna"));
    assertThat(listCity.get(1), is("Sofia"));

  }

  private Date createDate(String date) {
    java.util.Date dateFormat = null;

    try {
      dateFormat = formatter.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    if (dateFormat != null) {
      return new Date(dateFormat.getTime());
    }

    return null;
  }
}
