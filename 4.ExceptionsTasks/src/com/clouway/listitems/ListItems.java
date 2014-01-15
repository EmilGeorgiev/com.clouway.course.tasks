package com.clouway.listitems;

import java.util.ArrayList;
import java.util.List;

public class ListItems implements ListOperation {

  static final int HOME_INDEX = -1;
  private Object[] arrayList;
 // private List<Integer> list = new ArrayList<Integer>();
   /**
   * Shows which place in the array are
   */
  private int index = -1;

  public ListItems(int lengthOfArrayList) {
    arrayList = new Object[lengthOfArrayList];

  }

  public Object getObject(int value) {
    if (value < 0) {
      throw new IllegalArgumentException();
    }

    return arrayList[value];
  }

  /**
   * Print all list items.
   */
  @Override
  public void printAllElements() {
    for (int i = 0; i <= index; i++) {
      System.out.println(arrayList[i] + " ");
    }
  }

  /**
   * Adds an element to the end of the list.
   *
   * @param value is value added.
   * @return true if new value is added or false if not.
   */
  @Override
  public synchronized void add(Object value) throws FullArrayException {
    while (isFull()) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    index++;
    arrayList[index] = value;
    notify();
  }

  /**
   * Removes the last item in the list.
   *
   * @return true if operation is successful or false if not.
   */
  @Override
  public synchronized void remove() throws EmptyArrayException {
    while (isEmpty()) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    arrayList[index] = null;
    index--;
    notify();
  }

  /**
   * Check that the item list is full.
   *
   * @return true if item list is full or false if not.
   */
  private boolean isFull() {
    if (index == arrayList.length -1) {
      return true;
    }
    return false;
  }

  /**
   * Check that the item list is empty.
   *
   * @return true if the array is empty or false is not.
   */
  private boolean isEmpty() {
    if (index == HOME_INDEX) {
      return true;
    }
    return false;
  }
}
