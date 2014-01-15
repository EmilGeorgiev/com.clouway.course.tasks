package twocounterthread;

/**
 * Created by clouway on 1/15/14.
 */
public class Counter {
  private boolean finishCount = false;

  public synchronized void count(CounterThread counter) {
   while (!finishCount) {
     System.out.println(counter.getName() + " count to " + ++counter.count);
     if (counter.count == counter.limit) {
       finishCount = true;
       notify();
       return;
     }
     notify();
     try {
       wait();
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
   }
  }
}
