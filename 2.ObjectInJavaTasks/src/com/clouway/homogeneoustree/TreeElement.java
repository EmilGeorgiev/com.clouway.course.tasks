package com.clouway.homogeneoustree;

public class TreeElement {

    private static class BinaryTreeNode implements Comparable<BinaryTreeNode> {

        public BinaryTreeNode parent;
        public BinaryTreeNode leftChild;
        public BinaryTreeNode rightChild;
        int value;

        /**
         * Constructor the node.
         *
         * @param value - the value of the node.
         */
        public BinaryTreeNode(int value) {
            super();
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
            this.value = value;
        }

        @Override
        public int compareTo(BinaryTreeNode compared) {
            return (this.value - compared.value);
        }
    }

    // The root of the tree.
    private BinaryTreeNode root;

    public BinaryTreeNode getRoot() {
        return root;
    }

    /**
     * Constructor the tree.
     */
    public TreeElement() {

        this.root = null;
    }

    /**
     * Add new value in the tree.
     *
     * @param value is the new value added.
     */
    public void insert(int value) {
        this.root = insertNode(value, null, root);
    }

    public BinaryTreeNode find(int value) {
        return findNode(value, root);
    }

    /**
     * Print all element ascending.
     */
    public void printAllNode() {
        printAllNode(root);
    }

    /**
     * Inserts node in the binary search tree by given value.
     *
     * @param value      - the new value.
     * @param parentNode - the parent of the new node.
     * @param curentNode - current node.
     * @return the inserted node
     */
    private BinaryTreeNode insertNode(Integer value, BinaryTreeNode parentNode,
                                      BinaryTreeNode curentNode) {
        if (curentNode == null) {
            curentNode = new BinaryTreeNode(value);
            curentNode.parent = parentNode;
        } else {
            if (value < curentNode.value) {
                curentNode.leftChild = insertNode(value, curentNode,
                        curentNode.leftChild);
            } else if (value > curentNode.value) {
                curentNode.rightChild = insertNode(value, curentNode,
                        curentNode.rightChild);
            }
        }
        return curentNode;
    }


    /**
     * Finds a given value in the tree and returns the node which contains it if
     * such exists.
     *
     * @param value       - the value to be found.
     * @param currentNode - the current node.
     * @return
     */
    private BinaryTreeNode findNode(int value, BinaryTreeNode currentNode) {
        if (currentNode == null) {
            throw new NullPointerException("The tree is empty");
        }
        if (value == currentNode.value) {
            System.out.println(currentNode.value);
            return currentNode;
        } else if (currentNode.value < value) {
            findNode(value, currentNode.rightChild);
        } else if (currentNode.value > value) {
            findNode(value, currentNode.leftChild);
        }
        return null;
    }

    /**
     * Print all the values ​​of the node sorted
     *
     * @param root - root of the tree
     */
    private void printAllNode(BinaryTreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("The tree is empty");
        }

        if (root.leftChild == null) {
            System.out.print(root.value + " ");
        } else {
            printAllNode(root.leftChild);
            System.out.print(root.value + " ");
        }

        if (root.rightChild == null) {
            return;
        } else {
            printAllNode(root.rightChild);
        }
    }


}
