package twocounterthread;

/**
 * Created by clouway on 1/15/14.
 */
public class CounterThreadDemo {
  public static void main(String[] args) {
    Object object = new Object();
    CounterThread counterTo100 = new CounterThread(object, 100);
    counterTo100.setName("Counter to 100");
    CounterThread counterTo200 = new CounterThread(object, 200);
    counterTo200.setName("Counter to 200");
    counterTo100.setThread(counterTo200);
    counterTo200.setThread(counterTo100);
    counterTo100.start();
    counterTo200.start();
  }
}
