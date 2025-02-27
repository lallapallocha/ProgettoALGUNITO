package Main;

import Graph.*;
import PriorityQueue.*;
import java.util.Comparator;

public class MyComparator<V, L extends Comparable<L>> implements Comparator<AbstractEdge<V, L>> {

    @Override
    public int compare(AbstractEdge<V, L> a, AbstractEdge<V, L> b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        L labelA = a.getLabel();
        L labelB = b.getLabel();

        if (labelA == null || labelB == null) {
            throw new IllegalArgumentException("Edge labels cannot be null");
        }

        return labelA.compareTo(labelB);
    }
}