package com.clouway.sax;

/**
 * Created by clouway on 3/31/14.
 */
@XmlComplexElement(packageName ="com.clouway.sax.", beginningMethodName = "set")
public class Employee {

  private String name;
  private int age;
  private Address address;
  private Employer employer;

  public Employee(String name, int age, Address address, Employer employer) {
    this.name = name;
    this.age = age;
    this.address = address;
    this.employer = employer;
  }

  public Employee() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = Integer.parseInt(age);
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Employer getEmployer() {
    return employer;
  }

  public void setEmployer(Employer employer) {
    this.employer = employer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Employee employee = (Employee) o;

    if (age != employee.age) return false;
    if (address != null ? !address.equals(employee.address) : employee.address != null) return false;
    if (employer != null ? !employer.equals(employee.employer) : employee.employer != null) return false;
    if (name != null ? !name.equals(employee.name) : employee.name != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + age;
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (employer != null ? employer.hashCode() : 0);
    return result;
  }
}
