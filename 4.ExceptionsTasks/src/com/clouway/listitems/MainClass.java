package com.clouway.listitems;

import java.util.ListResourceBundle;

public class MainClass {

	public static void main(String[] args) {
		ListItems list = new ListItems(5);

        list.add(1);
        list.add(2);
        list.add(3);
        list.printAllElements();
        System.out.println();

        list.remove();
        list.printAllElements();
        System.out.println();

        list.add(3);
        list.printAllElements();
        System.out.println();
        list.remove();
        list.remove();
        list.remove();
        list.remove();
        list.remove();
        list.remove();
        list.remove();
        list.printAllElements();


	}

}
