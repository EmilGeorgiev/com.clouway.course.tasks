package com.clouway.person;

import org.junit.Test;

import static com.clouway.person.PersonBuilder.newPerson;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 2/24/14.
 */
public class PersonTest {

  @Test
  public void personIsAdult() throws Exception {
    Person person = newPerson().age(20).build();
    assertThat(verifyWherePersonIsAdult(person, 18), is(true));
  }

  @Test
  public void personWithEmptyName() {
    Person person = newPerson().named("").build();
    assertThat(verifyPerson(person),is(false));
  }

  @Test
  public void personWithNonEmptyName() {
    Person person = newPerson().named("John").build();
    assertThat(verifyPerson(person),is(true));
  }

  // production code
  private boolean verifyWherePersonIsAdult(Person person, int adultAge) {
    if (person.getAge() > adultAge) {
      return true;
    }

    return false;
  }

  // another production method
  private boolean verifyPerson(Person person) {
    if (person.getName().length() == 0) {
      return false;
    }
    return true;
  }

}
