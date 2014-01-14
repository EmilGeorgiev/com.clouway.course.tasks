package counter;

import java.util.Scanner;
import java.io.InputStream;

/**
 * Created by clouway on 1/14/14.
 */
public class ConsoleReader extends Thread{

  String breakSymbol;
  Thread otherThread;
  private InputStream inputStream;
  private boolean stopOther = false;

  public ConsoleReader(String breakSymbol, InputStream inputStream,  Thread otherThread) {
    this.breakSymbol = breakSymbol;
    this.inputStream = inputStream;
    this.otherThread = otherThread;
  }

  @Override
  public void run() {
    Scanner scanner = new Scanner(inputStream);
    while (!stopOther) {
      String symbol = scanner.nextLine();
      System.out.println(symbol);
      if (symbol.equals(breakSymbol)) {
        stopOther = true;
        otherThread.interrupt();
      }
    }
  }
}
