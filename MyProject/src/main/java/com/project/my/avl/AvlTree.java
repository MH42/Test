package com.project.my.avl;

public abstract class AvlTree {
	protected Node root;

	protected class Node {
		protected int key;
		protected int balance;
		protected Node left, right, parent;

		Node(int k, Node p) {
			key = k;
			parent = p;
		}
	}

	public boolean insert(int key) {
		if (root == null)
			root = new Node(key, null);
		else {
			Node n = root;
			Node parent;
			while (true) {
				if (n.key == key)
					return false;

				parent = n;

				boolean goLeft = n.key > key;
				n = goLeft ? n.left : n.right;

				if (n == null) {
					if (goLeft) {
						parent.left = new Node(key, parent);
					} else {
						parent.right = new Node(key, parent);
					}
					rebalance(parent);
					break;
				}
			}
		}
		return true;
	}

	protected abstract void rebalance(Node parent);
}