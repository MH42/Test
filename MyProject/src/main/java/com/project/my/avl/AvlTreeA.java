package com.project.my.avl;

/**
 * @see https://rosettacode.org/wiki/AVL_tree#Java
 */
public class AvlTreeA extends AvlTree {

	public void delete(int delKey) {
		if (root == null)
			return;
		Node n = root;
		Node parent = root;
		Node delNode = null;
		Node child = root;

		while (child != null) {
			parent = n;
			n = child;
			child = delKey >= n.key ? n.right : n.left;
			if (delKey == n.key)
				delNode = n;
		}

		if (delNode != null) {
			delNode.key = n.key;

			child = n.left != null ? n.left : n.right;

			if (root.key == delKey) {
				root = child;
			} else {
				if (parent.left == n) {
					parent.left = child;
				} else {
					parent.right = child;
				}
				rebalance(parent);
			}
		}
	}

	public boolean search(int value) {
		Node current = root;
		while (current != null) {
			if (current.key == value) {
				return true;
			} else if (current.key < value) {
				current = current.right;
			} else
				current = current.left;
		}
		return false;
	}

	protected void rebalance(Node n) {
		setBalance(n);

		if (n.balance == -2) {
			if (height(n.left.left) >= height(n.left.right))
				n = rotateRight(n);
			else
				n = rotateLeftThenRight(n);

		} else if (n.balance == 2) {
			if (height(n.right.right) >= height(n.right.left))
				n = rotateLeft(n);
			else
				n = rotateRightThenLeft(n);
		}

		if (n.parent != null) {
			rebalance(n.parent);
		} else {
			root = n;
		}
	}

	private Node rotateLeft(Node a) {

		Node b = a.right;
		b.parent = a.parent;

		a.right = b.left;

		if (a.right != null)
			a.right.parent = a;

		b.left = a;
		a.parent = b;

		if (b.parent != null) {
			if (b.parent.right == a) {
				b.parent.right = b;
			} else {
				b.parent.left = b;
			}
		}

		setBalance(a, b);

		return b;
	}

	private Node rotateRight(Node a) {

		Node b = a.left;
		b.parent = a.parent;

		a.left = b.right;

		if (a.left != null)
			a.left.parent = a;

		b.right = a;
		a.parent = b;

		if (b.parent != null) {
			if (b.parent.right == a) {
				b.parent.right = b;
			} else {
				b.parent.left = b;
			}
		}

		setBalance(a, b);

		return b;
	}

	private Node rotateLeftThenRight(Node n) {
		n.left = rotateLeft(n.left);
		return rotateRight(n);
	}

	private Node rotateRightThenLeft(Node n) {
		n.right = rotateRight(n.right);
		return rotateLeft(n);
	}

	private int height(Node n) {
		if (n == null)
			return -1;
		return 1 + Math.max(height(n.left), height(n.right));
	}

	private void setBalance(Node... nodes) {
		for (Node n : nodes)
			n.balance = height(n.right) - height(n.left);
	}

	public void printBalance() {
		printBalance(root);
	}

	private void printBalance(Node n) {
		if (n != null) {
			printBalance(n.left);
			System.out.printf("%s ", n.balance);
			printBalance(n.right);
		}
	}

	public static void main(String[] args) {
		AvlTreeA tree = new AvlTreeA();

		System.out.println("Inserting values 1 to 10");
		for (int i = 1; i <= 10; i++)
			tree.insert(i);

		System.out.print("Printing balance: ");
		tree.printBalance();
	}
}