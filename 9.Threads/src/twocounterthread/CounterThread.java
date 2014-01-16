package twocounterthread;

/**
 * Created by clouway on 1/15/14.
 */
public class CounterThread extends Thread {

  final int limit;
  private final Object lock;
  int count = 0;
  private CounterThread thread;

  public CounterThread(Object lock, int limit) {
    this.lock = lock;
    this.limit = limit;
  }

  @Override
  public void run() {
    synchronized (lock) {
      while (count != limit) {
        count++;
        System.out.println(getName() + " count: " + count);
        lock.notify();
        try {
          lock.wait();
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
