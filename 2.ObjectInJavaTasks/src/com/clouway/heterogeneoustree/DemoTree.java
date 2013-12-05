package com.clouway.heterogeneoustree;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 11/26/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoTree {
    public static void main(String[] args) {
        HeterogeneousTree tree = new HeterogeneousTree();

        tree.insert(4, 1);
        tree.insert(2, "Ivan");
        tree.insert(1, "Emil");
        tree.insert(3, 3.6);
        tree.insert(6, 8);
        tree.insert(5, "Georgi");
        tree.insert(7, 10);

        tree.printAllNode();
    }


}
