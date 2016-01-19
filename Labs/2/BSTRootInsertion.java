/******************************************************************************
 * Modified from BST in algs4.jar
 ******************************************************************************/

/**
 * The <tt>BSTRoot Insertion</tt> class represents an ordered tree of keys.
 * <p>
 * This implementation uses an (unbalanced) binary search tree. It requires that
 * the key type implements the <tt>Comparable</tt> interface and calls the
 * <tt>compareTo()</tt> and method to compare two keys.
 * 
 * TODO !!!!!!!!!!!!!
 * 
 * The current implementation inserts new nodes as leaves in the tree.
 * 
 * Your task is to modify the implementation so that new nodes become the root of the
 * tree while still maintaining the properties of a binary search tree (i.e. elements in left
 * subtree of a node N contain keys less than N.key and elements of the right subtree of a node N
 * contain keys greater than N.key.
 * 
 * Implement the methods
 * 
 * private Node rotR(Node h) {}
 * private Node rotL(Node h) {}
 * 
 * and modify the method
 * public void put(Key key)
 * 
 * Algorithm for root insertion:
 * 
 * Insert node as a leaf as usual and then use rotations to move the leaf up to the root.
 */
public class BSTRootInsertion<Key extends Comparable<Key>> {
	private Node root; // root of BST

	private class Node {
		private Key key; // sorted by key
		private Node left, right; // left and right subtrees

		public Node(Key key) {
			this.key = key;
		}
		
		@Override
		public String toString() {
			return key.toString();
		}
	}

	/**
	 * Initializes an empty symbol table.
	 */
	public BSTRootInsertion() {
	}

	// Rotate Right
	private Node rotR(Node oldRoot) {
		Node x = new Node(oldRoot.left.key);
		x.right = oldRoot.left;
		return x;
		// x = left child of old root
		// right child of x becomes the left child of old root
	}
    
	// Rotate Left
	private Node rotL(Node oldRoot) {
		Node x = new Node(oldRoot.right.key);
		x.left = oldRoot.right;
		return x;
	}
 
	/**
	 * Inserts the specified key into the symbol table
	 * 
	 * @param key
	 *            the key
	 * @throws NullPointerException
	 *             if <tt>key</tt> is <tt>null</tt>
	 */
	public void put(Key key) {
		root = put(root, key);
	}
	
	private Node put(Node x, Key key) {
	
		// TODO use rotR and rotL to move inserted node to root of tree
		
		if (x == null) {
			return new Node(key);
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key);
		} else if (cmp > 0) {
			x.right = put(x.right, key);
		}
		return x;
	}
	
	// pre-order traversal of tree, reported as a string
	private String traverse(Node node) {
		String s = "";
		if (node != null) {
			s += node.toString()+",";
			s += traverse(node.left);
			s += traverse(node.right);
		}
		return s;
	}
	@Override
	public String toString() {
		// print out pre-order traversal of tree
		String s = "";
		s += traverse(root);
		return s;
	}

}

/******************************************************************************
 * Copyright 2002-2015, Robert Sedgewick and Kevin Wayne.
 * 
 * This file is part of algs4.jar, which accompanies the textbook
 * 
 * Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 * Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 * 
 * 
 * algs4.jar is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * algs4.jar is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * algs4.jar. If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
