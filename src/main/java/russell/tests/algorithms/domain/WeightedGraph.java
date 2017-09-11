package russell.tests.algorithms.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * @author russellrazo
 *
 * @param <T>
 */
public class WeightedGraph<T> {

    private final Set<Edge<T>> edges = new LinkedHashSet<>();
    private final Set<T> vertices = new LinkedHashSet<>();
    
    public void addEdge(final T src, final T dest, final int weight) {
        Objects.requireNonNull(src);
        Objects.requireNonNull(dest);
        final Edge<T> edge = new Edge<>(src, dest, weight);
        edges.add(edge);
        vertices.add(src);
        vertices.add(dest);
    }
    
    public void addEdge(final T src, final T dest) {
        Objects.requireNonNull(src);
        Objects.requireNonNull(dest);
        final Edge<T> edge = new Edge<>(src, dest, 0);
        edges.add(edge);
        vertices.add(src);
        vertices.add(dest);
    }
    
    /**
     * Union-find algorithm
     * @see {@link http://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/}
     * @return
     */
    public Map<T, Subset<T>> newSubsetMap() {
        final Map<T, Subset<T>> subsetsMap = new LinkedHashMap<>();
        for (final T vertex : vertices) {
            subsetsMap.put(vertex, new Subset<>(vertex, 0));
        }
        return subsetsMap;
    }
    
    /**
     * Union-find algorithm
     * @see {@link http://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/}
     * @param subsets
     * @param element
     * @return
     */
    private static <T> T find(final Map<T, Subset<T>> subsets, final T element) {
        // find root of subset and make root as parent of element (path compression)
        if (subsets.get(element).parent != element) {
            subsets.get(element).parent = find(subsets, subsets.get(element).parent);
        }
        return subsets.get(element).parent;
    }
    
    /**
     * Union by rank and path compression algorithm
     * @see {@link http://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/}
     * @param subsets
     * @param v1
     * @param v2
     */
    private static <T> void union(final Map<T, Subset<T>> subsets, final T v1, final T v2) {
        T v1Root = find(subsets, v1);
        T v2Root = find(subsets, v2);
        
        // attach smaller rank tree under root of high rank tree (union by rank)
        if (subsets.get(v1Root).rank < subsets.get(v2Root).rank) {
            subsets.get(v1Root).parent = v2Root;
        } else if (subsets.get(v1Root).rank > subsets.get(v2Root).rank) {
            subsets.get(v2Root).parent = v1Root;
        } else {
            subsets.get(v2Root).parent = v1Root;
            subsets.get(v1Root).rank++;
        }
    }
    
    
    /**
     * Ksutal Minimum Spanning Tree
     * @return
     * @see {@link http://www.geeksforgeeks.org/?p=26604}
     */
    public WeightedGraph<T> krustalMST() {
        
        final WeightedGraph<T> result = new WeightedGraph<>();
        
        // 1. sort all edges in non decreasing order
        final List<Edge<T>> edgesList = new ArrayList<>(edges);
        Collections.sort(edgesList);
        
        final Map<T, Subset<T>> subsets = newSubsetMap();
        
        // while MST graph contains V-1 edges
        int i = 0;
        while (result.edges.size() < vertices.size() - 1) {
            final Edge<T> nextEdge = edgesList.get(i++);
            
            final T x = find(subsets, nextEdge.src);
            final T y = find(subsets, nextEdge.dest);
            
            // if including this edge doesn't cause a cycle, include it
            // else discard the edge
            if (x != y) {
                result.addEdge(nextEdge.src, nextEdge.dest, nextEdge.weight);
                union(subsets, x, y);
            }
        }
        
        return result;
    }
    
    
    /**
     * 
     * @author russellrazo
     *
     * @param <T>
     */
    public static class Subset<T> {
        public Subset(final T parent, final int rank) {
            this.parent = parent;
            this.rank = rank;
        }
        public T parent;
        public int rank;
    }
    
    /**
     * 
     * @author russellrazo
     *
     * @param <T>
     */
    public static class Edge<T> implements Comparable<Edge<T>> {
        private final T src;
        private final T dest;
        private final int weight;
        
        public Edge(final T src, final T dest, final int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge<T> edge) {
            Objects.requireNonNull(edge);
            return Integer.compare(this.weight, edge.weight);
        }
        
        @Override
        public String toString() {
            return new StringBuilder().append(src.toString()).append("--").append(dest.toString()).append("==").append(weight).toString();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((dest == null) ? 0 : dest.hashCode());
            result = prime * result + ((src == null) ? 0 : src.hashCode());
            result = prime * result + weight;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            /*
             * weight doesn't matter,
             * an edge is same if src and dest are the same 
             */
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Edge<?> other = (Edge<?>) obj;
            if (dest == null) {
                if (other.dest != null)
                    return false;
            } else if (!dest.equals(other.dest))
                return false;
            if (src == null) {
                if (other.src != null)
                    return false;
            } else if (!src.equals(other.src))
                return false;
            return true;
        }
    }
    
    
    
    public static void main(String[] args) {
        final WeightedGraph<Integer> g = new WeightedGraph<>();
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(7, 6, 1);
        g.addEdge(7, 8, 7);        
        g.addEdge(6, 8, 6);
        g.addEdge(6, 5, 2);
        g.addEdge(5, 4, 10);
        
        final WeightedGraph<Integer> krustalMST = g.krustalMST();
        System.out.println(krustalMST.edges.toString());
    }
    
}
