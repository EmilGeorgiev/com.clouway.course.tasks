package com.clouway.heterogeneoustree;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 11/26/13
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeterogeneousTree {

    private static class TreeNode {

        private int value;
        private TreeNode parent;
        private TreeNode leftChild;
        private TreeNode rightChild;
        private Object object;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    private TreeNode root;

    /**
     * Insert new object in the tree.
     *
     * @param value  key value of the new object.
     * @param object the added object.
     */
    public void insert(int value, Object object) {
        this.root = insertNode(value, null, root, object);
    }

    /**
     * Print all the values ​​of the node sorted
     */
    public void printAllNode() {
        printAllNode(root);
    }


    /**
     * Located object a set value.
     *
     * @param value a given value
     * @return object
     */
    public Object find(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("Invalid input value.Please enter value again");
        }
        return findNode(value, root).object;
    }

    /**
     * Find element on a given value.
     *
     * @param value      on a given value.
     * @param curentNode the current node
     * @return The found node of tree.
     */
    private TreeNode findNode(Integer value, TreeNode curentNode) {
        if (curentNode == null) {
            throw new IllegalArgumentException("Tree is empty.");
        }
        if (value == curentNode.value) {
            return curentNode;
        } else if (value < curentNode.value) {
            curentNode = findNode(value, curentNode.leftChild);
        } else {
            curentNode = findNode(value, curentNode.rightChild);
        }

        return curentNode;
    }


    private void printAllNode(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty");
        }

        if (root.leftChild == null) {
            System.out.println(root.object + " ");
        } else {
            printAllNode(root.leftChild);
            System.out.println(root.object + " ");
        }

        if (root.rightChild == null) {
            return;
        } else {
            printAllNode(root.rightChild);
        }
    }

    /**
     * Finds a given value in the tree and returns the node which contains it if
     * such exists.
     *
     * @param value      - the value to be found.
     * @param parentNode - the parent node
     * @param curentNode - the current node.
     * @param object     - the added object
     * @return - the new node.
     */
    private TreeNode insertNode(Integer value, TreeNode parentNode, TreeNode curentNode, Object object) {
        if (curentNode == null) {
            curentNode = new TreeNode(value);
            curentNode.parent = parentNode;
            curentNode.object = object;
        } else {
            if (value < curentNode.value) {
                curentNode.leftChild = insertNode(value, curentNode,
                        curentNode.leftChild, object);
            } else if (value > curentNode.value) {
                curentNode.rightChild = insertNode(value, curentNode,
                        curentNode.rightChild, object);
            }
        }
        return curentNode;
    }

}
