package russell.tests.algorithms.unsorted;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MyIterator<T> implements Iterator<T> {

    private Collection<Collection<T>> collection;
    private Iterator<Collection<T>> colIterator;
    private Iterator<T> elementIterator;

    public MyIterator(final Collection<Collection<T>> collection) {
        Objects.requireNonNull(collection);
        this.collection = collection;
        colIterator = collection.iterator();
    }

    @Override
    public boolean hasNext() {
        if (elementIterator != null && elementIterator.hasNext()) {
            return true;
        }
        
        Collection<T> nextCol = null;
        while (colIterator.hasNext() && (nextCol = colIterator.next()) != null) {
            elementIterator = nextCol.iterator();
            if (elementIterator.hasNext()) {
                return true;
            } else {
                return hasNext();
            }
        }
        return false;
    }

    @Override
    public T next() {
        final T next = elementIterator.next();
        hasNext();
        return next;
    }

    public static void main(String[] args) {
        
        final List<Collection<String>> parent = new ArrayList<>();
        final Set<String> c1 = new LinkedHashSet<>();
        final Collection<String> c2 = new ArrayList<>();
        final List<String> c3 = new ArrayList<>();
        final ArrayList<String> c4 = new ArrayList<>();
        
        parent.add(c1);
        parent.add(c2);
        parent.add(null);
        parent.add(c3);
        parent.add(c4);
        
        parent.add(null);
        c1.add("s1_1");
        c1.add("s1_2");
        c1.add("s1_3");
        c3.add("s3_1");
        c3.add(null);
        c3.add(null);
        c3.add("s3_2");
        c4.add("s4_1");
        c4.add(null);
        
        final MyIterator<String> myIt = new MyIterator<>(parent);
        while(myIt.hasNext()) {
            System.out.println(myIt.next());
        }
    }

}
