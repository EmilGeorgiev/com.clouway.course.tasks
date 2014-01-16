package timeout;

/**
 * Created by clouway on 1/15/14.
 */
public class TimoutHashtableDemo {
  public static void main(String[] args) {
    TimeoutHashTable<String, String> table = new TimeoutHashTable<String, String>(1000);
    String cityTarnovo = "Tarnovo";
    String citySofia = "Sofia";
    String cityPlovdiv = "Plovdiv";
    String cityVarna = "Varna";

    table.put("cityTarnovo", cityTarnovo);
    table.put("cityPlovdiv", cityPlovdiv);
    table.put("cityVarna", cityVarna);
    table.put("citySofia", citySofia);

    System.out.println(table.get("cityTarnovo"));
    table.get("cityPlovdiv");

    System.out.println(table.get("cityPlovdiv"));

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(table.get("cityVarna"));
  }
}
