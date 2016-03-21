package russell.tests.algorithms.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Graph {

	public static final Logger log = LoggerFactory.getLogger(Graph.class);

	public Node root;

	Graph(final Node root) {
		this.root = root;
	}

	/**
	 * Inner Node class
	 */
	static class Node {
		Integer value;
		List<Node> adjacents;

		Node(final int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return String.format("{value:%d}", value);
		}

		@Override
		public int hashCode() {
			int result = 1;
			final int prime = 31;
			result = result*prime + value.hashCode();
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
			if (value.equals(other.value))
				return false;
			return true;
		}
	}

	/**
	 * Inner BFS Result class
	 */
	static class BFSResult {
		final Node originNode;
		final Node destNode;
		final Map<Node, Node> path;

		BFSResult(final Node originNode, final Node destNode, final Map<Node, Node> path) {
			this.originNode = originNode;
			this.destNode = destNode;
			this.path = path;
		}
	}

	/** creates a new default graph */
	public static Graph newGraph() {
		final Node n0 = new Node(0);
		final Node n1 = new Node(1);
		final Node n2 = new Node(2);
		final Node n3 = new Node(3);
		final Node n4 = new Node(4);
		final Node n5 = new Node(5);

		n0.adjacents = Arrays.asList(n1, n4, n5);
		n1.adjacents = Arrays.asList(n0, n4, n3);
		n2.adjacents = Arrays.asList(n1);
		n3.adjacents = Arrays.asList(n2, n4);
		n4.adjacents = Collections.emptyList();
		n5.adjacents = Collections.emptyList();

		final Graph g = new Graph(n0);
		return g;
	}
	
	public static boolean areConnected(final Graph graph, final int a, final int b) {
        final BFSResult fromGraphToA = findNodeBFS(graph.root, a);
        
        if (fromGraphToA.destNode != null) {
            final BFSResult fromAToB = findNodeBFS(fromGraphToA.destNode, b);
            if (fromAToB.destNode != null) {
                
                printPath(fromAToB);
                
                return true;
            }
        }
        
        // No path from root to A, search B -> A
        final BFSResult fromGraphToB = findNodeBFS(graph.root, b);
        if (fromGraphToB.destNode != null) {
            final BFSResult fromBtoA = findNodeBFS(fromGraphToB.destNode, a);
            if (fromBtoA.destNode != null) {
                return true;
            }
        }
        
        return false;
    }
    
    private static void printPath(final BFSResult bfsResult) {
        final LinkedList<Node> path = new LinkedList<>();
        
        Node node = null;
        for (node = bfsResult.destNode; node != bfsResult.originNode; node = bfsResult.path.get(node)) {
            path.addFirst(node);
        }
        path.addFirst(node);
        
        System.out.println(String.format("Path from %s to %s: %s", bfsResult.originNode.value, bfsResult.destNode.value, path));
    }

    public static BFSResult findNodeBFS(final Node originNode, final int destValue) {
        final Queue<Node> pending = new LinkedList<>();
        pending.add(originNode);
        
        final Set<Node> visited = new HashSet<>();
        visited.add(originNode);
        
        final Map<Node, Node> previous = new HashMap<Node, Node>();
        
        while (!pending.isEmpty()) {
            final Node node = pending.poll();

            log.debug("visiting: " + node);
            if (node.value == destValue) {
                log.debug("found it: " + destValue);
                return new BFSResult(originNode, node, previous);
            }

            if (node.adjacents != null) {
                for (final Node adjacent : node.adjacents) {
                    if (!visited.contains(adjacent)) {
                        pending.add(adjacent);
                        visited.add(adjacent);
                        previous.put(adjacent, node);
                    }
                }
            }
            
        }
        return new BFSResult(originNode, null, null);
    }
    
    public static Node findNodeDFS(final Node originNode, final int value, final Set<Node> visited) {
        
        if (originNode == null || visited.contains(originNode)) {
            return null;
        }
        
        log.debug("visiting: " + originNode);
        visited.add(originNode);
        if (originNode.value == value) {
            log.debug("found it: " + value);
            return originNode;
        }
        
        if (originNode.adjacents != null) {
            for (final Node node : originNode.adjacents) {
                final Node foundNode = findNodeDFS(node, value, visited);
                if (foundNode != null) {
                    return foundNode;
                }
            }
        }
        
        return null;
    }
    
    public static Graph cloneGraph(final Node node) {
    	final Map<Node, Node> visited = new HashMap<>();
    	final Queue<Node> pending = new LinkedList<>();
    	
    	Node root = new Node(node.value);
    	
    	visited.put(node, root);
    	pending.add(node);
    	Node current, clone;
    	
    	while (!pending.isEmpty()) {
    		current = pending.poll();
    		clone = visited.get(current);
    		clone.adjacents = new ArrayList<>();
    		
    		for (Node adjacent : current.adjacents) {
    			Node adjacentClone = visited.get(adjacent);
    			if (adjacentClone == null) {
    				adjacentClone = new Node(adjacent.value);
    			}
    			clone.adjacents.add(adjacentClone);
    			
    			if (!visited.containsKey(adjacent)) {
    				pending.add(adjacent);
    				visited.put(adjacent, adjacentClone);
    			}
    		}
    	}
    	
    	return new Graph(root);
    }
}