package com.clouway.listitems;

public class MainClass {

  public static void main(String[] args) {
    ListOperation list = new ListItems(3);
    ProducerThread producer = new ProducerThread(list, "a");
    producer.start();
    ConsumerThread consumer = new ConsumerThread(list);
    consumer.start();

    try {
      consumer.join();
      producer.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    list.printAllElements();
  }

}
