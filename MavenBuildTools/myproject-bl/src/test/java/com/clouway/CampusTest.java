package com.clouway;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 4/15/14.
 */
public class CampusTest {
  private Campus campus;
  private Student ivan = StudentBuilder.instanceStudentBuilder().firstName("Ivan").idNumber(1).lastName("Ivanov")
          .age(23).build();
  private com.clouway.Student petar = StudentBuilder.instanceStudentBuilder().firstName("Petar").idNumber(2).lastName("Petrov")
          .age(24).build();
  private Student stoqn = StudentBuilder.instanceStudentBuilder().firstName("Stoqn").idNumber(3).lastName("Stoqnov")
          .age(25).build();
  private Student misho = StudentBuilder.instanceStudentBuilder().firstName("Misho").idNumber(1).lastName("Mihov")
          .age(26).build();
  @Rule
  public DataStoreRule dataStoreRule = new DataStoreRule();

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  StudentDAO studentDAO;



  @Before
  public void setUp() {
    campus = new Campus(studentDAO, 20);
  }

  @Test
  public void registeredStudentsInCampus() throws Exception {

    context.checking(new Expectations() {{
      oneOf(studentDAO).registered(ivan, 1);
      oneOf(studentDAO).registered(petar, 2);
      oneOf(studentDAO).registered(stoqn, 3);

      oneOf(studentDAO).getRoomNumberOnStudent(petar);
      will(returnValue(2));
      oneOf(studentDAO).unregistered(petar, 2);

      oneOf(studentDAO).registered(misho, 2);

      oneOf(studentDAO).findAllRegisteredStudents();
      will(returnValue(Arrays.asList(ivan, stoqn, misho)));
    }
    });
    try {

      campus.registeredStudent(ivan);
      campus.registeredStudent(petar);
      campus.registeredStudent(stoqn);

      campus.releaseStudent(petar);

      campus.registeredStudent(misho);

      List<Student> list = campus.findAllRegisteredStudents();

      assertThat(list, is(Arrays.asList(ivan, stoqn, misho)));

    } catch (CampusStudentLimitReachedException e) {
      e.printStackTrace();
    }

  }
}
