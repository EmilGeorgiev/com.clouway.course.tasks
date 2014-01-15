package timeout;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by clouway on 1/15/14.
 */
public class TimeoutHashTable {
  private Map<String, TimeoutThread> timeout = new Hashtable<String, TimeoutThread>();

  public void put(String key, TimeoutThread thread) {
    timeout.put(key, thread);
    thread.start();
  }

  public TimeoutThread get(String key) {
    if (timeout.containsKey(key)) {
      timeout.get(key).interrupt();
      return timeout.get(key);
    }
    return null;
  }

  public TimeoutThread remove(String key) {
    if (timeout.containsKey(key)) {
      return timeout.remove(key);
    }
    return null;
  }
}
