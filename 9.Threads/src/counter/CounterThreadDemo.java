package counter;

/**
 * Created by clouway on 1/14/14.
 */
public class CounterThreadDemo {
  public static void main(String[] args) {
    String breakSymbol = "q";
    CounterThread counter = new CounterThread(2000000);
    counter.start();
    ConsoleReader reader = new ConsoleReader(breakSymbol, System.in, counter);
    reader.start();
    System.out.println("count is: " + counter.count);
  }
}
