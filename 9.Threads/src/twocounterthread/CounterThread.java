package twocounterthread;

/**
 * Created by clouway on 1/15/14.
 */
public class CounterThread extends Thread {

  final int limit;
  private final Object objectSynchronized;
  int count = 0;
  private CounterThread thread;

  public CounterThread(Object objectSynchronized, int limit) {
    this.objectSynchronized = objectSynchronized;
    this.limit = limit;
  }

  @Override
  public void run() {
    synchronized (objectSynchronized) {
      while (count != limit) {
        count++;
        System.out.println(getName() + " count: " + count);
        objectSynchronized.notify();
        try {
          objectSynchronized.wait();
        } catch (InterruptedException e) {
          break;
        }
      }
      thread.interrupt();
    }
  }

  public void setThread(CounterThread thread) {
    this.thread = thread;
  }
}
