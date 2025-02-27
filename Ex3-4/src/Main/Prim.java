package Main;

import Graph.*;
import PriorityQueue.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Prim - Implements the Prim's algorithm for finding the minimum spanning tree
 * of a graph.
 * 
 * @param <V> The type of the vertices.
 * @param <L> The type of the labels, which must be comparable.
 *
 *            This class implements the Prim's algorithm for finding the minimum
 *            spanning
 *            tree of a graph. The algorithm starts from a node and adds the
 *            minimum edge
 *            to the tree until all nodes are visited.
 */
public class Prim {

    /**
     * minimumSpanningForest - Finds the minimum spanning tree of a graph.
     * 
     * @param graph The graph to find the minimum spanning tree of.
     * @return A collection of edges representing the minimum spanning tree.
     *
     *         This method finds the minimum spanning tree of a graph using the
     *         Prim's
     *         algorithm. The algorithm starts from a node and adds the minimum edge
     *         to
     *         the tree until all nodes are visited.
     */
    public static <V, L extends Number & Comparable<L>> Collection<? extends AbstractEdge<V, L>> minimumSpanningForest(
            Graph<V, L> graph) {
        Set<V> visitedNodes = new HashSet<>();
        List<AbstractEdge<V, L>> resultEdges = new ArrayList<>();

        PriorityQueue<AbstractEdge<V, L>> priorityQueue = new PriorityQueue<AbstractEdge<V, L>>(new MyComparator<>());

        for (V currentNode : graph.getNodes()) {
            if (!visitedNodes.contains(currentNode)) {
                visitedNodes.add(currentNode);

                for (Edge<V, L> edge : graph.getEdges(currentNode)) {
                    priorityQueue.push(edge);
                }

                while (!priorityQueue.empty()) {
                    AbstractEdge<V, L> minEdge = priorityQueue.top();
                    priorityQueue.pop();

                    V endNode = minEdge.getEnd();

                    if (!visitedNodes.contains(endNode)) {
                        visitedNodes.add(endNode);
                        resultEdges.add(minEdge);

                        for (Edge<V, L> adjacentEdge : graph.getEdges(endNode)) {
                            priorityQueue.push(adjacentEdge);
                        }
                    }
                }
            }
        }

        return resultEdges;
    }

    public static void main(String[] args) {
        Graph<String, Double> graph = new Graph<String, Double>(false, true);

        if (args.length != 1) {
            System.out.println("Usage: java Main.Prim <path>");
            return;
        }

        String path = args[0];
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("File not found: " + path);
            return;
        }

        String stop = ",";
        String line = "";

        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader(file));
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(stop);
                graph.addNode(fields[0]);
                graph.addNode(fields[1]);
                graph.addEdge(fields[0], fields[1], Double.valueOf(fields[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        long start = System.currentTimeMillis();
        Collection<? extends AbstractEdge<String, Double>> mst = Prim.minimumSpanningForest(graph);
        double duration = (double) (System.currentTimeMillis() - start);

        double total = 0;
        for (AbstractEdge<String, Double> e : mst) {
            total += e.getLabel();
        }
        total = total / 1000;

        System.out.println("Nodes number: " + graph.numNodes() + " Edge number: " + mst.size());
        System.out.println("Total distance: " + total + " km");
        System.out.println("Prim's Algorithm execution time: " + duration + " ms");

    }
}
