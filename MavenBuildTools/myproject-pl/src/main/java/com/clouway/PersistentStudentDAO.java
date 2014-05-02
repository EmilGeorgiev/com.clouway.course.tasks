package com.clouway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 4/14/14.
 */
public class PersistentStudentDAO implements StudentDAO {

  private final Connection connection;

  public PersistentStudentDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void registered(Student student, int roomNumber) {
    PreparedStatement roomStatement = null;
    PreparedStatement studentStatement = null;
    try {
      connection.setAutoCommit(false);
      roomStatement = connection.prepareStatement("INSERT INTO room (room_number, free) value(?, ?)");
      roomStatement.setInt(1, roomNumber);
      roomStatement.setString(2, "No");
      roomStatement.executeUpdate();

      studentStatement = connection.prepareStatement("INSERT INTO students(idNumber, fistName, lastName, age, room_number) " +
              "VALUES(?, ?, ?, ?, ?)");
      studentStatement.setInt(1, student.getIdNumber());
      studentStatement.setString(2, student.getFirstName());
      studentStatement.setString(3, student.getLastName());
      studentStatement.setInt(4, student.getAge());
      studentStatement.setInt(5, roomNumber);
      studentStatement.executeUpdate();

      connection.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.setAutoCommit(true);

        if (roomStatement != null) {
          roomStatement.close();
        }

        if (studentStatement != null) {
          studentStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void unregistered(Student student, int roomNumber) {
    PreparedStatement studentStatement = null;
    PreparedStatement roomStatement = null;

    try {
      connection.setAutoCommit(false);

      studentStatement = connection.prepareStatement("DELETE from students " +
              "WHERE idNumber=?");

      studentStatement.setInt(1, student.getIdNumber());
      studentStatement.executeUpdate();

      roomStatement = connection.prepareStatement("DELETE FROM room " +
              "WHERE room_number=?");

      roomStatement.setInt(1, roomNumber);
      roomStatement.executeUpdate();
      connection.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.setAutoCommit(true);
        if (studentStatement != null) {
          studentStatement.close();
        }
        if (roomStatement != null) {
          roomStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public Student findStudentByIDNumber(int idStudentNumber) {
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("SELECT firstName, LastName, age FROM students " +
              "WHERE idNumber = ?");

      preparedStatement.setInt(1, idStudentNumber);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        String firstNAme = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        int age = resultSet.getInt("age");
        return StudentBuilder.newStudent().firstName(firstNAme).lastName(lastName)
                .age(age).idNumber(idStudentNumber).build();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public List<Student> findAllRegisteredStudents() {
    List<Student> studentList = new ArrayList<Student>();

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("SELECT idNumber, firstName, lastName, age FROM students");

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int idNumber = resultSet.getInt("idNumber");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        int age = resultSet.getInt("age");
        studentList.add(StudentBuilder.newStudent().firstName(firstName).lastName(lastName)
                .age(age).idNumber(idNumber).build());
      }
      return studentList;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public int getRoomNumberOnStudent(Student student) {
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("SELECT room_number FROM students " +
              "WHERE idNumber = ?");
      preparedStatement.setInt(1, student.getIdNumber());
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        return resultSet.getInt("room_number");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return 0;
  }
}
