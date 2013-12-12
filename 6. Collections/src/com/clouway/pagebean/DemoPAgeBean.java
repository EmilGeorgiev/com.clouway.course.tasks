package com.clouway.pagebean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/10/13
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoPAgeBean {
  static final String BREAK_POINT = "q";
  static final String NEXT = "next";
  static final String PREVIOUS = "previous";


  public static void main(String[] args) {
    List<String> listIfMessage = new ArrayList<String>();
    Scanner scanner = new Scanner(System.in);
    Page page;
    listIfMessage.add("a ");
    listIfMessage.add("b ");
    listIfMessage.add("c ");
    listIfMessage.add("d ");
    listIfMessage.add("e ");
    listIfMessage.add("f ");
    listIfMessage.add("g ");
    listIfMessage.add("h ");
    listIfMessage.add("i ");
    listIfMessage.add("j ");
    listIfMessage.add("k ");
    listIfMessage.add("l ");
    listIfMessage.add("m ");
    listIfMessage.add("n ");
    PageBean pageList = new PageBean(listIfMessage);
    System.out.println("Enter \"next\" to display the first page.");
    String line;
    while (!(line = scanner.nextLine()).equals(BREAK_POINT)) {
      if (line.equalsIgnoreCase(NEXT)) {
        page = pageList.next();
        page.printPage();


      } else if (line.equalsIgnoreCase(PREVIOUS)) {
        page = pageList.previous();
        page.printPage();
      }
    }
    scanner.close();
  }
}
