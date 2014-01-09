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
public class ServiceTest {

  private Service service;
  private AgeRestriction ageRestriction;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  Validator validator;
  @Mock
  Database database;

  @Before
  public void setUp() {
    ageRestriction = new AgeRestriction(10, 18);
    service = new Service(validator, database, ageRestriction);
  }

  @Test
  public void saveCorrectAgeInDatabase() throws Exception {
    final Person person = new Person("Emil", 25);

    context.checking(new Expectations() {
      {
        oneOf(validator).validateYears(25, ageRestriction.getMinAge());
        oneOf(database).save(person);
      }
    });

    service.savePersonInDataBase(person);
  }

  @Test(expected = IllegalArgumentException.class)
  public void saveOldPeopleInTheDatabase() throws Exception {
    final Person person = new Person("Ivan", 150);
    context.checking(new Expectations() {
      {
        oneOf(validator).validateYears(150, ageRestriction.getMinAge());
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
        oneOf(validator).validateYears(25, ageRestriction.getMinAge());
        oneOf(database).save(person);
        oneOf(validator).validateYears(25, ageRestriction.getLawfulAge());
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
        oneOf(validator).validateYears(16, ageRestriction.getLawfulAge());
        will(throwException(new IllegalArgumentException()));
      }
    });

    service.getYearsOfPersonFromDatabase(person);
  }
}
