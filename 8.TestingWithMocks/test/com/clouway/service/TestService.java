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
    final String age = "25";

    context.checking(new Expectations() {
      {
        oneOf(validator).validatesTheYearsByAdding(age);
        oneOf(database).save(age);
      }
    });

    service.saveAge(age);
  }

  @Test(expected = IllegalArgumentException.class)
  public void saveOldPeopleInTheDatebase() throws Exception {
    final String age = "150";
    context.checking(new Expectations() {
      {
        oneOf(validator).validatesTheYearsByAdding(age);
        will(throwException(new IllegalArgumentException()));
      }
    });

    service.saveAge(age);
  }

  @Test
  public void getAgeOnDatabase() throws Exception {
    final String age = "25";
    context.checking(new Expectations() {
      {
        oneOf(validator).validatesTheYearsByAdding(age);
        oneOf(database).save(age);
        oneOf(validator).validatesTheYearsByGetting(age);
        oneOf(database).receive(age);
        will(returnValue(25));
      }
    });

    service.saveAge(age);

    assertThat(service.getAge(age), is(25));

  }

  @Test(expected = IllegalArgumentException.class)
  public void getUnderAgeFromDatabase() throws Exception {
    final String age = "16";
    context.checking(new Expectations() {
      {
        oneOf(validator).validatesTheYearsByGetting(age);
        will(throwException(new IllegalArgumentException()));
      }
    });

    service.getAge(age);
  }
}