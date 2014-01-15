package counter;

import java.util.Scanner;

/**
 * Created by clouway on 1/14/14.
 */
public class CounterThread extends Thread {
  private final int limit;
  int count = 0;

  public CounterThread(int limit) {
    this.limit = limit;
  }

  /**
   * Increment count while reached limit or it interrupted thread.
   */
  @Override
  public void run() {
    while (count != limit) {
      count++;
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        System.out.println("counter has reached: " + count);
        break;
      }
    }
    System.out.println("CounterThread finish.");
  }
}
