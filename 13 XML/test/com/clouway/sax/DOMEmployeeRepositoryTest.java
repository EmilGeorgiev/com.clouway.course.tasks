package com.clouway.sax;

import com.clouway.dom.DOMEmployeeRepository;
import com.clouway.util.CalendarUtil;
import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 4/2/14.
 */
public class DOMEmployeeRepositoryTest {

  CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  public void findAllEmployee() throws Exception {
    Address address1 = new Address("Ivan Vazov", 23);
    Employer employer1 = new Employer("Stoqn Stoqnov", calendarUtil.newDate(2010, 10, 10), calendarUtil.newDate(2013, 10, 10));
    Employee employee1 = EmployeeBuilder.newEmployee().age(25).name("Petar").address(address1).employer(employer1).build();

    Address address2 = new Address("vasil levski", 34);
    Employer employer2 = new Employer("Mihail Mishov", calendarUtil.newDate(2010, 10, 10), calendarUtil.newDate(2013, 10, 10));

    Employee employee2 = new Employee("Ivan", 26, address2, employer2);

    InputStream inputStream = DOMEmployeeRepository.class.getResourceAsStream("XMLEmployees");

    DOMEmployeeRepository domEmployeeRepository = new DOMEmployeeRepository(inputStream);

    List<Employee> employeeList = domEmployeeRepository.findEmployee();

    assertThat(employeeList, is(Arrays.asList(employee1, employee2)));
  }
}
