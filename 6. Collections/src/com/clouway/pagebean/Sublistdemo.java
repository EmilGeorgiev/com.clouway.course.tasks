package com.clouway.pagebean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/12/13
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class Sublistdemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("w");
        list.add("w");
        list.add("w");
        list.add("w");
        list.add("e");
        list.add("a");
        list.add("z");
        list.add("s");
        list.add("n");
        list.add("g");
        list.add("r");
        list.add("a");
        list.add("o");
        list.add("y");
        list.add("t");
        list = list.subList(0, 3);
        for (String str : list) {
            System.out.println(str);
        }
    }
}
