package russell.tests.algorithms.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import russell.tests.algorithms.domain.Graph;





public class GraphTest {
    static {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
    }
    public static final Logger log = LoggerFactory.getLogger(GraphTest.class);
    
    public static void main(String[] args) {
        final Graph graph = Graph.newGraph();
        
        System.out.println(Graph.areConnected(graph, 3, 5));
        //System.out.println(areConnected(graph, 5, 3));
        //System.out.println(areConnected(graph, 4, 5));
    }
    
   
    
    
    
}
