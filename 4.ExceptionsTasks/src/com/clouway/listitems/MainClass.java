package com.clouway.listitems;

public class MainClass {

  public static void main(String[] args) {
    ListOperation list = new ListItems(3);
    ProducerThread producer1 = new ProducerThread(list, "a");
    producer1.setName("producer1");
    ProducerThread producer2 = new ProducerThread(list, "a");
    producer2.setName("producer2");
    ProducerThread producer3 = new ProducerThread(list, "a");
    producer3.setName("producer3");
    ProducerThread producer4 = new ProducerThread(list, "a");
    producer4.setName("producer4");
    producer1.start();
    producer2.start();
    producer3.start();
    producer4.start();
    ConsumerThread consumer1 = new ConsumerThread(list);
    consumer1.setName("consumer1");
    ConsumerThread consumer2 = new ConsumerThread(list);
    consumer2.setName("consumer2");
    consumer1.start();
    consumer2.start();
  }

}
