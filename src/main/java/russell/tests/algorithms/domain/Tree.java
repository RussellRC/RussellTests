package russell.tests.algorithms.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree<T> {

	public static class Node<T> {
		public T data;
		public Node<T> left = null;
		public Node<T> right = null;

		public Node(T data) {
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
		
		@Override
		public String toString() {
			return data.toString();
		}
	}

	/** Root of the tree */
	public Node<T> root;

	public static void visit(final Node<?> node) {
		if (node != null) {
			System.out.print(node.data + " ");
		}
	}

	public static void inOrderRecursive(final Node<?> node) {
		if (node == null) {
			return;
		}
		inOrderRecursive(node.left);
		visit(node);
		inOrderRecursive(node.right);
	}

	public static void inOrderIterative(final Node<?> node) {
		if (node == null) {
			return;
		}

		final Deque<Node<?>> stack = new ArrayDeque<>();
		Node<?> current = node;
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

	public static void preOrderRecursive(final Node<?> node) {
		if (node == null) {
			return;
		}
		visit(node);
		preOrderRecursive(node.left);
		preOrderRecursive(node.right);
	}

	public static void preOrderIterative(final Node<?> node) {
		if (node == null) {
			return;
		}
		
		final Deque<Node<?>> stack = new ArrayDeque<>();
		Node<?> current = node;
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
	
	public static void postOrderRecursive(final Node<?> node) {
		if (node == null) {
			return;
		}
		
		postOrderRecursive(node.left);
		postOrderRecursive(node.right);
		visit(node);
	}
	
	public static void postOrderIterative(final Node<?> node) {
	    if (node == null) {
	        return;
	    }
	    
	    final Deque<Node<?>> stack = new ArrayDeque<>();
	    Node<?> current = node;
	    Node<?> lastVisited = null;
	    while (!stack.isEmpty() || current != null) {
	        if (current != null) {
	            stack.push(current);
	            current = current.left;
	        } else {
	            Node<?> peek = stack.peek();
	            if (peek.right != null && peek.right != lastVisited) {
	                current = peek.right;
	            } else {
	                visit(peek);
	                lastVisited = stack.pop();
	            }
	        }
	    }
	}

	public static int getHeight(final Node<?> node) {

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

	public static void bfs(final Node<?> node) {
		final Queue<Node<?>> pending = new LinkedList<>();
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
	
	public static <T> List<T> flattenTree(final Node<T> node) {
		if (node == null) {
			return Collections.emptyList();
		}
		
		final List<T> result = new LinkedList<>();
		final Deque<Node<T>> pending = new LinkedList<>();
		
		Node<T> current = node;
		while (current != null || !pending.isEmpty()) {
			if (current == null) {
				current = pending.pop();
			} else {
				if (current.right != null) {
					pending.push(current.right);
				}
				result.add(current.data);
				current = current.left;
			}
		}
		
		return result;
	}
	
	public static boolean hasLeafPathSum(final Node<Integer> node, final int sum) {
		if (node == null) {
			return false;
		}
		
		final Queue<Node<Integer>> nodes = new LinkedList<>();
		final Queue<Integer> sums = new LinkedList<>();
		
		nodes.add(node);
		sums.add(node.data);
		
		while (!nodes.isEmpty()) {
			final Node<Integer> current = nodes.poll();
			final int total = sums.poll();
			if (current.left == null && current.right == null) {
				if (total == sum) {
					return true;
				}
			}
			if (current.left != null) {
				nodes.add(current.left);
				sums.add(total + current.left.data);
			}
			if (current.right != null) {
				nodes.add(current.right);
				sums.add(total + current.right.data);
			}
		}
		return false;
	}
	
	public static List<List<Integer>> allLeafPathSum(final Node<Integer> node, final int sum) {
		if (node == null) {
			return Collections.emptyList();
		}
		
		final List<List<Integer>> result = new LinkedList<>();
		allLeafPathSumHelper(node, sum, result, Collections.emptyList());
		return result;
	}

	private static void allLeafPathSumHelper(final Node<Integer> node, final int sum, final List<List<Integer>> result, final List<Integer> list) {
		if (node == null) {
			return;
		}
		
		final List<Integer> path = new ArrayList<>(list);
		path.add(node.data);
		
		if (node.left == null && node.right == null) {
			if (sum - node.data == 0) {
				result.add(path);
				return;
			}
		}
		allLeafPathSumHelper(node.left, sum - node.data, result, path);
		allLeafPathSumHelper(node.right, sum - node.data, result, path);
	}
	
	public static <T> Node<T> buildTree(final List<T> inOrder, final List<T> postOrder) {
		int inStart = 0;
		int inEnd = inOrder.size() - 1;
		int postStart = 0;
		int postEnd = postOrder.size() - 1;
		
		return buildTree(inOrder, inStart, inEnd, postOrder, postStart, postEnd);
	}
	
	private static <T> Node<T> buildTree(final List<T> inOrder, final int inStart, final int inEnd,
			final List<T> postOrder, final int postStart, final int postEnd) {

		if (inStart == inEnd) {
			return new Node<>(inOrder.get(inEnd));
		}
		
		final T rootData = postOrder.get(postEnd);
		int leftLength = 0;
		for (int i = inStart; i <= inEnd; i++) {
			if (rootData.equals(inOrder.get(i))) {
				break;
			}
			leftLength++;
		}
		
		final Node<T> root = new Node<>(rootData);
		
		final int leftInOrderEnd = inStart+leftLength-1;
		final int leftPostOrderEnd = postStart + leftLength-1;
		if (inStart <= leftInOrderEnd) {
			root.left = buildTree(inOrder, inStart, leftInOrderEnd, postOrder, postStart, leftPostOrderEnd);
		}
		
		final int rightInOrderStart = inStart+leftLength+1;
		final int rightPostOrderStart = postStart+leftLength;
		if (rightInOrderStart <= inEnd) {
			root.right = buildTree(inOrder, rightInOrderStart, inEnd, postOrder, rightPostOrderStart, postEnd-1);
		}
		
		return root;
	}
}
