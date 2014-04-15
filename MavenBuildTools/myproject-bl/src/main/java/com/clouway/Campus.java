package com.clouway;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by clouway on 4/14/14.
 */
public class Campus {

  private final StudentDAO studentDAO;
  private int capacity;
  private int takenRooms = 0;
  private Queue<Integer> listOfFreeWaitingRooms = new LinkedList<Integer>();

  public Campus(StudentDAO studentDAO, int capacity) {
    this.studentDAO = studentDAO;
    this.capacity = capacity;

  }

  public void registeredStudent(Student  student) throws CampusStudentLimitReachedException {
    if(!listOfFreeWaitingRooms.isEmpty()) {
      studentDAO.registered(student, listOfFreeWaitingRooms.poll());
      takenRooms++;
      return;
    }
    if(takenRooms >= capacity) {
      throw new CampusStudentLimitReachedException("Campus reached its limit, no longer available rooms.");
    }
    takenRooms++;
    studentDAO.registered(student, takenRooms);
  }

  public void releaseStudent(Student  student) {
    int roomNumber = studentDAO.getRoomNumberOnStudent(student);
    listOfFreeWaitingRooms.offer(roomNumber);
    studentDAO.unregistered(student, roomNumber);
    takenRooms--;
  }

  public List<Student> findAllRegisteredStudents() {
    return studentDAO.findAllRegisteredStudents();
  }

}
