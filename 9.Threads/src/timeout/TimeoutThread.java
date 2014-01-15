package timeout;

import java.util.Map;

/**
 * Created by clouway on 1/15/14.
 */
public class TimeoutThread extends Thread{

  private final int limitTime;
  private TimeoutHashTable timeoutHashTable;
  private boolean clearValue = false;

  public TimeoutThread(int limitTime, TimeoutHashTable timeoutHashTable) {

    this.timeoutHashTable = timeoutHashTable;
    this.limitTime = limitTime;
  }

  @Override
  public void run() {
    while (!clearValue) {
      try {
        Thread.sleep(limitTime);
        clearValue = true;
      } catch (InterruptedException e) {
        System.out.println("Renew timer for: " + getName());
        clearValue = false;
      }
    }
    timeoutHashTable.remove(getName());

  }
}
