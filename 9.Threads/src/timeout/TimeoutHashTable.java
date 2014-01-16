package timeout;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by clouway on 1/15/14.
 */
public class TimeoutHashTable<K, V> {
  private final int limit;
  private Map<K, TimeoutThread<K, V>> containerTable = new Hashtable<K, TimeoutThread<K, V>>();

  public TimeoutHashTable(int limit) {

    this.limit = limit;
  }

  /**
   * Put new threads in Hashtable and start thread which adding as value.
   *
   * @param key
   * @param value
   */
  public void put(K key, V value) {
    TimeoutThread<K, V> thread = new TimeoutThread<K, V>(this.containerTable, limit, value, key);
    containerTable.put(key, thread);
    thread.start();
  }

  /**
   * Get thread whit this key or return "null" if not contains this key.
   * Interrupt thread and restart timer.
   *
   * @param key
   * @return
   */
  public V get(String key) {
    if (!containerTable.containsKey(key)) {
      return null;
    }
    containerTable.get(key).interrupt();
    return containerTable.get(key).getData();
  }

  public V remove(K key) {
    if (!containerTable.containsKey(key)) {
      return null;
    }
    return containerTable.remove(key).getData();
  }
}
