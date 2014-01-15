package twocounterthread;

/**
 * Created by clouway on 1/15/14.
 */
public class Counter {

  public synchronized void count(CounterThread counter) {
   while (!counter.finishCount) {
     System.out.println(counter.getName() + " count to " + ++counter.count);
     if (counter.count == counter.limit) {
       counter.finishCount = true;
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
