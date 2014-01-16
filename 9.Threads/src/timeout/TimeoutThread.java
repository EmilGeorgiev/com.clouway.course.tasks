package timeout;

import java.util.Map;

/**
 * Created by clouway on 1/15/14.
 */
public class TimeoutThread<K, T> extends Thread{

  private final int limitTime;
  private Map<K, TimeoutThread<K, T>> containerTable;
  private T data;
  private K key;


  public TimeoutThread(Map<K, TimeoutThread<K, T>> containerTable, int limitTime, T data, K key) {
    this.containerTable = containerTable;
    this.limitTime = limitTime;
    this.data = data;
    this.key = key;
  }

  @Override
  public void run() {
    while (true) {
      try {
        Thread.sleep(limitTime);
        break;
      } catch (InterruptedException e) {
        System.out.println("Renew timer for: " + getName());
      }
    }
    if (containerTable.containsValue(this)) {
      containerTable.remove(key);
    }
  }

  public T getData() {
    return data;
  }
}
