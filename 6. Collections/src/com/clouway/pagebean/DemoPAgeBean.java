package com.clouway.pagebean;

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
    System.out.println("Enter page size.");
    int pageSize =  Integer.parseInt(scanner.nextLine());
    Page page;
    listIfMessage.add("1 ");
    listIfMessage.add("2 ");
    listIfMessage.add("3 ");
    listIfMessage.add("4 ");
    listIfMessage.add("5 ");
    listIfMessage.add("6 ");
    listIfMessage.add("7 ");
    listIfMessage.add("8 ");
    listIfMessage.add("9 ");
    listIfMessage.add("10 ");
    listIfMessage.add("11 ");
    listIfMessage.add("12 ");
    listIfMessage.add("13 ");
    listIfMessage.add("14 ");
    PageBean pageList = new PageBean(listIfMessage, pageSize);
    System.out.println("Enter \"next\" to display the first page.");
    String line;
    while (!(line = scanner.nextLine()).equals(BREAK_POINT)) {
      if (line.equalsIgnoreCase(NEXT)) {
        try {

          page = pageList.next();
          page.printPage();
        } catch (RuntimeException ex) {
          System.out.println(ex.getMessage());
        }
      } else if (line.equalsIgnoreCase(PREVIOUS)) {
        try {
          page = pageList.previous();
          page.printPage();
        } catch (RuntimeException ex) {
          System.out.println(ex.getMessage());
        }
      }
    }
    scanner.close();
  }
}
