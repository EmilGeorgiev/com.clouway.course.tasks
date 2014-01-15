package twocounterthread;

/**
 * Created by clouway on 1/15/14.
 */
public class CounterThread extends Thread {
  private final Counter counter;
  final int limit;
  int count = 0;

  public CounterThread(Counter counter, int limit) {
    this.counter = counter;
    this.limit = limit;
  }

  @Override
  public void run() {
    counter.count(this);
    interrupt();
  }
}
