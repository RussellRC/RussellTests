package org.russell.dsa.graphs;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class DirectedGraph<T> {

    private final Map<T, Set<T>> vertices;
    
    public DirectedGraph() {
        vertices = new LinkedHashMap<>();
    }
    
    public void addEdge(final T origin, final T dest) {
        Set<T> neighbours = vertices.get(origin);
        if (neighbours == null) {
            neighbours = new LinkedHashSet<>();
            vertices.put(origin, neighbours);
        }
        neighbours.add(dest);
    }
    
    
    public boolean hasCycleDFS() {
        final Set<T> visited = new LinkedHashSet<>();
        final Set<T> recStack = new LinkedHashSet<>();
        
        for (final T node : vertices.keySet()) {
            if (!visited.contains(node)) {
                if (hasCycleDFS(node, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleDFS(final T node, final Set<T> visited, final Set<T> recStack) {
        visited.add(node);
        recStack.add(node);
        
        final Set<T> neighbours = vertices.get(node);
        if (neighbours != null) {
            for (final T neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    if (hasCycleDFS(neighbour, visited, recStack)) {
                        return true;
                    }
                } else if (recStack.contains(neighbour)) {
                    return true;
                }
            }
        }
        
        recStack.remove(node);
        return false;
    }
    
    public static void main(String[] args) {
        final DirectedGraph<Integer> g = new DirectedGraph<>();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        // make a cycle
        g.addEdge(2, 0);
//        g.addEdge(3, 3);
        
        System.out.println(g.hasCycleDFS());
    }
}
