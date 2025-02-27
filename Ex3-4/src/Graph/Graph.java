package Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Graph - Represents a graph data structure.
 * @param <V> The type of the vertices.
 * @param <L> The type of the labels, which must be comparable.
 *
 * This class implements the AbstractGraph interface and represents a graph
 * with vertices and edges. The graph can be directed or undirected, and
 * labeled or unlabeled.
 */
public class Graph<V, L extends Comparable<L>> implements AbstractGraph<V, L> {
    private boolean directed;
    private boolean labeled;
    private HashMap<V, HashSet<Edge<V, L>>> nodes;

    /**
     * Graph - Constructs a graph with the specified properties.
     * @param directed Indicates if the graph is directed.
     * @param labelled Indicates if the graph is labeled.
     *
     * This constructor initializes the graph with the given properties and
     * creates an empty set of nodes.
     */
    public Graph(boolean directed, boolean labelled) {
        this.directed = directed;
        this.labeled = labelled;
        this.nodes = new HashMap<>();
    }

     /**
     * isDirected - Checks if the graph is directed.
     * @return True if the graph is directed, false otherwise.
     */
    public boolean isDirected() {
        return this.directed;
    }

    /**
     * isLabelled - Checks if the graph is labeled.
     * @return True if the graph is labeled, false otherwise.
     */
    public boolean isLabelled() {
        return this.labeled;
    }

    /**
     * addNode - Adds a node to the graph.
     * @param a The node to be added.
     * @return True if the node was added, false if it already exists.
     *
     * This method adds a node to the graph if it does not already exist.
     */
    public boolean addNode(V a) {
        if (!nodes.containsKey(a)) {
            nodes.put(a, new HashSet<>());
            return true;
        } else
            return false;
    }

    /**
 * addEdge - Adds an edge to the graph.
 * @param a The start node of the edge.
 * @param b The end node of the edge.
 * @param l The label of the edge.
 * @return True if the edge was added, false if it already exists or if the nodes do not exist.
 *
 * This method adds an edge to the graph if both nodes exist and the edge does not already exist.
 * If the graph is undirected, it adds the reverse edge as well.
 */
    public boolean addEdge(V a, V b, L l) {
        if (nodes.containsKey(a) && nodes.containsKey(b) && !this.containsEdge(a, b)) {
            nodes.get(a).add(new Edge<V, L>(a, b, l));
            if (!this.isDirected())
                this.addEdge(b, a, l);
            return true;
        } else
            return false;
    }

    /**
 * containsNode - Checks if the graph contains a node.
 * @param a The node to check.
 * @return True if the node exists, false otherwise.
 *
 * This method checks if the graph contains the specified node.
 */
    public boolean containsNode(V a) {
        return this.nodes.containsKey(a);
    }

    /**
 * containsEdge - Checks if the graph contains an edge between two nodes.
 * @param a The start node of the edge.
 * @param b The end node of the edge.
 * @return True if the edge exists, false otherwise.
 *
 * This method checks if the graph contains an edge between the specified nodes.
 */
    public boolean containsEdge(V a, V b) {
        if (nodes.containsKey(a) && nodes.containsKey(b)) {
            for (Edge<V, L> e : nodes.get(a)) {
                if (e.getStart().equals(a) && e.getEnd().equals(b))
                    return true;
            }
        }
        return false;
    }

    /**
 * containsEdge - Checks if the graph contains an edge between two nodes.
 * @param a The start node of the edge.
 * @param b The end node of the edge.
 * @return True if the edge exists, false otherwise.
 *
 * This method checks if the graph contains an edge between the specified nodes.
 */
    public boolean removeNode(V a) {
        if (this.nodes.containsKey(a)) {
            for (V node : this.nodes.keySet()) {
                Iterator<Edge<V, L>> iterator = this.nodes.get(node).iterator();
                while (iterator.hasNext()) {
                    Edge<V, L> e = iterator.next();
                    if (e.getEnd().equals(a))
                        iterator.remove();
                }

            }
            this.nodes.remove(a);
            return true;
        }
        return false;
    }

    /**
     * removeEdge - Removes an edge from the graph.
     * @param a The start node of the edge.
     * @param b The end node of the edge.
     * @return True if the edge was removed, false if it does not exist.
     *
     * This method removes an edge from the graph if it exists. If the graph is undirected,
     * it also removes the reverse edge.
     */
    public boolean removeEdge(V a, V b) {
        Iterator<Edge<V, L>> iterator;
        boolean removed = false;
        Edge<V, L> tmpEdge;
        if (this.containsNode(a) && this.containsNode(b)) {
            iterator = this.nodes.get(a).iterator();
            while (iterator.hasNext()) {
                tmpEdge = iterator.next();
                if (tmpEdge.getEnd().equals(b)) {
                    iterator.remove();
                    removed = true;
                }
            }
            if (!this.directed) {
                iterator = this.nodes.get(b).iterator();
                while (iterator.hasNext()) {
                    tmpEdge = iterator.next();
                    if (tmpEdge.getEnd().equals(a))
                        iterator.remove();
                }
            }

        }
        return removed;
    }

    public int numNodes() {
        return this.nodes.size();
    }

    public int numEdges() {
        int sum = 0;
        for (V node : this.nodes.keySet())
            sum += this.nodes.get(node).size();

        return sum;
    }

    /**
     * getNodes - Returns a collection of all nodes in the graph.
     * @return A collection of all nodes in the graph.
     *
     * This method returns a collection of all nodes in the graph.
     */
    public Collection<V> getNodes() {
        return this.nodes.keySet();
    }

    /**
 * getLabel - Retrieves the label of the edge between two nodes.
 * @param a The start node of the edge.
 * @param b The end node of the edge.
 * @return The label of the edge if it exists, null otherwise.
 *
 * This method retrieves the label of the edge between the specified nodes if it exists.
 */
    public Collection<? extends AbstractEdge<V, L>> getEdges() {
        ArrayList<Edge<V, L>> edges = new ArrayList<>();
        for (V node : this.nodes.keySet()) {
            edges.addAll(this.nodes.get(node));
        }
        return edges;
    }

    /**
 * getNeighbours - Returns a collection of all neighbors of a node.
 * @param a The node whose neighbors are to be retrieved.
 * @return A collection of all neighbors of the specified node.
 *
 * This method returns a collection of all neighbors of the specified node.
 */
    public Collection<V> getNeighbours(V a) {
        ArrayList<V> neighbors = new ArrayList<>();
        if (this.containsNode(a)) {
            for (Edge<V, L> edge : this.nodes.get(a)) {
                neighbors.add(edge.getEnd());
            }
        }
        return neighbors;
    }
/**
 * getLabel - Retrieves the label of the edge between two nodes.
 * @param a The start node of the edge.
 * @param b The end node of the edge.
 * @return The label of the edge if it exists, null otherwise.
 *
 * This method retrieves the label of the edge between the specified nodes if it exists.
 */
    public L getLabel(V a, V b) {
        L label = null;
        if (this.containsEdge(a, b)) {
            for (Edge<V, L> edge : this.nodes.get(a)) {
                if (edge.getEnd().equals(b))
                    label = edge.getLabel();
            }
        }
        return label;
    }

/**
 * getEdges - Retrieves all edges starting from a specific node.
 * @param a The node whose edges are to be retrieved.
 * @return A list of edges starting from the specified node.
 *
 * This method retrieves all edges starting from the specified node.
 */
    public ArrayList<Edge<V, L>> getEdges(V a) {
        ArrayList<Edge<V, L>> edges = new ArrayList<>(this.nodes.get(a));
        return edges;
    }
}