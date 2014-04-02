package com.clouway.sax;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 3/31/14.
 */
@XmlComplexElement(packageName ="com.clouway.sax.", beginningMethodName = "set")
public class Employees {

  private List<Employee> listEmployee = new ArrayList<>();

  public Employees(List<Employee> listEmployee) {
    this.listEmployee = listEmployee;
  }

  public Employees() {
  }

  public List<Employee> getListEmployee() {
    return listEmployee;
  }

  public void addEmployee(Employee employee) {
    listEmployee.add(employee);
  }

  public void setListEmployee(List<Employee> listEmployee) {
    this.listEmployee = listEmployee;
  }
}
