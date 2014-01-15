package limitcounterthreads;

/**
 * Created by clouway on 1/15/14.
 */
public class CounterThread extends Thread{
  private final int limit;

  static boolean finishCount = false;
  private int count;

  public CounterThread(int limit) {

    this.limit = limit;
  }

  @Override
  public void run() {
    while (!finishCount) {
      if (count == limit) {
        finishCount = true;
      }
      count++;
      System.out.println(getName() + " count to: " + count);
    }
    System.out.println(getName() + " finished.");
  }
}
