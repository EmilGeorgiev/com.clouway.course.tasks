package timeout;

/**
 * Created by clouway on 1/15/14.
 */
public class TimoutHashtableDemo {
  public static void main(String[] args) {
    TimeoutHashTable table = new TimeoutHashTable();
    TimeoutThread timeoutThread1 = new TimeoutThread(10000, table);
    timeoutThread1.setName("10000");
    TimeoutThread timeoutThread2 = new TimeoutThread(2000, table);
    timeoutThread2.setName("2000");
    TimeoutThread timeoutThread3 = new TimeoutThread(3000, table);
    timeoutThread3.setName("3000");
    TimeoutThread timeoutThread4 = new TimeoutThread(4000, table);
    timeoutThread4.setName("4000");
    table.put("10000", timeoutThread1);
    table.get("10000");
    table.put("2000", timeoutThread2);
    table.put("3000", timeoutThread3);
    table.put("4000", timeoutThread4);

    System.out.println(table.get("10000"));
    try {
      timeoutThread1.join();
      timeoutThread2.join();
      timeoutThread3.join();
      timeoutThread4.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(table.get("10000"));
  }
}
