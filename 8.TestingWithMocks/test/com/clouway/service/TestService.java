package com.clouway.service;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * Created by clouway on 12/27/13.
 */
public class TestService {

  private Service service;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  Validator validator;
  @Mock
  Database database;

  @Before
  public void setUp() {
    service = new Service(validator, database);
  }

  @Test
  public void saveCorrectAgeInDatabase() throws Exception {
    final Person person = new Person("Emil", 25);

    context.checking(new Expectations() {
      {
        oneOf(validator).validatesTheYearsByAdding(25);
        oneOf(database).save(person);
      }
    });

    service.savePersonInDataBase(person);
  }

  @Test(expected = IllegalArgumentException.class)
  public void saveOldPeopleInTheDatebase() throws Exception {
    final Person person = new Person("Ivan", 150);
    context.checking(new Expectations() {
      {
        oneOf(validator).validatesTheYearsByAdding(150);
        will(throwException(new IllegalArgumentException()));
      }
    });

    service.savePersonInDataBase(person);
  }

  @Test
  public void getYearsOfAnAdultPersonFromTheDatabase() throws Exception {
    final Person person = new Person("Emil", 25);
    context.checking(new Expectations() {
      {
        oneOf(validator).validatesTheYearsByAdding(25);
        oneOf(database).save(person);
        oneOf(validator).validatesTheYearsByGetting(25);
        oneOf(database).receive(25);
        will(returnValue(25));
      }
    });

    service.savePersonInDataBase(person);

    assertThat(service.getYearsOfPersonFromDatabase(person), is(25));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getUnderagePersonFromDatabase() throws Exception {
    final Person person = new  Person("Petar", 16);
    context.checking(new Expectations() {
      {
        oneOf(validator).validatesTheYearsByGetting(16);
        will(throwException(new IllegalArgumentException()));
      }
    });

    service.getYearsOfPersonFromDatabase(person);
  }
}
