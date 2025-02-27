package PriorityQueue;

import java.util.Comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTesting {

    class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
        }
    }

    private PriorityQueue<Integer> priorityQueue;

    @Before
    public void createHeap() {
        this.priorityQueue = new PriorityQueue<Integer>(new IntegerComparator());
    }

    public void fillHeap() {
        this.priorityQueue.push(3);
        this.priorityQueue.push(8);
        this.priorityQueue.push(1);
        this.priorityQueue.push(5);
        printHeap();
    }

    public void printHeap() {
        System.out.println(this);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(this.priorityQueue.empty());
    }

    @Test
    public void testIsNotEmpty() {
        fillHeap();
        assertFalse(this.priorityQueue.empty());
    }

    @Test
    public void testInsert() {
        this.priorityQueue.push(1);
        assertTrue(this.priorityQueue.getSize() == 1);
        System.out.println(this);
    }

    @Test
    public void testHeapSize() {
        this.fillHeap();
        assertTrue(this.priorityQueue.getSize() == 4);
    }

    @Test
    public void getTop() {
        this.fillHeap();
        assertTrue(this.priorityQueue.top().equals(1));
    }

}
