package counter;

import java.util.Scanner;

/**
 * Created by clouway on 1/14/14.
 */
public class CounterThreadDemo {
  public static void main(String[] args) {
    String breakPoint = "";
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter break symbol");
    String breakSymbol = scanner.nextLine();

    CounterThread counter = new CounterThread(10000);
    counter.start();
    System.out.println("Starting CounterThread.");

    while (!breakPoint.equals(breakSymbol)) {
     breakPoint = scanner.nextLine();
    }
    counter.interrupt();
    scanner.close();
  }
}
