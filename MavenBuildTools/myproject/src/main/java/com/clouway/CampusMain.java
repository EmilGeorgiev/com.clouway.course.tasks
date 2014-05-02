package com.clouway;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.SQLException;

import static com.clouway.StudentBuilder.newStudent;

/**
 * Created by clouway on 4/14/14.
 */
public class CampusMain {
  public static void main(String[] args) {
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    basicDataSource.setUrl("jdbc:mysql://localhost/CAMPUS");
    basicDataSource.setUsername("root");
    basicDataSource.setPassword("root");

    PersistentStudentDAO persistentStudentDAO = null;
    try {
      persistentStudentDAO = new PersistentStudentDAO(basicDataSource.getConnection());
    } catch (SQLException e) {
      e.printStackTrace();
    }

    Student ivan = newStudent().firstName("Ivan").idNumber(1).lastName("Ivanov")
            .age(23).build();
    Student petar = newStudent().firstName("Petar").idNumber(20).lastName("Petrov")
            .age(24).build();
    Student stoqn = newStudent().firstName("Stoqn").idNumber(3).lastName("Stoqnov")
            .age(25).build();
    Student misho = newStudent().firstName("Misho").idNumber(4).lastName("Mihov")
            .age(26).build();
    Campus campus = new Campus(persistentStudentDAO, 20);

    try {
      campus.registeredStudent(ivan);
      campus.registeredStudent(petar);
      campus.registeredStudent(stoqn);
      campus.releaseStudent(petar);
      campus.registeredStudent(misho);
    } catch (CampusStudentLimitReachedException e) {
      e.printStackTrace();
    }
  }
}
