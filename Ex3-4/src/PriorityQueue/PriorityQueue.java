package PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * PriorityQueue - Represents a priority queue data structure.
 * @param <E> The type of the elements in the priority queue.
 *
 * This class implements the AbstractQueue interface and represents a priority queue
 * with elements that can be compared using a comparator.
 */
public class PriorityQueue<E> implements AbstractQueue<E> {
    private HashMap<E, Integer> hashedHeap;
    private ArrayList<E> heap;
    private Comparator<E> comparator;

    /**
     * PriorityQueue - Constructs a priority queue with the specified comparator.
     * @param comparator The comparator used to compare the elements.
     *
     * This constructor initializes the priority queue with the given comparator
     * and creates an empty heap.
     */
    public PriorityQueue(Comparator<E> comparator) {
        this.hashedHeap = new HashMap<>();
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    /**
     * empty - Checks if the priority queue is empty.
     * @return True if the priority queue is empty, false otherwise.
     */
    public boolean empty() {
        return getSize() == 0;
    }

    /**
     * top - Returns the element at the top of the priority queue.
     * @return The element at the top of the priority queue.
     */
    public E top() {
        if (!this.empty())
            return heap.get(0);
        else
            return null;
    }

    /**
     * push - Inserts an element into the priority queue.
     * @param e The element to be inserted.
     * @return True if the element was inserted, false if it already exists.
     *
     * This method inserts an element into the priority queue if it does not already exist.
     */
    public boolean push(E e) {
        if (!hashedHeap.containsKey(e)) {
            heap.add(e);

            int i = lastIndex();
            int indexParent = getParentIndex(i);

            while (i > 0 && this.comparator.compare(heap.get(indexParent), heap.get(i)) > 0) {
                swap(i, indexParent);
                i = indexParent;
                indexParent = getParentIndex(i);
            }
            hashedHeap.put(e, i);

            return true;
        }
        return false;
    }

    /**
     * contains - Checks if the priority queue contains an element.
     * @param e The element to be checked.
     * @return True if the priority queue contains the element, false otherwise.
     */
    public boolean contains(E e) {
        return hashedHeap.containsKey(e);
    }

    /**
     * pop - Removes the element at the top of the priority queue.
     *
     * This method removes the element at the top of the priority queue.
     */
    public void pop() {
        if (!this.empty()) {
            int last = lastIndex();
            swap(0, last);
            E removed = heap.remove(last);
            hashedHeap.remove(removed);

            if (!this.empty()) {
                hashedHeap.put(heap.get(0), 0);
                heapify(0);
            }
        }
    }

    /**
     * remove - Removes an element from the priority queue.
     * @param e The element to be removed.
     * @return True if the element was removed, false if it does not exist.
     *
     * This method removes an element from the priority queue if it exists.
     */
    public boolean remove(E e) {
        if (contains(e)) {
            int index = hashedHeap.get(e);
            int last = lastIndex();

            swap(index, last);
            E removed = heap.remove(last);
            hashedHeap.remove(e);

            if (index != last) {
                hashedHeap.put(heap.get(index), index);
                heapify(index);
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * update - Updates the position of an element in the priority queue.
     * @param e The element to be updated.
     *
     * This method updates the position of an element in the priority queue.
     */
    private void heapify(int index) {
        int smallest = index;
        int leftChild = getLeftChildIndex(index);
        int rightChild = getRightChildIndex(index);
        int heapSize = heap.size();

        if (leftChild < heapSize && this.comparator.compare(heap.get(leftChild), heap.get(smallest)) < 0) {
            smallest = leftChild;
        }

        if (rightChild < heapSize && this.comparator.compare(heap.get(rightChild), heap.get(smallest)) < 0) {
            smallest = rightChild;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    /**
     * swap - Swaps two elements in the priority queue.
     * @param i The index of the first element.
     * @param j The index of the second element.
     *
     * This method swaps two elements in the priority queue.
     */
    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        
        hashedHeap.put(heap.get(i), i);
        hashedHeap.put(heap.get(j), j);
    }

    /**
     * getSize - Returns the number of elements in the priority queue.
     * @return The number of elements in the priority queue.
     */
    public int getSize() {
        return heap.size();
    }

    /**
     * lastIndex - Returns the index of the last element in the priority queue.
     * @return The index of the last element in the priority queue.
     */
    private int lastIndex() {
        return getSize() - 1;
    }

    /**
     * getParentIndex - Returns the index of the parent of an element.
     * @param index The index of the element.
     * @return The index of the parent of the element.
     */
    private int getParentIndex(int index) {
        if (index == 0) {
            return -1;
        }
        return (index - 1) / 2;
    }

    /**
     * getLeftChildIndex - Returns the index of the left child of an element.
     * @param index The index of the element.
     * @return The index of the left child of the element.
     */
    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    /**
     * getRightChildIndex - Returns the index of the right child of an element.
     * @param index The index of the element.
     * @return The index of the right child of the element.
     */
    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    /**
     * toString - Returns a string representation of the priority queue.
     * @return A string representation of the priority queue.
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (E e : heap)
            ret.append(e.toString()).append(", ");
        return ret.toString();
    }
}
