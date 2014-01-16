package limitcounterthreads;

/**
 * Created by clouway on 1/15/14.
 */
public class CounterThreadDemo {
  public static void main(String[] args) {
    CounterThread counterTo100 = new CounterThread(100);
    counterTo100.setPriority(1);
    CounterThread counterTo200 = new CounterThread(200);
    counterTo100.setThread(counterTo200);
    counterTo200.setThread(counterTo100);
    counterTo200.setPriority(10);
    counterTo100.start();
    counterTo200.start();
  }
}
