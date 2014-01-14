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

  @Override
  public void run() {
    for (int i = 0; i < limit; i++) {
      if (!isInterrupted()) {
        count++;
      } else {
        System.out.println("count is: " + count);
        //interrupt();
        return;
      }
    }
  }
}
