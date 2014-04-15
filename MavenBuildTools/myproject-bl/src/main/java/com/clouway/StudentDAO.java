package com.clouway;

import java.util.List;

/**
 * Created by clouway on 4/14/14.
 */
public interface StudentDAO {
  /**
   * Add new <code>Student</code> in database.
   * @param student which append in database
   */
  void registered(Student student, int roomNumber);

  /**
   * Unregistered <code>Student</code> on database.
   * @param student which unregistered.
   */
  void unregistered(Student student, int roomNumber);

  /**
   * Find <code>Student</code> by <code>idNumber</code>
   * @param idStudentNUmber on <code>Student</code> who we search.
   * @return <code>Student</code> with concrete <code>idNumber</code>
   */
  Student findStudentByIDNumber(int idStudentNUmber);

  /**
   * Find all <code>Student</code> in database
   * @return all <code>Student</code> registered in database
   */
  List<Student> findAllRegisteredStudents();

  int getRoomNumberOnStudent(Student student);
}
