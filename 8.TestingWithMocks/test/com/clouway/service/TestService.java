package com.clouway.service;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 12/27/13.
 */
public class TestService {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock Validator mockValidator;
  @Mock ConnectionDatabase mockConnectionDatabase;

  @Test
  public void testSaveCorrectAgeInDatabase() throws Exception {
    final String name = "Emil Georgiev Georgiev";
    final String age = "25";
    Service service = new Service(mockValidator, mockConnectionDatabase);
    context.checking(new Expectations() {{
      oneOf(mockValidator).validateAge(age);
      will(returnValue(true));
      oneOf(mockConnectionDatabase).save(name, age);
    }
    });

    service.saveAge(name, age);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveInvalidAgeInDatabase() throws Exception {
    final String name = "Emil Georgiev Georgiev";
    final String age = "150";
    Service service = new Service(mockValidator, mockConnectionDatabase);
    context.checking(new Expectations() {{
      oneOf(mockValidator).validateAge(age);
      will(returnValue(false));
    }
    });

    service.saveAge(name, age);
  }

  @Test
  public void testGetAgeOnDatabase() throws Exception {
    final String name = "Emil Georgiev Georgiev";
    final String age = "25";
    Service service = new Service(mockValidator, mockConnectionDatabase);
    context.checking(new Expectations() {{
      oneOf(mockValidator).validateAge(age);
      will(returnValue(true));
      oneOf(mockConnectionDatabase).save(name, age);
      oneOf(mockValidator).validateGetAge(age);
      will(returnValue(true));
      oneOf(mockConnectionDatabase).receive(name, age);
      will(returnValue(true)) ;
    }
    });

    service.saveAge(name, age);
    service.getAge(name, age);
  }

  @Test
  public void testGetInvalidAgeOnDatabase() throws Exception {
    final String name = "Emil Georgiev Georgiev";
    final String age = "16";
    Service service = new Service(mockValidator, mockConnectionDatabase);
    context.checking(new Expectations() {{
      oneOf(mockValidator).validateGetAge(age);
      will(returnValue(false));
    }
    });

    boolean actual = service.getAge(name, age);
    boolean expected = false;
    assertThat(expected, is(actual));
  }
}