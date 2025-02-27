package Graph;

public class Edge<V, L extends Comparable<L>> implements AbstractEdge<V, L> {
    private V start;
    private V end;
    private L label;

     /**
     * Edge - Constructs an edge with the specified start vertex, end vertex, and label.
     * @param start The start vertex of the edge.
     * @param end The end vertex of the edge.
     * @param label The label of the edge.
     *
     * This constructor initializes the edge with the given start vertex, end vertex, and label.
     */
    public Edge(V start, V end, L label) {
        this.start = start;
        this.end = end;
        this.label = label;
    }

    /**
     * getStart - Returns the start vertex of the edge.
     * @return The start vertex of the edge.
     */
    public V getStart() {
        return this.start;
    }

    /**
     * getEnd - Returns the end vertex of the edge.
     * @return The end vertex of the edge.
     */
    public V getEnd() {
        return this.end;
    }

    /**
     * getLabel - Returns the label of the edge.
     * @return The label of the edge.
     */
    public L getLabel() {
        return this.label;
    }
}
