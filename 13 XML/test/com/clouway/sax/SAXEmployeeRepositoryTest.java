package com.clouway.sax;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.clouway.util.CalendarUtil.newDate;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 3/31/14.
 */
public class SAXEmployeeRepositoryTest {


  @Test
  public void convertXMLToObject() throws Exception {

    Address address1 = new Address("Ivan Vazov", 23);
    Employer employer1 = new Employer("Stoqn Stoqnov", newDate(2010, 10, 10), newDate(2013, 10, 10));
    Employee employee1 = EmployeeBuilder.newEmployee().age(25).name("Petar").address(address1).employer(employer1).build();

    Address address2 = new Address("vasil levski", 34);
    Employer employer2 = new Employer("Mihail Mishov", newDate(2010, 10, 10), newDate(2013, 10, 10));

    Employee employee2 = new Employee("Ivan", 26, address2, employer2);

    SAXEmployeeRepository saxEmployeeRepository = new SAXEmployeeRepository(SAXEmployeeRepository.class.getResourceAsStream("XMLEmployees"));
    List<Employee> employeeList = saxEmployeeRepository.findEmployee();

    assertThat(employeeList, is(Arrays.asList(employee1, employee2)));

  }
}
