package russell.tests.algorithms.domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Tree {

	public static class Node {
		public String data;
		public Node left = null;
		public Node right = null;

		public Node(String data) {
			this.data = data;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((data != null) ? data.hashCode() : 0);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (data != other.data)
				return false;
			return true;
		}
	}

	/** Root of the tree */
	public Node root;

	public static void visit(final Node node) {
		if (node != null) {
			System.out.print(node.data + " ");
		}
	}

	public static void inOrderRecursive(final Node node) {
		if (node == null) {
			return;
		}
		inOrderRecursive(node.left);
		visit(node);
		inOrderRecursive(node.right);
	}

	public static void inOrderIterative(final Node node) {
		if (node == null) {
			return;
		}

		final Deque<Node> stack = new ArrayDeque<>();
		Node current = node;
		while (!stack.isEmpty() || current != null) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else {
				current = stack.pop();
				visit(current);
				current = current.right;
			}
		}
	}

	public static void preOrderRecursive(final Node node) {
		if (node == null) {
			return;
		}
		visit(node);
		preOrderRecursive(node.left);
		preOrderRecursive(node.right);
	}

	public static void preOrderIterative(final Node node) {
		if (node == null) {
			return;
		}
		
		final Deque<Node> stack = new ArrayDeque<>();
		Node current = node;
		while (!stack.isEmpty() || current != null) {
			if (current != null) {
				if (current.right != null) {
					stack.push(current.right);
				}
				visit(current);
				current = current.left;
			} else {
				current = stack.pop();
			}
		}
	}
	
	public static void postOrderRecursive(final Node node) {
		if (node == null) {
			return;
		}
		
		postOrderRecursive(node.left);
		postOrderRecursive(node.right);
		visit(node);
	}
	
	public static void postOrderIterative(final Node node) {
	    if (node == null) {
	        return;
	    }
	    
	    final Deque<Node> stack = new ArrayDeque<>();
	    Node current = node;
	    Node lastVisited = null;
	    while (!stack.isEmpty() || current != null) {
	        if (current != null) {
	            stack.push(current);
	            current = current.left;
	        } else {
	            Node peek = stack.peek();
	            if (peek.right != null && peek.right != lastVisited) {
	                current = peek.right;
	            } else {
	                visit(peek);
	                lastVisited = stack.pop();
	            }
	        }
	    }
	}

	public static int getHeight(final Node node) {

		int leftHeight = 0;
		int rightHeight = 0;
		if (node.left != null) {
			leftHeight = getHeight(node.left);
		}
		if (node.right != null) {
			leftHeight = getHeight(node.right);
		}

		return Math.max(leftHeight, rightHeight) + 1;
	}

	public static void bfs(final Node node) {
		final Queue<Node> pending = new LinkedList<>();
		pending.add(node);

		Node current = null;
		while (!pending.isEmpty()) {
			current = pending.poll();
			visit(current);
			if (current.left != null) {
				pending.add(current.left);
			}
			if (current.right != null) {
				pending.add(current.right);
			}
		}
	}
}
