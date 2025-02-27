package Graph;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GraphTesting {
    private Graph<Integer, String> graph;

    @Before
    public void setUp() {
        this.graph = new Graph<Integer, String>(false, true);
    }

    @Test
    public void testAddNode() {
        assertTrue(graph.addNode(1));
        assertTrue(graph.addNode(2));
        assertFalse(graph.addNode(1)); // Node already exists
    }

    @Test
    public void testAddEdge() {
        graph.addNode(1);
        graph.addNode(2);
        assertTrue(graph.addEdge(1, 2, "Edge 1-2"));
        assertFalse(graph.addEdge(1, 2, "Edge 1-2")); // Edge already exists
        assertFalse(graph.addEdge(1, 3, "Edge 1-3")); // Node 3 does not exist
    }

    @Test
    public void testContainsNode() {
        graph.addNode(1);
        assertTrue(graph.containsNode(1));
        assertFalse(graph.containsNode(2));
    }

    @Test
    public void testContainsEdge() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, "Edge 1-2");
        assertTrue(graph.containsEdge(1, 2));
        assertFalse(graph.containsEdge(1, 3));
    }

    @Test
    public void testRemoveNode() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, "Edge 1-2");
        assertTrue(graph.removeNode(1));
        assertFalse(graph.containsNode(1));
        assertFalse(graph.containsEdge(1, 2));
    }

    @Test
    public void testRemoveEdge() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, "Edge 1-2");
        assertTrue(graph.removeEdge(1, 2));
        assertFalse(graph.containsEdge(1, 2));
    }

    @Test
    public void testNumNodes() {
        graph.addNode(1);
        graph.addNode(2);
        assertEquals(2, graph.numNodes());
    }

    @Test
    public void testNumEdges() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, "Edge 1-2");
        assertEquals(2, graph.numEdges());
    }

    @Test
    public void testGetNodes() {
        graph.addNode(1);
        graph.addNode(2);
        Collection<Integer> nodes = graph.getNodes();
        assertEquals(2, nodes.size());
        assertTrue(nodes.contains(1));
        assertTrue(nodes.contains(2));
    }

    @Test
    public void testGetEdges() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, "Edge 1-2");
        Collection<? extends AbstractEdge<Integer, String>> edges = graph.getEdges();
        assertEquals(2, edges.size());
        AbstractEdge<Integer, String> edge = edges.iterator().next();
        assertTrue(edge.getStart().equals(1));
        assertTrue(edge.getEnd().equals(2));
        assertEquals("Edge 1-2", edge.getLabel());
    }

    @Test
    public void testGetNeighbours() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, "Edge 1-2");
        Collection<Integer> neighbors = graph.getNeighbours(1);
        assertEquals(1, neighbors.size());
        assertTrue(neighbors.contains(2));
    }

    @Test
    public void testGetLabel() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, "Edge 1-2");
        assertEquals("Edge 1-2", graph.getLabel(1, 2));
        assertNull(graph.getLabel(1, 3));
    }
}