package com.clouway.homogeneoustree;

public class DemoOrderedBinaryTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeElement tree = new TreeElement();
		tree.insert(124);
		tree.insert(1);
		tree.insert(13);
		tree.insert(11);
		tree.insert(14);
		tree.insert(17);
		tree.insert(18);
		
		
		//tree.printAllNode(tree.getRoot());
		tree.find(11);
        tree.printAllNode();
		
		
		
	

	}

}
