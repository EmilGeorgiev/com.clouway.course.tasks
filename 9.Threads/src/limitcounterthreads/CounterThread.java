package limitcounterthreads;

/**
 * Created by clouway on 1/15/14.
 */
public class CounterThread extends Thread {
  private final int limit;
  private int count = 0;
  private CounterThread thread;

  public CounterThread(int limit) {

    this.limit = limit;
  }

  @Override
  public void run() {
      while (count != limit) {
        if (isInterrupted()) {
          System.out.println(getName() + " interrupted.");
          break;
        }
        count++;
        System.out.println(getName() + " count " + count);
      }
      thread.interrupt();
  }

  public void setThread(CounterThread thread) {
    this.thread = thread;
  }
}
