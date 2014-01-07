package com.clouway.service;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 12/27/13.
 */
public class TestService {

  private String recipient;
  private Service service;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  Validator mockValidator;
  @Mock
  ConnectionDatabase mockConnectionDatabase;

  @Before
  public void setUp() {
    recipient = "11223456";
    service = new Service(mockValidator, mockConnectionDatabase);
  }

  @Test
  public void testSaveCorrectAgeInDatabase() throws Exception {
    final String age = "25";

    context.checking(new Expectations() {
      {
        oneOf(mockValidator).validateAge(age);
        will(returnValue(true));
        oneOf(mockConnectionDatabase).save(recipient, age);
      }
    });

    service.saveAge(recipient, age);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveInvalidAgeInDatabase() throws Exception {
    final String age = "150";
    context.checking(new Expectations() {
      {
        oneOf(mockValidator).validateAge(age);
        will(returnValue(false));
      }
    });

    service.saveAge(recipient, age);
  }

  @Test
  public void testGetAgeOnDatabase() throws Exception {
    final String age = "25";
    context.checking(new Expectations() {
      {
        oneOf(mockValidator).validateAge(age);
        will(returnValue(true));
        oneOf(mockConnectionDatabase).save(recipient, age);
        oneOf(mockValidator).validateGetAge(age);
        will(returnValue(true));
        oneOf(mockConnectionDatabase).receive(recipient, age);
        will(returnValue(25));
      }
    });

    service.saveAge(recipient, age);
    service.getAge(recipient, age);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetInvalidAgeOnDatabase() throws Exception {
    final String age = "16";
    context.checking(new Expectations() {
      {
        oneOf(mockValidator).validateGetAge(age);
        will(returnValue(false));
      }
    });

    service.getAge(recipient, age);
  }
}