package russell.tests.algorithms.domain;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import russell.tests.algorithms.domain.Graph.Node;

@RunWith(JUnit4.class)
public class GraphTest {
	
    static {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
	}
	public static final Logger log = LoggerFactory.getLogger(GraphTest.class);

	final static Graph graph = newGraphA();
	final static Graph graphB = newGraphB();
	
	@Test
	public void testIsIterative() {
		System.out.println(Graph.dfsIterative(graph.root, 2));
	}

	@Test
	public void testClone() {
		System.out.println("\n##### Clone Graph #####");
		Graph cloneGraph = Graph.cloneGraph(graph.root);
		System.out.println(cloneGraph.root == graph.root);
	}

	@Test
	public void testAreConnected() {
		System.out.println(Graph.areConnected(graph, 3, 5));
		System.out.println(Graph.areConnected(graph, 5, 3));
		System.out.println(Graph.areConnected(graph, 4, 5));
	}
	
	@Test
	public void testTopologicalSort() {
	    Graph.topologicalSort(graphB);
	}

	
    /**
     * Creates a new Cyclical graph:
     * 0 -> 1, 4, 5
     * 1 -> 0, 4, 3
     * 2 -> 1
     * 3 -> 2, 4
     */
    public static Graph newGraphA() {
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
    
    /**
     * Creates a new Acyclical graph
     * 5 -> 0,2
     * 4 -> 0,1
     * 2 -> 3
     * 3 -> 1
     */
    public static Graph newGraphB() {
        final Node n0 = new Node(0);
        final Node n1 = new Node(1);
        final Node n2 = new Node(2);
        final Node n3 = new Node(3);
        final Node n4 = new Node(4);
        final Node n5 = new Node(5);
        
        n5.adjacents = Arrays.asList(n0, n2);
        n4.adjacents = Arrays.asList(n0, n1);
        n2.adjacents = Arrays.asList(n3);
        n3.adjacents = Arrays.asList(n1);
        n0.adjacents = Collections.emptyList();
        n1.adjacents = Collections.emptyList();

        final Graph g = new Graph(Arrays.asList(n5, n4));
        return g;
    }
}
