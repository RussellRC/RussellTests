package russell.tests.algorithms;

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

class Node {
    int value;
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
        return value;
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
        if (value != other.value)
            return false;
        return true;
    }
    
    
}

class Graph {
    Node root;
    
    Graph (final Node root) {
        this.root = root;
    }
}

class BFSResult {
    final Node originNode;
    final Node destNode;
    final Map<Node, Node> path;
    
    BFSResult(final Node originNode, final Node destNode, final Map<Node, Node> path) {
        this.originNode = originNode;
        this.destNode = destNode;
        this.path = path;
    }
}

public class GraphTest {
    static {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
    }
    public static final Logger log = LoggerFactory.getLogger(GraphTest.class);
    
    public static void main(String[] args) {
        final Graph graph = newGraph();
        
        System.out.println(areConnected(graph, 3, 5));
        //System.out.println(areConnected(graph, 5, 3));
        //System.out.println(areConnected(graph, 4, 5));
    }
    
    static Graph newGraph() {
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
    
    private static boolean areConnected(final Graph graph, final int a, final int b) {
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
            if (node == null) {
                // Sanity check
                continue;
            }

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
    
}
